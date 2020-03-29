package hex.api;

import hex.model.developers.BackendWeb;
import hex.model.developers.DeveloperWeb;
import hex.model.developers.FrontEndWeb;
import hex.model.technologies.TechnologyWeb;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Api {
    public static List<TechnologyWeb> getAllTechnologies(RestTemplate restTemplate) {
        return restTemplate.exchange("https://localhost:3443/api/v2/technologies",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<TechnologyWeb>>() {
                }).getBody();
    }

    public static List<DeveloperWeb> getAllDevelopers(RestTemplate restTemplate) {
        return restTemplate.exchange("https://localhost:3443/api/v2/developers",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<DeveloperWeb>>() {
                }).getBody();
    }

    public static List<TechnologyWeb> getAllBackendTechnologies(RestTemplate restTemplate) {
        return restTemplate.exchange("https://localhost:3443/api/v2/technologies/back-end",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<TechnologyWeb>>() {
                }).getBody();
    }

    public static List<TechnologyWeb> getAllFrontendTechnologies(RestTemplate restTemplate) {
        return restTemplate.exchange("https://localhost:3443/api/v2/technologies/front-end",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<TechnologyWeb>>() {
                }).getBody();
    }

    public static void postFrontEnd(RestTemplate restTemplate, FrontEndWeb developerWeb) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<FrontEndWeb> entity = new HttpEntity<>(developerWeb, headers);
        restTemplate.exchange("https://localhost:3443/api/v2/developers/front-end", HttpMethod.POST, entity, String.class);
    }

    public static void postBackEnd(RestTemplate restTemplate, BackendWeb developerWeb) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<BackendWeb> entity = new HttpEntity<>(developerWeb, headers);
        restTemplate.exchange("https://localhost:3443/api/v2/developers/back-end", HttpMethod.POST, entity, String.class);
    }

    public static void destroyDeveloper(RestTemplate restTemplate, UUID id) {
        restTemplate.delete("https://localhost:3443/api/v2/developers/" + id);
    }

    public static void putFrontEnd(RestTemplate restTemplate, FrontEndWeb developerWeb) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<FrontEndWeb> entity = new HttpEntity<>(developerWeb, headers);
        restTemplate.exchange("https://localhost:3443/api/v2/developers/front-end", HttpMethod.PUT, entity, String.class);
    }

    public static void putBackEnd(RestTemplate restTemplate, BackendWeb developerWeb) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<BackendWeb> entity = new HttpEntity<>(developerWeb, headers);
        restTemplate.exchange("https://localhost:3443/api/v2/developers/back-end", HttpMethod.PUT, entity, String.class);
    }

    public static List<BackendWeb> getBackEndDeveloperById(RestTemplate restTemplate, UUID id) {
        return restTemplate.exchange("https://localhost:3443/api/v2/developers?id=" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<BackendWeb>>() {
                }).getBody();
    }

    public static List<FrontEndWeb> getFrontEndDeveloperById(RestTemplate restTemplate, UUID id) {
        return restTemplate.exchange("https://localhost:3443/api/v2/developers?id=" + id,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<FrontEndWeb>>() {
                }).getBody();
    }

    public static ResponseEntity<String> getDeveloperByIdAsResponseEntity(RestTemplate restTemplate, UUID id) {
        return restTemplate.getForEntity("https://localhost:3443/api/v2/developers?id=" + id, String.class);
    }

    public static List<DeveloperWeb> getDeveloperByName(RestTemplate restTemplate, String name) {
        return restTemplate.exchange("https://localhost:3443/api/v2/developers?name=" + name,
                HttpMethod.GET, null, new ParameterizedTypeReference<List<DeveloperWeb>>() {
                }).getBody();
    }
}
