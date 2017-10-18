package soldier.rok.trancis.ceremonyschedulehelper;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;

public class CeremonyDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceremony_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar_mainpage);
        toolbar.setTitle(auth.getNick());
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);

        ListView listView = (ListView)findViewById(R.id.list_order_ceremony);
        Button btn_confirm_detail_page = (Button)findViewById(R.id.btn_detail_confirm);
    }
}
