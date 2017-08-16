package com.kla.FlixRating.model;

import javax.persistence.*;
import javax.validation.constraints.Max;

@Entity
@Table(name="flix")
public class Flix {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    private String name;
    private String description;
    @Column(name="avgrating")
    @Max(5)
    private Float avgRating;
    private String genre;

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
}
