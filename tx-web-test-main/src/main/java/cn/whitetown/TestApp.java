package cn.whitetown;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author taixian
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class TestApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(TestApp.class, args);
    }
}
