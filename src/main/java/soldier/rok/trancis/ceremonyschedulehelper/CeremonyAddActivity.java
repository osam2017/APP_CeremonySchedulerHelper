package soldier.rok.trancis.ceremonyschedulehelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


public class CeremonyAddActivity extends AppCompatActivity {
    DatePicker mDate;
    TextView mTxtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ceremony_add);

        mDate = (DatePicker)findViewById(R.id.datepicker);
        mTxtDate = (TextView)findViewById(R.id.txtdate);


        //처음 DatePicker를 오늘 날짜로 초기화한다.
        //그리고 리스너를 등록한다.
        mDate.init(mDate.getYear(), mDate.getMonth(), mDate.getDayOfMonth(),
                new DatePicker.OnDateChangedListener() {

                    //값이 바뀔때마다 텍스트뷰의 값을 바꿔준다.
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                        // TODO Auto-generated method stub
                        //monthOfYear는 0값이 1월을 뜻하므로 1을 더해줌 나머지는 같다.
                        mTxtDate.setText(String.format("%d/%d/%d", year,monthOfYear + 1
                                , dayOfMonth));
                    }

                });


        //선택기로부터 날짜 조사
        findViewById(R.id.btnnow).setOnClickListener(new View.OnClickListener() {


            //버튼 클릭시 DatePicker로부터 값을 읽어와서 Toast메시지로 보여준다.
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String result = String.format("%d년 %d월 %d일", mDate.getYear(),
                        mDate.getMonth() + 1, mDate.getDayOfMonth());

            }

        });

    }

}