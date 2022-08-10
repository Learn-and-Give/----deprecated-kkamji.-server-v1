package com.kkamjidot.api.domain;

import com.kkamjidot.api.dto.response.QuizbookResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "quizbook")
public class Quizbook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizbook_id", nullable = false)
    private Long id;

    @Column(name = "quizbook_title")
    private String quizbookTitle;

    @Column(name = "quizbook_description", columnDefinition = "TEXT")
    private String quizbookDescription;

    @Column(name = "quizbook_submitted_date")
    private Instant quizbookSubmittedDate;

    @Column(name = "quizbook_deleted_date")
    private Instant quizbookDeletedDate;

    @Column(name = "created_date", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Instant createdDate;

    @Column(name = "modified_date", columnDefinition = "timestamp null on update CURRENT_TIMESTAMP")
    private Instant modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @OneToMany(mappedBy = "quizbook")
    private Set<Quiz> quizzes = new LinkedHashSet<>();

    public int getNumberOfQuizzes() {
        return this.quizzes.size();
    }

    public String getQuizbookMemberName() {
        return this.member.getMemberName();
    }

    public Map<String, Long> verifyApi() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("quizbookId", this.id);
        map.putAll(this.chapter.verifyApi());
        return map;
    }

//    public void verifyApi(Long QuizbookId, Long chapterId) throws NoSuchElementException {
//        if (this.id != QuizbookId) throw new NoSuchElementException("존재하지 않는 챕터입니다.");
//        this.chapter.verifyApi(chapterId);
//    }
}