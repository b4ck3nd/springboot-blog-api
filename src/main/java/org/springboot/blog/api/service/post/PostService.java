package org.springboot.blog.api.service.post;

import org.springboot.blog.api.dto.post.PostCreateRequestDto;
import org.springboot.blog.api.dto.post.PostResponseDto;
import org.springboot.blog.api.dto.post.PostUpdateDto;

import java.util.List;

public interface PostService {
    void add(PostCreateRequestDto dto);
    List<PostResponseDto> findAll();
    PostResponseDto findById(long id);
    PostResponseDto updateById(long id, PostUpdateDto dto);
    void deleteById(long id);

    List<PostResponseDto> findAllPostsAsPageableAndSortable(int pageNo,int pageSize,String sortBy,String sortDir);



}
