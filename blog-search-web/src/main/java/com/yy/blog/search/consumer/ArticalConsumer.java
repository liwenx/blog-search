package com.yy.blog.search.consumer;

import com.yy.common.util.JsonUtil;
import com.yy.domain.Artical.Artical;
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
public class ArticalConsumer {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @KafkaListener(id = "articalConsumer", topics = {"artical"})
    public void addArtical(ConsumerRecord<String, String> consumerRecord) throws Exception {
        //文章json字符串
        String articalJson = consumerRecord.value();
        Artical artical = JsonUtil.fromJson(articalJson, Artical.class);
        //将文章放入elasticsearch服务器中
        IndexQuery indexQuery = new IndexQueryBuilder().withId(artical.getId()).withObject(artical).build();
        elasticsearchTemplate.index(indexQuery);
    }
}
