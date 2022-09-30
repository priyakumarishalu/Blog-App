package com.priya.payLoad;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Api(value = "Comment model information")
@Data
public class CommentDTO {
    @ApiModelProperty(value = "Comment id")
    private Long id;
    @ApiModelProperty(value = "Comment name")
    @NotEmpty(message = "Name should not be null or empty")
    private String name;
    @ApiModelProperty(value = "Comment email")
    @NotEmpty(message = "Email should not be null or empty")
    @Email
    private String email;
    @ApiModelProperty(value = "Comment body")
    @NotEmpty(message = "Body should not be null or empty")
    @Size(min = 10, message ="Body should containt at least 10 character" )
    private String body;
}
