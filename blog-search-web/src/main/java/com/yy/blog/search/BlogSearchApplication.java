package com.yy.blog.search;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author liwenxing
 * @date 2018/1/17 15:13
 */
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
//排除数据库自动配置
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@Import(FdfsClientConfig.class)
//扫描Spring自动注入
@ComponentScan(basePackages={"com.yy"})
//开启事物管理功能
@EnableTransactionManagement(proxyTargetClass = true)
//@ImportResource(locations={"classpath:sqlmap/sqlmap-config.xml"})
@SpringBootApplication
public class BlogSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogSearchApplication.class ,args);
    }
}
