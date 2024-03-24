package net.devh.boot.grpc.examples.lib;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.53.0)",
    comments = "Source: Funding.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class OrderServiceGrpc {

  private OrderServiceGrpc() {}

  public static final String SERVICE_NAME = "OrderService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.devh.boot.grpc.examples.lib.CallTossPayRequest,
      net.devh.boot.grpc.examples.lib.CallTossPayResponse> getCallTossPayMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CallTossPay",
      requestType = net.devh.boot.grpc.examples.lib.CallTossPayRequest.class,
      responseType = net.devh.boot.grpc.examples.lib.CallTossPayResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.devh.boot.grpc.examples.lib.CallTossPayRequest,
      net.devh.boot.grpc.examples.lib.CallTossPayResponse> getCallTossPayMethod() {
    io.grpc.MethodDescriptor<net.devh.boot.grpc.examples.lib.CallTossPayRequest, net.devh.boot.grpc.examples.lib.CallTossPayResponse> getCallTossPayMethod;
    if ((getCallTossPayMethod = OrderServiceGrpc.getCallTossPayMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getCallTossPayMethod = OrderServiceGrpc.getCallTossPayMethod) == null) {
          OrderServiceGrpc.getCallTossPayMethod = getCallTossPayMethod =
              io.grpc.MethodDescriptor.<net.devh.boot.grpc.examples.lib.CallTossPayRequest, net.devh.boot.grpc.examples.lib.CallTossPayResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CallTossPay"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.devh.boot.grpc.examples.lib.CallTossPayRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.devh.boot.grpc.examples.lib.CallTossPayResponse.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("CallTossPay"))
              .build();
        }
      }
    }
    return getCallTossPayMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest,
      net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse> getCallTossPayConfirmMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CallTossPayConfirm",
      requestType = net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest.class,
      responseType = net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest,
      net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse> getCallTossPayConfirmMethod() {
    io.grpc.MethodDescriptor<net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest, net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse> getCallTossPayConfirmMethod;
    if ((getCallTossPayConfirmMethod = OrderServiceGrpc.getCallTossPayConfirmMethod) == null) {
      synchronized (OrderServiceGrpc.class) {
        if ((getCallTossPayConfirmMethod = OrderServiceGrpc.getCallTossPayConfirmMethod) == null) {
          OrderServiceGrpc.getCallTossPayConfirmMethod = getCallTossPayConfirmMethod =
              io.grpc.MethodDescriptor.<net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest, net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CallTossPayConfirm"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse.getDefaultInstance()))
              .setSchemaDescriptor(new OrderServiceMethodDescriptorSupplier("CallTossPayConfirm"))
              .build();
        }
      }
    }
    return getCallTossPayConfirmMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OrderServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceStub>() {
        @java.lang.Override
        public OrderServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceStub(channel, callOptions);
        }
      };
    return OrderServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OrderServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceBlockingStub>() {
        @java.lang.Override
        public OrderServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceBlockingStub(channel, callOptions);
        }
      };
    return OrderServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OrderServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderServiceFutureStub>() {
        @java.lang.Override
        public OrderServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderServiceFutureStub(channel, callOptions);
        }
      };
    return OrderServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class OrderServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void callTossPay(net.devh.boot.grpc.examples.lib.CallTossPayRequest request,
        io.grpc.stub.StreamObserver<net.devh.boot.grpc.examples.lib.CallTossPayResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCallTossPayMethod(), responseObserver);
    }

    /**
     */
    public void callTossPayConfirm(net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest request,
        io.grpc.stub.StreamObserver<net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCallTossPayConfirmMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCallTossPayMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                net.devh.boot.grpc.examples.lib.CallTossPayRequest,
                net.devh.boot.grpc.examples.lib.CallTossPayResponse>(
                  this, METHODID_CALL_TOSS_PAY)))
          .addMethod(
            getCallTossPayConfirmMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest,
                net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse>(
                  this, METHODID_CALL_TOSS_PAY_CONFIRM)))
          .build();
    }
  }

  /**
   */
  public static final class OrderServiceStub extends io.grpc.stub.AbstractAsyncStub<OrderServiceStub> {
    private OrderServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceStub(channel, callOptions);
    }

    /**
     */
    public void callTossPay(net.devh.boot.grpc.examples.lib.CallTossPayRequest request,
        io.grpc.stub.StreamObserver<net.devh.boot.grpc.examples.lib.CallTossPayResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallTossPayMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void callTossPayConfirm(net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest request,
        io.grpc.stub.StreamObserver<net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCallTossPayConfirmMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class OrderServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<OrderServiceBlockingStub> {
    private OrderServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.devh.boot.grpc.examples.lib.CallTossPayResponse callTossPay(net.devh.boot.grpc.examples.lib.CallTossPayRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCallTossPayMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse callTossPayConfirm(net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCallTossPayConfirmMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class OrderServiceFutureStub extends io.grpc.stub.AbstractFutureStub<OrderServiceFutureStub> {
    private OrderServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.devh.boot.grpc.examples.lib.CallTossPayResponse> callTossPay(
        net.devh.boot.grpc.examples.lib.CallTossPayRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCallTossPayMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse> callTossPayConfirm(
        net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCallTossPayConfirmMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CALL_TOSS_PAY = 0;
  private static final int METHODID_CALL_TOSS_PAY_CONFIRM = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OrderServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OrderServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CALL_TOSS_PAY:
          serviceImpl.callTossPay((net.devh.boot.grpc.examples.lib.CallTossPayRequest) request,
              (io.grpc.stub.StreamObserver<net.devh.boot.grpc.examples.lib.CallTossPayResponse>) responseObserver);
          break;
        case METHODID_CALL_TOSS_PAY_CONFIRM:
          serviceImpl.callTossPayConfirm((net.devh.boot.grpc.examples.lib.CallTossPayConfirmRequest) request,
              (io.grpc.stub.StreamObserver<net.devh.boot.grpc.examples.lib.CallTossPayConfirmResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class OrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OrderServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return net.devh.boot.grpc.examples.lib.FundingProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OrderService");
    }
  }

  private static final class OrderServiceFileDescriptorSupplier
      extends OrderServiceBaseDescriptorSupplier {
    OrderServiceFileDescriptorSupplier() {}
  }

  private static final class OrderServiceMethodDescriptorSupplier
      extends OrderServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OrderServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (OrderServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OrderServiceFileDescriptorSupplier())
              .addMethod(getCallTossPayMethod())
              .addMethod(getCallTossPayConfirmMethod())
              .build();
        }
      }
    }
    return result;
  }
}
