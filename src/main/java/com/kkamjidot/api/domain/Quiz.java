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
    private String quizTitle;

    @Lob
    @Column(name = "quiz_content", nullable = false)
    private String quizContent;

    @Lob
    @Column(name = "quiz_answer", nullable = false)
    private String quizAnswer;

    @Lob
    @Column(name = "quiz_explanation")
    private String quizExplanation;

    @Lob
    @Column(name = "quiz_source")
    private String quizSource;

    @Column(name = "quiz_category", length = 50)
    private String quizCategory;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizbook_id")
    private Quizbook quizbook;
}