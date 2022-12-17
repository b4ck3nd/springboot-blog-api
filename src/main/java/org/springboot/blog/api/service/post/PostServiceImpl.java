package org.springboot.blog.api.service.post;


import lombok.RequiredArgsConstructor;
import org.springboot.blog.api.dto.post.PostCreateRequestDto;
import org.springboot.blog.api.dto.post.PostResponseDto;
import org.springboot.blog.api.dto.post.PostUpdateDto;
import org.springboot.blog.api.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    @Override
    public void add(PostCreateRequestDto dto) {

    }

    @Override
    public List<PostResponseDto> findAll(int pageNo, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public PostResponseDto findById(long id) {
        return null;
    }

    @Override
    public PostResponseDto updateById(long id, PostUpdateDto dto) {
        return null;
    }

    @Override
    public void deleteById(long id) {

    }
}
