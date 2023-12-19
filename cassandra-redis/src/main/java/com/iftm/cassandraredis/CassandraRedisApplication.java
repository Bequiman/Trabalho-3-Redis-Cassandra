package com.iftm.cassandraredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CassandraRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(CassandraRedisApplication.class, args);
	}

}
