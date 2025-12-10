package dev.Zerphyis.ParkTest.Infra.Configs.filtersLimiter;

import io.github.bucket4j.*;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {

    private final Map<String, Bucket> bucketCache = new ConcurrentHashMap<>();
    private final Map<String, Bandwidth> policies;

    public RateLimiterService(Map<String, Bandwidth> rateLimiterPolicies) {
        this.policies = rateLimiterPolicies;
    }

    private Bucket createBucket(String policyKey) {
        Bandwidth limit = policies.getOrDefault(policyKey, policies.get("DEFAULT_POLICY"));
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    private Bucket resolveBucket(String ip, String policyKey) {
        String key = ip + "::" + policyKey;
        return bucketCache.computeIfAbsent(key, k -> createBucket(policyKey));
    }

    public RateLimitResult tryConsume(String ip, String policyKey) {
        Bucket bucket = resolveBucket(ip == null ? "unknown" : ip, policyKey);

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);


        if (probe.isConsumed()) {
            return new RateLimitResult(true, 0);
        }

        return new RateLimitResult(false, probe.getNanosToWaitForRefill());
    }

    public record RateLimitResult(boolean allowed, long retryAfterNanos) {}
}