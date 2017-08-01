package org.mockserver.mock.action;

import com.example.demo.mock.server.util.MockServerEnhancer;
import com.example.demo.mock.server.util.callback.StaticStoringInteractionCallback;
import org.mockserver.client.netty.NettyHttpClient;
import org.mockserver.model.HttpForward;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * dirty hack for extension of useful framework
 *
 * @author jamesdbloom
 */
public class HttpForwardActionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // http client
    private NettyHttpClient httpClient = new NettyHttpClient();

    public HttpForwardActionHandler() {
        httpClient = MockServerEnhancer.enhanceNettyHttpClient(httpClient, new StaticStoringInteractionCallback());
    }

    public HttpResponse handle(HttpForward httpForward, HttpRequest httpRequest) {
        if (httpForward.getScheme().equals(HttpForward.Scheme.HTTPS)) {
            httpRequest.withSecure(true);
        } else {
            httpRequest.withSecure(false);
        }
        return sendRequest(httpRequest, new InetSocketAddress(httpForward.getHost(), httpForward.getPort()));
    }

    private HttpResponse sendRequest(HttpRequest httpRequest, InetSocketAddress remoteAddress) {
        if (httpRequest != null) {
            try {
                return httpClient.sendRequest(httpRequest, remoteAddress);
            } catch (Exception e) {
                logger.error("Exception forwarding request " + httpRequest, e);
            }
        }
        return null;
    }
}
