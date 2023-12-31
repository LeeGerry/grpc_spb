package com.me.user.service;

import com.me.grpc.common.Genre;
import com.me.grpc.user.UserGenreUpdateRequest;
import com.me.grpc.user.UserResponse;
import com.me.grpc.user.UserSearchRequest;
import com.me.grpc.user.UserServiceGrpc;
import com.me.user.repository.UserRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

@GrpcService
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    @Autowired
    private UserRepository repository;

    @Override
    public void getUserGenre(UserSearchRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        repository.findById(request.getLoginId()).ifPresent(user -> {
            builder.setName(user.getName()).setLoginId(user.getLogin()).setGenre(Genre.valueOf(user.getGenre()));
        });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void updateUserGenre(UserGenreUpdateRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse.Builder builder = UserResponse.newBuilder();
        repository.findById(request.getLoginId()).ifPresent(user -> {
            user.setGenre(request.getGenre().toString());
            builder.setName(user.getName()).setLoginId(user.getLogin()).setGenre(Genre.valueOf(user.getGenre().toUpperCase()));
        });
        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }
}