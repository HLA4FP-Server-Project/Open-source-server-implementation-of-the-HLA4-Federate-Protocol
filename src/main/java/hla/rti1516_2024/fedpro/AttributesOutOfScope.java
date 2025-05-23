// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: FederateAmbassador.proto
// Protobuf Java Version: 4.30.1

package hla.rti1516_2024.fedpro;

/**
 * Protobuf type {@code rti1516_2024.fedpro.AttributesOutOfScope}
 */
public final class AttributesOutOfScope extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:rti1516_2024.fedpro.AttributesOutOfScope)
    AttributesOutOfScopeOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 30,
      /* patch= */ 1,
      /* suffix= */ "",
      AttributesOutOfScope.class.getName());
  }
  // Use AttributesOutOfScope.newBuilder() to construct.
  private AttributesOutOfScope(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private AttributesOutOfScope() {
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_AttributesOutOfScope_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_AttributesOutOfScope_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            hla.rti1516_2024.fedpro.AttributesOutOfScope.class, hla.rti1516_2024.fedpro.AttributesOutOfScope.Builder.class);
  }

  private int bitField0_;
  public static final int OBJECTINSTANCE_FIELD_NUMBER = 1;
  private hla.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance_;
  /**
   * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
   * @return Whether the objectInstance field is set.
   */
  @java.lang.Override
  public boolean hasObjectInstance() {
    return ((bitField0_ & 0x00000001) != 0);
  }
  /**
   * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
   * @return The objectInstance.
   */
  @java.lang.Override
  public hla.rti1516_2024.fedpro.ObjectInstanceHandle getObjectInstance() {
    return objectInstance_ == null ? hla.rti1516_2024.fedpro.ObjectInstanceHandle.getDefaultInstance() : objectInstance_;
  }
  /**
   * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
   */
  @java.lang.Override
  public hla.rti1516_2024.fedpro.ObjectInstanceHandleOrBuilder getObjectInstanceOrBuilder() {
    return objectInstance_ == null ? hla.rti1516_2024.fedpro.ObjectInstanceHandle.getDefaultInstance() : objectInstance_;
  }

  public static final int ATTRIBUTES_FIELD_NUMBER = 2;
  private hla.rti1516_2024.fedpro.AttributeHandleSet attributes_;
  /**
   * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
   * @return Whether the attributes field is set.
   */
  @java.lang.Override
  public boolean hasAttributes() {
    return ((bitField0_ & 0x00000002) != 0);
  }
  /**
   * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
   * @return The attributes.
   */
  @java.lang.Override
  public hla.rti1516_2024.fedpro.AttributeHandleSet getAttributes() {
    return attributes_ == null ? hla.rti1516_2024.fedpro.AttributeHandleSet.getDefaultInstance() : attributes_;
  }
  /**
   * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
   */
  @java.lang.Override
  public hla.rti1516_2024.fedpro.AttributeHandleSetOrBuilder getAttributesOrBuilder() {
    return attributes_ == null ? hla.rti1516_2024.fedpro.AttributeHandleSet.getDefaultInstance() : attributes_;
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
      output.writeMessage(1, getObjectInstance());
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      output.writeMessage(2, getAttributes());
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
        .computeMessageSize(1, getObjectInstance());
    }
    if (((bitField0_ & 0x00000002) != 0)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getAttributes());
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
    if (!(obj instanceof hla.rti1516_2024.fedpro.AttributesOutOfScope)) {
      return super.equals(obj);
    }
    hla.rti1516_2024.fedpro.AttributesOutOfScope other = (hla.rti1516_2024.fedpro.AttributesOutOfScope) obj;

    if (hasObjectInstance() != other.hasObjectInstance()) return false;
    if (hasObjectInstance()) {
      if (!getObjectInstance()
          .equals(other.getObjectInstance())) return false;
    }
    if (hasAttributes() != other.hasAttributes()) return false;
    if (hasAttributes()) {
      if (!getAttributes()
          .equals(other.getAttributes())) return false;
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
    if (hasObjectInstance()) {
      hash = (37 * hash) + OBJECTINSTANCE_FIELD_NUMBER;
      hash = (53 * hash) + getObjectInstance().hashCode();
    }
    if (hasAttributes()) {
      hash = (37 * hash) + ATTRIBUTES_FIELD_NUMBER;
      hash = (53 * hash) + getAttributes().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hla.rti1516_2024.fedpro.AttributesOutOfScope parseFrom(
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
  public static Builder newBuilder(hla.rti1516_2024.fedpro.AttributesOutOfScope prototype) {
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
   * Protobuf type {@code rti1516_2024.fedpro.AttributesOutOfScope}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:rti1516_2024.fedpro.AttributesOutOfScope)
      hla.rti1516_2024.fedpro.AttributesOutOfScopeOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_AttributesOutOfScope_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_AttributesOutOfScope_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              hla.rti1516_2024.fedpro.AttributesOutOfScope.class, hla.rti1516_2024.fedpro.AttributesOutOfScope.Builder.class);
    }

    // Construct using hla.rti1516_2024.fedpro.AttributesOutOfScope.newBuilder()
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
        internalGetObjectInstanceFieldBuilder();
        internalGetAttributesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      objectInstance_ = null;
      if (objectInstanceBuilder_ != null) {
        objectInstanceBuilder_.dispose();
        objectInstanceBuilder_ = null;
      }
      attributes_ = null;
      if (attributesBuilder_ != null) {
        attributesBuilder_.dispose();
        attributesBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return hla.rti1516_2024.fedpro.FederateAmbassador.internal_static_rti1516_2024_fedpro_AttributesOutOfScope_descriptor;
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.AttributesOutOfScope getDefaultInstanceForType() {
      return hla.rti1516_2024.fedpro.AttributesOutOfScope.getDefaultInstance();
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.AttributesOutOfScope build() {
      hla.rti1516_2024.fedpro.AttributesOutOfScope result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.AttributesOutOfScope buildPartial() {
      hla.rti1516_2024.fedpro.AttributesOutOfScope result = new hla.rti1516_2024.fedpro.AttributesOutOfScope(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(hla.rti1516_2024.fedpro.AttributesOutOfScope result) {
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.objectInstance_ = objectInstanceBuilder_ == null
            ? objectInstance_
            : objectInstanceBuilder_.build();
        to_bitField0_ |= 0x00000001;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.attributes_ = attributesBuilder_ == null
            ? attributes_
            : attributesBuilder_.build();
        to_bitField0_ |= 0x00000002;
      }
      result.bitField0_ |= to_bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof hla.rti1516_2024.fedpro.AttributesOutOfScope) {
        return mergeFrom((hla.rti1516_2024.fedpro.AttributesOutOfScope)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(hla.rti1516_2024.fedpro.AttributesOutOfScope other) {
      if (other == hla.rti1516_2024.fedpro.AttributesOutOfScope.getDefaultInstance()) return this;
      if (other.hasObjectInstance()) {
        mergeObjectInstance(other.getObjectInstance());
      }
      if (other.hasAttributes()) {
        mergeAttributes(other.getAttributes());
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
                  internalGetObjectInstanceFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 18: {
              input.readMessage(
                  internalGetAttributesFieldBuilder().getBuilder(),
                  extensionRegistry);
              bitField0_ |= 0x00000002;
              break;
            } // case 18
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

    private hla.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance_;
    private com.google.protobuf.SingleFieldBuilder<
        hla.rti1516_2024.fedpro.ObjectInstanceHandle, hla.rti1516_2024.fedpro.ObjectInstanceHandle.Builder, hla.rti1516_2024.fedpro.ObjectInstanceHandleOrBuilder> objectInstanceBuilder_;
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     * @return Whether the objectInstance field is set.
     */
    public boolean hasObjectInstance() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     * @return The objectInstance.
     */
    public hla.rti1516_2024.fedpro.ObjectInstanceHandle getObjectInstance() {
      if (objectInstanceBuilder_ == null) {
        return objectInstance_ == null ? hla.rti1516_2024.fedpro.ObjectInstanceHandle.getDefaultInstance() : objectInstance_;
      } else {
        return objectInstanceBuilder_.getMessage();
      }
    }
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     */
    public Builder setObjectInstance(hla.rti1516_2024.fedpro.ObjectInstanceHandle value) {
      if (objectInstanceBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        objectInstance_ = value;
      } else {
        objectInstanceBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     */
    public Builder setObjectInstance(
        hla.rti1516_2024.fedpro.ObjectInstanceHandle.Builder builderForValue) {
      if (objectInstanceBuilder_ == null) {
        objectInstance_ = builderForValue.build();
      } else {
        objectInstanceBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     */
    public Builder mergeObjectInstance(hla.rti1516_2024.fedpro.ObjectInstanceHandle value) {
      if (objectInstanceBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0) &&
          objectInstance_ != null &&
          objectInstance_ != hla.rti1516_2024.fedpro.ObjectInstanceHandle.getDefaultInstance()) {
          getObjectInstanceBuilder().mergeFrom(value);
        } else {
          objectInstance_ = value;
        }
      } else {
        objectInstanceBuilder_.mergeFrom(value);
      }
      if (objectInstance_ != null) {
        bitField0_ |= 0x00000001;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     */
    public Builder clearObjectInstance() {
      bitField0_ = (bitField0_ & ~0x00000001);
      objectInstance_ = null;
      if (objectInstanceBuilder_ != null) {
        objectInstanceBuilder_.dispose();
        objectInstanceBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     */
    public hla.rti1516_2024.fedpro.ObjectInstanceHandle.Builder getObjectInstanceBuilder() {
      bitField0_ |= 0x00000001;
      onChanged();
      return internalGetObjectInstanceFieldBuilder().getBuilder();
    }
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     */
    public hla.rti1516_2024.fedpro.ObjectInstanceHandleOrBuilder getObjectInstanceOrBuilder() {
      if (objectInstanceBuilder_ != null) {
        return objectInstanceBuilder_.getMessageOrBuilder();
      } else {
        return objectInstance_ == null ?
            hla.rti1516_2024.fedpro.ObjectInstanceHandle.getDefaultInstance() : objectInstance_;
      }
    }
    /**
     * <code>.rti1516_2024.fedpro.ObjectInstanceHandle objectInstance = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilder<
        hla.rti1516_2024.fedpro.ObjectInstanceHandle, hla.rti1516_2024.fedpro.ObjectInstanceHandle.Builder, hla.rti1516_2024.fedpro.ObjectInstanceHandleOrBuilder> 
        internalGetObjectInstanceFieldBuilder() {
      if (objectInstanceBuilder_ == null) {
        objectInstanceBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            hla.rti1516_2024.fedpro.ObjectInstanceHandle, hla.rti1516_2024.fedpro.ObjectInstanceHandle.Builder, hla.rti1516_2024.fedpro.ObjectInstanceHandleOrBuilder>(
                getObjectInstance(),
                getParentForChildren(),
                isClean());
        objectInstance_ = null;
      }
      return objectInstanceBuilder_;
    }

    private hla.rti1516_2024.fedpro.AttributeHandleSet attributes_;
    private com.google.protobuf.SingleFieldBuilder<
        hla.rti1516_2024.fedpro.AttributeHandleSet, hla.rti1516_2024.fedpro.AttributeHandleSet.Builder, hla.rti1516_2024.fedpro.AttributeHandleSetOrBuilder> attributesBuilder_;
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     * @return Whether the attributes field is set.
     */
    public boolean hasAttributes() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     * @return The attributes.
     */
    public hla.rti1516_2024.fedpro.AttributeHandleSet getAttributes() {
      if (attributesBuilder_ == null) {
        return attributes_ == null ? hla.rti1516_2024.fedpro.AttributeHandleSet.getDefaultInstance() : attributes_;
      } else {
        return attributesBuilder_.getMessage();
      }
    }
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     */
    public Builder setAttributes(hla.rti1516_2024.fedpro.AttributeHandleSet value) {
      if (attributesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        attributes_ = value;
      } else {
        attributesBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     */
    public Builder setAttributes(
        hla.rti1516_2024.fedpro.AttributeHandleSet.Builder builderForValue) {
      if (attributesBuilder_ == null) {
        attributes_ = builderForValue.build();
      } else {
        attributesBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     */
    public Builder mergeAttributes(hla.rti1516_2024.fedpro.AttributeHandleSet value) {
      if (attributesBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0) &&
          attributes_ != null &&
          attributes_ != hla.rti1516_2024.fedpro.AttributeHandleSet.getDefaultInstance()) {
          getAttributesBuilder().mergeFrom(value);
        } else {
          attributes_ = value;
        }
      } else {
        attributesBuilder_.mergeFrom(value);
      }
      if (attributes_ != null) {
        bitField0_ |= 0x00000002;
        onChanged();
      }
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     */
    public Builder clearAttributes() {
      bitField0_ = (bitField0_ & ~0x00000002);
      attributes_ = null;
      if (attributesBuilder_ != null) {
        attributesBuilder_.dispose();
        attributesBuilder_ = null;
      }
      onChanged();
      return this;
    }
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     */
    public hla.rti1516_2024.fedpro.AttributeHandleSet.Builder getAttributesBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return internalGetAttributesFieldBuilder().getBuilder();
    }
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     */
    public hla.rti1516_2024.fedpro.AttributeHandleSetOrBuilder getAttributesOrBuilder() {
      if (attributesBuilder_ != null) {
        return attributesBuilder_.getMessageOrBuilder();
      } else {
        return attributes_ == null ?
            hla.rti1516_2024.fedpro.AttributeHandleSet.getDefaultInstance() : attributes_;
      }
    }
    /**
     * <code>.rti1516_2024.fedpro.AttributeHandleSet attributes = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilder<
        hla.rti1516_2024.fedpro.AttributeHandleSet, hla.rti1516_2024.fedpro.AttributeHandleSet.Builder, hla.rti1516_2024.fedpro.AttributeHandleSetOrBuilder> 
        internalGetAttributesFieldBuilder() {
      if (attributesBuilder_ == null) {
        attributesBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            hla.rti1516_2024.fedpro.AttributeHandleSet, hla.rti1516_2024.fedpro.AttributeHandleSet.Builder, hla.rti1516_2024.fedpro.AttributeHandleSetOrBuilder>(
                getAttributes(),
                getParentForChildren(),
                isClean());
        attributes_ = null;
      }
      return attributesBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:rti1516_2024.fedpro.AttributesOutOfScope)
  }

  // @@protoc_insertion_point(class_scope:rti1516_2024.fedpro.AttributesOutOfScope)
  private static final hla.rti1516_2024.fedpro.AttributesOutOfScope DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new hla.rti1516_2024.fedpro.AttributesOutOfScope();
  }

  public static hla.rti1516_2024.fedpro.AttributesOutOfScope getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AttributesOutOfScope>
      PARSER = new com.google.protobuf.AbstractParser<AttributesOutOfScope>() {
    @java.lang.Override
    public AttributesOutOfScope parsePartialFrom(
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

  public static com.google.protobuf.Parser<AttributesOutOfScope> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AttributesOutOfScope> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public hla.rti1516_2024.fedpro.AttributesOutOfScope getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

