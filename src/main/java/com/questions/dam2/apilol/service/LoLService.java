package com.questions.dam2.apilol.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.questions.dam2.apilol.entity.Champion;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

@Service
public class LoLService implements ILoLService {

    private final String baseUrl = "https://ddragon.leagueoflegends.com/cdn/11.24.1/data/en_US/champion.json";
    private final RestTemplate restTemplate;

    public LoLService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Champion> getChampions() {
        String response = restTemplate.getForObject(baseUrl, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = null;
        try {
            rootNode = objectMapper.readTree(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JsonNode championsNode = rootNode.path("data");

        List<Champion> champions = new ArrayList<>();
        championsNode.fields().forEachRemaining(field -> {
            Champion champion = new Champion();
            champion.setName(field.getValue().path("name").asText());
            champion.setImage("https://ddragon.leagueoflegends.com/cdn/11.24.1/img/champion/"
                    + field.getValue().path("image").path("full").asText());
            champions.add(champion);
        });
        return champions;
    }

    @Override
    public Champion getRandomChampion(List<Champion> champions) {
        return champions.get(new Random().nextInt(champions.size()));
    }

    @Override
    public boolean verificarNombreCampeon(String nombreIngresado, Champion champion) {
        return nombreIngresado.toUpperCase().equals(champion.getName().toUpperCase());
    }
}
