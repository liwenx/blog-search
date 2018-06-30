package com.yy.common.algorithm.article;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.SingleKeyDatabaseShardingAlgorithm;
import com.google.common.collect.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * 文章分库策略
 * @author zhengjm5
 * @date 2018/6/24 15:32
 */
@Component(value = "articleDatabaseShardingAlgorithm")
public class ArticleDatabaseShardingAlgorithm implements SingleKeyDatabaseShardingAlgorithm<Long> {

    /**
     * 默认使用的数据库名称
     */
    @Value("${article.default.datasource.name}")
    private String defaultDataSourceName;

    /**
     * 分库数量
     */
    @Value("${article.datasource.number}")
    private int articleDatasourceNumber;

    /**
     * 相等条件
     * @param databaseNames 数据库名称
     * @param shardingValue 条件值
     * @return
     */
    @Override
    public String doEqualSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
        for (String databaseName : databaseNames) {
            if (databaseName.endsWith(Long.parseLong(shardingValue.getValue().toString()) % articleDatasourceNumber + "")) {
                return databaseName;
            }
        }
        return defaultDataSourceName;
    }

    /**
     * In条件
     * @param databaseNames 数据库名称
     * @param shardingValue 条件值
     * @return
     */
    @Override
    public Collection<String> doInSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(databaseNames.size());
        //条件值
        for (Long value : shardingValue.getValues()) {
            for (String databaseName : databaseNames) {
                if (databaseName.endsWith(value % articleDatasourceNumber + "")) {
                    result.add(databaseName);
                }
            }
        }
        return result;
    }

    /**
     * Between条件
     * @param databaseNames 数据库名称
     * @param shardingValue 条件值
     * @return
     */
    @Override
    public Collection<String> doBetweenSharding(Collection<String> databaseNames, ShardingValue<Long> shardingValue) {
        Collection<String> result = new LinkedHashSet<>(databaseNames.size());
        Range<Long> range = (Range<Long>) shardingValue.getValueRange();
        for (Long i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
            for (String each : databaseNames) {
                if (each.endsWith(i % articleDatasourceNumber + "")) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
