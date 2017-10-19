package soldier.rok.trancis.ceremonyschedulehelper;


import android.app.Dialog;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

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

public class SharedListActivity extends AppCompatActivity {

    ArrayList<String> arrayList_ceremony_sharedlist;
    int iItemCnt = 0;
    int iFinishCnt = 0;
    private ProgressBar spinner;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_list);

        arrayList_ceremony_sharedlist = new ArrayList<String>();
        spinner = (ProgressBar)findViewById(R.id.progressBarLoad);

        //툴바 기능
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_sharedList);
        toolbar.setTitle(getIntent().getExtras().getString("ceremony_name")+"를 공유된 사람");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btn_detail_page_order_add = (Button) findViewById(R.id.btn_add_shared);
        btn_detail_page_order_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SharedListActivity.this);
                dialog.setContentView(R.layout.custom_dialog_add_shared);
                EditText et = (EditText) dialog.findViewById(R.id.editText_custom_dialog_add_shared_nickname);
                Button btn1 = (Button) dialog.findViewById(R.id.btn_custom_dialog_add_shared_confirm);
                Button btn2 = (Button) dialog.findViewById(R.id.btn_custom_dialog_add_shared_cancel);

                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                  //공유 인원 추가 코드 넣을것
                    }
                });
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });


        //search user name by user id by eid
        new GetUidByEid().execute();

        ListView listView = (ListView)findViewById(R.id.listview_shared_list);
        ArrayAdapter<String> simpleAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList_ceremony_sharedlist);
        listView.setAdapter(simpleAdapter3);

    }

    @Override
    public void onResume() {
        super.onResume();

        ListView listView = (ListView)findViewById(R.id.listview_shared_list);
        ArrayAdapter<String> simpleAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList_ceremony_sharedlist);
        listView.setAdapter(simpleAdapter3);
    }

    public class GetUserByUid extends AsyncTask<String, String, String> {
        int m_iUid;
        public GetUserByUid(int iUid)
        {
            m_iUid = iUid;
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
                URL url = new URL(GLOBALVAR.USER_UID_URL+ "/" + m_iUid);
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
                String strNick = jsonObj.get("nickname").toString();
                String strUid = jsonObj.get("uid").toString();
                arrayList_ceremony_sharedlist.add(strNick);
                iFinishCnt++;

                if(iItemCnt == iFinishCnt)
                {
                    spinner.setVisibility(View.GONE);
                    onResume();
                }
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        }
    }


    public class GetUidByEid extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            spinner.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... args) {
            BufferedInputStream bis = null;
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(GLOBALVAR.RELATION_EID_URL+ "/" + getIntent().getExtras().get("eid"));
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
                //get uids
                int[] iaUids = new int[strArr.length/4];
                int iIndex =0;
                for(int i=0; i<strArr.length; i++)
                {
                    if(strArr[i].contains("uid"))
                    {
                        String strNum = strArr[i].replaceAll("\\D", "");
                        iaUids[iIndex] = Integer.parseInt(strNum);
                        new GetUserByUid(iaUids[iIndex]).execute();
                        iIndex++;
                        iItemCnt++;
                    }
                }
            }
            catch(Exception e)
            {

            }
        }
    }
}
