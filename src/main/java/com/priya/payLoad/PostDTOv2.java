package com.priya.payLoad;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PostDTOv2 {
    private long id;
    private String title;
    private String description;
    private String content;
    private Set<CommentDTO> comments;
    private List<String> tags;
}
