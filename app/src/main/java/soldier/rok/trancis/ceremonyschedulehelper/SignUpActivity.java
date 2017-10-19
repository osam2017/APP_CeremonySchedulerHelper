package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

                //미입력 검출
                if(et_id.length()>0){
                    if(et_password.length()>0){
                        if(et_nickname.length()>0){
                            new SignUp(et_id.getText().toString(), et_password.getText().toString(), et_nickname.getText().toString()).execute(GLOBALVAR.SIGNUP_URL);
                        }
                        else{
                            Toast.makeText(getBaseContext(), "닉네임을 입력하세요", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getBaseContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getBaseContext(), "ID를 입력하세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void onSignUp(View v){
        new SignUp(et_id.getText().toString(), et_password.getText().toString(), et_nickname.getText().toString()).execute(GLOBALVAR.SIGNUP_URL);
        Intent intent = new Intent(this, MainActivity.class);
        finish();
    }

}