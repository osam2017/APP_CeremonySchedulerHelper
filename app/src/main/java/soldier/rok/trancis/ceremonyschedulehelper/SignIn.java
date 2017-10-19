package soldier.rok.trancis.ceremonyschedulehelper;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            URL url = new URL(GLOBALVAR.SIGNIN_URL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            OutputStream os = con.getOutputStream();
            String strData = "id=" + m_strId + "&password=" + m_strPassword;
            os.write(strData.getBytes("UTF-8"));
            os.flush();
            os.close();
            int response = con.getResponseCode();
            if (response == HttpURLConnection.HTTP_NOT_FOUND){
            }
            else if(response == HttpURLConnection.HTTP_OK)
            {
                InputStream is = con.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(is), 8*1024);
                String line = null;
                StringBuffer buff = new StringBuffer();
                while((line = in.readLine()) != null)
                {
                    buff.append(line+"\n");
                }
                String data = buff.toString().trim();
                m_bSuccess = true;
                return data;
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
            try{
                int i = result.indexOf("eid");
                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObj = (JSONObject) jsonParser.parse(result);
                int uid = Integer.parseInt(jsonObj.get("uid").toString());
                String strNick= jsonObj.get("nickname").toString();
                auth.SetUserId(uid);
                auth.SetAuth(true);
                auth.SetNick(strNick);
            }
            catch(ParseException e)
            {
                e.printStackTrace();
            }
        }
        ((MainActivity)(MainActivity.m_Ctxt)).onResume();
    }
}