package Base.NeoURLCheck.src.Base;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.net.ssl.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;


public class JsoupCrawler {

    private static String URL;
    static HashMap<String, Integer> HttpErrorMap = new HashMap();
    static HashMap<String, String> HttpErrorTracker = new HashMap();

    static int count;

    public static String TempUrl;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        ; // 1 뎁스
//        urlCheck(CreateHttpList("https://neolab.net/")); // 2 뎁스
        recuresiveCrawler("https://neolab.net", 2);
        HtmlJavaSend.sendEmail();

    }


    public static ArrayList<String> CreateHttpList(String URL) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        // 파라미터로 받은 URL이 가지고 있는 모든 Hyperlink 를 중복 제거 후 배열에 저장
        JsoupCrawler.URL = URL;
        ArrayList<String> HTTPlist = new ArrayList<>();

        Document doc = Jsoup.connect(JsoupCrawler.URL).timeout(5000).ignoreHttpErrors(true).get();
        Elements aTags = doc.select("a[href]");

        for (Element aTag : aTags) {
            String Url = aTag.attr("abs:href");

            if (Url.contains("https") & (!Url.endsWith(".exe")) & (!Url.endsWith(".dmg")) &(Url.contains("neo"))) {
                if (!HTTPlist.contains(String.valueOf(Url))) {
                    HTTPlist.add(Url); // 중복 제거된 애들만 여기 포함
                }
            }
        }
        return HTTPlist;
    }


    public static ArrayList<String> visitedLink = new ArrayList<>();

    public static void recuresiveCrawler(String seed, int nLevel) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        visitedLink.add(String.valueOf(seed));
        ArrayList<String> urlList = CreateHttpList(seed);


        for (String url : urlList) {
            // 1) filter를 적용해서
            // 결과가 true 이면 아래의 동작을 실행
            // 2) visitedLink에 포함되지 않았으면
            if (!visitedLink.contains(url) ){
                // 방문해서 죽어 있으면 에러를 출력
                // url @ seed
//                System.out.println("확인 중인 url은 : " + url);

                try {
                    //처리해보자 여기서
                    Connection.Response response = Jsoup.connect(url)
                            .ignoreHttpErrors(true)
                            .timeout(10 * 000)
                            .method(Connection.Method.GET)
                            .execute();

                    if (response.statusCode() != 200 ) {
                        System.out.println(url + " : " + response.statusCode());
                        HttpErrorMap.put(url, response.statusCode());
//                        HttpErrorTracker.put(url, TempUrl);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("예외 발생");
                }


                // 살아 있으면,
                if (nLevel < 3) {
                    recuresiveCrawler(url, nLevel + 1);
                }
            }
        }
//        System.out.println(HttpErrorMap.size());
//        HtmlJavaSend.sendEmail();
    }

    public static boolean neoFilter (String url) {
        if (url.contains("https") & (!url.endsWith(".exe")) & (!url.endsWith(".dmg")) &(url.contains("neo"))) {
            return true;
        } else {
            return false;
        }

    }
    public static void urlCheck(ArrayList<String> Httplist) throws NoSuchAlgorithmException, KeyManagementException {
        // 파라미터로 받은 Hyperlink 리스트를 순회하면서 각각 링크들이 가지고 있는 또 다른 링크들의 http status 를 확인
        System.out.println("Httplist의 사이즈 : " + Httplist.size()); // test code

        try {
            for (int i = 0; i < Httplist.size(); i++) {
                // 파라미터로 받은 Httplist 를 순회
                System.out.println("Checking for : " + String.valueOf(Httplist.get(i)));
                TempUrl = String.valueOf(Httplist.get(i));

                Document doc = Jsoup.connect(String.valueOf(Httplist.get(i))).ignoreHttpErrors(true).timeout(30000).get();
                Elements aTags = doc.select("a[href]");


                for (Element aTag : aTags) {
                    String url = aTag.attr("abs:href");

                    if (url.contains("https") & (!url.endsWith(".exe")) & (!url.endsWith(".dmg")) &(url.contains("neo"))) {
                        // url이 http가 포함되어있고, .exe로 끝나지 않으면

                        Connection.Response response = Jsoup.connect(url)
                                .timeout(30 * 1000)
                                .method(Connection.Method.GET)
                                .ignoreHttpErrors(true)
                                .execute();


//                        System.out.println(url + " : " + response.statusCode());
                        if (response.statusCode() != 200) {
                            // 200ok 가 아닐 경우 해쉬맵에 key로 URL, value로 statuscode 저장
                            HttpErrorMap.put(url, response.statusCode());
                            HttpErrorTracker.put(url, TempUrl);
                        }
                    }
                }
                System.out.println("ErrorList Size : " + HttpErrorMap.size());
                System.out.println(HttpErrorMap);
                System.out.println(HttpErrorTracker);
            }


        } catch (SSLHandshakeException sslHandshakeException) {
            System.out.println("에러발생 :" + sslHandshakeException.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void SingleUrlCheck(String URL) throws NoSuchAlgorithmException, KeyManagementException {
        JsoupCrawler.URL = URL;


        try {
            Document doc = Jsoup.connect(JsoupCrawler.URL).timeout(5000).ignoreHttpErrors(true).get();

            Elements aTags = doc.select("a[href]");


            for (Element aTag : aTags) {
                String Url = aTag.attr("abs:href");
//                System.out.println(Url);


                if (Url.contains("https") & (!Url.endsWith(".exe")) & (!Url.contains("wisdom"))) {

                    Connection.Response response = Jsoup.connect(Url)
                            .timeout(8000)
                            .method(Connection.Method.GET)
                            .ignoreHttpErrors(true)
                            .execute();

                    System.out.println(Url + " : " + response.statusCode());


                    if (response.statusCode() != 200) {
                        // 200ok 가 아닐 경우 해쉬맵에 key로 URL, value로 statuscode 저장
                        HttpErrorMap.put(Url, response.statusCode());
                    }
                }
            }

        } catch (SSLHandshakeException sslHandshakeException) {

            System.out.println("에러발생 :" + sslHandshakeException.getMessage());

        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static void setSSL() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        // TODO Auto-generated method stub
                        return null;
                    }
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                        // TODO Auto-generated method stub

                    }
                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType)
                            throws CertificateException {
                        // TODO Auto-generated method stub
                    }
                }
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

}

