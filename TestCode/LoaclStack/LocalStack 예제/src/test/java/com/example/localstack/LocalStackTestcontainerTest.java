package com.example.localstack;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.apache.commons.io.IOUtils;
import org.testcontainers.utility.DockerImageName;
import java.io.IOException;
import java.nio.charset.Charset;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;

@Testcontainers
@DisplayName("s3")
public class LocalStackTestcontainerTest {
    //docker 이미지 명
    private static final DockerImageName LOCALSTACK_NAME = DockerImageName.parse("localstack/localstack");

    //localstackcontainer를 해당 docker 이미지로 s3 서비스 실행
    @Rule
    public LocalStackContainer localStackContainer = new LocalStackContainer(LOCALSTACK_NAME).withServices(S3);

    @Test
    @DisplayName("s3 업로드 테스트")
    public void test() throws IOException {
        //localstack 컨테이너로 AmazonS3 설정 값에 추가.
        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard()
                            .withEndpointConfiguration(localStackContainer.getEndpointConfiguration(S3))
                            .withCredentials(localStackContainer.getDefaultCredentialsProvider())
                            .build();

        //S3 인스턴스로 버킷 생성 및 put, get test
        String bucketName = "woo";
        amazonS3.createBucket(bucketName);
        System.out.println("버킷 생성");

        String key = "aaaa";
        String content = "bbbbb";
        amazonS3.putObject(bucketName, key, content);
        System.out.println("파일 업로드");

        S3Object object = amazonS3.getObject(bucketName, key);
        System.out.println("object.getKey() = " + object.getKey());
        String key2 = object.getKey();
        String content2 = IOUtils.toString(object.getObjectContent(), Charset.forName("UTF-8"));

        assertAll(
                () -> assertEquals(content, content2),
                () -> assertEquals(key, key2)
        );
    }
}
