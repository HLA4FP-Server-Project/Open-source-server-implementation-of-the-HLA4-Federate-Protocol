package com.rug.tno.layers;

import com.google.protobuf.ByteString;
import com.rug.tno.fpdata.*;
import com.rug.tno.hla_202X.AttributeHandleSetImpl;
import com.rug.tno.hla_202X.AttributeHandleValueMapImpl;
import com.rug.tno.hla_202X.ParameterHandleValueMapImpl;
import com.rug.tno.url.OneTimeUseUrl;
import hla.rti1516_2024.fedpro.*;
import hla.rti1516_2024.fedpro.AttributeHandle;
import hla.rti1516_2024.fedpro.AttributeHandleSet;
import hla.rti1516_2024.fedpro.InteractionClassHandle;
import hla.rti1516_2024.fedpro.ObjectClassHandle;
import hla.rti1516_2024.fedpro.ObjectInstanceHandle;
import hla.rti1516_2024.fedpro.ParameterHandle;
import hla.rti1516_202X.*;
import hla.rti1516_202X.AdditionalSettingsResultCode;
import hla.rti1516_202X.AttributeHandleValueMap;
import hla.rti1516_202X.CallbackModel;
import hla.rti1516_202X.FederateHandle;
import hla.rti1516_202X.MessageRetractionHandle;
import hla.rti1516_202X.OrderType;
import hla.rti1516_202X.ParameterHandleValueMap;
import hla.rti1516_202X.RTIambassador;
import hla.rti1516_202X.RegionHandleSet;
import hla.rti1516_202X.ResignAction;
import hla.rti1516_202X.RtiConfiguration;
import hla.rti1516_202X.TransportationTypeHandle;
import hla.rti1516_202X.encoding.EncoderFactory;
import hla.rti1516_202X.exceptions.FederateInternalError;
import hla.rti1516_202X.exceptions.RTIexception;
import hla.rti1516_202X.exceptions.RTIinternalError;
import hla.rti1516_202X.time.LogicalTime;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.function.TriConsumer;

import java.util.ArrayList;
import java.util.function.Function;

