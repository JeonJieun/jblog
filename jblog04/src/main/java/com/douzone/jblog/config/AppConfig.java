package com.douzone.jblog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.douzone.config.app.DBConfig;
import com.douzone.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy  // <!-- auto proxy --> <aop:aspectj-autoproxy />
@ComponentScan({"com.douzone.jblog.service", "com.douzone.jblog.repository", "com.douzone.jblog.aspect"}) // <context:annotation-config />
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {

}
