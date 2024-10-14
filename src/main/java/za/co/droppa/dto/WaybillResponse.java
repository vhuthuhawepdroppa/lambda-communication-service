package za.co.droppa.dto;

import lombok.Data;

@Data
public class WaybillResponse {
    private String errorDescription;
    private int errorCode;
    private String waybillNumber;
}
