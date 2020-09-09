package com.mashup.backend.nawa_invitation_project.common.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import javax.annotation.PostConstruct;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@NoArgsConstructor
public class AwsS3Service {

  private AmazonS3 awsS3Client;

  @Value("${cloud.aws.credentials.accessKey}")
  private String accessKey;

  @Value("${cloud.aws.credentials.secretKey}")
  private String secretKey;

  @Value("${cloud.aws.s3.bucket}")
  private String bucket;

  @Value("${cloud.aws.region.static}")
  private String region;

  @PostConstruct
  public void setAwsS3Client() {
    AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

    awsS3Client = AmazonS3ClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(credentials))
        .withRegion(this.region)
        .build();
  }

  public String upload(MultipartFile file) throws IOException {
    StringBuffer stringBuffer = new StringBuffer(file.getOriginalFilename());
    Timestamp currentTimeStamp = new Timestamp(new Date().getTime());
    stringBuffer.append("-");
    stringBuffer.append(currentTimeStamp);
    String fileName = stringBuffer.toString();

    awsS3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
        .withCannedAcl(CannedAccessControlList.PublicRead));

    return awsS3Client.getUrl(bucket, fileName).toString();
  }
}
