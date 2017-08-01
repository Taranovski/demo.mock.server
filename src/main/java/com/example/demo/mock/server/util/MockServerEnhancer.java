package com.example.demo.mock.server.util;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockserver.client.netty.NettyHttpClient;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;

import java.net.InetSocketAddress;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by OTARANOVSKYI on 01.08.2017.
 */
public class MockServerEnhancer {

    public static NettyHttpClient enhanceNettyHttpClient(final NettyHttpClient original, final InteractionCallback interactionCallback) {
        NettyHttpClient enhancedNettyHttpClient = Mockito.mock(NettyHttpClient.class);

        when(enhancedNettyHttpClient.sendRequest(any(HttpRequest.class))).thenAnswer(new Answer<HttpResponse>() {
            @Override
            public HttpResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                HttpRequest httpRequest = (HttpRequest) invocationOnMock.getArguments()[0];

                long start = System.currentTimeMillis();
                HttpResponse httpResponse = original.sendRequest(httpRequest);
                long duration = System.currentTimeMillis() - start;

                if (interactionCallback != null) {
                    interactionCallback.call(httpRequest, httpResponse, duration);
                }

                return httpResponse;
            }
        });

        when(enhancedNettyHttpClient.sendRequest(any(HttpRequest.class), any(InetSocketAddress.class))).thenAnswer(new Answer<HttpResponse>() {
            @Override
            public HttpResponse answer(InvocationOnMock invocationOnMock) throws Throwable {
                HttpRequest httpRequest = (HttpRequest) invocationOnMock.getArguments()[0];

                long start = System.currentTimeMillis();
                HttpResponse httpResponse = original.sendRequest(httpRequest, (InetSocketAddress) invocationOnMock.getArguments()[1]);
                long duration = System.currentTimeMillis() - start;

                if (interactionCallback != null) {
                    interactionCallback.call(httpRequest, httpResponse, duration);
                }

                return httpResponse;
            }
        });
        return enhancedNettyHttpClient;
    }

}
