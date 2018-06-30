package com.yy.common.algorithm.article;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 文章分表策略
 * @author zhengjm5
 * @date 2018/6/30 18:05
 */
@Component(value = "articleTableShardingAlgorithm")
public class ArticleTableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

    /**
     * 默认使用的表名称
     */
    @Value("${article.default.table.name}")
    private String defaultTableName;

    /**
     * 分表数量
     */
    @Value("${article.table.number}")
    private int articleTableNumber;

    /**
     * 相等条件
     * @param tableNames 表名称
     * @param shardingValue 条件值
     * @return
     */
    @Override
    public String doEqualSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        for (String tableName : tableNames) {
            if (tableName.endsWith(Long.parseLong(shardingValue.getValue().toString()) % articleTableNumber + "")) {
                return tableName;
            }
        }
        return defaultTableName;
    }

    /**
     * In条件
     * @param tableNames 表名称
     * @param shardingValue 条件值
     * @return
     */
    @Override
    public Collection<String> doInSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        //条件值
        for (Long value : shardingValue.getValues()) {
            for (String tableName : tableNames) {
                if (tableName.endsWith(value % articleTableNumber + "")) {
                    result.add(tableName);
                }
            }
        }
        return result;
    }

    /**
     * Between条件
     * @param tableNames 表名称
     * @param shardingValue 条件值
     * @return
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> tableNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(tableNames.size());
        Range<Long> range = (Range<Long>) shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : tableNames) {
                if (each.endsWith(i % articleTableNumber + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
