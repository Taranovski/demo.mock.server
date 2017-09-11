package com.example.demo.mock.server.controller;

import com.example.demo.mock.server.service.MockServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by OTARANOVSKYI on 26.07.2017.
 */
@RestController
@RequestMapping(path = "/switch")
public class SwitchController {

    @Autowired
    private MockServerService mockServerService;

    @RequestMapping(method = RequestMethod.POST, path = "/recording")
    public String switchToRecording(){
        mockServerService.switchToRecording();

        return "switched to recording";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/mock")
    public String switchToMock(){
        mockServerService.switchToMock();

        return "switched to mock";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/mockWithFallback")
    public String switchToMockOrFallbackForward(){
        mockServerService.switchToMockWithFallback();

        return "switched to mock with fallback";
    }
}
