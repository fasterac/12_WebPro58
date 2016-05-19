package model;

public class Expense extends BaseModel {

    private int form_id, room_night_amount, day_amount;
    private double register_cost, room_night_each, day_cost_each, travel_cost;

    public int getForm_id() {
        return form_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

    public int getRoom_night_amount() {
        return room_night_amount;
    }

    public void setRoom_night_amount(int room_night_amount) {
        this.room_night_amount = room_night_amount;
    }

    public int getDay_amount() {
        return day_amount;
    }

    public void setDay_amount(int day_amount) {
        this.day_amount = day_amount;
    }

    public double getRegister_cost() {
        return register_cost;
    }

    public void setRegister_cost(double register_cost) {
        this.register_cost = register_cost;
    }

    public double getRoom_night_each() {
        return room_night_each;
    }

    public void setRoom_night_each(double room_night_each) {
        this.room_night_each = room_night_each;
    }

    public double getDay_cost_each() {
        return day_cost_each;
    }

    public void setDay_cost_each(double day_cost_each) {
        this.day_cost_each = day_cost_each;
    }

    public double getTravel_cost() {
        return travel_cost;
    }

    public void setTravel_cost(double travel_cost) {
        this.travel_cost = travel_cost;
    }

}
