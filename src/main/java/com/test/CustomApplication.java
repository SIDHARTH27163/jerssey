package com.test;

import org.glassfish.jersey.server.ResourceConfig;

import com.test.filter.ResponseTimeFilter;



public class CustomApplication extends ResourceConfig {
    public CustomApplication() {
        register(ResponseTimeFilter.class);
        
    }
}