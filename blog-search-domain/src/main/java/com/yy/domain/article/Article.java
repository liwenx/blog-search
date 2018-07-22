package com.yy.domain.article;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * 文章实体类
 * @author zhengjm5
 * @date 2018/1/22 20:21
 */
@Document(indexName = "blog", type = "article")
public class Article implements Serializable{

    /**
     * 主键
     */
    @Id
    private Long id;

    /**
     * 编号
     */
    private Long number;

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
    private String createDate;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 删除标志
     */
    private Integer dr;

    /**
     * 用户id
     */
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
