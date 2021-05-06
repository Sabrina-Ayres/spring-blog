package com.codeup.SpringBlog.controllers;

import com.codeup.SpringBlog.models.Post;
import com.codeup.SpringBlog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;

    public PostController(PostRepository postsDao) {
        this.postsDao = postsDao;
    }

    @GetMapping("/posts")
    public String getAllPosts(Model model) {
        List<Post> posts = postsDao.findAll();
        model.addAttribute("posts", posts);
        return "posts/index";
    }


    @GetMapping("/posts/{id}")
    public String getOnepost(@PathVariable long id, Model model) {
        Post post = new Post();
        model.addAttribute("id", id);
        model.addAttribute("post", post);
        model.addAttribute("post", postsDao.getOne(id));
        return "posts/show";
    }

//    @GetMapping("/posts/{id}")
//    public String getOnepost(@PathVariable long id, Model model) {
//        Post post = postsDao.getOne(id);
//        model.addAttribute("post", post);
//        return "posts/show";
//    }

    @GetMapping("/posts/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    public String saveNewPost(@ModelAttribute Post post) {
        postsDao.save(post);
        return "redirect:/posts";
    }

    @GetMapping("/posts/update/{id}")
    public String updatePostForm(@PathVariable("id") long id, Model model) {
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        return "/posts/update";
    }

    @PostMapping("/posts/update")
    public String saveUpdatedPost(@PathVariable("id") long id, @ModelAttribute Post post) {
        postsDao.save(post);
        return "redirect:/posts";
    }


    @GetMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") long id, Model model) {
        Post post = postsDao.getOne(id);
        postsDao.delete(post);
        return "redirect:/posts";
    }

//    @GetMapping("/posts/{id}/history")
//    public String showHistoryOfPost(@PathVariable("id") long id, Model model) {
//        String post = postsDao.getOne(id).getPostDetails().getHistoryOfPost();
//        model.addAttribute("post", post);
//        return "posts/{id}/history";
//    }

}
