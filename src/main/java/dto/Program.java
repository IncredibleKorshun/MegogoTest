package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Program {
    private int id;
    private int external_id;
    private String title;
    private Category category;
    private Long start_timestamp;
    private Long end_timestamp;
    private String start;
    private String end;
    private String virtual_object_id;
    private String schedule_type;
}
