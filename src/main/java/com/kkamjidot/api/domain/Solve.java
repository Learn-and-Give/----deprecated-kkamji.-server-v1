package com.kkamjidot.api.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "solve")
public class Solve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`solve _id`", nullable = false)
    private Long id;

    @Lob
    @Column(name = "solve_submitted_answer")
    private String solveSubmittedAnswer;

    @Column(name = "solve_is_correct")
    private Boolean solveIsCorrect;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}