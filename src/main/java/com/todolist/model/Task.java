package com.todolist.model;

public class Task {
    private int id;
    private String subject;
    private String detail;
    private String status;

    public Task(){
    }

    public Task(int id, String subject, String detail, String status){
        super();
        this.id = id;
        this.subject = subject;
        this.detail = detail;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format(
                "Task [id=%s, subject=%s, detail=%s, status=%s]", id,
                subject, detail, status);
    }
}
