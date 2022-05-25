package Main;

import Base.*;
import Case.*;
import Case.Record;
import static Base.GoogleLogin.driver;

public class NeoAuto {
    public static void main(String[] args) {
        try {
            GoogleLogin googleLogin = new GoogleLogin();

            googleLogin.Login();
            Timeline.checkPages();
            Timeline.PagesByDate();
            NoteBook.checkSorting();
            NoteBook.checkNotebookPage();
            Tag.checkTotalTag();
            Record.checkTotalRecord();
            Favorite.checkTotalFavorite();
        } catch (Exception e) {

        } finally {
            driver.close();
        }
    }
}
