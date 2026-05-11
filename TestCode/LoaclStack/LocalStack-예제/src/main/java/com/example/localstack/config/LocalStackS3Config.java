package com.example.localstack.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

@Configuration
public class LocalStackS3Config {

    private static final DockerImageName LOCAL_STACK_IMAGE = DockerImageName.parse("localstack/localstack");

    @Bean(initMethod = "start", destroyMethod = "stop")
    public LocalStackContainer localStackContainer() {
        return new LocalStackContainer(LOCAL_STACK_IMAGE)
                .withServices(LocalStackContainer.Service.S3);
    }

    @Bean
    public AmazonS3 amazonS3(LocalStackContainer localStackContainer) {
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(S3))
                .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                .build();

        amazonS3.createBucket("woo");
        return amazonS3;
    }
}
