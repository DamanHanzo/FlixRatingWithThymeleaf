package com.kla.FlixRating.model;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    private int id;
    private String username;
    private String comment;

    private Flix flix;

    public Comment() {}

    public Comment(String username, String comment){
//        this.id = id;
        this.username = username;
        this.comment = comment;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ManyToOne
    @JoinColumn(name="flix_id")
    public Flix getFlix() {
        return flix;
    }

    public void setFlix(Flix flix) {
        this.flix = flix;
    }
}
