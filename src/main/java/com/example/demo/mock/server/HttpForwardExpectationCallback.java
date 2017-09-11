package com.example.demo.mock.server;

import com.example.demo.mock.server.repository.HostConfigurationStorage;
import com.example.demo.mock.server.util.MockServerEnhancer;
import com.example.demo.mock.server.util.callback.StaticStoringInteractionCallback;
import org.mockserver.client.netty.NettyHttpClient;
import org.mockserver.mock.action.ExpectationCallback;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class HttpForwardExpectationCallback implements ExpectationCallback {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private HostConfigurationStorage config;

    private NettyHttpClient httpClient = MockServerEnhancer.enhanceNettyHttpClient(new NettyHttpClient(), new StaticStoringInteractionCallback());

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
