package soldier.rok.trancis.ceremonyschedulehelper;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;


public class MainActivity extends AppCompatActivity {

    public static Context m_Ctxt;
    public static Authentication auth = new Authentication();
    String sId, sPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_Ctxt = this;

        EditText login_user_idText = (EditText)findViewById(R.id.login_user_idText);
        EditText login_user_passwordText = (EditText)findViewById(R.id.login_user_passwordText);
        Button btn_main_sign_up = (Button)findViewById(R.id.btn_main_sign_up);

        auth.SetAuth(true);
        auth.SetUserId(1);

        btn_main_sign_up.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startLogin();
            }
        });


}

    public void SignInClick(View v){
        EditText et_id = (EditText)findViewById(R.id.login_user_idText);
        EditText et_pw = (EditText)findViewById(R.id.login_user_passwordText);
        sId = et_id.getText().toString();
        sPw = et_pw.getText().toString();
        new SignIn(sId, sPw).execute(GLOBALVAR.SIGNIN_URL);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(auth.getAuth() == true)
        {
            startLogin();
            finish();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Wrong Id or Password", Toast.LENGTH_SHORT).show();
        }

    }

    public void startLogin(){
        Intent intent = new Intent(this, MainPageActivity.class);
        startActivity(intent);
    }
}
