package com.kkamjidot.api.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "member_university_email")
    private String memberUniversityEmail;

    @Column(name = "member_second_email")
    private String memberSecondEmail;

    @Column(name = "member_password", nullable = false)
    private String memberPassword;

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "member_university")
    private String memberUniversity;

    @Column(name = "member_major")
    private String memberMajor;

    @Column(name = "member_minor")
    private String memberMinor;

    @Column(name = "member_deleted_date")
    private Instant memberDeletedDate;

    @Column(name = "member_image_url")
    private String memberImageUrl;
}