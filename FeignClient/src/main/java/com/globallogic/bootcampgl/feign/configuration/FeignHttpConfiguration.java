package com.globallogic.bootcampgl.feign.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.okhttp.OkHttpClient;

@Configuration
public class FeignHttpConfiguration {

	@Bean
	public OkHttpClient client()
	{
		return new OkHttpClient();
	}
}
