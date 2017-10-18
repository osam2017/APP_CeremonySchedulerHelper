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

public class SignUp extends AsyncTask<String, String, String> {
    String m_strId;
    String m_strPassword;
    String m_strNickname;

    public SignUp(String strId, String strPassword, String strNickname){
        m_strId = strId;
        m_strPassword = strPassword;
        m_strNickname = strNickname;
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
            String strData = "id=" + m_strId + "&password=" + m_strPassword + "&nickname=" + m_strNickname;
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
        new SignIn(m_strId, m_strPassword).execute(GLOBALVAR.SIGNIN_URL);
    }
}