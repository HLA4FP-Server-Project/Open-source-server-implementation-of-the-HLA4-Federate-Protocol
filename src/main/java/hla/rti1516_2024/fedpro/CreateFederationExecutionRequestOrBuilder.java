// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: RTIambassador.proto
// Protobuf Java Version: 4.30.1

package hla.rti1516_2024.fedpro;

public interface CreateFederationExecutionRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:rti1516_2024.fedpro.CreateFederationExecutionRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string federationName = 1;</code>
   * @return The federationName.
   */
  java.lang.String getFederationName();
  /**
   * <code>string federationName = 1;</code>
   * @return The bytes for federationName.
   */
  com.google.protobuf.ByteString
      getFederationNameBytes();

  /**
   * <code>.rti1516_2024.fedpro.FomModule fomModule = 2;</code>
   * @return Whether the fomModule field is set.
   */
  boolean hasFomModule();
  /**
   * <code>.rti1516_2024.fedpro.FomModule fomModule = 2;</code>
   * @return The fomModule.
   */
  hla.rti1516_2024.fedpro.FomModule getFomModule();
  /**
   * <code>.rti1516_2024.fedpro.FomModule fomModule = 2;</code>
   */
  hla.rti1516_2024.fedpro.FomModuleOrBuilder getFomModuleOrBuilder();
}
