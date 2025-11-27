package dev.Zerphyis.ParkTest.Infra.Configs.filtersLimiter;

import io.github.bucket4j.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final Map<String, Bucket> bucketCache = new ConcurrentHashMap<>();
    private final Map<String, Bandwidth> policies;

    public RateLimiterService(Map<String, Bandwidth> policies) {
        this.policies = policies;
    }

    private Bucket createBucket(String policy) {
        return Bucket.builder()
                .addLimit(policies.get(policy))
                .build();
    }

    private Bucket resolveBucket(String ip, String policy) {
        String key = ip + "::" + policy;
        return bucketCache.computeIfAbsent(key, k -> createBucket(policy));
    }

    public RateLimitResult tryConsume(String ip, String policy) {
        Bucket bucket = resolveBucket(ip == null ? "local" : ip, policy);

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);

        if (probe.isConsumed()) {
            return new RateLimitResult(true, 0);
        }

        return new RateLimitResult(false, probe.getNanosToWaitForRefill());
    }

    public record RateLimitResult(boolean allowed, long retryAfterNanos) {}
}
