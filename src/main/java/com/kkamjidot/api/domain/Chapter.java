package com.kkamjidot.api.domain;

import com.kkamjidot.api.repository.MemberRepository;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "chapter")
public class Chapter extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id", nullable = false)
    private Long id;

    @Column(name = "chapter_name", nullable = false)
    private String chapterName;

    @Column(name = "chapter_start_date", nullable = false)
    private Instant chapterStartDate;

    @Column(name = "chapter_end_date", nullable = false)
    private Instant chapterEndDate;

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

    public Map<String, Long> verifyApi() {
        Map<String, Long> map = new HashMap<>();
        map.put("chapterId", this.id);
        return map;
    }

//    public void verifyApi(Long chapterId) {
//        if (this.id != chapterId) throw new NoSuchElementException("존재하지 않는 챕터입니다.");
//    }

}