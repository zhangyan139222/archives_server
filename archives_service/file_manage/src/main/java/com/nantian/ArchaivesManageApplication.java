package com.nantian;


import com.nantian.util.aop.OperLogAspect;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(
//        exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
//        org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
//        })
@SpringBootApplication
@EnableSwagger2
//@EnableTransactionManagement // 启注解事务管理
public class ArchaivesManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArchaivesManageApplication.class,args);
    }
}
