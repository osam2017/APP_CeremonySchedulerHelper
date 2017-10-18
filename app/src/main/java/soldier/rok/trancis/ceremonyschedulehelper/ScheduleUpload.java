package soldier.rok.trancis.ceremonyschedulehelper;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ScheduleUpload extends AsyncTask<String, String, String> {
    String m_str_ceremony_date;
    String m_str_ceremony_name;
    String m_str_ceremony_detail;
    String m_str_ceremony_type;

    public ScheduleUpload(String str_ceremony_date, String str_ceremony_name, String str_ceremony_detail, String str_ceremony_type){
        m_str_ceremony_date = str_ceremony_date;
        m_str_ceremony_name = str_ceremony_name;
        m_str_ceremony_detail = str_ceremony_detail;
        m_str_ceremony_type = str_ceremony_type;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... args) {
        try {
            URL url = new URL(args[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            String strData = "DATE=" + m_str_ceremony_date + "&NAME=" + m_str_ceremony_name
                    + "&DETAIL=" + m_str_ceremony_detail +"&TYPE" + m_str_ceremony_type;
            os.write(strData.getBytes("UTF-8"));
            os.flush();
            os.close();
            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            } else {
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void onProgressUpdate(String... progress) {

    }

    protected void onPostExecute(String result) {
        new ScheduleUpload(m_str_ceremony_date, m_str_ceremony_name, m_str_ceremony_detail, m_str_ceremony_type).execute();//"http://10.53.128.114:8080/users/login");
    }
}