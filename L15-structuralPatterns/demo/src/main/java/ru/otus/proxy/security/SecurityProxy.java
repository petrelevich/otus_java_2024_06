package ru.otus.proxy.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecurityProxy implements SecurityAccess {
    private static final Logger logger = LoggerFactory.getLogger(SecurityProxy.class);
    private final SecurityAccess securityAccess;

    public SecurityProxy(SecurityAccess securityAccess) {
        this.securityAccess = securityAccess;
    }

    @Override
    public void access() {
        logger.info("before");
        securityAccess.access();
        logger.info("after");
    }
}
