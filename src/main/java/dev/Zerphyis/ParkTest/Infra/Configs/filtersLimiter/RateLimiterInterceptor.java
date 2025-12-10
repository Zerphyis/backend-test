package dev.Zerphyis.ParkTest.Infra.Configs.filtersLimiter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class RateLimiterInterceptor implements HandlerInterceptor {

    private final RateLimiterService rateLimiterService;

    private static final Map<String, String> PATH_POLICY_MAP = Map.of(
            "/parking/entry", "ENTRY_POLICY",
            "/parking/exit", "EXIT_POLICY",
            "/parking/resident", "RESIDENT_POLICY",
            "/parking/official", "OFFICIAL_POLICY"
    );

    public RateLimiterInterceptor(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    private String getPolicyKey(HttpServletRequest request) {
        String path = request.getRequestURI();
        if (path.endsWith("/")) {
            path = path.substring(0, path.length() - 1);
        }
        return PATH_POLICY_MAP.getOrDefault(path, "DEFAULT_POLICY");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String clientIp = getClientIp(request);
        String policyKey = getPolicyKey(request);

        RateLimiterService.RateLimitResult result = rateLimiterService.tryConsume(clientIp, policyKey);

        if (!result.allowed()) {
            long retryAfterSeconds = result.retryAfterNanos() / 1_000_000_000;

            final int HTTP_TOO_MANY_REQUESTS = 429;

            response.setStatus(HTTP_TOO_MANY_REQUESTS);
            response.setHeader("Retry-After", String.valueOf(retryAfterSeconds));
            response.setContentType("application/json");

            String jsonError = String.format("{\"error\": \"Rate limit exceeded\", \"retry_after_seconds\": %d}", retryAfterSeconds);
            response.getWriter().write(jsonError);

            return false;
        }

        return true;
    }

    private String getClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        return (forwarded != null) ? forwarded.split(",")[0].trim() : request.getRemoteAddr();
    }
}