package com.training.kean.mvpsampleapp.services;

import com.training.kean.mvpsampleapp.models.Post;
import com.training.kean.mvpsampleapp.models.Comment;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

import retrofit.http.GET;

/**
 * Created by kean on 21/08/2017.
 */

public class ForumService {
    private static final String FORUM_SERVER_URL = "http://jsonplaceholder.typicode.com";
    private ForumApi mForumApi;

    public ForumService() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/json");
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(FORUM_SERVER_URL)
                .setRequestInterceptor(requestInterceptor)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        mForumApi = restAdapter.create(ForumApi.class);

    }

    public ForumApi getApi() {
        return mForumApi;
    }

    public interface ForumApi {

        @GET("/posts")
        public Observable<List<Post>>
            getPosts();

        @GET("/posts/{id}")
        public Observable<Post>
            getPost(@Path("id") int postId);

        @GET("/comments")
        public Observable<List<Comment>>
        getComments(@Query("postId") int postId);

        @GET("/posts")
        public Observable<List<Post>>
        postPost(Post post);
    }

}
