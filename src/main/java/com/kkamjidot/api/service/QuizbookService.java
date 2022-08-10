package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Quizbook;
import com.kkamjidot.api.repository.QuizbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuizbookService {
    private final QuizbookRepository quizbookRepository;

    // 주차별 문제집 모음 조회
    public List<Quizbook> findQuizbooks(Long chapterId) throws NoSuchElementException{
        // DB에서 데이터 조회
        List<Quizbook> quizbooks = quizbookRepository.findAllByChapterId(chapterId);

        // 404 오류 처리
        if (quizbooks.isEmpty()) throw new NoSuchElementException("존재하지 않는 챕터별 문제집 모음입니다.");

        return quizbooks;
    }

    public Quizbook findOne(Long quizbookId, Long chapterId) throws NoSuchElementException {
        Quizbook quizbook = quizbookRepository.findById(quizbookId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 문제집입니다."));

        quizbook.verifyApi(quizbookId, chapterId);

        return quizbook;
    }
}
