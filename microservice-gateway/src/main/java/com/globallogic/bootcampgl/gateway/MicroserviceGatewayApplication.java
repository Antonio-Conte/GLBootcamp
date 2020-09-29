package com.globallogic.bootcampgl.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class MicroserviceGatewayApplication {

	public static void main(final String[] args) {
		SpringApplication.run(MicroserviceGatewayApplication.class);
	}

}
