package com.kkamjidot.api.domain;

import com.kkamjidot.api.repository.MemberRepository;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "chapter")
public class Chapter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id", nullable = false)
    private Long id;

    @Column(name = "chapter_name", nullable = false)
    private String chapterName;

    @Column(name = "chapter_start_date", nullable = false)
    private Instant chapterStartDate;

    @Column(name = "chapter_end_date", nullable = false)
    private Instant chapterEndDate;

    @Column(name = "created_date", columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    private Instant createdDate;

    @Column(name = "modified_date", columnDefinition = "timestamp null on update CURRENT_TIMESTAMP")
    private Instant modifiedDate;

    @Column(name = "challenge_id")
    private Long challengeId;

    @OneToMany(mappedBy = "chapter")
    private Set<Readable> readables = new LinkedHashSet<>();

    public boolean isReadable(Member member) {
        for (Readable readable : this.readables) {
            if (readable.getMember().equals(member) && readable.getIsReadable()) {
                return true;
            }
        } return false;
    }


}