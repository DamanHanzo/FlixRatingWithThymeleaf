package com.kla.FlixRating.model;

import javax.persistence.*;

@Entity
@Table(name="comments")
//https://stackoverflow.com/questions/814474/jpa-not-saving-foreign-key-to-onetomany-relation
//https://hellokoding.com/jpa-one-to-many-relationship-mapping-example-with-spring-boot-maven-and-mysql/
public class Comment {
    private int id;
    private String username;
    private String message;

//    @Column(name="flix_id")
//    private Long flixId;

    private Flix flix;

    public Comment() {}

    public Comment(String username, String message){
        this.username = username;
        this.message = message;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @ManyToOne
    @JoinColumn(name="flix_id", referencedColumnName = "id")
    public Flix getFlix() {
        return flix;
    }

    public void setFlix(Flix flix) {
        this.flix = flix;
    }

//    public long getFlixId() {
//        return this.flixId = flix.getId();
//    }
//
//    public void setFlixId(Long flixId) {
//        this.flixId = flixId;
//    }
}
