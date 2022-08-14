package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Comment;
import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByQuizAndDeletedDateNullOrderByCreatedDateDesc(Quiz quiz);

    Optional<Comment> findByIdAndMember_MemberPassword(Long id, String memberPassword);


}