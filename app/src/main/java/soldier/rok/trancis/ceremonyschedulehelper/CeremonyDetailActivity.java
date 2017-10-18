package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;

public class CeremonyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceremony_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_detail);
        toolbar.setTitle(getIntent().getExtras().getString("ceremony_name"));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        ListView listView = (ListView)findViewById(R.id.list_order_ceremony);
        ArrayList<String> arrayList_ceremony_detail = new ArrayList<String>();

        ArrayAdapter<String> simpleAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList_ceremony_detail);
        listView.setAdapter(simpleAdapter2);

        arrayList_ceremony_detail.add("애국가 제창");
        arrayList_ceremony_detail.add("경레");
        arrayList_ceremony_detail.add("신고");
        arrayList_ceremony_detail.add("훈시");
        arrayList_ceremony_detail.add("경례");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            //리스트뷰 아이템 클릭 리스너
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog dialog_national_anthem = createDialogBox();
                dialog_national_anthem.show();
            }

            //다이얼로그 생성->조건별 다른 다이얼로그 생성 필요.
            private AlertDialog createDialogBox(){
                AlertDialog.Builder builder = new AlertDialog.Builder(CeremonyDetailActivity.this);
                builder.setTitle("애국가제창");
                builder.setMessage("애국가를 부르세용");
                builder.setIcon(R.drawable.btn_back);

                AlertDialog dialog = builder.create();
                return dialog;
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
