package org.yohann.dynamic.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.yohann.dynamic.web.annotation.EnableDynamicWeb;
import org.yohann.dynamic.web.controller.DynamicTableController;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * description:
 *
 * @author Yohann
 * @date 8/11/2023 下午4:54
 */
@EnableDynamicWeb
@EnableSwagger2WebMvc
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        DynamicTableController bean = run.getBean(DynamicTableController.class);
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}
