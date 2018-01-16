package crawler;

import cn.edu.hfut.dmic.webcollector.conf.Configuration;
import cn.edu.hfut.dmic.webcollector.conf.Configured;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.Requester;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.SongEntity;
import neteasy.UrlSource;
import task.db.UpdateOrInsert;
import thread.PoolType;
import thread.TaskInPool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuhu on 18/1/11.
 */
public class MyCrawler extends BreadthCrawler {
    public MyCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);

        addSeed(UrlBean.seed);
        // 设置采集规则为所有类型的网页
        addRegex(RegexStr.allPage);

//        addRegex(RegexStr.songerList);
        this.conf.setConnectTimeout(500000);
        this.conf.setReadTimeout(500000);

        this.setResumable(false);
        this.setThreads(20);


    }


    @Override
    public void visit(Page page, CrawlDatums crawlDatums) {

        if(page.url().matches(RegexStr.song))
        {
            String title = page.doc().title();

            Matcher matcher = Pattern.compile(RegexStr.songNameAndSinger).matcher(title);

            if(matcher.find())
            {
                SongEntity songEntity = new SongEntity();

                songEntity.setName(matcher.group(1));
                songEntity.setSinger(matcher.group(2));

                matcher = Pattern.compile(RegexStr.songArg).matcher(page.url());

                if(matcher.find())
                {
                    songEntity.setId(Integer.valueOf(matcher.group(1)));
                    songEntity.setSingPageUrl("http://music.163.com/#/song?id="+matcher.group(1));
                }

                String infoUrl = "http://music.163.com/api/song/detail/?id=" + songEntity.getId() + "&ids=%5B+" + songEntity.getId() + "%5D";

                songEntity.setInfoUrl(infoUrl);


/** tho old version can be do it;
                OkHttpClient okHttpClient = new OkHttpClient();
                Request request = new Request.Builder().url(infoUrl).build();
                try {
                    Response response = okHttpClient.newCall(request).execute();

                    if(response.isSuccessful())
                    {
                        songEntity.setInfoUrl(infoUrl);
                        String content = response.body().string();

                        JsonParser jsonParser = new JsonParser();
                        JsonObject jsonObject = jsonParser.parse(content).getAsJsonObject();
                        if (jsonObject.has("songs")) {
                            JsonArray array = jsonObject.getAsJsonArray("songs");
                            JsonObject jsonObject1 = (JsonObject) array.get(0);
                            songEntity.setSingSourceUrl(jsonObject1.get("mp3Url").toString());
                        }


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
*/
                String content = new UrlSource().getUrlSource(String.valueOf(songEntity.getId()));

                if(content == null || "".equals(content))
                {
                    TaskInPool.addDoTaskToPool(PoolType.dbtype, new UpdateOrInsert<SongEntity>(songEntity));

                    return;
                }
                JsonParser jsonParser = new JsonParser();

                JsonElement jsonElement = jsonParser.parse(content);
                if(jsonElement == null || "null".equals(jsonElement))
                {
                    return;
                }
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                if (jsonObject.has("data")) {
                    JsonArray jsonElements = jsonObject.getAsJsonArray("data");
                    String url = jsonElements.get(0).toString();
                    String url1 = jsonParser.parse(url).getAsJsonObject().get("url").toString();


                    songEntity.setSingSourceUrl(url1);
                }

                TaskInPool.addDoTaskToPool(PoolType.dbtype, new UpdateOrInsert<SongEntity>(songEntity));


            }




        }

    }
}
