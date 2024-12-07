package cn.edu.zust.se.smarttravel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@MapperScan("cn.edu.zust.se.smarttravel.mapper")
public class RouteApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(RouteApplication.class, args);
    }
}
