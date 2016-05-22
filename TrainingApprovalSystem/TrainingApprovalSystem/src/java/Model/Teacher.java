package model;

public class Teacher extends User {

    private int teacher_id;
    private Work.Status status;
    private String position;

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Work.Status getStatus() {
        return status;
    }

    public void setStatus(Work.Status status) {
        this.status = status;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
