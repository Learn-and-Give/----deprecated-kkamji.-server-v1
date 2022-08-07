//package com.kkamjidot.api.controller;
//
//import com.kkamjidot.api.dto.request.LoginRequestDto;
//import com.kkamjidot.api.dto.response.LoginResponseDto;
//import com.kkamjidot.api.repository.MemberRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalManagementPort;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.web.client.RestTemplate;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("local")
//class MemberControllerTest {
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @AfterEach
//    public void tearDown() {
//        memberRepository.deleteAll();
//    }
//
//    @Test
//    void login() {
//        //given
//        String name = "홍길동";
//        String password = "1234";
//        LoginRequestDto request = LoginRequestDto.builder()
//                .name(name)
//                .code(password)
//                .build();
//
//        String url = "http://localhost:" + port + "/v1/login";
//
//        //when
//        ResponseEntity<LoginResponseDto> responseEntity = restTemplate.postForEntity(url, request, LoginResponseDto.class);
//
//        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
//    }
//}