public class HlaForwardingLayer extends ChannelInboundHandlerAdapter {
    private Channel channel;
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
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // TODO change when sessions are implemented properly
        this.channel = ctx.channel();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object object) throws Exception {
        var msg = (FpMessage)object;
        var payload = msg.payload();
        var session = ctx.channel().attr(FpSessionLayer.CHANNEL_SESSION).get();

        if (payload instanceof HlaCallRequest requestMessage) {
            var request = requestMessage.body();
            FpPayload response;
            try {
                var protobufResponse = handleCallRequest(request);
                response = new HlaCallResponse(msg.sequenceNumber(), protobufResponse);
            } catch (RTIexception e) {
                response = new HlaCallResponse(
                        msg.sequenceNumber(),
                        CallResponse.newBuilder()
                                .setExceptionData(ExceptionData.newBuilder()
                                        .setExceptionName(e.name())
                                        .setDetails(e.getMessage())
                                        .build())
                                .build()
                        );
            }
            // We've handled the message, we can now safely mark it as received
            session.markMessageReceived(msg.sequenceNumber());
            // And now we can send the response
            ctx.writeAndFlush(response);
        } else if (payload instanceof HlaCallbackResponse response) {
            // TODO, some checks if the federate responded to out callback.
            //  Unsure what we need to do here exactly
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
            case REGISTEROBJECTINSTANCEWITHNAMEREQUEST -> {
                var regRequest = request.getRegisterObjectInstanceWithNameRequest();

                var response = this.rtiAmbassador.registerObjectInstance(
                        decodeObjHandle(regRequest.getObjectClass()),
                        regRequest.getObjectInstanceName()
                );
                var responseBytes = encodeInstanceHandle(response);
                return CallResponse.newBuilder()
                        .setRegisterObjectInstanceWithNameResponse(
                                RegisterObjectInstanceWithNameResponse.newBuilder()
                                        .setResult(
                                                ObjectInstanceHandle.newBuilder()
                                                        .setData(responseBytes)
                                                        .build())
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
            case SUBSCRIBEOBJECTCLASSATTRIBUTESREQUEST -> {
                var subRequest = request.getSubscribeObjectClassAttributesRequest();

                this.rtiAmbassador.subscribeObjectClassAttributes(
                        decodeObjHandle(subRequest.getObjectClass()),
                        decodeAttrHandleSet(subRequest.getAttributes())
                );
                return CallResponse.newBuilder()
                        .setSubscribeObjectClassAttributesResponse(
                                SubscribeObjectClassAttributesResponse.newBuilder().build()
                        )
                        .build();
            }
            case PUBLISHINTERACTIONCLASSREQUEST -> {
                var pubRequest = request.getPublishInteractionClassRequest();
                this.rtiAmbassador.publishInteractionClass(decodeClassHandle(pubRequest.getInteractionClass()));
                return CallResponse.newBuilder()
                        .setPublishInteractionClassResponse(
                                PublishInteractionClassResponse.newBuilder().build()
                        )
                        .build();
            }
            case PUBLISHOBJECTCLASSATTRIBUTESREQUEST -> {
                var pubRequest = request.getPublishObjectClassAttributesRequest();

                this.rtiAmbassador.publishObjectClassAttributes(
                        decodeObjHandle(pubRequest.getObjectClass()),
                        decodeAttrHandleSet(pubRequest.getAttributes())
                );
                return CallResponse.newBuilder()
                        .setPublishObjectClassAttributesResponse(
                                PublishObjectClassAttributesResponse.newBuilder().build()
                        )
                        .build();
            }
            case RESERVEOBJECTINSTANCENAMEREQUEST -> {
                var resRequest = request.getReserveObjectInstanceNameRequest();

                this.rtiAmbassador.reserveObjectInstanceName(
                        resRequest.getObjectInstanceName()
                );
                return CallResponse.newBuilder()
                        .setReserveObjectInstanceNameResponse(
                                ReserveObjectInstanceNameResponse.newBuilder().build()
                        )
                        .build();
            }
            case UPDATEATTRIBUTEVALUESREQUEST -> {
                var regRequest = request.getUpdateAttributeValuesRequest();

                this.rtiAmbassador.updateAttributeValues(
                        decodeInstanceHandle(regRequest.getObjectInstance()),
                        decodeAttrHandleMap(regRequest.getAttributeValues()),
                        null
                );
                return CallResponse.newBuilder()
                        .setUpdateAttributeValuesResponse(
                                UpdateAttributeValuesResponse.newBuilder().build()
                        )
                        .build();
            }
            case SENDINTERACTIONREQUEST -> {
                var sendRequest = request.getSendInteractionRequest();

                this.rtiAmbassador.sendInteraction(
                        decodeClassHandle(sendRequest.getInteractionClass()),
                        decodeParamHandleMap(sendRequest.getParameterValues()),
                        null
                );
                return CallResponse.newBuilder()
                        .setSendInteractionResponse(
                                SendInteractionResponse.newBuilder().build()
                        )
                        .build();
            }
            case RESIGNFEDERATIONEXECUTIONREQUEST -> {
                var resignRequest = request.getResignFederationExecutionRequest();

                this.rtiAmbassador.resignFederationExecution(
                        switch (resignRequest.getResignAction()) {
                            case NO_ACTION -> ResignAction.NO_ACTION;
                            case DELETE_OBJECTS -> ResignAction.DELETE_OBJECTS;
                            case DELETE_OBJECTS_THEN_DIVEST -> ResignAction.DELETE_OBJECTS_THEN_DIVEST;
                            case CANCEL_THEN_DELETE_THEN_DIVEST -> ResignAction.CANCEL_THEN_DELETE_THEN_DIVEST;
                            case UNCONDITIONALLY_DIVEST_ATTRIBUTES -> ResignAction.UNCONDITIONALLY_DIVEST_ATTRIBUTES;
                            case CANCEL_PENDING_OWNERSHIP_ACQUISITIONS -> ResignAction.CANCEL_PENDING_OWNERSHIP_ACQUISITIONS;
                            case UNRECOGNIZED -> throw new IllegalStateException("Unknown resignation request type");
                        }
                );
                return CallResponse.newBuilder()
                        .setResignFederationExecutionResponse(
                                ResignFederationExecutionResponse.newBuilder().build()
                        )
                        .build();
            }
            default -> throw new IllegalStateException("Unknown call request " + request.getCallRequestCase());
        }
    }

    private ByteString encodeInstanceHandle(hla.rti1516_202X.ObjectInstanceHandle handle) {
        return encodeHandle(
                handle,
                hla.rti1516_202X.ObjectInstanceHandle::encodedLength,
                hla.rti1516_202X.ObjectInstanceHandle::encode
        );
    }

    private hla.rti1516_2024.fedpro.AttributeHandleSet encodeAttrHandleSet(hla.rti1516_202X.AttributeHandleSet handleSet) {
        var result = hla.rti1516_2024.fedpro.AttributeHandleSet.newBuilder();
        handleSet.forEach((v) -> {
            result.addAttributeHandle(AttributeHandle.newBuilder()
                    .setData(encodeAttrHandle(v))
                    .build());
        });
        return result.build();
    }

    private hla.rti1516_2024.fedpro.AttributeHandleValueMap encodeAttrHandleMap(hla.rti1516_202X.AttributeHandleValueMap handleSet) {
        var result = hla.rti1516_2024.fedpro.AttributeHandleValueMap.newBuilder();
        handleSet.forEach((key, val) -> {
            result.addAttributeHandleValue(AttributeHandleValue.newBuilder()
                    .setAttributeHandle(AttributeHandle.newBuilder()
                            .setData(encodeAttrHandle(key))
                            .build())
                    .setValue(ByteString.copyFrom(val))
                    .build());
        });
        return result.build();
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

    private ByteString encodeTransHandle(hla.rti1516_202X.TransportationTypeHandle handle) {
        return encodeHandle(
                handle,
                hla.rti1516_202X.TransportationTypeHandle::encodedLength,
                hla.rti1516_202X.TransportationTypeHandle::encode
        );
    }

    private ByteString encodeFedHandle(hla.rti1516_202X.FederateHandle handle) {
        return encodeHandle(
                handle,
                hla.rti1516_202X.FederateHandle::encodedLength,
                hla.rti1516_202X.FederateHandle::encode
        );
    }

    private hla.rti1516_2024.fedpro.ParameterHandleValueMap encodeParamHandleMap(hla.rti1516_202X.ParameterHandleValueMap handleSet) {
        var result = hla.rti1516_2024.fedpro.ParameterHandleValueMap.newBuilder();
        handleSet.forEach((key, val) -> {
            result.addParameterHandleValue(ParameterHandleValue
                    .newBuilder()
                    .setParameterHandle(ParameterHandle.newBuilder().setData(encodeParamHandle(key)).build())
                    .setValue(ByteString.copyFrom(val))
            );
        });
        return result.build();
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

    private hla.rti1516_202X.ObjectInstanceHandle decodeInstanceHandle(ObjectInstanceHandle handle) throws RTIexception {
        var factory = this.rtiAmbassador.getObjectInstanceHandleFactory();
        return factory.decode(handle.getData().toByteArray(), 0);
    }

    private hla.rti1516_202X.AttributeHandleSet decodeAttrHandleSet(AttributeHandleSet handleSet) throws RTIexception {
        var set = new AttributeHandleSetImpl();
        for (var h : handleSet.getAttributeHandleList()) {
            set.add(decodeAttrHandle(h));
        }
        return set;
    }

    private hla.rti1516_202X.AttributeHandleValueMap decodeAttrHandleMap(hla.rti1516_2024.fedpro.AttributeHandleValueMap handleSet) throws RTIexception {
        var set = new AttributeHandleValueMapImpl();
        for (var h : handleSet.getAttributeHandleValueList()) {
            set.put(decodeAttrHandle(h.getAttributeHandle()), h.getValue().toByteArray());
        }
        return set;
    }

    private hla.rti1516_202X.AttributeHandle decodeAttrHandle(AttributeHandle handle) throws RTIexception {
        var factory = this.rtiAmbassador.getAttributeHandleFactory();
        return factory.decode(handle.getData().toByteArray(), 0);
    }

    private hla.rti1516_202X.ObjectClassHandle decodeObjHandle(ObjectClassHandle handle) throws RTIexception {
        var factory = this.rtiAmbassador.getObjectClassHandleFactory();
        return factory.decode(handle.getData().toByteArray(), 0);
    }

    private hla.rti1516_202X.ParameterHandleValueMap decodeParamHandleMap(hla.rti1516_2024.fedpro.ParameterHandleValueMap handleSet) throws RTIexception {
        var set = new ParameterHandleValueMapImpl();
        for (var h : handleSet.getParameterHandleValueList()) {
            set.put(decodeParamHandle(h.getParameterHandle()), h.getValue().toByteArray());
        }
        return set;
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
        private void send(CallbackRequest request) {
            channel.writeAndFlush(new HlaCallbackRequest(request));
        }

        @Override
        public void receiveInteraction(hla.rti1516_202X.InteractionClassHandle interactionClass, ParameterHandleValueMap parameterValues, byte[] userSuppliedTag, TransportationTypeHandle transportationType, FederateHandle producingFederate, RegionHandleSet optionalSentRegions) throws FederateInternalError {
            send(CallbackRequest.newBuilder()
                    .setReceiveInteraction(ReceiveInteraction.newBuilder()
                            .setInteractionClass(InteractionClassHandle.newBuilder()
                                    .setData(encodeClassHandle(interactionClass))
                                    .build())
                            .setParameterValues(encodeParamHandleMap(parameterValues))
                            .setUserSuppliedTag(userSuppliedTag == null ? ByteString.EMPTY : ByteString.copyFrom(userSuppliedTag))
                            .setTransportationType(hla.rti1516_2024.fedpro.TransportationTypeHandle.newBuilder()
                                    .setData(encodeTransHandle(transportationType))
                                    .build())
                            .setProducingFederate(hla.rti1516_2024.fedpro.FederateHandle.newBuilder()
                                    .setData(encodeFedHandle(producingFederate))
                                    .build())
                            .setOptionalSentRegions(ConveyedRegionSet.newBuilder()
                                    // TODO
                                    .build())
                            .build())
                    .build());
        }

        @Override
        public void objectInstanceNameReservationSucceeded(String objectInstanceName) throws FederateInternalError {
            send(CallbackRequest.newBuilder()
                    .setObjectInstanceNameReservationSucceeded(ObjectInstanceNameReservationSucceeded.newBuilder()
                            .setObjectInstanceName(objectInstanceName)
                            .build())
                    .build());
        }

        @Override
        public void objectInstanceNameReservationFailed(String objectInstanceName) throws FederateInternalError {
            send(CallbackRequest.newBuilder()
                    .setObjectInstanceNameReservationFailed(ObjectInstanceNameReservationFailed.newBuilder()
                            .setObjectInstanceName(objectInstanceName)
                            .build())
                    .build());
        }

        @Override
        public void reflectAttributeValues(hla.rti1516_202X.ObjectInstanceHandle objectInstance, AttributeHandleValueMap attributeValues, byte[] userSuppliedTag, TransportationTypeHandle transportationType, FederateHandle producingFederate, RegionHandleSet optionalSentRegions) throws FederateInternalError {
            send(CallbackRequest.newBuilder()
                    .setReflectAttributeValues(ReflectAttributeValues.newBuilder()
                            .setObjectInstance(ObjectInstanceHandle.newBuilder()
                                    .setData(encodeInstanceHandle(objectInstance))
                                    .build())
                            .setAttributeValues(encodeAttrHandleMap(attributeValues))
                            .setUserSuppliedTag(userSuppliedTag == null ? ByteString.EMPTY : ByteString.copyFrom(userSuppliedTag))
                            .setTransportationType(hla.rti1516_2024.fedpro.TransportationTypeHandle.newBuilder()
                                    .setData(encodeTransHandle(transportationType))
                                    .build())
                            .setProducingFederate(hla.rti1516_2024.fedpro.FederateHandle.newBuilder()
                                    .setData(encodeFedHandle(producingFederate))
                                    .build())
                            .setOptionalSentRegions(ConveyedRegionSet.newBuilder()
                                    // TODO
                                    .build())
                            .build())
                    .build());
        }

        @Override
        public void removeObjectInstance(hla.rti1516_202X.ObjectInstanceHandle objectInstance, byte[] userSuppliedTag, FederateHandle producingFederate) throws FederateInternalError {
            send(CallbackRequest.newBuilder()
                    .setRemoveObjectInstance(RemoveObjectInstance.newBuilder()
                            .setObjectInstance(ObjectInstanceHandle.newBuilder()
                                    .setData(encodeInstanceHandle(objectInstance))
                                    .build())
                            .setUserSuppliedTag(userSuppliedTag == null ? ByteString.EMPTY : ByteString.copyFrom(userSuppliedTag))
                            .setProducingFederate(hla.rti1516_2024.fedpro.FederateHandle.newBuilder()
                                    .setData(encodeFedHandle(producingFederate))
                                    .build())
                            .build())
                    .build());
        }

        @Override
        public void provideAttributeValueUpdate(hla.rti1516_202X.ObjectInstanceHandle objectInstance, hla.rti1516_202X.AttributeHandleSet attributes, byte[] userSuppliedTag) throws FederateInternalError {
            send(CallbackRequest.newBuilder()
                    .setProvideAttributeValueUpdate(ProvideAttributeValueUpdate.newBuilder()
                            .setObjectInstance(ObjectInstanceHandle.newBuilder()
                                    .setData(encodeInstanceHandle(objectInstance))
                                    .build())
                            .setAttributes(encodeAttrHandleSet(attributes))
                            .setUserSuppliedTag(userSuppliedTag == null ? ByteString.EMPTY : ByteString.copyFrom(userSuppliedTag))
                            .build())
                    .build());
        }
    }
}
