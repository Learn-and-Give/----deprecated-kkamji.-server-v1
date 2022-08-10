package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}