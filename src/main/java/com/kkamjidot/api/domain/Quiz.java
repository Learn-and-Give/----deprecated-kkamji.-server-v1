package com.kkamjidot.api.domain;

import com.kkamjidot.api.domain.enumerate.QuizCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id", nullable = false)
    private Long id;

    @Column(name = "quiz_title", length = 50)
    private String quizTitle;

    @Column(name = "quiz_content", nullable = false, columnDefinition = "TEXT")
    private String quizContent;

    @Column(name = "quiz_answer", nullable = false, columnDefinition = "TEXT")
    private String quizAnswer;

    @Column(name = "quiz_explanation", columnDefinition = "TEXT")
    private String quizExplanation;

    @Column(name = "quiz_source", columnDefinition = "TEXT")
    private String quizSource;

    @Enumerated(EnumType.STRING)
    @Column(name = "quiz_category", length = 50, columnDefinition = "varchar(50) default 'BASIC'")
    private QuizCategory quizCategory;

    @Column(name = "quiz_number", nullable = false)
    private Integer quizNumber;

    @Column(name = "quiz_deleted_date")
    private Instant quizDeletedDate;

    @Column(name = "created_date", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Instant createdDate;

    @Column(name = "modified_date", columnDefinition = "timestamp null on update CURRENT_TIMESTAMP")
    private Instant modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizbook_id")
    private Quizbook quizbook;

    @OneToMany(mappedBy = "quiz")
    private Set<Solve> solves = new LinkedHashSet<>();

    public boolean getIsSolved(Member member) {
        return solves.stream()
                .filter(solve -> solve.getMember().equals(member))
                .findAny()
                .isPresent();
    }

    public Map<String, Long> verifyApi() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("quizId", this.id);
        map.putAll(this.quizbook.verifyApi());
        return map;
    }
}