package com.yy.rpc.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhengjm5
 * @date 2018/1/22 19:29
 */
public class ElasticsearchQueryParam implements Serializable{

    /**
     * 查询关键字(支持模糊该字段查询)
     */
    private String keyWord;

    /**
     * 排序字段(支持多字段排序)
     */
    private List<String> sortFieldList;

    /**
     * 排序方式(DESC 或 ASC)
     */
    private String sortType;

    /**
     * 页容量
     */
    private Integer pageSize;

    /**
     * 第几页
     */
    private Integer pageNumber;
}
