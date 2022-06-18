package com.leelee.thejavatest;

public class Study {
    private String content;
    private StudyStatus status;

    public Study(String content) {
        if (content.equals("")) {
            throw new IllegalArgumentException("content 내용이 있어야 합니다");
        }
        this.content = content;
        this.status = StudyStatus.START;
    }

    public String getContent() {
        return content;
    }

    public StudyStatus getStatus() {
        return status;
    }
}
