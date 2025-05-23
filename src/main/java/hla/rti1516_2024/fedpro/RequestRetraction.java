// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: FederateAmbassador.proto
// Protobuf Java Version: 4.30.1

package hla.rti1516_2024.fedpro;

/**
 * Protobuf type {@code rti1516_2024.fedpro.RequestRetraction}
 */
public final class RequestRetraction extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:rti1516_2024.fedpro.RequestRetraction)
    RequestRetractionOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 30,
      /* patch= */ 1,
      /* suffix= */ "",
      RequestRetraction.class.getName());
  }
  // Use RequestRetraction.newBuilder() to construct.
  private RequestRetraction(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private RequestRetraction() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_RequestRetraction_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_RequestRetraction_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            hla.rti1516_2024.fedpro.RequestRetraction.class, hla.rti1516_2024.fedpro.RequestRetraction.Builder.class);
  }

  private int bitField0_;
  public static final int RETRACTION_FIELD_NUMBER = 1;
  private hla.rti1516_2024.fedpro.MessageRetractionHandle retraction_;
  /**
   * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
   * @return Whether the retraction field is set.
   */
  @java.lang.Override
  public boolean hasRetraction() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
   * @return The retraction.
   */
  @java.lang.Override
  public hla.rti1516_2024.fedpro.MessageRetractionHandle getRetraction() {
    return retraction_ == null ? hla.rti1516_2024.fedpro.MessageRetractionHandle.getDefaultInstance() : retraction_;
  }
  /**
   * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
   */
  @java.lang.Override
  public hla.rti1516_2024.fedpro.MessageRetractionHandleOrBuilder getRetractionOrBuilder() {
    return retraction_ == null ? hla.rti1516_2024.fedpro.MessageRetractionHandle.getDefaultInstance() : retraction_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (((bitField0_ & 0x00000001) != 0)) {
      output.writeMessage(1, getRetraction());
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getRetraction());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof hla.rti1516_2024.fedpro.RequestRetraction)) {
      return super.equals(obj);
    }
    hla.rti1516_2024.fedpro.RequestRetraction other = (hla.rti1516_2024.fedpro.RequestRetraction) obj;

    if (hasRetraction() != other.hasRetraction()) return false;
    if (hasRetraction()) {
      if (!getRetraction()
          .equals(other.getRetraction())) return false;
    }
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (hasRetraction()) {
      hash = (37 * hash) + RETRACTION_FIELD_NUMBER;
      hash = (53 * hash) + getRetraction().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static hla.rti1516_2024.fedpro.RequestRetraction parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static hla.rti1516_2024.fedpro.RequestRetraction parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hla.rti1516_2024.fedpro.RequestRetraction parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(hla.rti1516_2024.fedpro.RequestRetraction prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code rti1516_2024.fedpro.RequestRetraction}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:rti1516_2024.fedpro.RequestRetraction)
      hla.rti1516_2024.fedpro.RequestRetractionOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_RequestRetraction_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_RequestRetraction_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              hla.rti1516_2024.fedpro.RequestRetraction.class, hla.rti1516_2024.fedpro.RequestRetraction.Builder.class);
    }

    // Construct using hla.rti1516_2024.fedpro.RequestRetraction.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage
              .alwaysUseFieldBuilders) {
        internalGetRetractionFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      retraction_ = null;
      if (retractionBuilder_ != null) {
        retractionBuilder_.dispose();
        retractionBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_RequestRetraction_descriptor;
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.RequestRetraction getDefaultInstanceForType() {
      return hla.rti1516_2024.fedpro.RequestRetraction.getDefaultInstance();
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.RequestRetraction build() {
      hla.rti1516_2024.fedpro.RequestRetraction result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.RequestRetraction buildPartial() {
      hla.rti1516_2024.fedpro.RequestRetraction result = new hla.rti1516_2024.fedpro.RequestRetraction(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(hla.rti1516_2024.fedpro.RequestRetraction result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.retraction_ = retractionBuilder_ == null
            ? retraction_
            : retractionBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      result.bitField0_ |= to_bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof hla.rti1516_2024.fedpro.RequestRetraction) {
        return mergeFrom((hla.rti1516_2024.fedpro.RequestRetraction)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(hla.rti1516_2024.fedpro.RequestRetraction other) {
      if (other == hla.rti1516_2024.fedpro.RequestRetraction.getDefaultInstance()) return this;
      if (other.hasRetraction()) {
        mergeRetraction(other.getRetraction());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              input.readMessage(
                  internalGetRetractionFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private hla.rti1516_2024.fedpro.MessageRetractionHandle retraction_;
    private com.google.protobuf.SingleFieldBuilder<
        hla.rti1516_2024.fedpro.MessageRetractionHandle, hla.rti1516_2024.fedpro.MessageRetractionHandle.Builder, hla.rti1516_2024.fedpro.MessageRetractionHandleOrBuilder> retractionBuilder_;
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     * @return Whether the retraction field is set.
     */
    public boolean hasRetraction() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     * @return The retraction.
     */
    public hla.rti1516_2024.fedpro.MessageRetractionHandle getRetraction() {
      if (retractionBuilder_ == null) {
        return retraction_ == null ? hla.rti1516_2024.fedpro.MessageRetractionHandle.getDefaultInstance() : retraction_;
      } else {
        return retractionBuilder_.getMessage();
      }
    }
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     */
    public Builder setRetraction(hla.rti1516_2024.fedpro.MessageRetractionHandle value) {
      if (retractionBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        retraction_ = value;
      } else {
        retractionBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     */
    public Builder setRetraction(
        hla.rti1516_2024.fedpro.MessageRetractionHandle.Builder builderForValue) {
      if (retractionBuilder_ == null) {
        retraction_ = builderForValue.build();
      } else {
        retractionBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     */
    public Builder mergeRetraction(hla.rti1516_2024.fedpro.MessageRetractionHandle value) {
      if (retractionBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          retraction_ != null &&
          retraction_ != hla.rti1516_2024.fedpro.MessageRetractionHandle.getDefaultInstance()) {
          getRetractionBuilder().mergeFrom(value);
        } else {
          retraction_ = value;
        }
      } else {
        retractionBuilder_.mergeFrom(value);
      }
      if (retraction_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     */
    public Builder clearRetraction() {
      bitField0_ = (bitField0_ & ~0x00000001);
      retraction_ = null;
      if (retractionBuilder_ != null) {
        retractionBuilder_.dispose();
        retractionBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     */
    public hla.rti1516_2024.fedpro.MessageRetractionHandle.Builder getRetractionBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return internalGetRetractionFieldBuilder().getBuilder();
    }
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     */
    public hla.rti1516_2024.fedpro.MessageRetractionHandleOrBuilder getRetractionOrBuilder() {
      if (retractionBuilder_ != null) {
        return retractionBuilder_.getMessageOrBuilder();
      } else {
        return retraction_ == null ?
            hla.rti1516_2024.fedpro.MessageRetractionHandle.getDefaultInstance() : retraction_;
      }
    }
    /**
     * <code>.rti1516_2024.fedpro.MessageRetractionHandle retraction = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilder<
        hla.rti1516_2024.fedpro.MessageRetractionHandle, hla.rti1516_2024.fedpro.MessageRetractionHandle.Builder, hla.rti1516_2024.fedpro.MessageRetractionHandleOrBuilder> 
        internalGetRetractionFieldBuilder() {
      if (retractionBuilder_ == null) {
        retractionBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            hla.rti1516_2024.fedpro.MessageRetractionHandle, hla.rti1516_2024.fedpro.MessageRetractionHandle.Builder, hla.rti1516_2024.fedpro.MessageRetractionHandleOrBuilder>(
                getRetraction(),
                getParentForChildren(),
                isClean());
        retraction_ = null;
      }
      return retractionBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:rti1516_2024.fedpro.RequestRetraction)
  }

  // @@protoc_insertion_point(class_scope:rti1516_2024.fedpro.RequestRetraction)
  private static final hla.rti1516_2024.fedpro.RequestRetraction DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new hla.rti1516_2024.fedpro.RequestRetraction();
  }

  public static hla.rti1516_2024.fedpro.RequestRetraction getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RequestRetraction>
      PARSER = new com.google.protobuf.AbstractParser<RequestRetraction>() {
    @java.lang.Override
    public RequestRetraction parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<RequestRetraction> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RequestRetraction> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public hla.rti1516_2024.fedpro.RequestRetraction getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

