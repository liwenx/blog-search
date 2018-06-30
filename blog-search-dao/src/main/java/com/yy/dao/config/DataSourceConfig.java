package com.yy.dao.config;

import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.BindingTableRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yy.common.algorithm.article.ArticleDatabaseShardingAlgorithm;
import com.yy.common.algorithm.article.ArticleTableShardingAlgorithm;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author zhengjm5
 * @date 2018/6/30 15:30
 */

@Configuration
public class DataSourceConfig {

    /**
     * article分库基础表名
     */
    @Value("${article.table.base.name}")
    private String articleBaseTableName;

    /**
     * article分库表名
     */
    @Value("${article.table.sub.name}")
    private String articleSubTableName;

    /**
     * article分库字段
     */
    @Value("${article.datasource.sub.column}")
    private String articleSubDatasourceColumn;

    /**
     * article分表字段
     */
    @Value("${article.table.sub.column}")
    private String articleSubTablecColumn;

    /**
     * 需要动态去application.properties取参数数，改为注入方式
     */
    @Resource(name = "articleDatabaseShardingAlgorithm")
    private ArticleDatabaseShardingAlgorithm articleDatabaseShardingAlgorithm;

    /**
     * 需要动态去application.properties取参数数，改为注入方式
     */
    @Resource(name = "articleTableShardingAlgorithm")
    private ArticleTableShardingAlgorithm articleTableShardingAlgorithm;

    /**
     * 默认数据源(不使用分库分表)
     * @author: zhengjm5
     * @Date: 2018-06-30 15:34:37
     * @return
     */
    @Bean(name="defalutDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.default")
    public DataSource defalutDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置数据源0，数据源的名称最好要有一定的规则，方便配置分库的计算规则
     * @author: zhengjm5
     * @Date: 2018-06-30 15:45:15
     * @return
     */
    @Bean(name="dataSource_0")
    @ConfigurationProperties(prefix = "spring.datasource.datasource_0")
    public DataSource dataSource_0(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置数据源1，数据源的名称最好要有一定的规则，方便配置分库的计算规则
     * @author: zhengjm5
     * @Date: 2018-06-30 15:45:15
     * @return
     */
    @Bean(name="dataSource_1")
    @ConfigurationProperties(prefix = "spring.datasource.datasource_1")
    public DataSource dataSource_1(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置数据源2，数据源的名称最好要有一定的规则，方便配置分库的计算规则
     * @author: zhengjm5
     * @Date: 2018-06-30 15:45:15
     * @return
     */
    @Bean(name="dataSource_2")
    @ConfigurationProperties(prefix = "spring.datasource.datasource_2")
    public DataSource dataSource_2(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 配置数据源规则，即将多个数据源交给sharding-jdbc管理，并且可以设置默认的数据源，
     * 当表没有配置分库规则时会使用默认的数据源
     * @author: zhengjm5
     * @Date: 2018-06-30 15:53:44
     * @param defalutDataSource
     * @param dataSource_0
     * @param dataSource_1
     * @param dataSource_2
     * @return
     */
    @Bean
    public DataSourceRule dataSourceRule(@Qualifier("defalutDataSource") DataSource defalutDataSource,
                                         @Qualifier("dataSource_0") DataSource dataSource_0,
                                         @Qualifier("dataSource_1") DataSource dataSource_1,
                                         @Qualifier("dataSource_2") DataSource dataSource_2){
        //设置分库映射
        Map<String, DataSource> dataSourceMap = Maps.newHashMapWithExpectedSize(4);
        dataSourceMap.put("defalutDataSource", defalutDataSource);
        dataSourceMap.put("dataSource0", dataSource_0);
        dataSourceMap.put("dataSource1", dataSource_1);
        dataSourceMap.put("dataSource_2", dataSource_2);
        //设置默认库，两个库以上时必须设置默认库。默认库的数据源名称必须是dataSourceMap的key之一
        return new DataSourceRule(dataSourceMap, "defalutDataSource");
    }

    /**
     * 配置数据源策略和表策略，具体策略需要自己实现
     * @param dataSourceRule
     * @return
     */
    @Bean
    public ShardingRule shardingRule(DataSourceRule dataSourceRule){
        Assert.hasLength(articleBaseTableName, "articleBaseTableName is null or empty.");
        Assert.hasLength(articleSubTableName, "articleSubTableName is null or empty.");
        Assert.hasLength(articleSubDatasourceColumn, "articleSubDatasourceColumn is null or empty.");
        Assert.hasLength(articleSubTablecColumn, "articleSubTablecColumn is null or empty.");
        //具体分库分表策略
        //blog分表规则
        TableRule articleTableRule = TableRule.builder(articleBaseTableName)
                .actualTables(Arrays.asList(articleSubTableName.split(",")))
                .tableShardingStrategy(new TableShardingStrategy(articleSubTablecColumn, articleTableShardingAlgorithm))
                .dataSourceRule(dataSourceRule)
                .build();

        //绑定表策略，在查询时会使用主表策略计算路由的数据源，因此需要约定绑定表策略的表的规则需要一致，可以一定程度提高效率
        List<BindingTableRule> bindingTableRules = Lists.newArrayList();
        bindingTableRules.add(new BindingTableRule(Arrays.asList(articleTableRule)));
        return ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Arrays.asList(articleTableRule))
                .bindingTableRules(bindingTableRules)
                .databaseShardingStrategy(new DatabaseShardingStrategy(articleSubDatasourceColumn, articleDatabaseShardingAlgorithm))
                .tableShardingStrategy(new TableShardingStrategy(articleSubTablecColumn, articleTableShardingAlgorithm))
                .build();
    }

    /**
     * 创建sharding-jdbc的数据源DataSource，MybatisAutoConfiguration会使用此数据源
     * @param shardingRule
     * @return
     * @throws SQLException
     */
    @Bean(name="dataSource")
    public DataSource shardingDataSource(ShardingRule shardingRule) throws SQLException {
        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

    /**
     * 需要手动配置事务管理器
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactitonManager(@Qualifier("dataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "articleSqlSessionFactory")
    @Primary
    public SqlSessionFactory articleSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //加载全局的配置文件
        bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:sqlmap/sqlmap-config.xml"));
        //加载mapper的配置文件
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:sqlmap/article/*.xml"));
        //设置别名
        bean.setTypeAliasesPackage("com.yy.domain");
        return bean.getObject();
    }

    @Bean(name = "articleSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate articleSqlSessionTemplate(@Qualifier("articleSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
