package com.priya.payLoad;

import com.priya.entity.Post;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDtoResponse {
    private List<PostDTO> content;
    private Integer pageNo;
    private Integer pageSize;
    private Long totalElement;
    private int totalPages;
    private Boolean last;
}
