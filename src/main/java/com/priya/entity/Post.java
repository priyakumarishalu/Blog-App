package com.priya.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String title;

    private String description;

    private String content;
    @OneToMany(mappedBy = "post",cascade =CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();


}
