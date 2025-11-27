package dev.Zerphyis.ParkTest.Infra.Configs.filtersLimiter;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Map;

@Configuration
public class RateLimiterConfig {

    @Bean
    public Map<String, Bucket> buckets() {
        return Map.of(
                "/parking/entry", createBucket(5, 1),
                "/parking/exit", createBucket(5, 1),
                "/parking/resident", createBucket(10, 2),
                "/parking/official", createBucket(10, 2),
                "/parking/all", createBucket(20, 5)
        );
    }

    private Bucket createBucket(int capacity, int refillTokensPerSecond) {
        return Bucket.builder()
                .addLimit(Bandwidth.classic(
                        capacity,
                        Refill.intervally(refillTokensPerSecond, Duration.ofSeconds(1))
                ))
                .build();
    }
}