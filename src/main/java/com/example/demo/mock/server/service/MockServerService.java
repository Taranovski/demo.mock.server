package com.example.demo.mock.server.service;

import com.example.demo.mock.server.HttpForwardExpectationCallback;
import com.example.demo.mock.server.repository.HostConfigurationStorage;
import com.example.demo.mock.server.repository.ResponseRepository;
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
    private ResponseRepository responseRepository;
    @Autowired
    private HttpForwardExpectationCallback forwardCallback;

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

        mockServer.when(HttpRequest.request()).callback(request -> responseRepository.findResponseByCriteria(request));
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
