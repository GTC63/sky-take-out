package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix ="qcloud.cos")
public class COSClientProperties {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;

}
