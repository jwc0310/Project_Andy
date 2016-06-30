package example.andy.com.emandy.retrofit;

import java.util.List;

/**
 * 热搜应用
 * Created by Andy on 2016/6/30.
 */
public class HotSearch {
    /**
     * id : 1
     * key_1 : QQ
     * key_2 : 微信
     * key_3 : 讯飞输入法
     * key_4 : 手机淘宝
     * key_5 : 酷我音乐
     * key_6 : 今日头条
     * key_7 : 支付宝
     * key_8 : 借贷宝
     * key_9 : 多看阅读
     */

    private List<HotBean> hot;

    public List<HotBean> getHot() {
        return hot;
    }

    public void setHot(List<HotBean> hot) {
        this.hot = hot;
    }

    public class HotBean {
        private String id;
        private String key_1;
        private String key_2;
        private String key_3;
        private String key_4;
        private String key_5;
        private String key_6;
        private String key_7;
        private String key_8;
        private String key_9;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKey_1() {
            return key_1;
        }

        public void setKey_1(String key_1) {
            this.key_1 = key_1;
        }

        public String getKey_2() {
            return key_2;
        }

        public void setKey_2(String key_2) {
            this.key_2 = key_2;
        }

        public String getKey_3() {
            return key_3;
        }

        public void setKey_3(String key_3) {
            this.key_3 = key_3;
        }

        public String getKey_4() {
            return key_4;
        }

        public void setKey_4(String key_4) {
            this.key_4 = key_4;
        }

        public String getKey_5() {
            return key_5;
        }

        public void setKey_5(String key_5) {
            this.key_5 = key_5;
        }

        public String getKey_6() {
            return key_6;
        }

        public void setKey_6(String key_6) {
            this.key_6 = key_6;
        }

        public String getKey_7() {
            return key_7;
        }

        public void setKey_7(String key_7) {
            this.key_7 = key_7;
        }

        public String getKey_8() {
            return key_8;
        }

        public void setKey_8(String key_8) {
            this.key_8 = key_8;
        }

        public String getKey_9() {
            return key_9;
        }

        public void setKey_9(String key_9) {
            this.key_9 = key_9;
        }
    }
}
