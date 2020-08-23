package fr.jbwittner.myguild.server.tools.rest;

import org.springframework.web.client.RestTemplate;

public class BlizzardRestTools implements IRestTools {

    RestTemplate restTemplate;
    String uri;

    public BlizzardRestTools() {
        this.restTemplate = new RestTemplate();
        this.uri="";
    }


    public <T> T get(String url, Class<T> type){
        T result = restTemplate.getForObject(url, type);
        return result;
    }

    
    
}