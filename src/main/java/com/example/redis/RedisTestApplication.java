package com.example.redis;

import com.example.redis.model.SuppressionEmail;
import com.example.redis.repository.SuppressionEmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisKeyExpiredEvent;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@EnableRedisRepositories(considerNestedRepositories = true, enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
@SpringBootApplication
public class RedisTestApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(RedisTestApplication.class, args);
	}

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	SuppressionEmailRepository suppressionEmailRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		/*
		RedisTemplate 방법

		ValueOperations<String, String> values = restTemplate.opsForValue();
		values.set("test1", "OK");

		//https://www.logicbig.com/how-to/code-snippets/jcode-java-8-date-time-api-instant-plus.html
		restTemplate.expireAt("test1", Instant.now().plus(Duration.ofSeconds(10)));
		log.info("Before Expired: " + values.get("test1"));
		Thread.sleep(10000L);
		log.info("After Expired:" + values.get("test1"));
		*/

		suppressionEmailRepository.save(SuppressionEmail.builder()
														.email("gg@gmail.com")
														.timeToLive(5L).build());

		Optional<SuppressionEmail> optSuppressionEmail = suppressionEmailRepository.findById("gg@gmail.com");
		log.info(optSuppressionEmail.get().toString());

		Thread.sleep(5000L);

		Optional<SuppressionEmail> optSuppressionEmail2 = suppressionEmailRepository.findById("gg@gmail.com");
		optSuppressionEmail2.ifPresentOrElse(
				suppressionEmail -> log.info("Still :{}", suppressionEmail.toString()),
				() -> log.info("Expired Data")
		);

	}

}
