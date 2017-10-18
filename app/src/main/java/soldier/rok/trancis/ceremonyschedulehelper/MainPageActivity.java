package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;

public class MainPageActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ArrayList<ListData> listDataArray = new ArrayList<ListData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        ListData data1 = new ListData("2017/10/18", "김정환상병 진급","진급식");
        listDataArray.add(data1);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_mainpage);
        toolbar.setTitle(auth.getNick()+"의 행사 내역");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

               ListView listView = (ListView)findViewById(R.id.list_user_schedule);
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.custom_list_row, listDataArray);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(this);




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
    public void onItemClick(AdapterView<?> Parent, View view, int position, long id){
        Intent intent = new Intent(this, CeremonyDetailActivity.class);
        intent.putExtra("position", position);
    }

    @Override
    public void onBackPressed() {
    }






}