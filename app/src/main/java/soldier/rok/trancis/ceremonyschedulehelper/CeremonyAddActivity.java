package soldier.rok.trancis.ceremonyschedulehelper;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;


public class CeremonyAddActivity extends AppCompatActivity {
    private String TAG = "PickerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceremony_add);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_ceremony_add);
        toolbar.setTitle(auth.getNick());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        EditText text_input_ceremony_name = (EditText) findViewById(R.id.text_input_ceremony_name);
        TextView text_disp_ceremony_date = (TextView) findViewById(R.id.text_disp_ceremony_type_ask);
        Button btn_add_page_confirm = (Button)findViewById(R.id.btn_add_page_confirm);
        Button btn_add_page_cancel = (Button)findViewById(R.id.btn_add_page_cancel);

        //라디오 버튼 객체 선원
        RadioButton btn_rg_1 = (RadioButton)findViewById(R.id.btn_rg_1);
        RadioButton btn_rg_2 = (RadioButton)findViewById(R.id.btn_rg_2);
        RadioButton btn_rg_3 = (RadioButton)findViewById(R.id.btn_rg_3);
        RadioButton btn_rg_4 = (RadioButton)findViewById(R.id.btn_rg_4);
        RadioButton btn_rg_5 = (RadioButton)findViewById(R.id.btn_rg_5);
        RadioButton btn_rg_6 = (RadioButton)findViewById(R.id.btn_rg_6);
        RadioButton btn_rg_7 = (RadioButton)findViewById(R.id.btn_rg_7);
        RadioButton btn_rg_8 = (RadioButton)findViewById(R.id.btn_rg_8);
        RadioButton btn_rg_9 = (RadioButton)findViewById(R.id.btn_rg_9);


        /*
        btn_add_page_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), 1234.class);
                startActivity(intent);
            }
        });
*/
        btn_add_page_cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_rg_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.1 selected", Toast.LENGTH_SHORT).show();
            }
        });
        btn_rg_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.2 selected", Toast.LENGTH_SHORT).show();
            }
        });
        btn_rg_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.3 selected", Toast.LENGTH_SHORT).show();
            }
        });
        btn_rg_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.4 selected", Toast.LENGTH_SHORT).show();
            }
        });
        btn_rg_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.5 selected", Toast.LENGTH_SHORT).show();
            }
        });
        btn_rg_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.6 selected", Toast.LENGTH_SHORT).show();
            }
        });
        btn_rg_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.7 selected", Toast.LENGTH_SHORT).show();
            }
        });
        btn_rg_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.8 selected", Toast.LENGTH_SHORT).show();
            }
        });
        btn_rg_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "no.9 selected", Toast.LENGTH_SHORT).show();
            }
        });



        init();
    }

    private void init() {

        //Calendar를 이용하여 년, 월, 일, 시간, 분을 PICKER에 넣어준다.
        final Calendar cal = Calendar.getInstance();

        Log.e(TAG, cal.get(YEAR) + "");
        Log.e(TAG, cal.get(MONTH) + 1 + "");
        Log.e(TAG, cal.get(Calendar.DATE) + "");
        Log.e(TAG, cal.get(Calendar.HOUR_OF_DAY) + "");
        Log.e(TAG, cal.get(Calendar.MINUTE) + "");




        //DATE PICKER DIALOG
        findViewById(R.id.btn_date_picker_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(CeremonyAddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        String msg = String.format("%d 년 %d 월 %d 일", year, month + 1, date);
                        Toast.makeText(CeremonyAddActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }, cal.get(YEAR), cal.get(MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMinDate(new Date().getTime());    //현재 날짜 이후로 클릭 안되게 옵션
                dialog.show();

            }
        });


    }
}