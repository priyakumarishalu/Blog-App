package com.priya.payLoad;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
@Api(value = "Post model information")
@Data
public class PostDTO {
    @ApiModelProperty(value = "Post id")
    private Long id;
    @ApiModelProperty(value = "Post title")
    @NotEmpty
    @Size(min = 2,message = "Post title at-least have 2 character ")
    private String title;
    @ApiModelProperty(value = "Post description")
    @NotEmpty
    @Size(min = 2,message = "Post description should have at least 10 character ")
    private String description;
    @ApiModelProperty(value = "Post content")
    @NotEmpty
    private String content;
    @ApiModelProperty(value = "Post comments")
    private Set<CommentDTO> comments;
}
