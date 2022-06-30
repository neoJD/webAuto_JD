package Main;

import Base.*;

public class Main {
    public static void main(String[] args) {
        try {
            CheckURL.urlCheck_run();
            HtmlJavaSend.sendEmail();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
