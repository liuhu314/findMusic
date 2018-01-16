package crawler;

/**
 * static class include all regex string
 * Created by liuhu on 18/1/11.
 */
public class RegexStr {
    public static final String allPage = "http://music.163.com/.*";

    public static final String song = ".*song\\?id=[0-9]+";

    public static final String songArg = ".*song\\?id=([0-9]+)";


    public static final String songNameAndSinger = "(.*?)-(.*?)-";
}
