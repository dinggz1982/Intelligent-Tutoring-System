package edu.gzhu.its.base.redis;

import org.springframework.cache.annotation.CachingConfigurerSupport;

/**
 * Redis缓存配置类
 *
 */
//@Configuration
//@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

	/*@Value("${spring.redis.host}")
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
	}*/
}