package soldier.rok.trancis.ceremonyschedulehelper;


import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;
import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;


public class CeremonyAddActivity extends AppCompatActivity {
    private String TAG = "PickerActivity";
    private String m_strCeremonyType;
    Context m_Ctxt = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceremony_add);

        final Calendar cal = Calendar.getInstance();
        int iYear = cal.get(Calendar.YEAR);
        int iMonth = cal.get(Calendar.MONTH) + 1;
        int iDay = cal.get(Calendar.DAY_OF_MONTH);

        final TextView tv_date = (TextView)findViewById(R.id.text_disp_ceremony_date);
        EditText text_input_ceremony_name = (EditText) findViewById(R.id.text_input_ceremony_name);
        TextView text_disp_ceremony_date = (TextView) findViewById(R.id.text_disp_ceremony_type_ask);
        Button btn_add_page_confirm = (Button)findViewById(R.id.btn_add_page_confirm);
        Button btn_add_page_cancel = (Button)findViewById(R.id.btn_add_page_cancel);

        tv_date.setText(iYear + "년 " + iMonth + "월 " + iDay + "일");

        //DATE PICKER DIALOG
        findViewById(R.id.btn_date_picker_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(CeremonyAddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        tv_date.setText(year + "년 " + (month+1) + "월 " + date + "일");
                    }
                }, cal.get(YEAR), cal.get(MONTH), cal.get(Calendar.DATE));

                dialog.getDatePicker().setMinDate(new Date().getTime());    //현재 날짜 이후로 클릭 안되게 옵션
                dialog.show();
            }
        });




        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_ceremony_add);
        toolbar.setTitle("행사 추가");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

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
        RadioButton btn_rg_10 = (RadioButton)findViewById(R.id.btn_rg_10);



        btn_add_page_confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //not selected
                if(m_strCeremonyType == null)
                {
                    Toast.makeText(getApplicationContext(), "Please Check Ceremony", Toast.LENGTH_SHORT).show();
                }
                //check ceremonyname
                else if(((EditText) findViewById(R.id.text_input_ceremony_name)).getText().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please Write Down Ceremony Name", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String strDate = ((TextView) findViewById(R.id.text_disp_ceremony_date)).getText().toString();
                    String strTitle = ((EditText) findViewById(R.id.text_input_ceremony_name)).getText().toString();
                    String strSort = m_strCeremonyType;

                    new PostSchedule(strDate, strTitle, strSort, auth.getUserId()).execute(GLOBALVAR.SCHEDULE_URL);
                }
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

        btn_rg_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "사열식";
            }
        });
        btn_rg_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "표창수여식";
            }
        });
        btn_rg_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "경축식";
            }
        });
        btn_rg_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "이,취임식";
            }
        });
        btn_rg_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "입대,임관,입교,수료식";
            }
        });
        btn_rg_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "전역식";
            }
        });
        btn_rg_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "취,퇴역식";
            }
        });
        btn_rg_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "영결식";
            }
        });
        btn_rg_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "하관식";
            }
        }); btn_rg_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_strCeremonyType = "사용자 정의";
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

    public class PostSchedule extends AsyncTask<String, String, String> {
        String m_strDate;
        String m_strTitle;
        String m_strSort;
        int m_iUid;

        public PostSchedule(String strDate, String strTitle, String strSort, int iUid){
            m_strDate = strDate;
            m_strTitle = strTitle;
            m_strSort = strSort;
            m_iUid = iUid;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                URL url = new URL(args[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
                con.setRequestMethod("POST");
                con.setDoOutput(true);

                OutputStream os = con.getOutputStream();
                String strData = "date=" + m_strDate + "&title=" + m_strTitle + "&sort=" + m_strSort +"&uid=" + m_iUid +"&detail=*";
                os.write(strData.getBytes("UTF-8"));
                os.flush();
                os.close();
                int response = con.getResponseCode();
                if (response == HttpURLConnection.HTTP_NOT_FOUND){
                }
                else if(response == HttpURLConnection.HTTP_OK)
                {
                    InputStream is = con.getInputStream();
                    BufferedReader in = new BufferedReader(new InputStreamReader(is), 8*1024);
                    String line = null;
                    StringBuffer buff = new StringBuffer();
                    while((line = in.readLine()) != null)
                    {
                        buff.append(line+"\n");
                    }
                    String data = buff.toString().trim();
                    return data;
                }
                else {
                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {

            //show 등록중입니다 프로세스
        }

        protected void onPostExecute(String result) {
            Intent intent = new Intent(getBaseContext(), MainPageActivity.class);
            startActivity(intent);
        }
    }
}


