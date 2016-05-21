package model;

public class Improvement extends BaseModel {

    private int form_id, date_duration, report_sent_duration;
    private String detail;

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public int getDate_duration() {
        return date_duration;
    }

    public void setDate_duration(int date_duration) {
        this.date_duration = date_duration;
    }

    public int getReport_sent_duration() {
        return report_sent_duration;
    }

    public void setReport_sent_duration(int report_sent_duration) {
        this.report_sent_duration = report_sent_duration;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

}
