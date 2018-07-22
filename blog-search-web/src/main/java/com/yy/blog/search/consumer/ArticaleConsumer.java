package com.yy.blog.search.consumer;

import com.yy.common.util.JsonUtil;
import com.yy.domain.article.Article;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 监听增加文章消费者
 * @author zhengjm5
 * @date 2018/1/22 21:01
 */

@Component
public class ArticaleConsumer {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @KafkaListener(id = "articleConsumer", topics = {"zjmTest"})
    public void addArtical(ConsumerRecord<String, String> consumerRecord) throws Exception {
        //文章json字符串
        String articleJson = consumerRecord.value();
        Article article = JsonUtil.fromJson(articleJson, Article.class);
        //将文章放入elasticsearch服务器中
        IndexQuery indexQuery = new IndexQueryBuilder().withId(String.valueOf(article.getId())).withObject(article).build();
        elasticsearchTemplate.index(indexQuery);
    }
}
