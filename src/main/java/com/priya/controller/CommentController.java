package com.priya.controller;

import com.priya.payLoad.CommentDTO;
import com.priya.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@Api(value = "CRUD Rest API for Comment resource")
@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private CommentService service;

    CommentController(CommentService service){
        this.service=service;
    }
    @ApiOperation(value = "Create Comment Rest API")
    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long postId, @Valid @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(service.createComment(postId,commentDTO), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Get Comment By Post Id Rest API")
    @GetMapping("/post/{postId}/comments")
    public List<CommentDTO> getCommentByPostId(@PathVariable(value = "postId") long postId){
        return service.getCommentByPostId(postId);
    }
    @ApiOperation(value = "Get Comment By Post Id Rest API")
    @GetMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId){
      return  new ResponseEntity<>(service.getCommentById(postId,commentId),HttpStatus.OK);

    }
    @ApiOperation(value = "Update Comment By Post&Comment Id Rest Api")
    @PutMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment
            (@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId"
            ) long commentId, @Valid @RequestBody CommentDTO dto)
    {
        return new ResponseEntity<>(service.updateComment(postId,commentId,dto),HttpStatus.OK);

    }
    @ApiOperation(value = "Delete Comment By Post&Comment Id Rest API")
    @DeleteMapping("/post/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "postId") long postId,
                                             @PathVariable(value = "commentId") long commentid)
    {
        service.deleteCommentById(postId,commentid);
        return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);
    }
}
