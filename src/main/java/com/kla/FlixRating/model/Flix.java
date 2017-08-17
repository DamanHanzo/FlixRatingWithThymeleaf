package com.kla.FlixRating.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="flix")
public class Flix {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @Column(name="avgrating")
    @Max(value = 5, message = "Cannot be more than 5")
    private Float avgRating;
    private String genre;
    private Long raters;

    @OneToMany(mappedBy = "flix", cascade = CascadeType.PERSIST)
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public  String toString(){
        return "id="+id+", name="+name+", description="+description+", avgRating="+avgRating;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment){
        if(comment != null){
            if(comments == null){
                comments = new ArrayList<Comment>();
            }
            comments.add(comment);
            comment.setFlix(this);
        }
    }

    public Long getRaters() {
        return raters;
    }

    public void setRaters(Long raters) {
        this.raters = raters;
    }
}
