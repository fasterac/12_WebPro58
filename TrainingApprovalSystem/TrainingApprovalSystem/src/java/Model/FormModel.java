
package Model;


public class FormModel {
    private int form_id, status_id, inter_id, sum_date;
    private String form_date, course, organizer, location, start_date, end_date;

    public FormModel(int inter_id, int sum_date, String form_date, String course, String organizer, String location, String start_date, String end_date) {
        this.inter_id = inter_id;
        this.sum_date = sum_date;
        this.form_date = form_date;
        this.course = course;
        this.organizer = organizer;
        this.location = location;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    public int getForm_id() {
        return form_id;
    }

    public String getCourse() {
        return course;
    }

    public String getStart_date() {
        return start_date;
    }

    
    
    
}
