package cn.ddossec;

import cn.ddossec.irule.NacosWeightRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AppUser {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(AppUser.class, args);
        String userName = applicationContext.getEnvironment().getProperty("server.port");
        String userAge = applicationContext.getEnvironment().getProperty("spring.application.name");
        System.err.println("server.port :"+userName+"; spring.application.name: "+userAge);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public IRule iRule(){
        return new NacosWeightRule();
    }
}
