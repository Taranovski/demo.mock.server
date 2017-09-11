package com.example.demo.mock.server.service;

import com.example.demo.mock.server.service.strategy.ForwardCallback;
import com.example.demo.mock.server.service.strategy.ReplayCallback;
import com.example.demo.mock.server.service.strategy.ReplayOrForwardCallback;
import com.example.demo.mock.server.repository.HostConfigurationStorage;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by OTARANOVSKYI on 26.07.2017.
 */
@Component
public class MockServerService {

    private ClientAndServer mockServer;

    @Autowired
    private HostConfigurationStorage hostConfigurationStorage;
    @Autowired
    private ReplayCallback replayCallback;
    @Autowired
    private ForwardCallback forwardCallback;
    @Autowired
    private ReplayOrForwardCallback fallbackCallback;

    @PostConstruct
    public void init() {
        startServer();
        switchToRecording();
    }

    @PreDestroy
    public void destroy() {
        stopServer();
    }

    public void switchToRecording() {
        restart();

        mockServer.when(HttpRequest.request()).callback(forwardCallback);
    }

    public void switchToMock() {
        restart();

        mockServer.when(HttpRequest.request()).callback(replayCallback);
    }

    public void switchToMockWithFallback() {
        restart();

        mockServer.when(HttpRequest.request()).callback(fallbackCallback);
    }

    private void startServer() {
        mockServer = ClientAndServer.startClientAndServer(hostConfigurationStorage.getProxyPort());
    }

    private void stopServer() {
        if (mockServer != null) {
            mockServer.stop();
            mockServer = null;
        }
    }

    private void restart() {
        stopServer();
        startServer();
    }

}
