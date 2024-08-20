package service;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Objects;

public class BaseService {

    protected static final String GET_TIME_URL = "/time";
    protected static final String GET_CHANNEL_VIDEO_BY_VIDEO_ID_URL = "/channel";

    protected static RequestSpecification setup() {
        RestAssured.baseURI = "https://epg.megogo.net";
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(),
                        new ResponseLoggingFilter(),
                        new AllureRestAssured());
    }

    protected static RequestSpecification sendRequestWithQueryParams(Map<String, Object> queryParams) {
        RequestSpecification rs = setup().when();
        if (Objects.isNull(queryParams)) {
            return rs;
        } else {
            return rs.queryParams(queryParams);
        }
    }
}
