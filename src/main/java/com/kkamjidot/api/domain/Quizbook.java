package com.kkamjidot.api.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "quizbook")
public class Quizbook {
    @Id
    @Column(name = "quizbook_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quizbook_title")
    private String quizbookTitle;

    @Column(name = "quizbook_description", columnDefinition = "TEXT")
    private String quizbookDescription;

    @Column(name = "quizbook_week")
    private Integer quizbookWeek;

    @Column(name = "quizbook_is_submit")
    private Boolean quizbookIsSubmit;

    @Column(name = "quizbook_submit_date")
    private Instant quizbookSubmitDate;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "challenge_id")
    private String challengeId;

    @OneToMany(mappedBy = "quizbook", fetch = FetchType.LAZY)
    private List<Quiz> quizs = new ArrayList<>();

    public int getNumberOfQuizs() {
        return quizs.size();
    }
}
