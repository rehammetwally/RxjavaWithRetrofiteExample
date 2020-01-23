package com.rehammetwally.rxjavawithretrofiteexample.retrofit;

import com.rehammetwally.rxjavawithretrofiteexample.model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface API {
    @GET("/posts")
    Observable<List<Post>> getPosts();

}
