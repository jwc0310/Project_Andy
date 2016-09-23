package example.andy.com.emandy.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 礼包游戏详情
 * Created by Andy on 2016/7/6.
 */
public class GiftGameData {

    public static final String TYPE = "_type";
    public static final String NAME = "_name";
    public static final String ICON = "_icon";
    public static final String SOURCE = "_source";
    public static final String APP_ID = "_app_id";
    public static final String COUNT = "_count";

    /**
     * gameName : 不良人
     * icon : http://p18.qhimg.com/t0130b2116c49a7eb8f.png
     * source : 百度
     * appid : null
     * gift_count : 3
     */

    private List<GameNameBean> gameName;

    public List<GameNameBean> getGameName() {
        return gameName;
    }

    public void setGameName(List<GameNameBean> gameName) {
        this.gameName = gameName;
    }

    public static class GameNameBean {
        @SerializedName("gameName")
        private String name;
        private String icon;
        private String source;
        private String appid;
        @SerializedName("gift_count")
        private String count;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }
    }
}
