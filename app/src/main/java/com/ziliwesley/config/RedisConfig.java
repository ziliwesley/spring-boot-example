package com.ziliwesley.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfig {

    private String host; // spring.redis.host
    private int port; // spring.redis.port
    private int timeout; // spring.redis.timeout

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory conn = new JedisConnectionFactory();

        conn.setHostName(host);
        conn.setPort(port);
        conn.setTimeout(timeout);

        return conn;
    }

    @Bean
    public StringRedisTemplate redisTemplate() {
        final StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
