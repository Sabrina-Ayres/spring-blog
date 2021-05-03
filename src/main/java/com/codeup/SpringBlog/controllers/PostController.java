package com.codeup.SpringBlog.controllers;

import com.codeup.SpringBlog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> posts = new ArrayList<>();
        Post post1 = new Post("My First Blog", "Some content goes here");
        Post post2 = new Post("My Second Blog", "Some content goes here");
        posts.add(post1);
        posts.add(post2);
        model.addAttribute("posts", posts);
        return "posts/index";
    }

//    public String posts() {
//        return "posts index page";
//    }

    @GetMapping("/posts/{id}")
    public String post(@PathVariable long id, Model model) {
        Post post = new Post("My First Blog", "Some content goes here");
        model.addAttribute("post", post);
        return "posts/show";
    }

//    public String post() {
//        return "view an individual post";
//    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createPostForm() {
        return "view the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createPost() {
        return "create a new post";
    }

}
