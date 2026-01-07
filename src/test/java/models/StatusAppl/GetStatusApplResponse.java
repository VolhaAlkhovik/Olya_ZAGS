package models.StatusAppl;

import lombok.Data;
import models.Application.ApplicationData;

import java.util.List;

@Data

public class GetStatusApplResponse {

    private ApplStatusData data;
    private String requestId;
}
