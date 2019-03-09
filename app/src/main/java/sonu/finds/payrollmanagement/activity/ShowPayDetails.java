package sonu.finds.payrollmanagement.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import sonu.finds.payrollmanagement.R;
import sonu.finds.payrollmanagement.room.PayRollEntity;
import sonu.finds.payrollmanagement.room.PayRollViewModel;
import sonu.finds.payrollmanagement.utills.Constants;

public class ShowPayDetails extends AppCompatActivity  {

    String  emp_name,emp_insert,emp_id;
    long emp_basic_salary;
    PayRollViewModel model;
    TableLayout tableLayout;
    TextView notExist;
    float da,hra,ma,pa,gs,tax,netsallary;
    TextView t_da,t_hra,t_ma,t_empid,t_emp_name,t_pa,t_gs,t_tax,t_netsallary,basic_pay;
    private static final String TAG = "ShowPayDetails";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pay_details);
        tableLayout = findViewById(R.id.tableLayout);
        notExist = findViewById(R.id.no_result_text);
        emp_insert = getIntent().getStringExtra("emp_insert");
        emp_id = getIntent().getStringExtra("emp_id");
        emp_name =  getIntent().getStringExtra("emp_name");
        emp_basic_salary  = getIntent().getLongExtra("emp_basic_salary",0);
        Log.d(TAG, "onCreate: Constant value is"+Constants.id_value);
        t_empid = findViewById(R.id.emp_id);
        t_emp_name = findViewById(R.id.emp_name);
        t_da = findViewById(R.id.da);
        t_hra = findViewById(R.id.hra);
        t_ma = findViewById(R.id.ma);
        t_pa = findViewById(R.id.pa);
        t_gs = findViewById(R.id.gs);
        t_tax = findViewById(R.id.tax);
        t_netsallary = findViewById(R.id.ns);
        basic_pay = findViewById(R.id.basic_pay);

    }


    @Override
    protected void onStart() {
        super.onStart();
        model = ViewModelProviders.of(this).get(PayRollViewModel.class);
        if (emp_insert != null) {

            da = (float) ((132.00 / 100.00) * emp_basic_salary);
            hra = (float) ((20.00 / 100.00) * emp_basic_salary);
            ma = (float) (10.00 / 100.00) * emp_basic_salary;
            pa = 500;
            gs = da + hra + ma + pa + emp_basic_salary;
            tax = (float) (10.00 / 100.00) * gs;
            netsallary = gs - tax;

            //t_empid.setText(String.valueOf(Constants.id_value));
            t_emp_name.setText(emp_name);
            t_da.setText(getString(R.string.Rs) + String.valueOf(da));
            t_hra.setText(getString(R.string.Rs) + String.valueOf(hra));
            t_ma.setText(getString(R.string.Rs) + String.valueOf(ma));
            t_pa.setText(getString(R.string.Rs) + String.valueOf(pa));
            t_gs.setText(getString(R.string.Rs) + String.valueOf(gs));
            t_tax.setText(getString(R.string.Rs) + String.valueOf(tax));
            t_netsallary.setText(getString(R.string.Rs) + String.valueOf(netsallary));
            basic_pay.setText(getString(R.string.Rs) + String.valueOf(emp_basic_salary));


            Log.d(TAG, "onStart:  basic salary is " + emp_basic_salary);
            Log.d(TAG, "onStart: " + "da is " + da + " hra is " + hra + " ma is " + ma
                    + " pa is " + pa + " gross salary is " + gs + " tax is " + tax + "net sallary is " + netsallary);

            model.insert(new PayRollEntity(emp_name,emp_basic_salary, da, hra, ma, pa, gs, tax, netsallary));
            model.getLastId().observe(this, new Observer<Long>() {
                @Override
                public void onChanged(@Nullable Long aLong) {
                    t_empid.setText(String.valueOf(aLong));

                }
            });
            //Log.d(TAG, "onStart: inserted record is "+result);


        }
        else {
            if (emp_id !=null){
                Log.d(TAG, "onStart: empid  is "+emp_id);
                 model.findPayRollEntity(Integer.valueOf(emp_id));
                 model.getSearchResults().observe(this, new Observer<PayRollEntity>() {
                     @Override
                     public void onChanged(@Nullable PayRollEntity entity) {
                         Log.d(TAG, "onChanged: "+entity);
                         if (entity !=null){
                             t_empid.setText(emp_id);
                             t_emp_name.setText(entity.getEmp_name());
                             t_da.setText(getString(R.string.Rs) + String.valueOf(entity.getDa()));
                             t_hra.setText(getString(R.string.Rs) + String.valueOf(entity.getHra()));
                             t_ma.setText(getString(R.string.Rs) + String.valueOf(entity.getMa()));
                             t_pa.setText(getString(R.string.Rs) + String.valueOf(entity.getPa()));
                             t_gs.setText(getString(R.string.Rs) + String.valueOf(entity.getGs()));
                             t_tax.setText(getString(R.string.Rs) + String.valueOf(entity.getTax()));
                             t_netsallary.setText(getString(R.string.Rs) + String.valueOf(entity.getNet_sallary()));
                             basic_pay.setText(getString(R.string.Rs) + String.valueOf(entity.getBasic_pay()));


                         }
                         else {
                             tableLayout.setVisibility(View.GONE);
                             notExist.setVisibility(View.VISIBLE);

                             //Toast.makeText(ShowPayDetails.this, "Employee Id does not exist", Toast.LENGTH_SHORT).show();
                         }
                     }
                 });

            }

        }
    }


}
