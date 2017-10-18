package soldier.rok.trancis.ceremonyschedulehelper;



public class ListData {
    private String text_ceremony_date;
    private String text_ceremony_name;
    private String text_ceremony_detail;

    ListData(String text_date, String text1, String text_ceremony_detail){

        this.text_ceremony_date = text_date;
        this.text_ceremony_name = text1;
        this.text_ceremony_detail = text_ceremony_detail;

    }

    public String getText_ceremony_date() {
        return text_ceremony_date;
    }

    public String getText_ceremony_name() {
        return text_ceremony_name;
    }

    public String getText_ceremony_detail() {
        return text_ceremony_detail;
    }




}

