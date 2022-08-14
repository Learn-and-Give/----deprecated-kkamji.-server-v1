package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.FileDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileDomain, Long> {
}