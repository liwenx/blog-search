package com.yy.rpc.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 搜索引擎查询字段
 * @author zhengjm5
 * @date 2018/1/22 19:29
 */
public class ElasticsearchQueryParam implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 查询关键字(支持模糊该字段查询)
     */
    private String keyWord;


    /**
     * 排序参数Map<排序字段，排序方式>
     * 排序方式(DESC 或 ASC)
     */
    private Map<String, String> sortFidldMap;


    /**
     * 页容量
     */
    private Integer pageSize;

    /**
     * 第几页
     */
    private Integer pageNumber;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Map<String, String> getSortFidldMap() {
        return sortFidldMap;
    }

    public void setSortFidldMap(Map<String, String> sortFidldMap) {
        this.sortFidldMap = sortFidldMap;
    }
}
