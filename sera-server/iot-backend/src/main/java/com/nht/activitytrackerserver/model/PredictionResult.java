package com.nht.activitytrackerserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PredictionResult {

    @JsonProperty("class")
    private String predictedClass;

    private double confidence;

}

