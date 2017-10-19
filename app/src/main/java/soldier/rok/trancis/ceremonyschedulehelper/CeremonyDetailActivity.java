package soldier.rok.trancis.ceremonyschedulehelper;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import java.util.ArrayList;
import android.media.MediaPlayer;
import android.widget.TextView;

public class CeremonyDetailActivity extends AppCompatActivity {

    private MediaPlayer mp_anthem;
    private MediaPlayer mp_oath;
    private MediaPlayer mp_salute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        ListView listView = (ListView)findViewById(R.id.list_order_ceremony);
        final ArrayList<String> arrayList_ceremony_detail = new ArrayList<String>();


        ArrayAdapter<String> simpleAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList_ceremony_detail);
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

        //add + button when custom
        if(strSort.compareTo("커스텀") == 0)
        {
            arrayList_ceremony_detail.add("+");
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            //리스트뷰 아이템 클릭 리스너
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mText_ceremony_type = arrayList_ceremony_detail.get(position).toString();

                if(mText_ceremony_type == "애국가 제창"){
                    showpopup_Anthem();
                }
                else if(mText_ceremony_type == "국기에 대한 맹세"){
                    showpopup_Oath();
                }
                else if(mText_ceremony_type == "상관에 대한 경례"){
                    showpopup_Salute();
                }
                else if(mText_ceremony_type == "신고"){
                    showpopup_Report();
                }
                else if(mText_ceremony_type == "훈시"){
                    showpopup_Speech();
                }
                else if(mText_ceremony_type == "+"){
                    showpopup_Edit();
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
                dialog.show();
            }

        });

        Button btn_confirm_detail_page = (Button) findViewById(R.id.btn_detail_confirm);
        btn_confirm_detail_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainPageActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
