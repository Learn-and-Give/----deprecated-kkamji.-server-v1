package com.kkamjidot.api.domain;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "comment")
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cmt_id", nullable = false)
    private Long id;

    @Column(name = "cmt_content", columnDefinition = "TEXT", nullable = false)
    private String cmtContent;

    @Column(name = "deleted_date")
    private LocalDateTime deletedDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    public void delete() {
        this.deletedDate = LocalDateTime.now();
    }

    public boolean getIsMine(Member member) {
        return this.member.equals(member);
    }
}