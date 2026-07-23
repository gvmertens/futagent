package com.futagend.system;

import java.time.Instant;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/system")
public class SystemStatusController {
    private final IntegrationProperties properties;

    public SystemStatusController(IntegrationProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/status")
    public SystemStatus status() {
        var mcp = properties.mcp();
        return new SystemStatus(
                "ok",
                Instant.now(),
                Map.of(
                        "ai", properties.ai().enabled(),
                        "calendar", configured(mcp.calendarUrl()),
                        "weather", configured(mcp.weatherUrl()),
                        "github", configured(mcp.githubUrl())));
    }

    private boolean configured(String value) {
        return value != null && !value.isBlank();
    }

    public record SystemStatus(String status, Instant timestamp, Map<String, Boolean> integrations) {}
}

