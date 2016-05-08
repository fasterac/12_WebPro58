package Model;

public class Form {
    
    private int form_id, status_id, inter_id, user_id, sum_date = 0;
    private String form_date, course, organizer = "", location = "", start_date = "CURRENT_TIMESTAMP", end_date = "";
    
    public int getForm_id() {
        return form_id;
    }

    public String getCourse() {
        return course;
    }

    public String getStart_date() {
        return start_date;
    }

    public int getStatus_id() {
        return status_id;
    }

    public int getInter_id() {
        return inter_id;
    }

    public int getSum_date() {
        return sum_date;
    }

    public String getForm_date() {
        return form_date;
    }

    public String getOrganizer() {
        return organizer;
    }

    public String getLocation() {
        return location;
    }

    public String getEnd_date() {
        return end_date;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public void setInter_id(int inter_id) {
        this.inter_id = inter_id;
    }

    public void setSum_date(int sum_date) {
        this.sum_date = sum_date;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setForm_date(String form_date) {
        this.form_date = form_date;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }
    
}
