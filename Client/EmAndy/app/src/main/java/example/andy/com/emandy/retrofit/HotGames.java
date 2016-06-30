package example.andy.com.emandy.retrofit;

import java.util.List;

/**
 * 热门游戏
 * Created by Andy on 2016/6/30.
 */
public class HotGames {

    private int dataSize;
    /**
     * id : 74
     * name : 问道
     * iconUrl : http://p16.qhimg.com/t012110dd7b94cd4cee.png
     * apkSize : 223093301
     * downloadUrl : http://www.microvirt.com.cn/new_market/apk/wendao2_xiaoyao_ew08052797743.apk
     * description : null
     * screenshotsUrl : null
     * downloadTimes : 36972
     * packageName : com.gbits.atm.qihoo.juhe.ewan.xyaz
     * from : 官方
     * versionName : null
     * categoryName : null
     */

    private List<ApkBean> apk;

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }

    public List<ApkBean> getApk() {
        return apk;
    }

    public void setApk(List<ApkBean> apk) {
        this.apk = apk;
    }

    public class ApkBean {
        private String id;
        private String name;
        private String iconUrl;
        private String apkSize;
        private String downloadUrl;
        private String description;
        private String screenshotsUrl;
        private String downloadTimes;
        private String packageName;
        private String from;
        private String versionName;
        private String categoryName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public String getApkSize() {
            return apkSize;
        }

        public void setApkSize(String apkSize) {
            this.apkSize = apkSize;
        }

        public String getDownloadUrl() {
            return downloadUrl;
        }

        public void setDownloadUrl(String downloadUrl) {
            this.downloadUrl = downloadUrl;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getScreenshotsUrl() {
            return screenshotsUrl;
        }

        public void setScreenshotsUrl(String screenshotsUrl) {
            this.screenshotsUrl = screenshotsUrl;
        }

        public String getDownloadTimes() {
            return downloadTimes;
        }

        public void setDownloadTimes(String downloadTimes) {
            this.downloadTimes = downloadTimes;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }
    }
}
