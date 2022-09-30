package com.priya.serviceImpl;

import com.priya.entity.Comment;
import com.priya.entity.Post;
import com.priya.exceptions.BlogAPIException;
import com.priya.exceptions.ResourceNotFoundException;
import com.priya.payLoad.CommentDTO;
import com.priya.repository.CommentRepo;
import com.priya.repository.PostRepo;
import com.priya.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(long postId, CommentDTO commentDTO) {
        Comment comment = this.mapToEntity(commentDTO);
       Post post= this.getPost(postId);
       comment.setPost(post);
       Comment tempComment = commentRepo.save(comment);
       return mapToDto(tempComment);
    }

    @Override
    public List<CommentDTO> getCommentByPostId(long postId) {
        return commentRepo.findByPostId(postId).stream().map(comment -> this.mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
       Post post = this.getPost(postId);
       Comment comment = this.getComment(commentId);
       if(!comment.getPost().getId().equals(post.getId())){
           throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belongs to post ");
       }
       return this.mapToDto(comment);
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO dto) {
       Post post = this.getPost(postId);
       Comment comment = this.getComment(commentId);
       if(!comment.getPost().getId().equals(post.getId())){
           throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belog to post");
       }
       comment.setName(dto.getName());
       comment.setEmail(dto.getEmail());
       comment.setBody(dto.getBody());
       Comment updatedComment = commentRepo.save(comment);
        return this.mapToDto(updatedComment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Comment comment= this.getComment(commentId);
        Post post = this.getPost(postId);
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Comment does not belog to post");
        }
        commentRepo.deleteById(commentId);

    }

    private Comment mapToEntity(CommentDTO commentDTO){
        Comment comment = modelMapper.map(commentDTO,Comment.class);
        /*Comment comment= new Comment();
        comment.setId(commentDTO.getId());
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setBody(commentDTO.getBody());*/
        return comment;
    }
    private CommentDTO mapToDto(Comment comment){
        CommentDTO dto = modelMapper.map(comment,CommentDTO.class);
        /*CommentDTO dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());*/
        return dto;
    }
    private Post getPost(long postId){
        return postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Id", postId));
    }
    private Comment getComment(long commentId){
        return commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","Id",commentId));
    }
}
