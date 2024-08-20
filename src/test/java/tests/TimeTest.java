package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import dto.CurrentTimeResponse;
import io.qameta.allure.Description;
import lombok.SneakyThrows;
import org.testng.annotations.Test;
import service.TimeService;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


public class TimeTest extends BaseTest{

    @Test
    @SneakyThrows
    @Description("Get current time")
    void getCurrentTime() {
        CurrentTimeResponse currentTimeResponse = TimeService.getTime().assertStatus(200)
                .getResponseBodyFromWrapper(new TypeReference<>() {});

        Date dateFromResponse = currentTimeResponse.getTime_local();

        //Note for Java version 17 and later zoneId is changed to 'Europe/Kyiv'
        var currentDate = Date.from(LocalDateTime.now().atZone(ZoneId.of("Europe/Kiev")).toInstant());
        sa.assertThat(dateFromResponse.getDate()).isEqualTo(currentDate.getDate());
        sa.assertThat(dateFromResponse.getMonth()).isEqualTo(currentDate.getMonth());
        sa.assertThat(dateFromResponse.getYear()).isEqualTo(currentDate.getYear());
        sa.assertAll();
    }
}
