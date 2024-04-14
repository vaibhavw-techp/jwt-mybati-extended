package com.demo.jwt.JwtMybatisApplication.config.jwt;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
public class RateLimitingService {

    private final Map<String, Bucket> planBuckets = new HashMap<>();

    public RateLimitingService() {
        // Initialize plan buckets with default values
        planBuckets.put("FREE", createBucket(5));
        planBuckets.put("PRO", createBucket(10));
    }

    private Bucket createBucket(int requestsPerMinute) {
        Bandwidth limit = Bandwidth.classic(requestsPerMinute, Refill.intervally(requestsPerMinute, Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }

    public boolean tryConsume(String plan) {
        Bucket bucket = planBuckets.get(plan);
        if (bucket == null) {
            return false; // Unknown plan, deny access
        }
        return bucket.tryConsume(1);
    }
}


