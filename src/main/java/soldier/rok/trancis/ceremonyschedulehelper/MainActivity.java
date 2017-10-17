package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        EditText login_user_idText = (EditText)findViewById(R.id.login_user_idText);
        EditText login_user_passwordText = (EditText)findViewById(R.id.login_user_passwordText);
        Button btn_main_sign_in = (Button)findViewById(R.id.btn_main_sign_in);
        Button btn_main_sign_up = (Button)findViewById(R.id.btn_main_sign_up);

        btn_main_sign_in.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), MainPageActivity.class);
                startActivity(intent);
            }
        });


        btn_main_sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });




    }



}

