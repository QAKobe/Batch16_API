package com.mentoring_1;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InterviewTask {

    private String actualCapital = "Bishkek";
    private String expectedCapital = "";

    @Test
    public void validateCapital() {


        RestAssured.baseURI = "https://restcountries.com";
        RestAssured.basePath = "v3.1/all";

        Response response = RestAssured.given().accept("application/json")
                .when().get().then().statusCode(200).extract().response();

        List<Map<String, Object>> parsed = response.as(new TypeRef<>() {
        });
        List<String> capital;
        for (int i = 0; i < parsed.size(); i++) {

            Map<String, Object> outerMap = parsed.get(i);

            Map<String, Object> innerMap = (Map<String, Object>) outerMap.get("name");

            if (innerMap.get("common").equals("Kyrgyzstan")) {

                capital = (List<String>) outerMap.get("capital");
                expectedCapital = capital.get(0);
            }

        }
        System.out.println("actualCapital = " + actualCapital);
        System.out.println("expectedCapital = " + expectedCapital);
        Assert.assertEquals(expectedCapital, actualCapital);


    }


}
