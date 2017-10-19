package soldier.rok.trancis.ceremonyschedulehelper;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import android.media.MediaPlayer;
import android.widget.TextView;
import android.widget.Toast;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;

public class CeremonyDetailActivity extends AppCompatActivity {

    private MediaPlayer mp_anthem;
    private MediaPlayer mp_oath;
    private MediaPlayer mp_salute;

    private int m_iEid;
    private String m_strName;


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu_detailpage, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item){
        //각각의 버튼을 클릭할때의 수행할것을 정의해 준다.
        switch (item.getItemId()){
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.action_move_to_share_list:
                Intent intent = new Intent(this, SharedListActivity.class);
                intent.putExtra("ceremony name", m_strName);
                intent.putExtra("eid", m_iEid);
                startActivity(intent);
                break;
            case R.id.action_delete_ceremony:
                new DeleteScheduleByEid().execute();
                //Intent intent2 = new Intent(this, MainPageActivity.class);
                //startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        m_strName = getIntent().getExtras().getString("ceremony_name");
        m_iEid = getIntent().getExtras().getInt("eid");

        setContentView(R.layout.activity_ceremony_detail);
        mp_anthem = MediaPlayer.create(this,R.raw.anthem_1);
        mp_oath = MediaPlayer.create(this,R.raw.oath_to_flag);
        mp_salute = MediaPlayer.create(this,R.raw.salute_to_field_officer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_ceremony_detail);
        toolbar.setTitle(getIntent().getExtras().getString("ceremony_name"));
        String strDetail = getIntent().getExtras().getString("ceremony_detail");
        String strSort = getIntent().getExtras().getString("ceremony_sort");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView)findViewById(R.id.list_order_ceremony);
        final ArrayList<String> arrayList_ceremony_detail = new ArrayList<String>();
        final ArrayAdapter<String> simpleAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList_ceremony_detail);
        listView.setAdapter(simpleAdapter2);

        if(strDetail.compareTo("*") == 0)
            strDetail = "";

        //divide detail by \n and add elements into arrayList_ceremony_detail
        String[] strDiv = strDetail.split("\n");
        for(int i=0; i<strDiv.length; i++)
        {
            if(strDiv[i] != "")
                arrayList_ceremony_detail.add(strDiv[i]);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            //리스트뷰 아이템 클릭 리스너
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mText_ceremony_type = arrayList_ceremony_detail.get(position).toString();

                switch(mText_ceremony_type)
                {
                    case "애국가 제창":
                        showpopup_Anthem();
                        break;
                    case "국기에 대한 맹세":
                        showpopup_Oath();
                        break;
                    case "상관에 대한 경례":
                        showpopup_Salute();
                        break;
                    case "신고":
                        showpopup_Report();
                        break;
                    case "훈시":
                        showpopup_Speech();
                        break;
                    case "커스텀":
                        showpopup_Edit();
                        break;
                }
            }

            public void showpopup_Anthem()
            {
                final Dialog dialog = new Dialog(CeremonyDetailActivity.this);
                dialog.setContentView( R.layout.custom_dialog);
                TextView tv1 = (TextView) dialog.findViewById(R.id.textView_custom_dialog_subtitle);
                tv1.setText("애국가 제창");
                ImageView iv = (ImageView) dialog.findViewById(R.id.imageView_custom_dialog);
                TextView tv2 = (TextView) dialog.findViewById(R.id.textView_custom_dialog);
                tv2.setText("애국가 1절을 제창하세요");
                iv.setImageResource(R.drawable.korean_flag);

                mp_anthem.start();

                Button btn1 = (Button) dialog.findViewById(R.id.button_custom_dialog);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mp_anthem.stop();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            public void showpopup_Oath()
            {
                final Dialog dialog = new Dialog(CeremonyDetailActivity.this);
                dialog.setContentView( R.layout.custom_dialog);
                TextView tv1 = (TextView) dialog.findViewById(R.id.textView_custom_dialog_subtitle);
                tv1.setText("맹세");
                ImageView iv = (ImageView) dialog.findViewById(R.id.imageView_custom_dialog);
                TextView tv2 = (TextView) dialog.findViewById(R.id.textView_custom_dialog);
                tv2.setText("국기에 대한 맹세");
                iv.setImageResource(R.drawable.korean_flag);

                mp_oath.start();

                Button btn1 = (Button) dialog.findViewById(R.id.button_custom_dialog);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mp_oath.stop();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            public void showpopup_Salute()
            {
                final Dialog dialog = new Dialog(CeremonyDetailActivity.this);
                dialog.setContentView( R.layout.custom_dialog);
                TextView tv1 = (TextView) dialog.findViewById(R.id.textView_custom_dialog_subtitle);
                tv1.setText("상관에 대한 경례");
                ImageView iv = (ImageView) dialog.findViewById(R.id.imageView_custom_dialog);
                TextView tv2 = (TextView) dialog.findViewById(R.id.textView_custom_dialog);
                tv2.setText("경례하세요");
                iv.setImageResource(R.drawable.korean_flag);

                mp_salute.start();

                Button btn1 = (Button) dialog.findViewById(R.id.button_custom_dialog);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mp_salute.stop();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            public void showpopup_Report()
            {
                final Dialog dialog = new Dialog(CeremonyDetailActivity.this);
                dialog.setContentView( R.layout.custom_dialog);
                TextView tv1 = (TextView) dialog.findViewById(R.id.textView_custom_dialog_subtitle);
                tv1.setText("신고");
                ImageView iv = (ImageView) dialog.findViewById(R.id.imageView_custom_dialog);
                TextView tv2= (TextView) dialog.findViewById(R.id.textView_custom_dialog);
                tv2.setText("신!고!합니다.");
                iv.setImageResource(R.drawable.korean_flag);
                Button btn1 = (Button) dialog.findViewById(R.id.button_custom_dialog);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            public void showpopup_Speech()
            {
                final Dialog dialog = new Dialog(CeremonyDetailActivity.this);
                dialog.setContentView( R.layout.custom_dialog);
                TextView tv1 = (TextView) dialog.findViewById(R.id.textView_custom_dialog_subtitle);
                tv1.setText("훈시");
                ImageView iv = (ImageView) dialog.findViewById(R.id.imageView_custom_dialog);
                TextView tv2 = (TextView) dialog.findViewById(R.id.textView_custom_dialog);
                tv2.setText("충성! 훈시!");
                iv.setImageResource(R.drawable.korean_flag);
                Button btn1 = (Button) dialog.findViewById(R.id.button_custom_dialog);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            public void showpopup_Edit()
            {
                final Dialog dialog = new Dialog(CeremonyDetailActivity.this);
                dialog.setContentView( R.layout.edit_dialog);
                EditText tv1 = (EditText) dialog.findViewById(R.id.editText_custom_dialog_subtitle);
                tv1.setText("훈시");
                ImageView iv = (ImageView) dialog.findViewById(R.id.imageView_custom_dialog);
                EditText tv2 = (EditText) dialog.findViewById(R.id.editText_custom_dialog);
                tv2.setText("충성! 훈시!");
                iv.setImageResource(R.drawable.korean_flag);
                Button btn1 = (Button) dialog.findViewById(R.id.button_edit_dialog);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });

        Button btn_detail_page_order_add = (Button) findViewById(R.id.btn_detail_page_order_add);

        btn_detail_page_order_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(CeremonyDetailActivity.this);
                dialog.setContentView(R.layout.custom_dialog_ceremony_detail_custom);
                final EditText et = (EditText) dialog.findViewById(R.id.edittext_detail_page_order_ceremony_name);
                Button btn1 = (Button) dialog.findViewById(R.id.btn_detail_page_order_ceremony_add);
                Button btn2 = (Button) dialog.findViewById(R.id.btn_detail_page_order_ceremony_cancel);


                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(et.getText().toString().compareTo("") == 0)
                            return;
                        if (et.getText().toString().length()>0) {
                            arrayList_ceremony_detail.add(et.getText().toString());
                            simpleAdapter2.notifyDataSetChanged();
                            //서버 전송필요
                            dialog.dismiss();
                        }
                        else{
                            Toast.makeText(CeremonyDetailActivity.this, "내용을 입력해주세요",Toast.LENGTH_SHORT).show();
                        }
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
        Button btn_detail_page_order_delete = (Button) findViewById(R.id.btn_detail_page_order_delete);
        btn_detail_page_order_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new AlertDialog.Builder(CeremonyDetailActivity.this)
                       .setTitle("경고")
                       .setMessage("정말로 삭제하시겠습니까?")
                       .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               arrayList_ceremony_detail.remove(arrayList_ceremony_detail.size()-1);
                              //arrayList_ceremony_detail.remove();
                               simpleAdapter2.notifyDataSetChanged();
                               dialog.dismiss();
                           }
                       })
                       .setNegativeButton("취소", null)
                       .show();
            }
        });
        Button btn_detail_page_order_save = (Button) findViewById(R.id.btn_detail_page_order_save);
        btn_detail_page_order_save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // 서버저장 코드
            }
        });

    }

    public class DeleteScheduleByEid extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... args) {
            try {
                URL url = new URL(GLOBALVAR.SCHEDULE_URL + "/" + m_iEid);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
                con.setRequestMethod("DELETE");

                int iSuccess = con.getResponseCode();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "";
        }

        protected void onProgressUpdate(String... progress) {

        }

        protected void onPostExecute(String result) {
            //delete relation
            new DeleteRelationEidAndUid().execute();
        }
    }

    public class DeleteRelationEidAndUid extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                URL url = new URL(GLOBALVAR.RELATION_URL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
                con.setRequestMethod("DELETE");
                OutputStream os = con.getOutputStream();
                String strData = "eid=" + m_iEid + "&uid=" + auth.getUserId();
                os.write(strData.getBytes("UTF-8"));
                os.flush();
                os.close();

                int iSuccess = con.getResponseCode();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onProgressUpdate(String... progress) {

        }

        protected void onPostExecute(String result) {
            //go back page and reload schedules
            Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
            startActivity(intent);
            finish();
        }
    }
}