package com.kkamjidot.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "member_password")
    @JsonIgnore
    private String memberPassword;

    @Column(name = "member_university_email")
    private String memberUniversityEmail;

    @Column(name = "member_second_email")
    private String memberSecondEmail;

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

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "modified_date")
    private Instant modifiedDate;
}
