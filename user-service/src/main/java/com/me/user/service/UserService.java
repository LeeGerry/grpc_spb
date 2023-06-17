package com.me.user.service;

import com.me.grpc.user.UserGenreUpdateRequest;
import com.me.grpc.user.UserResponse;
import com.me.grpc.user.UserSearchRequest;
import com.me.grpc.user.UserServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        super.getUserGenre(request, responseObserver);
    }

    @Override
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        super.updateUserGenre(request, responseObserver);
    }
}