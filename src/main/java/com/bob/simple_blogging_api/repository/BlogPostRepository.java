package com.bob.simple_blogging_api.repository;

import com.bob.simple_blogging_api.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

}
