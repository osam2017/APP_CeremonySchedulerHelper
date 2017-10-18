package soldier.rok.trancis.ceremonyschedulehelper;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;
import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;


public class CeremonyAddActivity extends AppCompatActivity {
    private String TAG = "PickerActivity";
    private String checkedbtn;
    private int myear;
    private int mmonth;
    private int mday;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceremony_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_ceremony_add);
        toolbar.setTitle("행사 추가");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final EditText text_input_ceremony_name = (EditText) findViewById(R.id.text_input_ceremony_name);
        final EditText text_input_ceremony_detail =(EditText) findViewById(R.id.text_input_ceremony_detail);
        final TextView text_disp_ceremony_date = (TextView) findViewById(R.id.text_disp_ceremony_date);
        final TextView text_disp_ceremony_type_ask = (TextView) findViewById(R.id.text_disp_ceremony_type_ask);
        Button btn_date_picker_dialog = (Button)findViewById((R.id.btn_date_picker_dialog);
        Button btn_add_page_confirm = (Button)findViewById(R.id.btn_add_page_confirm);
        Button btn_add_page_cancel = (Button)findViewById(R.id.btn_add_page_cancel);

        btn_date_picker_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog datepicker = new DatePickerDialog(CeremonyAddActivity.this, dateSetListner)
            }
        });


        //라디오 그룹 선언
        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup_ceremony_type);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(group==rg){
                    if(checkedId == R.id.btn_rg_1){
                        checkedbtn = "1";
                    }
                    else if(checkedId == R.id.btn_rg_2){
                        checkedbtn = "2";
                    }
                    else if(checkedId == R.id.btn_rg_3){
                        checkedbtn = "3";
                    }
                    else if(checkedId == R.id.btn_rg_4){
                        checkedbtn = "4";
                    }
                    else if(checkedId == R.id.btn_rg_5){
                        checkedbtn = "5";
                    }
                    else if(checkedId == R.id.btn_rg_6){
                        checkedbtn = "6";
                    }
                    else if(checkedId == R.id.btn_rg_7){
                        checkedbtn = "7";
                    }
                    else if(checkedId == R.id.btn_rg_8){
                        checkedbtn = "8";
                    }
                    if(checkedId == R.id.btn_rg_9){
                        checkedbtn = "9";
                    }
                    if(checkedId == R.id.btn_rg_10){
                        checkedbtn = "10";
                    }
                }
            }

        });


        btn_add_page_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), ScheduleUpload.class);
                intent.putExtra("date", text_disp_ceremony_date.getText().toString());
                intent.putExtra("name", text_input_ceremony_name.getText().toString());
                intent.putExtra("detail", text_input_ceremony_detail.getText().toString());
                intent.putExtra("type", checkedbtn);
                startActivity(intent);
            }
        });

        btn_add_page_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }




    }

}