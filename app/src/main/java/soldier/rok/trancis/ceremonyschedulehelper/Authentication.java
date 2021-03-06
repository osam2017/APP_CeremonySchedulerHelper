package soldier.rok.trancis.ceremonyschedulehelper;

public class Authentication {
    private boolean m_bAuthenticated = false;
    private String m_strNickName = null;
    private int m_iUserId;

    protected void SetAuth(boolean bTF){
        m_bAuthenticated = bTF;
    }

    protected void SetNick(String strNickName){
        m_strNickName = strNickName;
    }

    protected void SetUserId(int iUid){
        m_iUserId = iUid;
    }

    protected String getNick(){
        return m_strNickName;
    }

    protected boolean getAuth(){
        return m_bAuthenticated;
    }

    protected int getUserId(){
        return m_iUserId;
    }
}