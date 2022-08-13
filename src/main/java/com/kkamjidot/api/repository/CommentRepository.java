package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Comment;
import com.kkamjidot.api.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByQuizAndDeletedDateNullOrderByCreatedDateDesc(Quiz quiz);
}