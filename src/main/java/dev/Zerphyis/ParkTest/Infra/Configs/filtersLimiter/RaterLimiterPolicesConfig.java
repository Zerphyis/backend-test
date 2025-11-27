package dev.Zerphyis.ParkTest.Infra.Configs.filtersLimiter;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.Map;

@Configuration
public class RaterLimiterPolicesConfig {

    @Bean
    public Map<String, Bandwidth> rateLimiterPolicies() {
        return Map.of(
                "ENTRY_POLICY", Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))),
                "EXIT_POLICY", Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))),
                "RESIDENT_POLICY", Bandwidth.classic(20, Refill.intervally(20, Duration.ofMinutes(1))),
                "OFFICIAL_POLICY", Bandwidth.classic(15, Refill.intervally(15, Duration.ofMinutes(1))),
                "DEFAULT", Bandwidth.classic(60, Refill.intervally(60, Duration.ofMinutes(1)))
        );
    }
}

