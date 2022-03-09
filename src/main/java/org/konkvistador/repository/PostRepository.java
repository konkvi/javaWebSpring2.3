package org.konkvistador.repository;

import org.konkvistador.model.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class PostRepository {
    private ConcurrentMap<Long, Post> posts;

    public PostRepository() {
        this.posts = new ConcurrentHashMap<>();
    }

    public ConcurrentMap<Long, Post> all() {
        return posts;
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        long postId = post.getId();
        Post newPost = new Post(postId, post.getContent());
        if (posts.containsKey(postId)){
            replacePost(postId, post);
        } else {
            posts.put(postId, newPost);
            postId++;
        }
        return newPost;
    }

    // если такой id уже существует
    public void replacePost (long id, Post post) {
        posts.replace(id, post);
    }

    public Optional<Post> removeById(long id) {
        return Optional.of(posts.remove(id));
    }
}