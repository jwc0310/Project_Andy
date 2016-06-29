package example.andy.com.emandy.retrofit;

import java.util.ArrayList;

/**
 * Created by lk on 16/6/29.
 */
public class Names {

    private ArrayList<Name> name;

    public ArrayList<Name> getName() {
        return name;
    }

    public void setName(ArrayList<Name> name) {
        this.name = name;
    }

    class Name{
        private String game_name;
        private String icon;
        private String name;
        private String source;
        private String content;
        private String method;
        private String date;
        private String total;
        private String left;

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }
    }

}
