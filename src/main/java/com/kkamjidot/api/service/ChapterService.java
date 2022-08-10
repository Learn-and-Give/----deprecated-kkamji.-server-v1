package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Chapter;
import com.kkamjidot.api.repository.ChapterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;

    public List<Chapter> findAll() throws NoSuchElementException {
        List<Chapter> chapters = chapterRepository.findAll();
        if (chapters.isEmpty()) throw new NoSuchElementException("존재하지 않는 챕터입니다.");
        return chapters;
    }

    public Chapter findOne(Long chapterId) throws NoSuchElementException {
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 챕터입니다."));

        return chapter;
    }
}
