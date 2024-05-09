package com.test.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class ResponseTimeFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final String START_TIME_PROPERTY = "startTime";
    private static final String ACCESS_CONTROL_ALLOW_ORIGIN = "Access-Control-Allow-Origin";
    private static final String ACCESS_CONTROL_ALLOW_METHODS = "Access-Control-Allow-Methods";
    private static final String ACCESS_CONTROL_ALLOW_HEADERS = "Access-Control-Allow-Headers";
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.setProperty(START_TIME_PROPERTY, System.currentTimeMillis());
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        // Add CORS headers to response
        responseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000"); // Replace with your frontend origin
        responseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS");
        responseContext.getHeaders().add(ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type, Authorization");

        // Optionally, you may also check if it's a preflight request and handle it differently
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) {
            // Preflight request, respond with OK status
            responseContext.setStatus(200);
        }

        // Calculate and log response time
        Long startTime = (Long) requestContext.getProperty(START_TIME_PROPERTY);
        if (startTime != null) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            String apiUrl = requestContext.getUriInfo().getPath();
            System.out.println("API: " + apiUrl + " took " + elapsedTime + " milliseconds");
        } else {
            System.out.println("Response time cannot be calculated. Start time property not set.");
        }
    }

}

