package sonu.finds.payrollmanagement.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import sonu.finds.payrollmanagement.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button compute_sallary,view_basic_pay;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compute_sallary = findViewById(R.id.compute_sallary);
        view_basic_pay = findViewById(R.id.view_sallary);
        compute_sallary.setOnClickListener(this);
        view_basic_pay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.view_sallary:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage("ADD EMPLOYEE DETAILS");
                builder.setCancelable(false);
                LayoutInflater inflater1 = getLayoutInflater();
                final View view1 =   inflater1.inflate(R.layout.input_id_layouty,null);
                final TextInputLayout id = view1.findViewById(R.id.search_emp_id);
                builder.setView(view1)
                        // Add action buttons
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel",null);

                builder.create();
                dialog =  builder.show();
                Button pbtn1 = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button ntbn1 = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                pbtn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (id.getEditText().getText().toString().trim().isEmpty()){
                            Toast.makeText(MainActivity.this, "Please Enter An Employee Id", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            dialog.dismiss();
                            Intent intent = new Intent(MainActivity.this,ShowPayDetails.class);
                            intent.putExtra("emp_id",id.getEditText().getText().toString());
                            startActivity(intent);
                        }
                    }
                });
                ntbn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.compute_sallary:
                AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);

                builder1.setMessage("ADD EMPLOYEE DETAILS");
               builder1.setCancelable(false);
                LayoutInflater inflater = getLayoutInflater();
                final View view =   inflater.inflate(R.layout.basic_pay_layout,null);
                final TextInputLayout salary = view.findViewById(R.id.add_basic_pay);
                final TextInputLayout name = view.findViewById(R.id.add_emp_name);
                builder1.setView(view)
                        // Add action buttons
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel",null);

                 builder1.create();
                dialog =  builder1.show();
                Button pbtn = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                Button ntbn = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                pbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (salary.getEditText().getText().toString().trim().isEmpty()){
                            Toast.makeText(MainActivity.this, "Basic Pay Can't Be Empty", Toast.LENGTH_SHORT).show();
                        }
                        else  if (name.getEditText().getText().toString().trim().isEmpty()){
                            Toast.makeText(MainActivity.this, "Name Can't Be Empty", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dialog.dismiss();
                            Intent intent = new Intent(MainActivity.this,ShowPayDetails.class);
                            intent.putExtra("emp_insert","emp_insert");
                            intent.putExtra("emp_name",name.getEditText().getText().toString());
                            intent.putExtra("emp_basic_salary",Long.valueOf(salary.getEditText().getText().toString()));

                            startActivity(intent);
                        }
                    }
                });
                ntbn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }

    }
}
