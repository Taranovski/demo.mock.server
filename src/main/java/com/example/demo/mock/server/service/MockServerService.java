package com.example.demo.mock.server.service;

import com.example.demo.mock.server.service.recording.RecordingService;
import com.example.demo.mock.server.storage.HostConfigurationStorage;
import com.example.demo.mock.server.storage.ResponseRepository;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
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
    private RecordingService recordingService;
    @Autowired
    private EventCriteriaProvider eventCriteriaProvider;
    @Autowired
    private ResponseRepository responseRepository;

    @PostConstruct
    public void init(){
        startServer();
        switchToRecording();
    }

    @PreDestroy
    public void destroy(){
        stopServer();
    }

    public void switchToRecording() {
        restart();

        mockServer
                .dumpToLog()
                .when(HttpRequest.request())
                .forward(HttpForward.forward().withHost(hostConfigurationStorage.getHost()).withPort(hostConfigurationStorage.getPort()));

        recordingService.startRecordingOfEventsKeyAndContent();
    }

    public void switchToMock() {
        restart();

        mockServer.when(HttpRequest.request())
                .callback(new ExpectationCallback() {
                    @Override
                    public HttpResponse handle(HttpRequest httpRequest) {
                        return responseRepository.findResponseByCriteria(eventCriteriaProvider.forRequest(httpRequest));
                    }
                });
    }


    private void startServer() {
        stopServer();
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
        recordingService.stopRecordingEvents();
        startServer();
    }


}
