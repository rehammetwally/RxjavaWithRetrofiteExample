package com.rehammetwally.rxjavawithretrofiteexample;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.rehammetwally.rxjavawithretrofiteexample.adapter.PostAdapter;
import com.rehammetwally.rxjavawithretrofiteexample.model.Post;
import com.rehammetwally.rxjavawithretrofiteexample.retrofit.API;
import com.rehammetwally.rxjavawithretrofiteexample.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    API api;
    RecyclerView postsRv;
    LinearLayoutManager linearLayoutManager;
    CompositeDisposable compositeDisposable=new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Retrofit retrofit = RetrofitClient.getInstance();
        api = retrofit.create(API.class);
        postsRv = findViewById(R.id.postsRv);
        linearLayoutManager = new LinearLayoutManager(this);
        postsRv.setHasFixedSize(true);
        postsRv.setLayoutManager(linearLayoutManager);
        compositeDisposable.add(
                api.getPosts()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<List<Post>>() {
                            @Override
                            public void accept(List<Post> posts) throws Exception {

                                PostAdapter postAdapter = new PostAdapter(MainActivity.this, posts);
                                postsRv.setAdapter(postAdapter);
                            }
                        })
        );

    }

    @Override
    protected void onStop() {
        super.onStop();
        compositeDisposable.clear();
    }
}
