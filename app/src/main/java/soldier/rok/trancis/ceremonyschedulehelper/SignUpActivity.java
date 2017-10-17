package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {
    EditText et_id;
    EditText et_password;
    EditText et_nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_id = (EditText) findViewById(R.id.sign_up_user_id) ;
        et_password = (EditText) findViewById(R.id.sign_up_user_password) ;
        et_nickname = (EditText) findViewById(R.id.sign_up_user_nickname) ;
        Button btn_sign_up = (Button) findViewById(R.id.btn_sign_up);

        btn_sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getBaseContext(), MainPageActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onSignUp(View v){
        new SignUp(et_id.getText().toString(), et_password.getText().toString(), et_nickname.getText().toString()).execute("http://10.53.128.114:8080/users/signup");
    }

}