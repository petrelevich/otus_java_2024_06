package ru.otus.mainpackage.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public record AppConfigForConfigProps(String paramName) {}
