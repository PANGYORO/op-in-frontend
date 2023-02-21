package dev.opin.opinbackend.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import io.netty.util.concurrent.ThreadPerTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration {
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(30);
		executor.setQueueCapacity(50);
		executor.setThreadNamePrefix("LSH-ASYNC-");
		executor.initialize();
		return executor;
	}
}
