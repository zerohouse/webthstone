package org.next.ws.web;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.Filter;

@Configuration
@EnableAsync
@EnableScheduling
@EnableAutoConfiguration
@EnableWebMvc
@Import(PersistenceConfig.class)
@ComponentScan(
        basePackages = {
                "org.next",
        },
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class)
        }
)
public class AppConfig {

    @Bean
    public Filter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    public Filter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

}
