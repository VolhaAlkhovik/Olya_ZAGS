package models.Application;

import lombok.Getter;

import java.util.List;

@Getter
public class GetApplicationResponse  {

    private Integer total;
    private List<ApplicationData> data;
    private String requestId;
}
