package Model;

public class Expense {

    private double reg_expense = 0, inter_expense = 0,  acc_each = 0, acc_sum = 0, 
             allo_each = 0, allo_sum = 0, travelling = 0, sum_expense = 0;
    private int allo_day = 0, acc_night = 0;
    private int expense_id, form_id;

    public double getSum_expense() {
        return sum_expense;
    }

    public double getReg_expense() {
        return reg_expense;
    }

    public double getInter_expense() {
        return inter_expense;
    }

    public double getAcc_each() {
        return acc_each;
    }

    public double getAcc_sum() {
        return acc_sum;
    }

    public double getAllo_each() {
        return allo_each;
    }

    public double getAllo_sum() {
        return allo_sum;
    }

    public double gettravelling() {
        return travelling;
    }

    public int getAllo_day() {
        return allo_day;
    }

    public int getAcc_night() {
        return acc_night;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public int getForm_id() {
        return form_id;
    }

    public void setReg_expense(double reg_expense) {
        this.reg_expense = reg_expense;
    }

    public void setInter_expense(double inter_expense) {
        this.inter_expense = inter_expense;
    }

    public void setAcc_each(double acc_each) {
        this.acc_each = acc_each;
    }

    public void setAcc_sum(double acc_sum) {
        this.acc_sum = acc_sum;
    }

    public void setAllo_each(double allo_each) {
        this.allo_each = allo_each;
    }

    public void setAllo_sum(double allo_sum) {
        this.allo_sum = allo_sum;
    }

    public void setTravelling(double travelling) {
        this.travelling = travelling;
    }

    public void setSum_expense(double sum_expense) {
        this.sum_expense = sum_expense;
    }

    public void setAllo_day(int allo_day) {
        this.allo_day = allo_day;
    }

    public void setAcc_night(int acc_night) {
        this.acc_night = acc_night;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }

    public void setForm_id(int form_id) {
        this.form_id = form_id;
    }

}
