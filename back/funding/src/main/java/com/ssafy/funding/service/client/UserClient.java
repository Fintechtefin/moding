package com.ssafy.funding.service.client;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.examples.lib.CurrentUserInfo;
import net.devh.boot.grpc.examples.lib.Empty;
import net.devh.boot.grpc.examples.lib.UserServiceGrpc;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserClient {

    @GrpcClient("user")
    UserServiceGrpc.UserServiceBlockingStub userStub;

    public CurrentUserInfo findUserInfo() {
        return userStub.getCurrentUserId(Empty.newBuilder().build());
    }
}
