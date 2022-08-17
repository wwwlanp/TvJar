package com.github.catvod.spider;

import android.content.Context;
import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.utils.okhttp.OkHttpUtil;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Yisou extends Spider {
    private PushAgent z;

    public String searchContent(String str, boolean z) {
        String str2 = "list";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("https://yiso.fun/api/search?name=");
            stringBuilder.append(URLEncoder.encode(str));
            stringBuilder.append("&from=ali");
            JSONArray jSONArray = new JSONObject(z(o.E(stringBuilder.toString(), mF()))).getJSONObject("data").getJSONArray(str2);
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getJSONArray("fileInfos").getJSONObject(0).getString("fileName");
                String string2 = jSONObject.getString("gmtCreate");
                String string3 = jSONObject.getString("url");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("vod_id", string3);
                jSONObject2.put("vod_name", string);
                jSONObject2.put("vod_pic", "https://inews.gtimg.com/newsapp_bt/0/13263837859/1000");
                jSONObject2.put("vod_remarks", string2);
                jSONArray2.put(jSONObject2);
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(str2, jSONArray2);
            return jSONObject3.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    protected static HashMap<String, String> mF() {
        HashMap hashMap = new HashMap();
        hashMap.put("User-Agent", "Mozilla/5.0 (Linux; Android 12; V2049A Build/SP1A.210812.003; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/103.0.5060.129 Mobile Safari/537.36");
        hashMap.put("Referer", "https://yiso.fun/");
        return hashMap;
    }

    public void init(Context context, String str) {
        super.init(context, str);
        PushAgent pushAgent = new PushAgent();
        this.z = pushAgent;
        pushAgent.init(context, str);
    }

    public String detailContent(List<String> list) {
        try {
            return this.z.detailContent(list);
        } catch (Exception e) {
            SpiderDebug.log(e);
            return "";
        }
    }

    public String playerContent(String str, String str2, List<String> list) {
        return this.z.playerContent(str, str2, list);
    }

    protected String z(String str) {
        return str;
    }
}