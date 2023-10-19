package api_imotors.api_imotors.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobContainerClientBuilder;

@Service
public class AzureStorageAccountService {
    
    public String uploadFileToAzure(MultipartFile file) throws IOException {
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=projetocloudblob;AccountKey=QSMXHirWuZj6RIHDrITGM6TncvzxZKC29kMJVihgiiNhxX9dAxc7ePMDGt9VzD+KH8JIhJoXngtO+ASt2KEDsg==;EndpointSuffix=core.windows.net";

        BlobContainerClient client = new BlobContainerClientBuilder()
            .connectionString(connectionString)
            .containerName("images")
            .buildClient();

        BlobClient blob = client.getBlobClient(file.getOriginalFilename());

        blob.upload(file.getInputStream(), file.getSize(), true);

        return "https://projetocloudblob.blob.core.windows.net/images/" + file.getOriginalFilename();

    }
    
}