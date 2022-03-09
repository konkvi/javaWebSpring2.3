package org.konkvistador.service;

import org.konkvistador.exeption.NotFoundException;
import org.konkvistador.model.Post;
import org.konkvistador.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public ConcurrentMap<Long,Post> all() { return repository.all(); }

    public Post getById(long id) { return repository.getById(id).orElseThrow(NotFoundException::new); }

    public Post save(Post post) { return repository.save(post); }

    public Post removeById(long id) { return repository.removeById(id).orElseThrow(NotFoundException::new); }
}