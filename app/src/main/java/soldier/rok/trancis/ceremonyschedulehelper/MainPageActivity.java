package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;

public class MainPageActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<ListData> listDataArray = new ArrayList<ListData>();
    public static Context m_Ctxt;
    String testStr1, testStr2, testStr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        //get schedules from server
        new GetEidByUid().execute();


        ListData data1 = new ListData("a", "b", "C");
        listDataArray.add(data1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_mainpage);
        toolbar.setTitle(auth.getNick()+"의 행사 내역");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), CeremonyAddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();


        ListView listView = (ListView)findViewById(R.id.list_user_schedule);
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, listDataArray);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onItemClick(AdapterView<?> Parent, View view, int position, long id){
        Intent intent_list_click = new Intent(getBaseContext(), CeremonyDetailActivity.class);
        intent_list_click.putExtra("ceremony_name", listDataArray.get(position).getText_ceremony_name());
        startActivity(intent_list_click);
    }

    public class GetScheduleByEId extends AsyncTask<String, String, String> {
        int m_iEid;
        public GetScheduleByEId(int iEid)
        {
            m_iEid = iEid;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            BufferedInputStream bis = null;
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(GLOBALVAR.SCHEDULE_URL+ "/" + m_iEid);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                int responseCode;

                con.setConnectTimeout(1500);
                con.setReadTimeout(1500);

                responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    bis = new BufferedInputStream(con.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));

                    String line = null;
                    while ((line = reader.readLine()) != null)
                        sb.append(line);
                    bis.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        protected void onProgressUpdate(String... progress) {

            //show 등록중입니다 프로세스
        }

        protected void onPostExecute(String result) {
            try{
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
                String strTitle = jsonObj.get("title").toString();
                String strDate = jsonObj.get("date").toString();
                listDataArray.add(1, new ListData(strTitle, strDate, "C"));
                onResume();
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        }
    }


    public class GetEidByUid extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            BufferedInputStream bis = null;
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(GLOBALVAR.RELATION_IDNAME_URL+ "/" + auth.getUserId());
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                int responseCode;

                con.setConnectTimeout(1500);
                con.setReadTimeout(1500);

                responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    bis = new BufferedInputStream(con.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));

                    String line = null;
                    while ((line = reader.readLine()) != null)
                        sb.append(line);
                    bis.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        protected void onProgressUpdate(String... progress) {

            //show 등록중입니다 프로세스
        }

        protected void onPostExecute(String result) {
            try{
                String[] strArr = result.split(",");
                //get eids
                int[] iaEids = new int[strArr.length/4];
                int iIndex =0;
                for(int i=0; i<strArr.length; i++)
                {
                    if(strArr[i].contains("eid"))
                    {
                        String strNum = strArr[i].replaceAll("\\D", "");
                        iaEids[iIndex] = Integer.parseInt(strNum);
                        new GetScheduleByEId(iaEids[iIndex]).execute();
                        iIndex++;
                    }
                }
            }
            catch(Exception e)
            {

            }
        }
    }
}