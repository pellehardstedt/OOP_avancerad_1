package com.example.oop_avancerad_1.Service;

import com.example.oop_avancerad_1.Entity.Post;
import com.example.oop_avancerad_1.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    public void savePost(Post post){
        postRepository.save(post);
    }
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
}
