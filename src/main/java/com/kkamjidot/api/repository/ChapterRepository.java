package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}