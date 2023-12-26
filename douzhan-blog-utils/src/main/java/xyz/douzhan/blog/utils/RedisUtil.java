package xyz.douzhan.blog.utils;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Redis配置工具类
 *
 * @author 斗战圣洋
 * @date 2023/12/26 21:20
 * @since JDK 17
 **/
@Component
public class RedisUtil {
    private static RedisTemplate<String, Object> redisTemplate;

    public RedisUtil(RedisConnectionFactory redisConnectionFactory) {
        RedisUtil.redisTemplate = getRedisTemplate(redisConnectionFactory);
    }

    /**
    * @Description: 自定义RedisTemplate
    * @Param: [redisConnectionFactory]
    * @return: org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.Object>
    * @Author: 斗战圣洋
    * @Date: 2023/12/26 21:22
    */
    public RedisTemplate<String, Object> getRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(createObjectMapper(), Object.class);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }
    /**
    * @Description: 配置objectMapper
    * @Param: []
    * @return: com.fasterxml.jackson.databind.ObjectMapper
    * @Author: 斗战圣洋
    * @Date: 2023/12/26 21:22
    */

    private ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY
        );
        return mapper;
    }


    /**
     * 设置
     *
     * @param key
     * @param val
     */
    public static boolean set(String key, Object val) {
        if (key==null|| key.isEmpty()) {
            return false;
        }
        redisTemplate.opsForValue().set(key, val);
        return true;
    }

    /**
     * 设置附带失效时间
     *
     * @param key
     * @param val
     * @param time
     * @param timeUnit
     * @return
     */
    public static boolean setWithExpire(String key, Object val, long time, TimeUnit timeUnit) {
        if (time <= 0) {
            return false;
        }
        redisTemplate.opsForValue().set(key, val, time, timeUnit);
        return true;
    }

    /**
     * 获取
     *
     * @param key
     * @return
     */
    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 判断是否存在key
     *
     * @param key
     * @return
     */
    public static boolean has(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除
     *
     * @param key
     */
    public static void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(Arrays.asList(key));
            }
        }
    }


}
