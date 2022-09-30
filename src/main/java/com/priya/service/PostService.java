package com.priya.service;

import com.priya.entity.Post;
import com.priya.payLoad.PostDTO;
import com.priya.payLoad.PostDtoResponse;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
    PostDtoResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
    PostDTO getPostById(Long id);
    PostDTO updatePost(PostDTO postDTO);
    void deletePostById(long id);

}
