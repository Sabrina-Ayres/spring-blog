package com.codeup.SpringBlog.controllers;

import com.codeup.SpringBlog.models.Post;
import com.codeup.SpringBlog.models.User;
import com.codeup.SpringBlog.repositories.PostRepository;
import com.codeup.SpringBlog.repositories.UserRepository;
import com.codeup.SpringBlog.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postsDao, UserRepository userDao, EmailService emailService) {
        this.postsDao = postsDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
//        model.addAttribute("id", id);
//        model.addAttribute("post", post);
        model.addAttribute("post", postsDao.getOne(id));
        return "/posts/show";
    }


    @GetMapping("/posts/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "/posts/create";
    }


        @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post) {
        User author = userDao.getOne(1L);
        post.setUser(author);
        Post savedPost = postsDao.save(post);
        emailService.prepareAndSend(post, "title", "body");
        return "redirect:/posts/" + savedPost.getId();
    }

    //    @PostMapping("/posts/create")
//    public String saveNewPost(@ModelAttribute Post post) {
//        postsDao.save(post);
//        return "redirect:/posts";
//    }


    @GetMapping("/posts/edit/{id}")
    public String editPostForm(@PathVariable("id") long id, Model model) {
        Post post = postsDao.getOne(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/posts/edit/{id}")
    public String saveUpdatedPost(@ModelAttribute Post post) {
        postsDao.save(post);
        return "redirect:/posts";
    }


    @PostMapping("/posts/delete/{id}")
    public String deletePost(@PathVariable("id") long id, Model model) {
        Post post = postsDao.getOne(id);
        postsDao.delete(post);
        return "redirect:/posts";
    }


}
