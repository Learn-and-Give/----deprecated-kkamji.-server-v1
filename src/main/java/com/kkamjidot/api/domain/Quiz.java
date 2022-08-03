package com.kkamjidot.api.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id", nullable = false)
    private Long id;

    @Column(name = "quiz_title", length = 50)
    private String quizTitle;       // 제목

    @Lob
    @Column(name = "quiz_content", nullable = false)
    private String quizContent;     // 문제

    @Lob
    @Column(name = "quiz_answer", nullable = false)
    private String quizAnswer;      // 정정

    @Lob
    @Column(name = "quiz_explanation")
    private String quizExplanation; // 해설

    @Lob
    @Column(name = "quiz_source")
    private String quizSource;      // 출처

    @Column(name = "quiz_category", length = 50)
    private String quizCategory;    // 유형(BASIC, ADVANCED, EXAM)

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizbook_id")
    private Quizbook quizbook;
}