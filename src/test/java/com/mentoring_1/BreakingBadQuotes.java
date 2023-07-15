package com.mentoring_1;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class BreakingBadQuotes {


    @Test
    public void findJessePinkManWords(){

        RestAssured.baseURI = "https://api.breakingbadquotes.xyz";
        RestAssured.basePath = "v1/quotes";

        Response response = RestAssured.given().accept("application/json").when()
                .get().then().statusCode(200).extract().response();


        List<Map<String, Object>> parsed = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        for (int i = 0; i < parsed.size(); i++) {

            Map<String, Object> map = parsed.get(i);

            if (map.containsValue("Jesse Pinkman") || map.containsValue("Walter White")){
                System.out.println(map.get("quote"));
            }

        }


    }

    @Test
    public void getSeveralQuotes(){

        RestAssured.baseURI = "https://api.breakingbadquotes.xyz";
        RestAssured.basePath = "v1/quotes";

        Response response = RestAssured.given().accept("application/json")
                .when()
                .get("/10")
                .then()
                .statusCode(200)
                .extract().response();

        List<Map<String, String>> parsed = response.as(new TypeRef<List<Map<String, String>>>() {
        });

        for (int i = 0; i < parsed.size(); i++) {

            Map<String, String> map = parsed.get(i);

            System.out.println(map.get("quote"));
            System.out.println(map.get("author"));

        }





    }
}
