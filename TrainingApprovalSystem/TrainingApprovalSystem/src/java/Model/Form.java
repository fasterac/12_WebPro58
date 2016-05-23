package model;

import java.util.Date;

public class Form extends BaseModel {
    
    public enum Status {
        PENDING,
        APPROVED,
        REJECTED,
        CANCEL
    }

    public enum Location {
        DOMESTIC,
        INTERNATIONAL
    }
    
    private int id;
    private User user, approver;
    private Date form_date, start_date, end_date;
    private String course_name, organizer_name, location_name, course_file_path;
    private Status status;
    private boolean use_expense;

    // Dependencies
    private Expense expense;
    private Report report;
    private Improvement improvement;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getForm_date() {
        return form_date;
    }

    public void setForm_date(Date form_date) {
        this.form_date = form_date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getOrganizer_name() {
        return organizer_name;
    }

    public void setOrganizer_name(String organizer_name) {
        this.organizer_name = organizer_name;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isUse_expense() {
        return use_expense;
    }

    public void setUse_expense(boolean use_expense) {
        this.use_expense = use_expense;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Improvement getImprovement() {
        return improvement;
    }

    public void setImprovement(Improvement improvement) {
        this.improvement = improvement;
    }

    public String getCourse_file_path() {
        return course_file_path;
    }

    public void setCourse_file_path(String course_file_path) {
        this.course_file_path = course_file_path;
    }

    public User getApprover() {
        return approver;
    }

    public void setApprover(User approver) {
        this.approver = approver;
    }

}
