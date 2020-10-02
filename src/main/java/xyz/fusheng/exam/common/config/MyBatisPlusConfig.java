package xyz.fusheng.exam.common.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @FileName: MyBatisPlusConfig
 * @Author: code-fusheng
 * @Date: 2020/9/1 15:39
 * @version: 1.0
 * Description: MybatisPlus 分页插件配置类
 */

@EnableTransactionManagement
@Configuration
public class MyBatisPlusConfig {

    /**
     * 注册 MyBatisPlus 乐观锁拦截器
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor(){
        return new OptimisticLockerInterceptor();
    }

    /**
     * 注册 MyBatisPlus Page拦截器
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
