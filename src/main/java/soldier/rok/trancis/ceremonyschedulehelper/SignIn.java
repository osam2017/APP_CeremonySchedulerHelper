package soldier.rok.trancis.ceremonyschedulehelper;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static soldier.rok.trancis.ceremonyschedulehelper.MainActivity.auth;

public class SignIn extends AsyncTask<String, String, String> {
    String m_strId;
    String m_strPassword;
    boolean m_bSuccess = false;

    public SignIn(String strId, String strPassword){
        m_strId = strId;
        m_strPassword = strPassword;
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
            String strData = "ID=" + m_strId + "&PASSWORD=" + m_strPassword;
            os.write(strData.getBytes("UTF-8"));
            os.flush();
            os.close();
            int response = con.getResponseCode();
            if (response == HttpURLConnection.HTTP_NOT_FOUND){

            }
            else if(response == HttpURLConnection.HTTP_OK)
            {
                m_bSuccess = true;
            }
            else {
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
        if(m_bSuccess == true) {
            auth.SetNick(result);
            auth.SetAuth(true);
        }
        ((MainActivity)(MainActivity.m_Ctxt)).onResume();
    }
}