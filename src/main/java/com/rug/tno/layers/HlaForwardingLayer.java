package com.rug.tno.layers;

import com.google.protobuf.ByteString;
import com.rug.tno.fpdata.FpMessage;
import com.rug.tno.fpdata.HlaCallRequest;
import com.rug.tno.fpdata.HlaCallResponse;
import com.rug.tno.url.OneTimeUseUrl;
import hla.rti1516_2024.fedpro.*;
import hla.rti1516_2024.fedpro.AttributeHandle;
import hla.rti1516_2024.fedpro.InteractionClassHandle;
import hla.rti1516_2024.fedpro.ObjectClassHandle;
import hla.rti1516_2024.fedpro.ParameterHandle;
import hla.rti1516_202X.*;
import hla.rti1516_202X.AdditionalSettingsResultCode;
import hla.rti1516_202X.CallbackModel;
import hla.rti1516_202X.RTIambassador;
import hla.rti1516_202X.RtiConfiguration;
import hla.rti1516_202X.encoding.EncoderFactory;
import hla.rti1516_202X.exceptions.RTIexception;
import hla.rti1516_202X.exceptions.RTIinternalError;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.function.TriConsumer;

import java.util.ArrayList;
import java.util.function.Function;

public class HlaForwardingLayer extends ChannelInboundHandlerAdapter {
    private final String serverAddress;
    private final RTIambassador rtiAmbassador;
    private final EncoderFactory encoderFactory;
    private final FederateAmbassador ambassador;

    public HlaForwardingLayer(String serverAddress) throws RTIinternalError {
        this.serverAddress = serverAddress;
        var rtiFactory = RtiFactoryFactory.getRtiFactory();
        this.rtiAmbassador = rtiFactory.getRtiAmbassador();
        this.encoderFactory = rtiFactory.getEncoderFactory();
        this.ambassador = new FederateAmbassador(); // TODO
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object object) throws Exception {
        var msg = (FpMessage)object;
        var payload = msg.payload();
        if (payload instanceof HlaCallRequest requestMessage) {
            var request = requestMessage.body();
            try {
                var response = handleCallRequest(request);
                ctx.writeAndFlush(new HlaCallResponse(msg.sequenceNumber(), response));
            } catch (RTIexception e) {
                ctx.writeAndFlush(new HlaCallResponse(
                        msg.sequenceNumber(),
                        CallResponse.newBuilder()
                                .setExceptionData(ExceptionData.newBuilder()
                                        .setExceptionName(e.name())
                                        .setDetails(e.getMessage())
                                        .build())
                                .build()
                        ));
            }
        } else {
            throw new IllegalStateException("Unknown object " + msg + " passed through pipeline. This should not be possible");
        }
    }

