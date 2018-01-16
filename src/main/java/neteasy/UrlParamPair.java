package neteasy;

import com.alibaba.fastjson.JSONObject;

/**
 * URL对象 包含参数结构
 */
public class UrlParamPair {
    public String url;
    public JSONObject paras;

    public UrlParamPair() {
        this.paras = new JSONObject();
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public JSONObject getParas() {
        return paras;
    }

    public UrlParamPair addPara(String key, Object value) {
        this.paras.put(key, value.toString());
        return this;
    }


    @Override
    public String toString() {
        return "UrlParamPair{" +
                "url='" + url + '\'' +
                ", paras=" + paras +
                '}';
    }
}
