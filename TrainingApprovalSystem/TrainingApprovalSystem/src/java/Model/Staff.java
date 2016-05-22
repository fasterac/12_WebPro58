package model;

public class Staff extends User {

    private int staff_id;
    private Work.Section work_section;

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public Work.Section getWork_section() {
        return work_section;
    }

    public void setWork_section(Work.Section work_section) {
        this.work_section = work_section;
    }

}
