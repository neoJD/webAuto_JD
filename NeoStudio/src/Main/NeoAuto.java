package Base.NeoStudio.src.Main;

import Base.NeoStudio.src.Base.*;
import Base.NeoStudio.src.Case.*;
import Base.NeoStudio.src.Case.Record;
import static Base.GoogleLogin.driver;

public class NeoAuto {


    public static void main(String[] args) {
        try {
            GoogleLogin googleLogin = new GoogleLogin();

            RunTime.setStartTime();
            googleLogin.Login();
            Timeline.checkPages();
            Timeline.PagesByDate();
            NoteBook.checkSorting();
            NoteBook.checkNotebookPage();
            Tag.checkTotalTag();
            Record.checkTotalRecord();
            Favorite.checkTotalFavorite();
            RunTime.setEndTime();
            RunTime.elapsedTime();
            TestcaseDown.tcDown();
        } catch (Exception e) {

        } finally {
            driver.close();
        }
    }
}
