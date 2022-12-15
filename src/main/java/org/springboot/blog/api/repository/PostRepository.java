package org.springboot.blog.api.repository;

import org.springboot.blog.api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
}
