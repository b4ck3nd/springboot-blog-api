package org.springboot.blog.api.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name",nullable = false)
    private String name;

    @Column(name = "email",nullable = false)
    @Email
    private String email;

    @Column(name = "body",nullable = false)
    @Size(min = 10,max = 255)
    private String body;

    @ManyToOne()
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

}
