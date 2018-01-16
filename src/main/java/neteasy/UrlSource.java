package neteasy;

import okhttp3.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

/**
 * Created by liuhu on 2018/1/15.
 */
public class UrlSource {
    public static String getUrlSource(String songId) {

        UrlParamPair upp = Api.GetOneSong(songId);
        String req_str = upp.getParas().toJSONString();
        System.out.println("req_str:" + req_str);

        try {

            Connection.Response
                    response = Jsoup.connect("http://music.163.com/weapi/song/enhance/player/url?csrf_token=")
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:57.0) Gecko/20100101 Firefox/57.0")
                    .header("Accept", "*/*")
                    .header("Cache-Control", "no-cache")
                    .header("Connection", "keep-alive")
                    .header("Host", "music.163.com")
                    .header("Accept-Language", "zh-CN,en-US;q=0.7,en;q=0.3")
                    .header("DNT", "1")
                    .header("Pragma", "no-cache")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .data(JSSecret.getDatas(req_str))
                    .method(Connection.Method.POST)
                    .ignoreContentType(true)
                    .timeout(20000)
                    .execute();
            String content = response.body();

            return content;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
