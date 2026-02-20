package moviePoster.com.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CacheService {

    private final RedisTemplate<String,Object> template;

    public CacheService(RedisTemplate<String,Object> template) {
        this.template = template;
    }

    public void put(String key, String value, Duration ttl){
        template.opsForValue().set(key,value,ttl);
    }

    public Object get(String key){
        return template.opsForValue().get(key);
    }

    public void delete(String key){
        template.delete(key);
    }
}
