package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChannelVideoResponse {
    private int id;
    private int external_id;
    private String title;
    private int video_id;
    public List<Program> programs;
}
