package com.albumservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class S3BucketDto {

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Setter
    private String key;
}
