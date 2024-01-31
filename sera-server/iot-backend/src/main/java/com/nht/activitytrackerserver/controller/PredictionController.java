package com.nht.activitytrackerserver.controller;

import com.nht.activitytrackerserver.model.PredictionResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class PredictionController {

    private final String FASTAPI_URL = "http://localhost:9000/predict";

    @PostMapping("/predict")
    public ResponseEntity<PredictionResult> predict(@RequestPart("file") MultipartFile file) {
        // Resmi FastAPI'ye gönder
        log.info("IMAGE PREDICTING....");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PredictionResult> responseEntity = restTemplate.exchange(
                FASTAPI_URL,
                HttpMethod.POST,
                requestEntity,
                PredictionResult.class
        );

        // FastAPI'den gelen sonucu al
        PredictionResult predictionResult = responseEntity.getBody();

        // Sonucu istemciye gönder
        return new ResponseEntity<>(predictionResult, HttpStatus.OK);
    }
}
