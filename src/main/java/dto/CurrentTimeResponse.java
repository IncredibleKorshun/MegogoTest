package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentTimeResponse {
    private int utc_offset;
    private Long timestamp_gmt;
    private Long timestamp_local;
    private String timezone;
    private Long timestamp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEEE, MMMM d, yyyy HH:mm:ss aaa XXX", timezone = "Europe/Kiev")
    private Date time_local;
}
