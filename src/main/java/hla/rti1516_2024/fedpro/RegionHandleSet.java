// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: datatypes.proto
// Protobuf Java Version: 4.30.1

package hla.rti1516_2024.fedpro;

/**
 * <pre>
 * Set of RegionHandle elements.
 * The set shall not contain duplicate elements.
 * </pre>
 *
 * Protobuf type {@code rti1516_2024.fedpro.RegionHandleSet}
 */
public final class RegionHandleSet extends
    com.google.protobuf.GeneratedMessage implements
    // @@protoc_insertion_point(message_implements:rti1516_2024.fedpro.RegionHandleSet)
    RegionHandleSetOrBuilder {
private static final long serialVersionUID = 0L;
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 30,
      /* patch= */ 1,
      /* suffix= */ "",
      RegionHandleSet.class.getName());
  }
  // Use RegionHandleSet.newBuilder() to construct.
  private RegionHandleSet(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
  }
  private RegionHandleSet() {
    regionHandle_ = java.util.Collections.emptyList();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return hla.rti1516_2024.fedpro.Datatypes.internal_static_rti1516_2024_fedpro_RegionHandleSet_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return hla.rti1516_2024.fedpro.Datatypes.internal_static_rti1516_2024_fedpro_RegionHandleSet_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            hla.rti1516_2024.fedpro.RegionHandleSet.class, hla.rti1516_2024.fedpro.RegionHandleSet.Builder.class);
  }

  public static final int REGIONHANDLE_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private java.util.List<hla.rti1516_2024.fedpro.RegionHandle> regionHandle_;
  /**
   * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
   */
  @java.lang.Override
  public java.util.List<hla.rti1516_2024.fedpro.RegionHandle> getRegionHandleList() {
    return regionHandle_;
  }
  /**
   * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends hla.rti1516_2024.fedpro.RegionHandleOrBuilder> 
      getRegionHandleOrBuilderList() {
    return regionHandle_;
  }
  /**
   * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
   */
  @java.lang.Override
  public int getRegionHandleCount() {
    return regionHandle_.size();
  }
  /**
   * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
   */
  @java.lang.Override
  public hla.rti1516_2024.fedpro.RegionHandle getRegionHandle(int index) {
    return regionHandle_.get(index);
  }
  /**
   * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
   */
  @java.lang.Override
  public hla.rti1516_2024.fedpro.RegionHandleOrBuilder getRegionHandleOrBuilder(
      int index) {
    return regionHandle_.get(index);
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
    for (int i = 0; i < regionHandle_.size(); i++) {
      output.writeMessage(1, regionHandle_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < regionHandle_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, regionHandle_.get(i));
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
    if (!(obj instanceof hla.rti1516_2024.fedpro.RegionHandleSet)) {
      return super.equals(obj);
    }
    hla.rti1516_2024.fedpro.RegionHandleSet other = (hla.rti1516_2024.fedpro.RegionHandleSet) obj;

    if (!getRegionHandleList()
        .equals(other.getRegionHandleList())) return false;
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
    if (getRegionHandleCount() > 0) {
      hash = (37 * hash) + REGIONHANDLE_FIELD_NUMBER;
      hash = (53 * hash) + getRegionHandleList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static hla.rti1516_2024.fedpro.RegionHandleSet parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static hla.rti1516_2024.fedpro.RegionHandleSet parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessage
        .parseWithIOException(PARSER, input);
  }
  public static hla.rti1516_2024.fedpro.RegionHandleSet parseFrom(
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
  public static Builder newBuilder(hla.rti1516_2024.fedpro.RegionHandleSet prototype) {
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
   * <pre>
   * Set of RegionHandle elements.
   * The set shall not contain duplicate elements.
   * </pre>
   *
   * Protobuf type {@code rti1516_2024.fedpro.RegionHandleSet}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:rti1516_2024.fedpro.RegionHandleSet)
      hla.rti1516_2024.fedpro.RegionHandleSetOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return hla.rti1516_2024.fedpro.Datatypes.internal_static_rti1516_2024_fedpro_RegionHandleSet_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return hla.rti1516_2024.fedpro.Datatypes.internal_static_rti1516_2024_fedpro_RegionHandleSet_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              hla.rti1516_2024.fedpro.RegionHandleSet.class, hla.rti1516_2024.fedpro.RegionHandleSet.Builder.class);
    }

    // Construct using hla.rti1516_2024.fedpro.RegionHandleSet.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      if (regionHandleBuilder_ == null) {
        regionHandle_ = java.util.Collections.emptyList();
      } else {
        regionHandle_ = null;
        regionHandleBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return hla.rti1516_2024.fedpro.Datatypes.internal_static_rti1516_2024_fedpro_RegionHandleSet_descriptor;
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.RegionHandleSet getDefaultInstanceForType() {
      return hla.rti1516_2024.fedpro.RegionHandleSet.getDefaultInstance();
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.RegionHandleSet build() {
      hla.rti1516_2024.fedpro.RegionHandleSet result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public hla.rti1516_2024.fedpro.RegionHandleSet buildPartial() {
      hla.rti1516_2024.fedpro.RegionHandleSet result = new hla.rti1516_2024.fedpro.RegionHandleSet(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(hla.rti1516_2024.fedpro.RegionHandleSet result) {
      if (regionHandleBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          regionHandle_ = java.util.Collections.unmodifiableList(regionHandle_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.regionHandle_ = regionHandle_;
      } else {
        result.regionHandle_ = regionHandleBuilder_.build();
      }
    }

    private void buildPartial0(hla.rti1516_2024.fedpro.RegionHandleSet result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof hla.rti1516_2024.fedpro.RegionHandleSet) {
        return mergeFrom((hla.rti1516_2024.fedpro.RegionHandleSet)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(hla.rti1516_2024.fedpro.RegionHandleSet other) {
      if (other == hla.rti1516_2024.fedpro.RegionHandleSet.getDefaultInstance()) return this;
      if (regionHandleBuilder_ == null) {
        if (!other.regionHandle_.isEmpty()) {
          if (regionHandle_.isEmpty()) {
            regionHandle_ = other.regionHandle_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureRegionHandleIsMutable();
            regionHandle_.addAll(other.regionHandle_);
          }
          onChanged();
        }
      } else {
        if (!other.regionHandle_.isEmpty()) {
          if (regionHandleBuilder_.isEmpty()) {
            regionHandleBuilder_.dispose();
            regionHandleBuilder_ = null;
            regionHandle_ = other.regionHandle_;
            bitField0_ = (bitField0_ & ~0x00000001);
            regionHandleBuilder_ = 
              com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders ?
                 internalGetRegionHandleFieldBuilder() : null;
          } else {
            regionHandleBuilder_.addAllMessages(other.regionHandle_);
          }
        }
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
              hla.rti1516_2024.fedpro.RegionHandle m =
                  input.readMessage(
                      hla.rti1516_2024.fedpro.RegionHandle.parser(),
                      extensionRegistry);
              if (regionHandleBuilder_ == null) {
                ensureRegionHandleIsMutable();
                regionHandle_.add(m);
              } else {
                regionHandleBuilder_.addMessage(m);
              }
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

    private java.util.List<hla.rti1516_2024.fedpro.RegionHandle> regionHandle_ =
      java.util.Collections.emptyList();
    private void ensureRegionHandleIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        regionHandle_ = new java.util.ArrayList<hla.rti1516_2024.fedpro.RegionHandle>(regionHandle_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilder<
        hla.rti1516_2024.fedpro.RegionHandle, hla.rti1516_2024.fedpro.RegionHandle.Builder, hla.rti1516_2024.fedpro.RegionHandleOrBuilder> regionHandleBuilder_;

    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public java.util.List<hla.rti1516_2024.fedpro.RegionHandle> getRegionHandleList() {
      if (regionHandleBuilder_ == null) {
        return java.util.Collections.unmodifiableList(regionHandle_);
      } else {
        return regionHandleBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public int getRegionHandleCount() {
      if (regionHandleBuilder_ == null) {
        return regionHandle_.size();
      } else {
        return regionHandleBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public hla.rti1516_2024.fedpro.RegionHandle getRegionHandle(int index) {
      if (regionHandleBuilder_ == null) {
        return regionHandle_.get(index);
      } else {
        return regionHandleBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder setRegionHandle(
        int index, hla.rti1516_2024.fedpro.RegionHandle value) {
      if (regionHandleBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRegionHandleIsMutable();
        regionHandle_.set(index, value);
        onChanged();
      } else {
        regionHandleBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder setRegionHandle(
        int index, hla.rti1516_2024.fedpro.RegionHandle.Builder builderForValue) {
      if (regionHandleBuilder_ == null) {
        ensureRegionHandleIsMutable();
        regionHandle_.set(index, builderForValue.build());
        onChanged();
      } else {
        regionHandleBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder addRegionHandle(hla.rti1516_2024.fedpro.RegionHandle value) {
      if (regionHandleBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRegionHandleIsMutable();
        regionHandle_.add(value);
        onChanged();
      } else {
        regionHandleBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder addRegionHandle(
        int index, hla.rti1516_2024.fedpro.RegionHandle value) {
      if (regionHandleBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRegionHandleIsMutable();
        regionHandle_.add(index, value);
        onChanged();
      } else {
        regionHandleBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder addRegionHandle(
        hla.rti1516_2024.fedpro.RegionHandle.Builder builderForValue) {
      if (regionHandleBuilder_ == null) {
        ensureRegionHandleIsMutable();
        regionHandle_.add(builderForValue.build());
        onChanged();
      } else {
        regionHandleBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder addRegionHandle(
        int index, hla.rti1516_2024.fedpro.RegionHandle.Builder builderForValue) {
      if (regionHandleBuilder_ == null) {
        ensureRegionHandleIsMutable();
        regionHandle_.add(index, builderForValue.build());
        onChanged();
      } else {
        regionHandleBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder addAllRegionHandle(
        java.lang.Iterable<? extends hla.rti1516_2024.fedpro.RegionHandle> values) {
      if (regionHandleBuilder_ == null) {
        ensureRegionHandleIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, regionHandle_);
        onChanged();
      } else {
        regionHandleBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder clearRegionHandle() {
      if (regionHandleBuilder_ == null) {
        regionHandle_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        regionHandleBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public Builder removeRegionHandle(int index) {
      if (regionHandleBuilder_ == null) {
        ensureRegionHandleIsMutable();
        regionHandle_.remove(index);
        onChanged();
      } else {
        regionHandleBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public hla.rti1516_2024.fedpro.RegionHandle.Builder getRegionHandleBuilder(
        int index) {
      return internalGetRegionHandleFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public hla.rti1516_2024.fedpro.RegionHandleOrBuilder getRegionHandleOrBuilder(
        int index) {
      if (regionHandleBuilder_ == null) {
        return regionHandle_.get(index);  } else {
        return regionHandleBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public java.util.List<? extends hla.rti1516_2024.fedpro.RegionHandleOrBuilder> 
         getRegionHandleOrBuilderList() {
      if (regionHandleBuilder_ != null) {
        return regionHandleBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(regionHandle_);
      }
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public hla.rti1516_2024.fedpro.RegionHandle.Builder addRegionHandleBuilder() {
      return internalGetRegionHandleFieldBuilder().addBuilder(
          hla.rti1516_2024.fedpro.RegionHandle.getDefaultInstance());
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public hla.rti1516_2024.fedpro.RegionHandle.Builder addRegionHandleBuilder(
        int index) {
      return internalGetRegionHandleFieldBuilder().addBuilder(
          index, hla.rti1516_2024.fedpro.RegionHandle.getDefaultInstance());
    }
    /**
     * <code>repeated .rti1516_2024.fedpro.RegionHandle regionHandle = 1;</code>
     */
    public java.util.List<hla.rti1516_2024.fedpro.RegionHandle.Builder> 
         getRegionHandleBuilderList() {
      return internalGetRegionHandleFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilder<
        hla.rti1516_2024.fedpro.RegionHandle, hla.rti1516_2024.fedpro.RegionHandle.Builder, hla.rti1516_2024.fedpro.RegionHandleOrBuilder> 
        internalGetRegionHandleFieldBuilder() {
      if (regionHandleBuilder_ == null) {
        regionHandleBuilder_ = new com.google.protobuf.RepeatedFieldBuilder<
            hla.rti1516_2024.fedpro.RegionHandle, hla.rti1516_2024.fedpro.RegionHandle.Builder, hla.rti1516_2024.fedpro.RegionHandleOrBuilder>(
                regionHandle_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        regionHandle_ = null;
      }
      return regionHandleBuilder_;
    }

    // @@protoc_insertion_point(builder_scope:rti1516_2024.fedpro.RegionHandleSet)
  }

  // @@protoc_insertion_point(class_scope:rti1516_2024.fedpro.RegionHandleSet)
  private static final hla.rti1516_2024.fedpro.RegionHandleSet DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new hla.rti1516_2024.fedpro.RegionHandleSet();
  }

  public static hla.rti1516_2024.fedpro.RegionHandleSet getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RegionHandleSet>
      PARSER = new com.google.protobuf.AbstractParser<RegionHandleSet>() {
    @java.lang.Override
    public RegionHandleSet parsePartialFrom(
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

  public static com.google.protobuf.Parser<RegionHandleSet> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RegionHandleSet> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public hla.rti1516_2024.fedpro.RegionHandleSet getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

