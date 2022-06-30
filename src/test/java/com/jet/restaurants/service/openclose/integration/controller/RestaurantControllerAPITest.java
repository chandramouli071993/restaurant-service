package com.jet.restaurants.service.openclose.integration.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.jet.restaurants.service.restaurants.RestaurantServiceApplication;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static io.restassured.RestAssured.given;

@SpringBootTest(classes = {RestaurantServiceApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RestaurantControllerAPITest {

    @LocalServerPort
    private int randomServerPort;

    private WireMockServer wireMockServer;

    private Response response;

    @BeforeEach
    void beforeEach() {
        startWireMockServer();
    }

    @AfterEach
    void tearDown() {
        stopWireMockServer();
    }

    private void stopWireMockServer() {
        wireMockServer.stop();
    }

    private void startWireMockServer() {
        wireMockServer = new WireMockServer(
                wireMockConfig().port(8888));
        wireMockServer.start();
        configureFor("localhost", wireMockServer.port());
    }

    @Test
    void shouldFetchAllRestaurants() {
        String url = "http://localhost:" + randomServerPort + "/restaurants";
        response = given().when().get(url);
        response.then().statusCode(200);
    }

    @Test
    void shouldFetchRestaurantByName() {
        String name = "name";
        String url = "http://localhost:" + randomServerPort + "/restaurants?name=" + name;
        response = given().when().get(url);
        response.then().statusCode(200);
    }
}
