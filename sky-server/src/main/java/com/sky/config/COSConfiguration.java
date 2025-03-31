package com.sky.config;

import com.sky.properties.COSClientProperties;
import com.sky.utils.COSClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class COSConfiguration {
    @ConditionalOnMissingBean
    @Bean
    public COSClientUtil cosClientUtil(COSClientProperties cosClientProperties) {
        return new COSClientUtil(cosClientProperties);
    }

    ;
}
