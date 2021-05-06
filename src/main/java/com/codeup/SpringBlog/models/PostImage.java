package com.codeup.SpringBlog.models;


import javax.persistence.*;

@Entity
@Table(name = "post_image")
public class PostImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String image_title;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(length = 255)
    private String topicDescription;

    @ManyToOne
    private Post post;

    public PostImage(long id, String image_title, String url, String topicDescription) {
        this.id = id;
        this.image_title = image_title;
        this.url = url;
        this.topicDescription = topicDescription;
    }

    public PostImage(String image_title, String url, String topicDescription, Post post) {
        this.image_title = image_title;
        this.url = url;
        this.topicDescription = topicDescription;
        this.post = post;
    }

    public PostImage() {

    }

}
