package response;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AssertableResponse {

    private Response response;

    public AssertableResponse assertStatus(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
        return this;
    }

    public <T> T getResponseBodyFromWrapper(TypeReference<T> typeReference) {
        Object content = response
                .jsonPath()
                .get("data");
        return new ObjectMapper().registerModule(new JavaTimeModule()).convertValue(content, typeReference);
    }
}
