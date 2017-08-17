package com.kla.FlixRating.repository;

import com.kla.FlixRating.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//https://ankushs92.github.io/tutorial/2016/05/03/pagination-with-spring-boot.html
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> getCommentByFlix_Id(Long id);
}
