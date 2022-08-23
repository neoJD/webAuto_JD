//public class test             for (Element hrefTag : aTags) {
//        String URL = hrefTag.attr("href");
////                System.out.println(aTags);
//        Connection.Response response = (Connection.Response) Jsoup.connect(String.valueOf(URL));
//        if (response.statusCode() != 200) {
//        try {
//        JSONObject json = new JSONObject(response.body());
//        String errorDescription = json.getString("error_description");
//        IOException ex = new IOException("Error while connecting to Live: token request error: " + errorDescription);
////                        logger.log(Level.SEVERE, "", ex);
////                        throw ex;
//        } catch (JSONException | IllegalFormatException e) {
//        IOException ex = new IOException("Error while connecting to Live: unknown token request error: " + response.body());
////                        logger.log(Level.SEVERE, "", ex);
////                        throw ex;
//        }
//        }
//        try {
//        JSONObject json = new JSONObject(response.body());
//        String accessToken = json.getString("access_token");
//        System.out.println(accessToken);
//
//        } catch (JSONException | IllegalFormatException e) {
//        IOException ex = new IOException("Error while connecting to Live: failed reading token response: " + response.body());
////                    logger.log(Level.SEVERE, "", ex);
//        throw ex;
//        }
//
//
//        }{
//}
