package Main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
	@EnableEurekaClient
	public class Main{

		//private static long id = 0;


		public static void main(final String[] args ){
			SpringApplication.run(Main.class);
		}
	}