package com.yy.rpc.elasticsearch;

import com.yy.rpc.domain.ElasticsearchQueryParam;
import com.yy.rpc.domain.Result;

/**
 * 博客搜索RPC服务
 * @author zhengjm5
 * @date 2018/1/22 19:17
 */
public interface BlogElasticsearchServiceRpc {

    /**
     * 根据关键字全量查询匹配文章(支持排序，分页)
     * @author: zhengjm5
     * @Date: 2018-01-24 09:47:22
     * @param elasticsearchQueryParam
     * @return
     */
    public Result queryListArticleByWord(ElasticsearchQueryParam elasticsearchQueryParam);
}
