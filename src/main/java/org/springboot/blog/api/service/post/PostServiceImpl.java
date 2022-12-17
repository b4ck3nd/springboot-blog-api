package org.springboot.blog.api.service.post;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springboot.blog.api.dto.post.PostCreateRequestDto;
import org.springboot.blog.api.dto.post.PostResponseDto;
import org.springboot.blog.api.dto.post.PostUpdateDto;
import org.springboot.blog.api.model.Post;
import org.springboot.blog.api.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModelMapper mapper;

    @Override
    public void add(PostCreateRequestDto dto) {
        postRepository.save(mapper.map(dto,Post.class));
    }


    @Override
    public List<PostResponseDto> findAll() {
        List<PostResponseDto> responseDtos=new ArrayList<>();
        //Collection<PostResponseDto> collection=new ArrayList<>();
        for (Post post : postRepository.findAll()) {
            responseDtos.add(mapper.map(post,PostResponseDto.class));
            //collection.add(mapper.map(post,PostResponseDto.class));

        }
        return responseDtos;
    }

    @Override
    public PostResponseDto findById(long id) {
        Optional<Post> by = postRepository.findById(id);
        if (by.isPresent()) {
            return mapper.map(by.get(),PostResponseDto.class);
        }
        else {
            throw new RuntimeException("error");
        }
    }

    @Override
    public PostResponseDto updateById(long id, PostUpdateDto dto) {
        if (postRepository.existsById(id)) {
            Post post = postRepository.findById(id).get();
            post.setBody(dto.getBody());
            post.setTitle(dto.getTitle());
            postRepository.save(post);
            return mapper.map(post,PostResponseDto.class);
        }
        else {
            throw new RuntimeException("error");
        }
    }

    @Override
    public void deleteById(long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("error");
        }

    }
}
