package com.me.aggregator.service;

import com.me.aggregator.dto.RecommendedMovie;
import com.me.aggregator.dto.UserGenre;
import com.me.grpc.common.Genre;
import com.me.grpc.movie.MovieSearchRequest;
import com.me.grpc.movie.MovieSearchResponse;
import com.me.grpc.movie.MovieServiceGrpc;
import com.me.grpc.user.UserGenreUpdateRequest;
import com.me.grpc.user.UserResponse;
import com.me.grpc.user.UserSearchRequest;
import com.me.grpc.user.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMovieService {
    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userStub;

    @GrpcClient("movie-service")
    private MovieServiceGrpc.MovieServiceBlockingStub movieStub;

    public List<RecommendedMovie> getUserMovieSuggestions(String loginId) {
        UserSearchRequest userSearchRequest = UserSearchRequest.newBuilder().setLoginId(loginId).build();
        UserResponse userResponse = userStub.getUserGenre(userSearchRequest);
        MovieSearchRequest movieSearchRequest = MovieSearchRequest.newBuilder().setGenre(userResponse.getGenre()).build();
        MovieSearchResponse movieSearchResponse = movieStub.getMovies(movieSearchRequest);
        return movieSearchResponse.getMoviesList()
                .stream().map(movieDto ->
                        new RecommendedMovie(movieDto.getTitle(), movieDto.getYear(), movieDto.getRating())
                ).toList();
    }

    public void setUserGenre(UserGenre userGenre) {
        UserGenreUpdateRequest userGenreUpdateRequest = UserGenreUpdateRequest.newBuilder()
                .setLoginId(userGenre.getLoginId())
                .setGenre(Genre.valueOf(userGenre.getGenre().toUpperCase()))
                .build();
        UserResponse userResponse = userStub.updateUserGenre(userGenreUpdateRequest);
    }
}
