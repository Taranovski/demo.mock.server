package com.example.demo.mock.server.controller;

import com.example.demo.mock.server.repository.HostConfigurationStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by OTARANOVSKYI on 28.07.2017.
 */
@RestController
@RequestMapping(path = "/config")
public class ConfigurationController {

    @Autowired
    private HostConfigurationStorage hostConfigurationStorage;

    @RequestMapping(method = RequestMethod.POST, path = "/host")
    public String setHost(@RequestParam("host") String host) {
        hostConfigurationStorage.setHost(host);

        return "host now is: " + host;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/port")
    public String setPort(@RequestParam("port") Integer port) {
        hostConfigurationStorage.setPort(port);

        return "port now is: " + port;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/proxyPort")
    public String setProxyPort(@RequestParam("proxyPort") Integer proxyPort) {
        hostConfigurationStorage.setProxyPort(proxyPort);

        return "proxyPort now is: " + proxyPort;
    }
}