    private CallResponse handleCallRequest(CallRequest request) throws RTIexception {
        switch (request.getCallRequestCase()) {
            case CONNECTWITHCONFIGURATIONREQUEST -> {
                var connectRequest = request.getConnectWithConfigurationRequest();
                var settings = RtiConfiguration.createConfiguration();
                settings = settings.withRtiAddress(this.serverAddress);
                var result = this.rtiAmbassador.connect(this.ambassador, CallbackModel.HLA_IMMEDIATE, settings);
                return CallResponse.newBuilder().setConnectResponse(
                                ConnectResponse.newBuilder()
                                        .setConfigurationResult(hla.rti1516_2024.fedpro.ConfigurationResult.newBuilder()
                                                .setMessage(result.message)
                                                .setAddressUsed(result.addressUsed)
                                                .setConfigurationUsed(result.configurationUsed)
                                                .setAdditionalSettingsResultCode(convertASRC(result.additionalSettingsResultCode))
                                                .build())
                                        .build())
                        .build();
            }
            case DESTROYFEDERATIONEXECUTIONREQUEST -> {
                var destroyRequest = request.getDestroyFederationExecutionRequest();
                this.rtiAmbassador.destroyFederationExecution(destroyRequest.getFederationName());
                return CallResponse.newBuilder()
                        .setDestroyFederationExecutionResponse(DestroyFederationExecutionResponse.newBuilder())
                        .build();
            }
            case CREATEFEDERATIONEXECUTIONWITHMODULESANDTIMEREQUEST -> {
                var createRequest = request.getCreateFederationExecutionWithModulesAndTimeRequest();
                this.rtiAmbassador.createFederationExecution(
                        createRequest.getFederationName(),
                        fomModulesToUrls(createRequest.getFomModules()),
                        createRequest.getLogicalTimeImplementationName()
                );
                return CallResponse.newBuilder()
                        .setCreateFederationExecutionWithModulesAndTimeResponse(
                                CreateFederationExecutionWithModulesAndTimeResponse.newBuilder().build()
                        )
                        .build();
            }
            case JOINFEDERATIONEXECUTIONWITHMODULESREQUEST -> {
                var joinRequest = request.getJoinFederationExecutionWithModulesRequest();
                this.rtiAmbassador.joinFederationExecution(
                        joinRequest.getFederateType(),
                        joinRequest.getFederationName(),
                        fomModulesToUrls(joinRequest.getAdditionalFomModules())
                );
                return CallResponse.newBuilder()
                        .setJoinFederationExecutionWithModulesResponse(
                                JoinFederationExecutionWithModulesResponse.newBuilder().build()
                        )
                        .build();
            }
            case GETINTERACTIONCLASSHANDLEREQUEST -> {
                var getRequest = request.getGetInteractionClassHandleRequest();
                var response = this.rtiAmbassador.getInteractionClassHandle(getRequest.getInteractionClassName());
                var responseBytes = encodeClassHandle(response);
                return CallResponse.newBuilder()
                        .setGetInteractionClassHandleResponse(
                                GetInteractionClassHandleResponse.newBuilder()
                                        .setResult(
                                                InteractionClassHandle.newBuilder()
                                                        .setData(responseBytes)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
            }
            case GETPARAMETERHANDLEREQUEST -> {
                var getRequest = request.getGetParameterHandleRequest();
                var response = this.rtiAmbassador.getParameterHandle(decodeClassHandle(getRequest.getInteractionClass()), getRequest.getParameterName());
                var responseBytes = encodeParamHandle(response);
                return CallResponse.newBuilder()
                        .setGetParameterHandleResponse(
                                GetParameterHandleResponse.newBuilder()
                                        .setResult(
                                                ParameterHandle.newBuilder()
                                                        .setData(responseBytes)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
            }
            case GETOBJECTCLASSHANDLEREQUEST -> {
                var getRequest = request.getGetObjectClassHandleRequest();
                var response = this.rtiAmbassador.getObjectClassHandle(getRequest.getObjectClassName());
                var responseBytes = encodeObjHandle(response);
                return CallResponse.newBuilder()
                        .setGetObjectClassHandleResponse(
                                GetObjectClassHandleResponse.newBuilder()
                                        .setResult(
                                                ObjectClassHandle.newBuilder()
                                                        .setData(responseBytes)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
            }
            case GETATTRIBUTEHANDLEREQUEST -> {
                var getRequest = request.getGetAttributeHandleRequest();
                var response = this.rtiAmbassador.getAttributeHandle(decodeObjHandle(getRequest.getObjectClass()), getRequest.getAttributeName());
                var responseBytes = encodeAttrHandle(response);
                return CallResponse.newBuilder()
                        .setGetAttributeHandleResponse(
                                GetAttributeHandleResponse.newBuilder()
                                        .setResult(
                                                AttributeHandle.newBuilder()
                                                        .setData(responseBytes)
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
            }
            case SUBSCRIBEINTERACTIONCLASSREQUEST -> {
                var subRequest = request.getSubscribeInteractionClassRequest();
                this.rtiAmbassador.subscribeInteractionClass(decodeClassHandle(subRequest.getInteractionClass()));
                return CallResponse.newBuilder()
                        .setSubscribeInteractionClassResponse(
                                SubscribeInteractionClassResponse.newBuilder().build()
                        )
                        .build();
            }
            case PUBLISHINTERACTIONCLASSREQUEST -> {
                var pubRequest = request.getPublishInteractionClassRequest();
                this.rtiAmbassador.subscribeInteractionClass(decodeClassHandle(pubRequest.getInteractionClass()));
                return CallResponse.newBuilder()
                        .setPublishInteractionClassResponse(
                                PublishInteractionClassResponse.newBuilder().build()
                        )
                        .build();
            }
            default -> throw new IllegalStateException("Unknown call request " + request.getCallRequestCase());
        }
    }

    private ByteString encodeAttrHandle(hla.rti1516_202X.AttributeHandle handle) {
        return encodeHandle(
                handle,
                hla.rti1516_202X.AttributeHandle::encodedLength,
                hla.rti1516_202X.AttributeHandle::encode
        );
    }

    private ByteString encodeObjHandle(hla.rti1516_202X.ObjectClassHandle handle) {
        return encodeHandle(
                handle,
                hla.rti1516_202X.ObjectClassHandle::encodedLength,
                hla.rti1516_202X.ObjectClassHandle::encode
        );
    }

    private ByteString encodeParamHandle(hla.rti1516_202X.ParameterHandle handle) {
        return encodeHandle(
                handle,
                hla.rti1516_202X.ParameterHandle::encodedLength,
                hla.rti1516_202X.ParameterHandle::encode
        );
    }

    private ByteString encodeClassHandle(hla.rti1516_202X.InteractionClassHandle handle) {
        return encodeHandle(
                handle,
                hla.rti1516_202X.InteractionClassHandle::encodedLength,
                hla.rti1516_202X.InteractionClassHandle::encode
        );
    }

    private <T> ByteString encodeHandle(T obj, Function<T, Integer> encodedLength, TriConsumer<T, byte[], Integer> encode) {
        var responseBytes = new byte[encodedLength.apply(obj)];
        encode.accept(obj, responseBytes, 0);
        return ByteString.copyFrom(responseBytes);
    }

    private hla.rti1516_202X.ObjectClassHandle decodeObjHandle(ObjectClassHandle handle) throws RTIexception {
        var factory = this.rtiAmbassador.getObjectClassHandleFactory();
        return factory.decode(handle.getData().toByteArray(), 0);
    }

    private hla.rti1516_202X.ParameterHandle decodeParamHandle(ParameterHandle handle) throws RTIexception {
        var factory = this.rtiAmbassador.getParameterHandleFactory();
        return factory.decode(handle.getData().toByteArray(), 0);
    }

    private hla.rti1516_202X.InteractionClassHandle decodeClassHandle(InteractionClassHandle handle) throws RTIexception {
        var factory = this.rtiAmbassador.getInteractionClassHandleFactory();
        return factory.decode(handle.getData().toByteArray(), 0);
    }

    private static String[] fomModulesToUrls(FomModuleSet set) {
        var urls = new ArrayList<String>();
        set.getFomModuleList().forEach(mo -> {
            switch (mo.getFomModuleCase()) {
                case URL -> urls.add(mo.getUrl());
                case FILE -> urls.add(
                        OneTimeUseUrl.generateUrl(
                                mo.getFile().getContent().asReadOnlyByteBuffer(),
                                mo.getFile().getName(),
                                null
                        )
                );
                case COMPRESSEDMODULE -> urls.add(
                        OneTimeUseUrl.generateUrl(
                                mo.getCompressedModule().asReadOnlyByteBuffer(),
                                null,
                                "zip"
                        )
                );
            }
        });
        return urls.toArray(String[]::new);
    }

    private static hla.rti1516_2024.fedpro.AdditionalSettingsResultCode convertASRC(AdditionalSettingsResultCode original) {
        return switch (original) {
            case SETTINGS_IGNORED -> hla.rti1516_2024.fedpro.AdditionalSettingsResultCode.SETTINGS_IGNORED;
            case SETTINGS_FAILED_TO_PARSE -> hla.rti1516_2024.fedpro.AdditionalSettingsResultCode.SETTINGS_FAILED_TO_PARSE;
            case SETTINGS_APPLIED -> hla.rti1516_2024.fedpro.AdditionalSettingsResultCode.SETTINGS_APPLIED;
        };
    }

    private class FederateAmbassador extends NullFederateAmbassador {

    }
}
