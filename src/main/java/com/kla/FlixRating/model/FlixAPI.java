package com.kla.FlixRating.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FlixAPI {
    private String url;
    private String name;
    private Map<String, Float> rating;
    private List<String> genres;

    public FlixAPI() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Map<String, Float> getRating() {
        return rating;
    }

    public void setRating(Map<String, Float> rating) {
        this.rating = rating;
    }
}
