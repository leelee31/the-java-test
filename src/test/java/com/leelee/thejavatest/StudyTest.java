package com.leelee.thejavatest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class StudyTest {
    @BeforeAll
    static void setup(){
        System.out.println("hi");
    }

    @Test
    @DisplayName("스터디 생성")
    void create_study() {
        Study study = new Study("테스트 코드");
        assertNotNull(study);
    }

    @Test
    @DisplayName("스터디 내용")
    void get_content() {
        Study study = new Study("테스트 코드");
        assertEquals("테스트 코드", study.getContent());
    }

    @Test
    @DisplayName("스터디 상태")
    void get_status() {
        Study study = new Study("테스트 코드");
        assertEquals(StudyStatus.START, study.getStatus(), "스터디 생성 시 상태값이 " + StudyStatus.START + "여야 한다.");
//        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디 생성 시 상태값이 " + StudyStatus.START + "여야 한다.");
/*        assertEquals(StudyStatus.DRAFT, study.getStatus(), new Supplier<String>() {
            @Override
            public String get() {
                return "스터디 생성 시 상태값이 " + StudyStatus.START + "여야 한다.";
            }
        });*/
    }

    @Test
    @DisplayName("스터디 생성 및 체크")
    void create_study2() {
        Study study = new Study("테스트 코드");
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.START, study.getStatus(), () -> "스터디 생성 시 상태값이 " + StudyStatus.START + "여야 한다."),
                () -> assertTrue(study.getContent().equals("테스트 코드"), "이 스터디는 테스트 코드 관련이어야 한다")
        );
    }

    @Test
    @DisplayName("스터디 exception check")
    void check_throws_exception() {
        //IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Study(""));
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Study(""));
        String exMessage = ex.getMessage();
        assertEquals("content 내용이 있어야 합니다", exMessage);
    }

    @Test
    @DisplayName("스터디 timeout checkout")
    void check_timeout() {
       assertTimeout(Duration.ofSeconds(10), () -> {
           new Study("테스트 코드");
           Thread.sleep(500);
       });
    }
}