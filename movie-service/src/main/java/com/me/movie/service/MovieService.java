package com.me.movie.service;

import com.me.grpc.movie.MovieDto;
import com.me.grpc.movie.MovieSearchRequest;
import com.me.grpc.movie.MovieSearchResponse;
import com.me.grpc.movie.MovieServiceGrpc;
import com.me.movie.repository.MovieRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class MovieService extends MovieServiceGrpc.MovieServiceImplBase {
    @Autowired
    private MovieRepository repository;

    @Override
    public void getMovies(MovieSearchRequest request, StreamObserver<MovieSearchResponse> responseObserver) {
        List<MovieDto> movieDtoList = repository.getMovieByGenreOrderByYearDesc(request.getGenre().toString())
                .stream().map(movie ->
                        MovieDto.newBuilder().setTitle(movie.getTitle())
                                .setYear(movie.getYear())
                                .setRating(movie.getRating())
                                .build()
                ).toList();
        MovieSearchResponse response = MovieSearchResponse.newBuilder().addAllMovies(movieDtoList).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
