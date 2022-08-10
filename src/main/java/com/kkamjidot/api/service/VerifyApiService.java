package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Quiz;
import com.kkamjidot.api.domain.Quizbook;
import com.kkamjidot.api.repository.ChapterRepository;
import com.kkamjidot.api.repository.QuizRepository;
import com.kkamjidot.api.repository.QuizbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class VerifyApiService {
    private final ChapterRepository chapterRepository;
    private final QuizbookRepository quizbookRepository;
    private final QuizRepository quizRepository;

    public void verifyApiQuizbookToChapter(Quizbook quizbook, Long chapterId) throws NoSuchElementException {
        Map<String, Long> map = quizbook.verifyApi();
        if (!map.get("chapterId").equals(chapterId)) {
            throw new NoSuchElementException("존재하지 않는 문제집입니다.");
        }
    };

    public void verifyApiQuizToChapter(Quiz quiz, Long quizbookId, Long chapterId) throws NoSuchElementException {
        Map<String, Long> map = quiz.verifyApi();

        System.out.println("map = " + map);

        if (!map.get("quizbookId").equals(quizbookId) ||
                !map.get("chapterId").equals(chapterId)) {
            throw new NoSuchElementException("존재하지 않는 문제입니다.");
        }
    };
}
