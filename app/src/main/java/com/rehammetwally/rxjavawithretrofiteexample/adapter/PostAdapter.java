package com.rehammetwally.rxjavawithretrofiteexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rehammetwally.rxjavawithretrofiteexample.R;
import com.rehammetwally.rxjavawithretrofiteexample.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(context).inflate(R.layout.post_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.PostViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.postTitleTv.setText(post.title);
        holder.postBodyTv.setText(post.body);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        TextView postTitleTv, postBodyTv;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            postTitleTv = itemView.findViewById(R.id.postTitleTv);
            postBodyTv = itemView.findViewById(R.id.postBodyTv);
        }
    }
}
