package com.project.webapi.core.config;

import com.project.webapi.core.config.cache.CacheProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

@Configuration
@EnableCaching
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class CoreRedisConfiguration extends CachingConfigurerSupport {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private CacheProperties cacheProperties;

    /*
    * Kanal-güvenli (thread-safe) bir istemci yapısına sahiptir.
    * (https://github.com/spring-projects/spring-session/issues/789)
    * Lettuce’da bir bağlantı nesnesi bütün kanallar arasında paylaşılabilir olurken,
    * Jedis’te her kanal kendi Jedis bağlantı nesnesini oluşturur.
    * */
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory connectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheConfiguration cacheConfiguration = configuration
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));
        RedisCacheManager manager = RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration).build();
        return manager;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void clearCache() {
        System.out.println("In Clear Cache");
        Jedis jedis = new Jedis(cacheProperties.getHost(), cacheProperties.getPort(), 1000);
        jedis.flushAll();
        jedis.close();
    }

}
