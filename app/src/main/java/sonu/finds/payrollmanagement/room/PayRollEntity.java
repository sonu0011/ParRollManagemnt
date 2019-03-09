package sonu.finds.payrollmanagement.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "payroll_table")
public class PayRollEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String emp_name;

    public String getEmp_name() {
        return emp_name;
    }

    private float basic_pay;
    private float da;
    private float hra;
    private float ma;
    private float pa;
    private float gs;
    private float tax;
    private float net_sallary;

    public void setId(int id) {
        this.id = id;
    }

    public PayRollEntity(String emp_name,float basic_pay, float da, float hra, float ma, float pa, float gs, float tax, float net_sallary) {
        this.emp_name = emp_name;
        this.basic_pay = basic_pay;
        this.da = da;
        this.hra = hra;
        this.ma = ma;
        this.pa = pa;
        this.gs = gs;
        this.tax = tax;
        this.net_sallary = net_sallary;
    }

    public int getId() {
        return id;
    }

    public float getBasic_pay() {
        return basic_pay;
    }

    public float getDa() {
        return da;
    }

    public float getHra() {
        return hra;
    }

    public float getMa() {
        return ma;
    }

    public float getPa() {
        return pa;
    }

    public float getGs() {
        return gs;
    }

    public float getTax() {
        return tax;
    }

    public float getNet_sallary() {
        return net_sallary;
    }
}
