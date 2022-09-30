package com.priya.serviceImpl;

import com.priya.entity.Post;
import com.priya.exceptions.ResourceNotFoundException;
import com.priya.payLoad.PostDTO;
import com.priya.payLoad.PostDtoResponse;
import com.priya.repository.PostRepo;
import com.priya.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post=this.mapToEntity(postDTO);
        return mapToDTO(postRepo.save(post));

    }

    @Override
    public PostDtoResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
       Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> listOfPost = posts.getContent();
        List<PostDTO> content =  listOfPost.stream().map(post-> mapToDTO(post)).collect(Collectors.toList());
        PostDtoResponse response = new PostDtoResponse();
        response.setContent(content);
        response.setPageNo(posts.getNumber());
        response.setPageSize(posts.getSize());
        response.setTotalElement(posts.getTotalElements());
        response.setTotalPages(posts.getTotalPages());
        response.setLast(posts.isLast());
        return response;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "id", id));
        return this.mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO) {
        Post post = postRepo.findById(postDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postDTO.getId()));
        Post p = new Post();
        p.setId(postDTO.getId());
        p.setTitle(postDTO.getTitle());
        p.setDescription(postDTO.getDescription());
        p.setContent(postDTO.getContent());
        Post temp= postRepo.save(p);
        PostDTO dto = this.mapToDTO(temp);
        return dto;

    }

    @Override
    public void deletePostById(long id) {
       Post post =  postRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", id));
        postRepo.delete(post);
    }

    private PostDTO mapToDTO(Post post){
        PostDTO postDTO = modelMapper.map(post,PostDTO.class);
        /*PostDTO postDTO= new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setContent(post.getContent());*/
        return postDTO;
    }
    private Post mapToEntity(PostDTO postDTO){
        Post post= modelMapper.map(postDTO,Post.class);
       /* Post post = new Post();
        post.setId(postDTO.getId());
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());*/
        return post;
    }
}
