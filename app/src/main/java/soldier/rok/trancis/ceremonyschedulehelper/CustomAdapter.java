package soldier.rok.trancis.ceremonyschedulehelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ListData> {
    private  Context context;
    private int layoutResourceId;
    public  ArrayList<ListData> listData;

    public  CustomAdapter(Context context, int layoutResourceId, ArrayList<ListData> listData){
        super(context,layoutResourceId, listData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.listData = listData;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row = convertView;

        if ( row==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }

        //row.findViewById로 row 레이아웃 구성.
        TextView tvText_ceremony_date = (TextView) row.findViewById(R.id.custom_row_textview_date);
        TextView tvText_ceremony_name = (TextView) row.findViewById(R.id.custom_row_textView_ceremony_name);
        TextView tvText_ceremony_sort = (TextView) row.findViewById(R.id.custom_row_textView_ceremony_sort);

        ImageButton imageButton = (ImageButton) row.findViewById(R.id.imageButton) ;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        //position(List데이터 순서값(index)
        //listData(어레이리스트)에서 ListData(객체)참조
        //get으로 순서값 참조 후 setText
        tvText_ceremony_date.setText(listData.get(position).getText_ceremony_date());
        tvText_ceremony_name.setText(listData.get(position).getText_ceremony_name());
        tvText_ceremony_sort.setText(listData.get(position).getText_ceremony_sort());


        return row;
    }
}
