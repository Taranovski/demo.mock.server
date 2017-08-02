package com.example.demo.mock.server.repository;

import org.springframework.stereotype.Component;

/**
 * Created by OTARANOVSKYI on 27.07.2017.
 */
@Component
public class HostConfigurationStorage {

    private static final String DEFAULT_HOST = "localhost";
    private static final Integer DEFAULT_PORT = 18080;
    private static final Integer DEFAULT_PROXY_PORT = 18082;
    private String host;
    private Integer port;
    private Integer proxyPort;

    public String getHost() {
        return host == null ? DEFAULT_HOST : host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port == null ? DEFAULT_PORT : port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getProxyPort() {
        return proxyPort == null ? DEFAULT_PROXY_PORT : proxyPort;
    }

    public void setProxyPort(Integer proxyPort) {
        this.proxyPort = proxyPort;
    }
}
