//package com.kkamjidot.api.dto.response;
//
//import com.kkamjidot.api.domain.Quiz;
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Getter;
//
//@Getter
//@Schema(name = "문제 내용 조회 응답 Dto")
//public class QuizContentResponseDto {
//    @Schema(description = "문제 아이디", example = "1", required = true)
//    private Long quizbookId;
//
//    @Schema(description = "문제집 제목", example = "문제집1 제목", required = true)
//    private String quizbookTitle;
//
//    @Schema(description = "문제집 주차", example = "1", required = true)
//    private Integer quizbookWeek;
//
//    @Schema(description = "제출 회원 이름", example = "홍길동", required = true)
//    private String submitUserName;
//
//    @Schema(description = "문제 아이디", example = "1", required = true)
//    private Long quizId;
//
//    @Schema(description = "문제 제목", example = "문제1 제목", required = true)
//    private String quizTitle;
//
//    @Schema(description = "문제 내용", example = "문제1 내용", required = true)
//    private String quizContent;
//
//    @Schema(description = "문제 유형", example = "BASIC", required = true, defaultValue = "BASIC")
//    private String quizCategory;
//
//    public QuizContentResponseDto(Quiz quiz) {
//        this.quizbookId = quiz.getQuizbook().getId();
//        this.quizbookTitle = quiz.getQuizbook().getQuizbookTitle();
//        this.quizbookWeek = quiz.getQuizbook().getQuizbookWeek();
//        this.submitUserName = quiz.getQuizbook().getMember().getMemberName();
//        this.quizId = quiz.getId();
//        this.quizTitle = quiz.getQuizTitle();
//        this.quizContent = quiz.getQuizContent();
//        this.quizCategory = quiz.getQuizCategory();
//    }
//}
