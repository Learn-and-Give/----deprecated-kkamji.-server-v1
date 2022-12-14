package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Member;
import com.kkamjidot.api.domain.Readable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadableRepository extends JpaRepository<Readable, Long> {
    boolean existsByChapterIdAndMemberAndIsReadable(Long chapterId, Member member, boolean isReadable);   // 열람 가능 여부 검사
}