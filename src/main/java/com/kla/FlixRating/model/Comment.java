package com.kla.FlixRating.model;

import javax.persistence.*;

@Entity
@Table(name="comments")
//https://stackoverflow.com/questions/814474/jpa-not-saving-foreign-key-to-onetomany-relation
//https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String message;
    private Long rating;

    @ManyToOne
    @JoinColumn(name="flix_id", referencedColumnName = "id")
    private Flix flix;

    public Comment() {}

    public Comment(String username, String message, Long rating){
        this.username = username;
        this.message = message;
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Flix getFlix() {
        return flix;
    }

    public void setFlix(Flix flix) {
        this.flix = flix;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }
}
