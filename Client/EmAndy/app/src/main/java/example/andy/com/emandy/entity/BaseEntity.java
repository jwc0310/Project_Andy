package example.andy.com.emandy.entity;

import java.io.Serializable;

/**
 * Created by andy on 16/5/26.
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String code;
    protected String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
