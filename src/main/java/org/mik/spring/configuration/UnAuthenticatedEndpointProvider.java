package org.mik.spring.configuration;

public class UnAuthenticatedEndpointProvider {


    public static final String[] DEFAULT_UNAUTHENTICATED_ENDPOINTS = new String[] {
        "/v2/api-docs",
        "/configuration",
            "/swagger*/**",
            "/actuator/**"
    };

    public String[] getUnAuthenticated() {
        return DEFAULT_UNAUTHENTICATED_ENDPOINTS;
    }
}
