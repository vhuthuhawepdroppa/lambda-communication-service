package za.co.droppa.service.aws;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import java.io.InputStream;

@Service
public class S3Service {

    private final Region region = Region.EU_WEST_1;

    private final S3Client s3 = S3Client.builder()
            .region(region)
            .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
            .build();

    public InputStream loadFileFromS3(String bucketName, String objectKey) {
        try {
            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(objectKey)
                    .build();

            System.out.println("loading font from s3");
            return s3.getObject(getObjectRequest);

        } catch (Exception e) {
            System.out.println("loading font from s3");
            e.printStackTrace();
        }

        return null;
    }
}


