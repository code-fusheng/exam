package xyz.fusheng.exam;

/**
 * @author: code-fusheng
 * @Date: 2020/9/29 23:14
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @FileName: ExamApplication
 * @Author: code-fusheng
 * @Date: 2020/9/29 23:14
 * @version: 1.0
 * Description:
 */

@EnableTransactionManagement
@MapperScan("xyz.fusheng.exam.core.mapper")
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class ExamApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

}
