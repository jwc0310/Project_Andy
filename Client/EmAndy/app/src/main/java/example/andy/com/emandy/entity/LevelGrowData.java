/**
 * FileName:	LevelGrowData.java
 * Copyright:	偶像计划
 * Author: 		zhangjingjing
 * Description:	<文件描述>
 * History:		2015-12-22 下午4:19:36
 */
package example.andy.com.emandy.entity;

import java.util.List;

/**
 * @author Andy
 */
public class LevelGrowData {

    private int totalCount;
    private int pageInvertedIndex;

    private List<LevelGrowItem> list;

    public List<LevelGrowItem> getList() {
        return list;
    }

    public int getPageInvertedIndex() {
        return pageInvertedIndex;
    }

    public void setPageInvertedIndex(int pageInvertedIndex) {
        this.pageInvertedIndex = pageInvertedIndex;
    }

    public void setList(List<LevelGrowItem> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

}
