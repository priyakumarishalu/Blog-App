package com.priya.controller;

import com.priya.payLoad.PostDTO;
import com.priya.payLoad.PostDTOv2;
import com.priya.payLoad.PostDtoResponse;
import com.priya.service.PostService;
import com.priya.utills.AppConstraints;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Api(value = "CRUD Rest API for Post resource")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @ApiOperation(value = "Create Post Rest API")
    @PostMapping(value = "/api/v1/posts")
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDto){
    return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }
    @ApiOperation(value = "Get All Post Rest API")
    @GetMapping("/api/v1/posts")
    public PostDtoResponse getAllPosts(@RequestParam(value = "pageNo", defaultValue = AppConstraints.DEFAULT_PAGE_NO, required = false) int pageNo,
                                       @RequestParam(value = "pageSize", defaultValue = AppConstraints.DEFAULT_PAGE_SIZE, required = false) int pageSize,
                                       @RequestParam(value = "sortBy", defaultValue = AppConstraints.DEFAULT_SORT_BY, required = false) String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = AppConstraints.DEFAULT_SORT_DIRECTION,required = false) String sortDir
                                     ){
        return postService.getAllPost(pageNo,pageSize,sortBy,sortDir);
    }

    @ApiOperation(value = "Get Post By ID V1 RestAPI")
    @GetMapping(value = "/api/v1/posts/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "id") long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }
    @ApiOperation(value = "Get Post By Id V2 Rest API")
    @GetMapping(value = "/api/v2/posts/{id}")
    public ResponseEntity<PostDTOv2> getPostByIdv2(@PathVariable(name = "id") long id){
        PostDTO dto=postService.getPostById(id);
        PostDTOv2 t= new PostDTOv2();
        t.setId(dto.getId());
        t.setDescription(dto.getDescription());
        t.setComments(dto.getComments());
        t.setContent(dto.getContent());
        t.setContent(dto.getContent());
        List<String> l = new ArrayList<>();
        l.add("spring");
        l.add("java");
        l.add("hibernate");
        t.setTags(l);
        return ResponseEntity.ok(t);
    }
    @ApiOperation(value = "Update Post Rest API")
    @PutMapping("/api/v1/posts")
    public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO postDTO){
        return new ResponseEntity<>(postService.updatePost(postDTO), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Post By Id Rest API")
    @DeleteMapping("/api/v1/posts/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post deleted successfully with id : "+ id, HttpStatus.OK);
    }

}
