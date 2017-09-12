package com.example.demo.mock.server.service.strategy;

import com.example.demo.mock.server.repository.HostConfigurationStorage;
import com.example.demo.mock.server.repository.RequestResponseStorage;
import com.example.demo.mock.server.repository.StorageQualifiers;
import com.example.demo.mock.server.util.MockServerEnhancer;
import org.mockserver.client.netty.NettyHttpClient;
import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

@Component
public class ForwardCallback implements ExpectationCallback {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HostConfigurationStorage config;

    private NettyHttpClient httpClient;

    @Autowired
    @Qualifier(StorageQualifiers.CURRENT_STORAGE_TYPE)
    private RequestResponseStorage requestResponseStorage;

    @PostConstruct
    public void initClient() {
        httpClient = MockServerEnhancer.enhanceNettyHttpClient(new NettyHttpClient(), requestResponseStorage::saveRequestAndResponse);
    }

    @Override
    public HttpResponse handle(HttpRequest httpRequest) {
        try {
            return httpClient.sendRequest(httpRequest, new InetSocketAddress(config.getHost(), config.getPort()));
        } catch (Exception e) {
            logger.error("Exception forwarding request " + httpRequest, e);
            return HttpResponse.response("Exception forwarding request " + httpRequest + e).withStatusCode(520);
        }
    }
}
