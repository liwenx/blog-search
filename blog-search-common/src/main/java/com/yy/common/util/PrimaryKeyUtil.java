package com.yy.common.util;

import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.yy.common.SnowflakeIdWorker;

/**
 * 获取主键方法
 * @author zhengjm5
 * @date 2018/1/24 16:14
 */
public class PrimaryKeyUtil {

    private static SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(1, 0);

    public static DefaultKeyGenerator defaultKeyGenerator = new DefaultKeyGenerator();

    private PrimaryKeyUtil(){

    }

    /**
     * 使用sharding-jdbc获取分布式主键
     * @return
     */
    public static Long getPrimaryKey(){
//        return String.valueOf(snowflakeIdWorker.nextId());
        defaultKeyGenerator.setWorkerId(0);
        return defaultKeyGenerator.generateKey().longValue();
    }
}
