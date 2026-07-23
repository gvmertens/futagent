package com.futagend.system;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "futagend")
public record IntegrationProperties(Ai ai, Mcp mcp) {
    public record Ai(boolean enabled) {}
    public record Mcp(String calendarUrl, String weatherUrl, String githubUrl) {}
}

