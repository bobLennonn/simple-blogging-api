package com.bob.simple_blogging_api.controller;

import com.bob.simple_blogging_api.entity.BlogPost;
import com.bob.simple_blogging_api.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository repository;

    @GetMapping
    public List<BlogPost> getAllBlogPosts(){
        return repository.findAll();
    }

    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost post){
        return repository.save(post);
    }

    @GetMapping("/{id}")
    public BlogPost getPostById(@PathVariable Long id){
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post não encontrado com id: " + id));
    }

    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost updatedPost){
        return repository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());
                    post.setAuthor(updatedPost.getAuthor());
                    post.setDate(updatedPost.getDate());
                    return repository.save(post);
                })
                .orElseThrow(() -> new RuntimeException("Post não encontrado com id: " + id));
        
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Post não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
