package com.rug.tno.layers;

import com.rug.tno.fpdata.FpMessage;
import com.rug.tno.fpdata.HlaCallRequest;
import com.rug.tno.fpdata.HlaCallResponse;
import hla.rti1516_2024.fedpro.*;
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
            default -> throw new IllegalStateException("Unknown call request " + request.getCallRequestCase());
        }
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
