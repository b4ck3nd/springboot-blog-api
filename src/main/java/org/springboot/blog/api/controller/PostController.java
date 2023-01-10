package org.springboot.blog.api.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springboot.blog.api.dto.post.PostCreateRequestDto;
import org.springboot.blog.api.dto.post.PostResponseDto;
import org.springboot.blog.api.dto.post.PostUpdateDto;
import org.springboot.blog.api.service.post.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/")
    public ResponseEntity<List<PostResponseDto>> findAll() {
        return new ResponseEntity<>(postService.findAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> createPost(@RequestBody @Valid PostCreateRequestDto dto) {
        postService.add(dto);
        return new ResponseEntity<>("added",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable long id) {
        PostResponseDto postResponseDto = postService.findById(id);
        return new ResponseEntity<>(postResponseDto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/{id}/update")
    public ResponseEntity<PostResponseDto> updateById(@PathVariable long id, @RequestBody @Valid PostUpdateDto dto) {
        PostResponseDto postResponseDto = postService.updateById(id, dto);
        return new ResponseEntity<>(postResponseDto,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        postService.deleteById(id);
        return new ResponseEntity<>("deleted",HttpStatus.OK);
    }

    //public List<PostResponseDto> findAllPostsAsPageableAndSortable(int pageNo, int pageSize, String sortBy, String sortDirection) {
    @GetMapping("/search")
    public ResponseEntity<List<PostResponseDto>> findAllPostsAsPageableAndSortable(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue ="id",required = false) String sortBy,
            @RequestParam(value = "sortDirection",defaultValue = "asc",required = false) String sortDirection
    ) {
        List<PostResponseDto> postResponseDtoList = postService.findAllPostsAsPageableAndSortable(pageNo, pageSize, sortBy, sortDirection);
        return new ResponseEntity<>(postResponseDtoList,HttpStatus.OK);
    }



}
