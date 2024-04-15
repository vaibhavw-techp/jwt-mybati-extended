package com.demo.jwt.JwtMybatisApplication.ratelimit;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitingService {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public Bucket getBucketForUserAndPlan(String username, String plan) {
        String key = username + "-" + plan;
        return buckets.computeIfAbsent(key, k -> createBucketForUserAndPlan(username, plan));
    }

    private Bucket createBucketForUserAndPlan(String username, String plan) {
        Bandwidth bandwidth = getBandwidthForPlan(plan);
        return Bucket.builder().addLimit(bandwidth).build();
    }

    private Bandwidth getBandwidthForPlan(String plan) {
        switch (plan) {
            case "FREE":
                return Bandwidth.classic(5, Refill.intervally(5, Duration.ofHours(1)));
            case "BASIC":
                return Bandwidth.classic(10, Refill.intervally(10, Duration.ofHours(1)));
            case "PROFESSIONAL":
                return Bandwidth.classic(100, Refill.intervally(100, Duration.ofHours(1)));
            default:
                throw new IllegalArgumentException("Unknown pricing plan: " + plan);
        }
    }

    public boolean tryConsume(String username, String plan) {
        Bucket bucket = getBucketForUserAndPlan(username, plan);
        return bucket.tryConsume(1);
    }
}

