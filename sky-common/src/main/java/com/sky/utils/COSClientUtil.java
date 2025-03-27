package com.sky.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.sky.properties.COSClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Configuration
@EnableConfigurationProperties(COSClientProperties.class)
public class COSClientUtil {

    COSClientProperties cosClientProperties;

    public String upload(MultipartFile file) throws IOException {
        String region = cosClientProperties.getRegion();
        String bucketName = cosClientProperties.getBucketName();
        String secretId = cosClientProperties.getSecretId();
        String secretKey = cosClientProperties.getSecretKey();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        ClientConfig clientConfig = new ClientConfig(new Region(region));
        InputStream inputStream = file.getInputStream();
        COSClient cosClient = new COSClient(cred, clientConfig);
        String key = UUID.randomUUID().toString() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, new ObjectMetadata());
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult.getRequestId());
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        cosClient.shutdown();
        String url = "https://" + bucketName + ".cos.+" + region + ".myqcloud.com/" + key;
        return url;
    }

}
