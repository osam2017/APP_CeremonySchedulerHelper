package soldier.rok.trancis.ceremonyschedulehelper;



public class ListData {
    private String text_ceremony_date;
    private String text_ceremony_name;
    private String text_ceremony_type;
    private String text_ceremony_sort;
    private String text_ceremony_detail;

    ListData(String text_date, String text1, String text_sort, String text_ceremony_detail){

        this.text_ceremony_date = text_date;
        this.text_ceremony_name = text1;
        this.text_ceremony_sort = text_sort;
        this.text_ceremony_detail = "*";

        switch(text_sort)
        {
            case "사열식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"상관에 대한 경례";
                break;
            case "표창수여식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"상관에 대한 경례"+"\n"
                        +"상관에 대한 경례";
                break;
            case "경축식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"상관에 대한 경례"+"\n"
                        +"상관에 대한 경례";
                break;
            case "이,취임식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"상관에 대한 경례"+"\n"
                        +"상관에 대한 경례";
                break;
            case "입대,임관,입교,수료식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"상관에 대한 경례"+"\n"
                        +"상관에 대한 경례";
                break;
            case "전역식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"상관에 대한 경례"+"\n"
                        +"상관에 대한 경례";
                break;
            case "취,퇴역식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"상관에 대한 경례"+"\n"
                        +"상관에 대한 경례";

                break;
            case "영결식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"신고"+"\n"
                        +"국기에 대한 맹세";
                break;
            case "하관식":
                this.text_ceremony_detail = "애국가 제창"+"\n"
                        +"국기에 대한 맹세";
                break;
            case "커스텀":
                this.text_ceremony_detail = text_ceremony_detail;
        }


    }

    public String getText_ceremony_date() {
        return text_ceremony_date;
    }

    public String getText_ceremony_name() {
        return text_ceremony_name;
    }

    public String getText_ceremony_type() {
        return text_ceremony_type;
    }

    public String getText_ceremony_sort() {
        return text_ceremony_sort;
    }




}

