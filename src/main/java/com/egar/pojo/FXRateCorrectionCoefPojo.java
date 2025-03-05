package com.egar.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FXRateCorrectionCoefPojo {

    @JsonProperty("FXRateCorrectionCoef")
    private List<JsonPojo> FXRateCorrectionCoef;

}
