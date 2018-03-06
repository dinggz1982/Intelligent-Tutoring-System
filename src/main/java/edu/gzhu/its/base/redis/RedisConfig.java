package edu.gzhu.its.base.redis;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.SerializationException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Redis缓存配置类
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	@Value("${spring.redis.host}")
	private String redisHost;

	@Value("${spring.redis.port}")
	private int redisPort;

	@Value("${spring.redis.passwd}")
	private String redisPasswd;

	@Value("${spring.redis.timeOut}")
	private int timeOut = 2000;

	@Value("${spring.redis.max-redirects}")
	private int redirects = 8;

	@Bean
	public RedisClusterConfiguration getClusterConfiguration() {
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("spring.redis.host", redisHost);
		source.put("spring.redis.timeout", timeOut);
		source.put("spring.redis.max-redirects", redirects);
		return new RedisClusterConfiguration(new MapPropertySource("RedisClusterConfiguration", source));
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory cf = null;

		cf = new JedisConnectionFactory();
		cf.setHostName(redisHost);
		cf.setPort(redisPort);
		cf.setPassword(redisPasswd);
		cf.setTimeout(timeOut);
		cf.afterPropertiesSet();
		return cf;
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> rt = new RedisTemplate<String, Object>();
		rt.setConnectionFactory(redisConnectionFactory());
		return rt;
	}

	public static enum StringSerializer implements RedisSerializer<String> {
		INSTANCE;

		public byte[] serialize(String s) throws SerializationException {
			return (null != s ? s.getBytes() : new byte[0]);
		}

		@Override
		public String deserialize(byte[] bytes) throws SerializationException {
			if (bytes.length > 0) {
				return new String(bytes);
			} else {
				return null;
			}
		}
	}

	public static enum LongSerializer implements RedisSerializer<Long> {
		INSTANCE;

		@Override
		public byte[] serialize(Long aLong) throws SerializationException {
			if (null != aLong) {
				return aLong.toString().getBytes();
			} else {
				return new byte[0];
			}
		}

		@Override
		public Long deserialize(byte[] bytes) throws SerializationException {
			if (bytes.length > 0) {
				return Long.parseLong(new String(bytes));
			} else {
				return null;
			}
		}
	}

	public static enum IntSerializer implements RedisSerializer<Integer> {
		INSTANCE;

		@Override
		public byte[] serialize(Integer i) throws SerializationException {
			if (null != i) {
				return i.toString().getBytes();
			} else {
				return new byte[0];
			}
		}

		@Override
		public Integer deserialize(byte[] bytes) throws SerializationException {
			if (bytes.length > 0) {
				return Integer.parseInt(new String(bytes));
			} else {
				return null;
			}
		}
	}
}