package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Comment;
import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.dto.request.CommentRequestDto;
import com.kkamjidot.api.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public Comment saveOne(CommentRequestDto requestDto, Quiz quiz, Member member) {
        Comment comment = new Comment().builder()
                .cmtContent(requestDto.getCmtContent())
                .quiz(quiz)
                .member(member)
                .build();
        return commentRepository.save(comment);
    }

    public List<Comment> findComments(Quiz quiz) {
        return commentRepository.findByQuizAndDeletedDateNullOrderByCreatedDateDesc(quiz);
    }

    @Transactional
    public void deleteOne(Long commentId, String code) throws NoSuchElementException {
        commentRepository.findByIdAndMember_MemberPassword(commentId, code).orElseThrow(() -> new NoSuchElementException("존재하지 않는 의견입니다.")).delete();
    }
}
