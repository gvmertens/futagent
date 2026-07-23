package com.futagend.system;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class SystemStatusControllerTest {
    @Test
    void fut001_reports_optional_integrations_without_exposing_values() {
        var properties = new IntegrationProperties(
                new IntegrationProperties.Ai(false),
                new IntegrationProperties.Mcp("", "https://weather.invalid", null));

        var status = new SystemStatusController(properties).status();

        assertThat(status.status()).isEqualTo("ok");
        assertThat(status.integrations()).containsEntry("ai", false)
                .containsEntry("calendar", false)
                .containsEntry("weather", true)
                .containsEntry("github", false);
        assertThat(status.toString()).doesNotContain("weather.invalid");
    }
}

