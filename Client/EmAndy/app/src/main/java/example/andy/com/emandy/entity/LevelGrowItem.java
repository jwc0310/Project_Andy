/**
 * FileName:	LevelGrowItem.java
 * Copyright:	偶像计划
 * Author: 		zhangjingjing
 * Description:	<文件描述>
 * History:		2015-12-23 下午2:56:42
 */
package example.andy.com.emandy.entity;

import java.util.List;

/**
 * @author jingjing
 *         <p/>
 *         Last modified by Andy
 */
public class LevelGrowItem {
    private String id;
    private String title;
    private String stage;
    private String isNode;
    private String dataType;
    private String resourceType;
    private long likeCount;
    private long commentCount;
    private long dealCount;
    private long shareCount;
    private String dateTime;
    private String content;
    private String coverSrc;
    private String eventId;

    //added by Andy
    private List<ImgList> imgList; //图片列表
    private String resource;
    private int isLike;            //根据实际情况 1:已赞，0:未赞


    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getId() {
        return id;
    }

    public void setIdString(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getIsNode() {
        return isNode;
    }

    public void setIsNode(String isNode) {
        this.isNode = isNode;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public long getDealCount() {
        return dealCount;
    }

    public void setDealCount(long dealCount) {
        this.dealCount = dealCount;
    }

    public long getShareCount() {
        return shareCount;
    }

    public void setShareCount(long shareCount) {
        this.shareCount = shareCount;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverSrc() {
        return coverSrc;
    }

    public void setCoverSrc(String coverSrc) {
        this.coverSrc = coverSrc;
    }

    public void setImgList(List<ImgList> imgList) {
        this.imgList = imgList;
    }

    public List<ImgList> getImgList() {
        return imgList;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResource() {
        return resource;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public int getIsLike() {
        return isLike;
    }

}
