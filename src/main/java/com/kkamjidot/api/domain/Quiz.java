package com.kkamjidot.api.domain;

import com.kkamjidot.api.domain.enumerate.QuizCategory;
import com.kkamjidot.api.dto.request.CreateQuizRequestDto;
import com.kkamjidot.api.dto.request.UpdateAnswerRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "quiz")
public class Quiz extends BaseTimeEntity {
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
    @Column(name = "quiz_category", length = 50)
    @ColumnDefault("BASIC")
    private QuizCategory quizCategory;

    @Column(name = "quiz_number", nullable = false)
    private Integer quizNumber;

    @Column(name = "quiz_deleted_date")
    private Instant quizDeletedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizbook_id")
    private Quizbook quizbook;

    @OneToMany(mappedBy = "quiz")
    private Set<Solve> solves = new LinkedHashSet<>();

    @OneToMany(mappedBy = "quiz")
    private Set<FileDomain> files = new LinkedHashSet<>();

    public boolean getIsSolved(Member member) {
        return this.solves.stream()
                .anyMatch(solve -> solve.getMember().equals(member));
    }

    public boolean getIsMine(Member member) {
        return this.quizbook.getMember().equals(member);
    }

    public Quiz update(UpdateAnswerRequestDto requestDto) {
        if(requestDto.getQuizAnswer() != null) this.quizAnswer = requestDto.getQuizAnswer();
        if(requestDto.getQuizExplanation() != null) this.quizExplanation = requestDto.getQuizExplanation();
        if(requestDto.getQuizSource() != null) this.quizSource = requestDto.getQuizSource();
        return this;
    }

    public Map<String, Long> verifyApi() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("quizId", this.id);
        map.putAll(this.quizbook.verifyApi());
        return map;
    }

//    public void verifyApi(/*Long quizId,*/Long quizbookId, Long chapterId) throws NoSuchElementException {
//        // if (!Objects.equals(this.id, quizId)) throw new NoSuchElementException("존재하지 않는 문제입니다.");
//        this.quizbook.verifyApi(quizbookId, chapterId);
//    }

    public static Quiz of(CreateQuizRequestDto requestDto, Quizbook quizbook) {
        return Quiz.builder()
                .quizTitle(requestDto.getQuizTitle())
                .quizContent(requestDto.getQuizContent())
                .quizAnswer(requestDto.getQuizAnswer())
                .quizExplanation(requestDto.getQuizExplanation())
                .quizSource(requestDto.getQuizSource())
//                .quizCategory(QuizCategory.BASIC)
                .quizbook(quizbook)
                .build();
    }
}