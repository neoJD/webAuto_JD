import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class RecursiveCrawler {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {
//        recursiveCrawler(CreateHttpList("https://neolab.net"), 1);
        tester();
    }

    public static ArrayList<String> CreateHttpList(String URL) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        // 파라미터로 받은 URL이 가지고 있는 모든 Hyperlink 를 중복 제거 후 배열에 저장
        ArrayList<String> HTTPlist = new ArrayList<>();

        Document doc = Jsoup.connect(URL).timeout(5000).get();

        Elements aTags = doc.select("a[href]");

        for (Element aTag : aTags) {
            String Url = aTag.attr("abs:href");

            if (Url.contains("https") & (!Url.endsWith(".exe"))) {
                if (!HTTPlist.contains(String.valueOf(Url))) {
                    HTTPlist.add(Url); // 중복 제거된 애들만 여기 포함
                }
            }
        }
        return HTTPlist;
    }

    public static ArrayList<String> visitedLink = new ArrayList<>();

    public static void recursiveCrawler(ArrayList seed, int nLevel) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        ArrayList<String> urlList = seed;
        ArrayList<String> hrefList = new ArrayList<>();
        ArrayList<Element> buttonList = new ArrayList<>();
        System.out.println(urlList);

        try {
            System.setProperty("webdriver.chrome.driver", "/Users/jd/Desktop/chromedriver");
            WebDriver driver = new ChromeDriver();
            driver.get(urlList.get(0));
            for (int i = 0; i < urlList.size(); i++) {
                visitedLink.add(urlList.get(i));
                if (i+1 == urlList.size()) {
                    break;
                }

                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


                HttpURLConnection connection = (HttpURLConnection) new URL(urlList.get(i))
                        .openConnection();

                connection.setRequestMethod("HEAD");
                connection.connect();
                // 1) filter를 적용해서
                // 결과가 true 이면 아래의 동작을 실행
                // 2) visitedLink에 포함되지 않았으면
                if (!visitedLink.contains(urlList.get(i+1))){
                    // 방문해서 죽어 있으면 에러를 출력
                    // url @ seed
                    driver.navigate().to(urlList.get(i+1));


                    try {
                        //처리해보자 여기서

                        Document doc = Jsoup.connect(urlList.get(i)).ignoreHttpErrors(true).timeout(10000).get();
                        Elements aTags = doc.select("a[href]");
                        Elements buttons = doc.select("button");


                        for (Element aTag : aTags) {
                            String address = aTag.attr("abs:href");

                            if (address.contains("https") & (!address.endsWith(".exe")) & (!address.endsWith(".dmg")) &(address.contains("neo"))){
                                hrefList.add(address);
                            }
                        }

                        for (Element button : buttons) {
                            buttonList.add(button);

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // 살아 있으면,
//                    if (nLevel < 3){
//                        recursiveCrawler(url, nLevel + 1);
//                    }
                }
            }
            System.out.println(hrefList.size());
            System.out.println(buttonList.size()); // test code
            driver.close();

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public static boolean neoFilter (String url) {
        if (url.contains("neo")) {
            return true;
        } else {
            return false;
        }
    }

    public static void tester(){
        System.setProperty("webdriver.chrome.driver", "/Users/jd/Desktop/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://neolab.net");
        System.out.println(driver.getPageSource());

        try {
            Connection.Response response = Jsoup.connect("https://datalab.naver.com")
                    .timeout(30 * 1000)
                    .method(Connection.Method.POST)
                    .ignoreHttpErrors(true)
                    .execute();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
