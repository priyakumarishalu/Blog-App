package com.priya.service;

import com.priya.entity.Comment;
import com.priya.payLoad.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(long id, CommentDTO commentDTO);
    List<CommentDTO> getCommentByPostId(long postId);
    CommentDTO getCommentById(long postId, long commentId);
    CommentDTO updateComment(long postId, long commentId, CommentDTO dto);
    void deleteCommentById(long postId, long commentId);

}
