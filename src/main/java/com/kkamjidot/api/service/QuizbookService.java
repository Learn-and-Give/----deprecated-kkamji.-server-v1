package com.kkamjidot.api.service;

import com.kkamjidot.api.domain.Quizbook;
import com.kkamjidot.api.dto.response.QuizbookDetailResponseDto;
import com.kkamjidot.api.dto.response.QuizbookResponseDto;
import com.kkamjidot.api.repository.QuizbookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuizbookService {
    private final QuizbookRepository quizbookRepository;

    // 주차별 문제집 모음 조회
    public List<QuizbookResponseDto> findQuizbooksByWeek(int week) throws IllegalArgumentException{
        // 반환하기 위한 응답 객체 리스트 선언
        List<QuizbookResponseDto> responseDtos = new ArrayList<>();

        // DB에서 데이터 조회
        List<Quizbook> quizbooks = quizbookRepository.findByQuizbookWeek(week);

        // 404 오류 처리
        if (quizbooks.isEmpty()) {
            throw new IllegalArgumentException("존재하지 않는 주차별 문제집 모음입니다.");
        }

        // 주차별 문제집 정보 응답 객체 생성
        for (Quizbook quizbook : quizbooks) {
            responseDtos.add(new QuizbookResponseDto(quizbook));
        }
        return responseDtos;
    }

    public QuizbookDetailResponseDto findQuizbookDetailById(Long id) {
        Quizbook quizbook = quizbookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 문제집입니다."));
        return new QuizbookDetailResponseDto(quizbook);
    }
}
