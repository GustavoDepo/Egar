package com.egar.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JsonPojo {

    @JsonProperty("External_ID")
    private String id;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Currency_1")
    private String currency1;

    @JsonProperty("Currency_2")
    private String currency2;

    @JsonProperty("Period_Number")
    private Integer periodNumber;

    @JsonProperty("Period_Unit_Code")
    private String periodUnit;

    @JsonProperty("Positive_Coefficient")
    private Double positiveCoefficient;

    @JsonProperty("Negative_Coefficient")
    private Double negativeCoefficient;

}


