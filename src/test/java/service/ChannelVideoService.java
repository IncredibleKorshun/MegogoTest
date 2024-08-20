package service;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import response.AssertableResponse;

import java.util.Collections;

public class ChannelVideoService extends BaseService{

    @Step("Get Channel Video")
    public static AssertableResponse getChannelVideo(Integer videoId) {
        Response response = sendRequestWithQueryParams(Collections.singletonMap("video_ids", videoId)).
                get(GET_CHANNEL_VIDEO_BY_VIDEO_ID_URL).then().extract().response();
        return new AssertableResponse(response);
    }
}
