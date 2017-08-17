package com.kla.FlixRating.repository;

import com.kla.FlixRating.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> getCommentByFlix_Id(Long id);
}
