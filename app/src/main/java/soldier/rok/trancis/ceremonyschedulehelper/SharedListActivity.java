package soldier.rok.trancis.ceremonyschedulehelper;

import android.app.Dialog;
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

import java.util.ArrayList;

import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;

public class SharedListActivity extends AppCompatActivity {


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


        //툴바 기능
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_sharedList);
        toolbar.setTitle(getIntent().getExtras().getString("ceremony_name")+"를 공유된 사람");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView listView = (ListView)findViewById(R.id.listview_shared_list);
        ArrayList<String> arrayList_ceremony_sharedlist = new ArrayList<String>();
        ArrayAdapter<String> simpleAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList_ceremony_sharedlist);
        listView.setAdapter(simpleAdapter3);

        //시험용
        arrayList_ceremony_sharedlist.add("이사람");
        arrayList_ceremony_sharedlist.add("저사람");

        Button btn_detail_page_order_add = (Button) findViewById(R.id.btn_add_shared);
        btn_detail_page_order_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getBaseContext());
                dialog.setContentView(R.layout.custom_dialog_add_shared);
                EditText et = (EditText)findViewById(R.id.editText_custom_dialog_add_shared_nickname);
                Button btn1 = (Button)findViewById(R.id.btn_custom_dialog_add_shared_confirm);
                Button btn2 = (Button)findViewById(R.id.btn_custom_dialog_add_shared_cancel);

                dialog.show();
            }
        });
    }
}
