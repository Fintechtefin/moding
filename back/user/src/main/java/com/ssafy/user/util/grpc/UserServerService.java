package com.ssafy.user.util.grpc;

import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.examples.lib.CurrentUserInfo;
import net.devh.boot.grpc.examples.lib.Empty;
import net.devh.boot.grpc.examples.lib.UserServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class UserServerService extends UserServiceGrpc.UserServiceImplBase {

    @Override
    public void getCurrentUserId(Empty request, StreamObserver<CurrentUserInfo> responseObserver) {
        // super.getCurrentUserId(request, responseObserver);
        // String userId = AuthenticationUtil.getCurrentUserSocialId();

        // CurrentUserInfo response =
        // CurrentUserInfo.newBuilder().setUsername(AuthenticationUtil.getCurrentUserSocialId()).build();
        CurrentUserInfo response = CurrentUserInfo.newBuilder().setUsername("1").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
