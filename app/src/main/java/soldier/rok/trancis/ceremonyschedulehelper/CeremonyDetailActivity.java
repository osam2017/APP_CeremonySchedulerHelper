package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Intent;
import android.support.v7.app.ActionBar;
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
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

    }

}
