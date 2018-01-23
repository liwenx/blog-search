package com.yy.domain.Artical;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 文章实体类
 * @author zhengjm5
 * @date 2018/1/22 20:21
 */
@Document(indexName = "blog", type = "artical")
public class Artical implements Serializable{

    /**
     * 主键
     */
    @Id
    private String id;

    /**
     * 浏览量
     */
    private String pageviews;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String describes;

    /**
     * 点赞量
     */
    private String greatNumber;

    /**
     * 踩量
     */
    private String badNumber;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建日期
     */
    private String createdDate;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 类别
     */
    private Integer category;

    /**
     * 是否已发布
     */
    private Integer isPublish;

    /**
     * 时间戳
     */
    private String ts;

    /**
     * 删除标志
     */
    private Integer dr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPageviews() {
        return pageviews;
    }

    public void setPageviews(String pageviews) {
        this.pageviews = pageviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getGreatNumber() {
        return greatNumber;
    }

    public void setGreatNumber(String greatNumber) {
        this.greatNumber = greatNumber;
    }

    public String getBadNumber() {
        return badNumber;
    }

    public void setBadNumber(String badNumber) {
        this.badNumber = badNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getIsPublish() {
        return isPublish;
    }

    public void setIsPublish(Integer isPublish) {
        this.isPublish = isPublish;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }
}
