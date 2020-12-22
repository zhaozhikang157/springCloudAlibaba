package cloudH;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Client3Application {

    public static void main(String[] args) {
        SpringApplication.run(Client3Application.class);
    }
}
