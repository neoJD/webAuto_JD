package Base.NeoStudio.src.Case;

import Base.NeoStudio.src.Base.PageViewItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static Base.GoogleLogin.driver;
import static Base.NeoStudio.src.Base.PageViewItem.*;

public class Test {
    public static void test(){


        try {
//        div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div > div:nth-child(2) > div > ul > div:nth-child( + 3 + )
            ArrayList<WebElement> page = new ArrayList<>();

            page = (ArrayList<WebElement>) driver.findElements(By.cssSelector("div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div > div" + " > div > ul > div " + " > span.MuiTypography-root.MuiTypography-caption.MuiTypography-colorTextPrimary.MuiTypography-alignLeft"));
            ArrayList<WebElement> pageList = new ArrayList<>();

            for (int i = 0; i < page.size(); i++) {
                Thread.sleep(15000);

                WebElement pages = driver.findElement(By.cssSelector("#root > div > div > main > div > div > div.MuiGrid-root.MuiGrid-container.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-sm-true > div > div:nth-child(2) > div > ul > div:nth-child(" + (i+3) + ") > button > div"));
                pageList.add(pages);
                pageList.get(i).click();
//                driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                Thread.sleep(15000);

                PageViewItem P = new PageViewItem();
                P.Getpageviewitem();
                ImagePaste_Button.click();
                System.out.println(driver.switchTo().alert().getText());
                Thread.sleep(8000);
                Back_Button.click();
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {

        }

    }
}
