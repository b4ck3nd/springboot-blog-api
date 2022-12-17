package org.springboot.blog.api.dto.post;


import lombok.Data;

@Data
public class PostCreateRequestDto {

    private String title;
    private String body;
}
