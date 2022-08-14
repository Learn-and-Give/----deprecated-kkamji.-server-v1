package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.Quizbook;
import com.kkamjidot.api.dto.request.CreateQuizRequestDto;
import com.kkamjidot.api.dto.request.UpdateAnswerRequestDto;
import com.kkamjidot.api.dto.response.CreateQuizResponseDto;
import com.kkamjidot.api.exception.UnauthorizedException;
import com.kkamjidot.api.repository.QuizRepository;
import com.kkamjidot.api.repository.QuizbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    // 문제 모음 반환
    public List<Quiz> findQuizzes(Long quizbookId) throws NoSuchElementException {
        List<Quiz> quizzes = quizRepository.findAllByQuizbookIdOrderByQuizNumber(quizbookId);

        if (quizzes.isEmpty()) throw new NoSuchElementException("존재하지 않는 문제입니다.");

        return quizzes;
    }

    public Quiz findOne(Long quizId) throws NoSuchElementException {
        return quizRepository.findById(quizId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 문제입니다."));
    }

    public Quiz findOne(Long quizbookId, Integer quizNumber) throws NoSuchElementException {
        return quizRepository.findByQuizbookIdAndQuizNumber(quizbookId, quizNumber).orElseThrow(() -> new NoSuchElementException("존재하지 않는 문제입니다."));
    }

    @Transactional
    public Quiz updateOne(Long quizId, Member member, UpdateAnswerRequestDto requestDto) throws UnauthorizedException {
        Quiz quiz = findOne(quizId);
        if (!quiz.getIsMine(member)) throw new UnauthorizedException("자신의 문제가 아닙니다.");
        return quiz.update(requestDto);
    }

    @Transactional
    public Long createOne(CreateQuizRequestDto responseDto, Quizbook quizbook) {
        Quiz quiz = Quiz.of(responseDto, quizbook);
        return quizRepository.save(quiz).getId();
    }
}
