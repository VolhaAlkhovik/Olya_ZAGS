package models.StatusAppl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import models.Application.ApplicationData;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplStatusData {

    private String dateofapplication;
    private String kindofapplication;
    private String statusofapplication;
}
