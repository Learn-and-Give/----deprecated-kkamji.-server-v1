package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.exception.UnauthorizedException;
import com.kkamjidot.api.repository.ReadableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReadableService {
    private final ReadableRepository readableRepository;

    public void isReadable(Long chapterId, Member member) throws UnauthorizedException {
        if (!readableRepository.existsByChapterIdAndMemberAndIsReadable(chapterId, member, true)) throw new UnauthorizedException("열람 가능한 챕터가 아닙니다.");
    }
}
