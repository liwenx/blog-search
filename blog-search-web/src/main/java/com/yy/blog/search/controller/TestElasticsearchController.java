package com.yy.blog.search.controller;

import com.yy.domain.TestArtical;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;


/**
 * elasticsearch测试
 * @author zhengjm5
 * @date 2018/1/21 19:30
 */
@RestController
public class TestElasticsearchController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/add")
    public void testSaveArticleIndex() {
        TestArtical article = new TestArtical();
        article.setTitle("郑佳铭 integreate elasticsearch");
        article.setContent("elasticsearch based on lucene,"
                + "spring-data-elastichsearch based on elaticsearch"
                + ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
        IndexQuery indexQuery = new IndexQueryBuilder().withId(article.getId()).withObject(article).build();
        elasticsearchTemplate.index(indexQuery);
    }

    @RequestMapping("/query")
    public @ResponseBody Object testSearch(String word) {
        QueryStringQueryBuilder queryStringQueryBuilder = new QueryStringQueryBuilder(word);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery(word)).build();
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchQuery("content", word)).build();
        return elasticsearchTemplate.queryForList(searchQuery, TestArtical.class);
    }
}
