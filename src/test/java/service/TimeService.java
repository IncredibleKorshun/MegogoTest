package service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import response.AssertableResponse;

public class TimeService extends BaseService{
    @Step("Get Time")
    public static AssertableResponse getTime() {
        Response response = setup().when().get(GET_TIME_URL).then().extract().response();
        return new AssertableResponse(response);
    }
}
