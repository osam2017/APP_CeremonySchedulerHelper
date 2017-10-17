package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        EditText sign_up_user_id = (EditText) findViewById(R.id.sign_up_user_id) ;
        EditText sign_up_user_password = (EditText) findViewById(R.id.sign_up_user_password) ;
        Button btn_sign_up = (Button) findViewById(R.id.btn_sign_up);



    }


}
