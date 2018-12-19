package cn.mth.mthuserprovider.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisUtil {
    private static Logger log = Logger.getLogger(RedisUtil.class.getClass());

    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 批量删除对应的value
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception e) {
            log.error("set cache error", e);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            log.error("set cache error", e);
        }
        return result;
    }

    /**
     * 获取所有的键
     * @return
     */
    public List<Object> getAllKey(){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        Set<Serializable> keys = redisTemplate.keys("*");
        HashMap<Object,Object> map = new HashMap<Object, Object>();
        List<Object> objects = operations.multiGet(keys);
        return objects;

    }

    /**
     * 判断是否存在value
     * @param value
     * @return
     */
    public boolean exitValue(String value){
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        List<Object> allKey = getAllKey();
        boolean flag = false;
        for (Object key: allKey) {
            String key1 = (String)key;
            if(exists(key1)){
                return true;
            }
        }
        return false;
    }

    public long increment(final String key , long delta){
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public void setRedisTemplate(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
