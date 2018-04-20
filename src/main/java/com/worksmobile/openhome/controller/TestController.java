package com.worksmobile.openhome.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

	@RequestMapping("/test")
	public Object test(HttpServletResponse response) {
		Map<String, String> result = new HashMap<>();
		for(int i = 0; i < ThreadLocalRandom.current().nextInt(10000); i++) {
			result.put(i + "", UUID.randomUUID().toString());
		}
		return result;
	}
}
