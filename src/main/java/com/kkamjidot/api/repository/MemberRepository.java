package com.kkamjidot.api.repository;

import com.kkamjidot.api.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    //이름과 비밀번호로 회원 조회
    Optional<Member> findByMemberNameAndMemberPassword(String name, String password);
}