package com.evoluum.api.controller;

import java.util.Map;

import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consume")
public class JsonController {

	@GetMapping
	public void process() {
		String url="https://jsonplaceholder.typicode.com/todos/1";
		RestTemplate restTemplate = new RestTemplate();
		String resp = restTemplate.getForObject(url, String.class);

		JsonParser springParser = JsonParserFactory.getJsonParser();
		Map<String, Object> map = springParser.parseMap(resp);

		String mapArray[] = new String[map.size()];
		System.out.println("Items found: " + mapArray.length);

		int i = 0;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " = " + entry.getValue());
				i++;
		}
	}
	
}
