// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Funding.proto

package net.devh.boot.grpc.examples.lib;

/**
 * Protobuf type {@code CallTossPayResponse}
 */
public final class CallTossPayResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:CallTossPayResponse)
    CallTossPayResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use CallTossPayResponse.newBuilder() to construct.
  private CallTossPayResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private CallTossPayResponse() {
    paymentKey_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new CallTossPayResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return net.devh.boot.grpc.examples.lib.FundingProto.internal_static_CallTossPayResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return net.devh.boot.grpc.examples.lib.FundingProto.internal_static_CallTossPayResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            net.devh.boot.grpc.examples.lib.CallTossPayResponse.class, net.devh.boot.grpc.examples.lib.CallTossPayResponse.Builder.class);
  }

  public static final int PAYMENTKEY_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object paymentKey_ = "";
  /**
   * <code>string paymentKey = 1;</code>
   * @return The paymentKey.
   */
  @java.lang.Override
  public java.lang.String getPaymentKey() {
    java.lang.Object ref = paymentKey_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      paymentKey_ = s;
      return s;
    }
  }
  /**
   * <code>string paymentKey = 1;</code>
   * @return The bytes for paymentKey.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getPaymentKeyBytes() {
    java.lang.Object ref = paymentKey_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      paymentKey_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(paymentKey_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, paymentKey_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(paymentKey_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, paymentKey_);
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
    if (!(obj instanceof net.devh.boot.grpc.examples.lib.CallTossPayResponse)) {
      return super.equals(obj);
    }
    net.devh.boot.grpc.examples.lib.CallTossPayResponse other = (net.devh.boot.grpc.examples.lib.CallTossPayResponse) obj;

    if (!getPaymentKey()
        .equals(other.getPaymentKey())) return false;
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
    hash = (37 * hash) + PAYMENTKEY_FIELD_NUMBER;
    hash = (53 * hash) + getPaymentKey().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(net.devh.boot.grpc.examples.lib.CallTossPayResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code CallTossPayResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:CallTossPayResponse)
      net.devh.boot.grpc.examples.lib.CallTossPayResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return net.devh.boot.grpc.examples.lib.FundingProto.internal_static_CallTossPayResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return net.devh.boot.grpc.examples.lib.FundingProto.internal_static_CallTossPayResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              net.devh.boot.grpc.examples.lib.CallTossPayResponse.class, net.devh.boot.grpc.examples.lib.CallTossPayResponse.Builder.class);
    }

    // Construct using net.devh.boot.grpc.examples.lib.CallTossPayResponse.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      paymentKey_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return net.devh.boot.grpc.examples.lib.FundingProto.internal_static_CallTossPayResponse_descriptor;
    }

    @java.lang.Override
    public net.devh.boot.grpc.examples.lib.CallTossPayResponse getDefaultInstanceForType() {
      return net.devh.boot.grpc.examples.lib.CallTossPayResponse.getDefaultInstance();
    }

    @java.lang.Override
    public net.devh.boot.grpc.examples.lib.CallTossPayResponse build() {
      net.devh.boot.grpc.examples.lib.CallTossPayResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public net.devh.boot.grpc.examples.lib.CallTossPayResponse buildPartial() {
      net.devh.boot.grpc.examples.lib.CallTossPayResponse result = new net.devh.boot.grpc.examples.lib.CallTossPayResponse(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(net.devh.boot.grpc.examples.lib.CallTossPayResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.paymentKey_ = paymentKey_;
      }
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof net.devh.boot.grpc.examples.lib.CallTossPayResponse) {
        return mergeFrom((net.devh.boot.grpc.examples.lib.CallTossPayResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(net.devh.boot.grpc.examples.lib.CallTossPayResponse other) {
      if (other == net.devh.boot.grpc.examples.lib.CallTossPayResponse.getDefaultInstance()) return this;
      if (!other.getPaymentKey().isEmpty()) {
        paymentKey_ = other.paymentKey_;
        bitField0_ |= 0x00000001;
        onChanged();
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
              paymentKey_ = input.readStringRequireUtf8();
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

    private java.lang.Object paymentKey_ = "";
    /**
     * <code>string paymentKey = 1;</code>
     * @return The paymentKey.
     */
    public java.lang.String getPaymentKey() {
      java.lang.Object ref = paymentKey_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        paymentKey_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string paymentKey = 1;</code>
     * @return The bytes for paymentKey.
     */
    public com.google.protobuf.ByteString
        getPaymentKeyBytes() {
      java.lang.Object ref = paymentKey_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        paymentKey_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string paymentKey = 1;</code>
     * @param value The paymentKey to set.
     * @return This builder for chaining.
     */
    public Builder setPaymentKey(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      paymentKey_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <code>string paymentKey = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPaymentKey() {
      paymentKey_ = getDefaultInstance().getPaymentKey();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>string paymentKey = 1;</code>
     * @param value The bytes for paymentKey to set.
     * @return This builder for chaining.
     */
    public Builder setPaymentKeyBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      paymentKey_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:CallTossPayResponse)
  }

  // @@protoc_insertion_point(class_scope:CallTossPayResponse)
  private static final net.devh.boot.grpc.examples.lib.CallTossPayResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new net.devh.boot.grpc.examples.lib.CallTossPayResponse();
  }

  public static net.devh.boot.grpc.examples.lib.CallTossPayResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<CallTossPayResponse>
      PARSER = new com.google.protobuf.AbstractParser<CallTossPayResponse>() {
    @java.lang.Override
    public CallTossPayResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<CallTossPayResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<CallTossPayResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public net.devh.boot.grpc.examples.lib.CallTossPayResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
