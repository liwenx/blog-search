package com.yy.service.rpc.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yy.domain.article.Article;
import com.yy.rpc.domain.ElasticsearchQueryParam;
import com.yy.rpc.domain.Result;
import com.yy.rpc.elasticsearch.BlogElasticsearchServiceRpc;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * 博客搜索RPC实现类
 * @author zhengjm5
 * @date 2018/1/24 10:11
 */
@Component
@Service(timeout = 50000)
public class BlogElasticsearchServiceRpcImpl implements BlogElasticsearchServiceRpc {

    /**
     * elastic搜索
     */
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    /**
     * 日志
     */
    private final static Logger logger = Logger.getLogger(BlogElasticsearchServiceRpcImpl.class);

    @Override
    public Result queryListArticleByWord(ElasticsearchQueryParam elasticsearchQueryParam) {
        Result result = new Result(false);
        String keyWord = elasticsearchQueryParam.getKeyWord();
        if (StringUtils.isEmpty(keyWord)) {
            logger.error("未传入有效的关键字！");
            result.setErrorMessage("未传入有效的关键字！");
            result.setSuccess(false);
            return result;
        }
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(queryStringQuery(keyWord));
        Map<String, String> sortFidldMap = elasticsearchQueryParam.getSortFidldMap();
        //排序
        if (MapUtils.isNotEmpty(sortFidldMap)) {
            for (Map.Entry<String, String> entry : sortFidldMap.entrySet()) {
                if (SortOrder.ASC.toString().equals(entry.getValue())) {
                    SortBuilder sortBuilder = SortBuilders.fieldSort(entry.getKey()).order(SortOrder.ASC);
                    builder.withSort(sortBuilder);
                }
                if (SortOrder.DESC.toString().equals(entry.getValue())) {
                    SortBuilder sortBuilder = SortBuilders.fieldSort(entry.getKey()).order(SortOrder.DESC);
                    builder.withSort(sortBuilder);
                }
            }
        }
        //分页
        //页容量
        Integer pageSize = elasticsearchQueryParam.getPageSize();
        //第几页
        Integer pageNumber = elasticsearchQueryParam.getPageNumber();
        if (pageSize != null && pageSize != 0 && pageNumber != null && pageNumber != 0) {
            Pageable pageable = new PageRequest(pageNumber, pageSize);
            builder.withPageable(pageable);
        }
        SearchQuery searchQuery = builder.build();
        try {
            List<Article> articleList = elasticsearchTemplate.queryForList(searchQuery, Article.class);
            result.addDefaultModel("articleList", articleList);
            result.setSuccess(true);
        } catch (Exception ex) {
            logger.error("搜索文章错误：" + ex.getMessage());
            result.setErrorMessage("搜索文章错误：" + ex.getMessage());
        }
        return result;
    }
}
