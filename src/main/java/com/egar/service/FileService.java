package com.egar.service;

import com.egar.client.JsonClient;
import com.egar.pojo.FXRateCorrectionCoefPojo;
import com.egar.pojo.JsonPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;

@Service
@RequiredArgsConstructor
public class FileService {

    private static final String PROCESSED = "data/processed";
    private static final String FAILED = "data/failed";
    private static final String REPORT = "data/html";

    private final JsonClient jsonClient;
    private final ObjectMapper objectMapper;
    private final ReportService reportService;
    private final Logger logger = LoggerFactory.getLogger(FileService.class);


    @Value("${filesPath.files}")
    private String path;

    public void execute(){
        var folder = new File(path);
        if (ObjectUtils.isEmpty(folder.listFiles())) return;
        var listOfFiles = List.of(folder.listFiles());
        for (var file : listOfFiles) {
            if (file.isFile()) {
                var processedPath = Path.of(PROCESSED, file.getName());
                var failedPath = Path.of(FAILED, file.getName());
                try {
                    Files.move(file.toPath(), processedPath , ATOMIC_MOVE);
                    var json = objectMapper.readValue(new File(processedPath.toUri()),
                            new TypeReference<FXRateCorrectionCoefPojo>(){});

                    var response = jsonClient.putJson(json);

                    if (response.getStatusCode() == HttpStatus.OK){
                        if (!response.getBody().getSuccess()){
                            reportService.htmlReport(REPORT, response.getBody(), file.getName());
                        }
                    } else {
                        Files.move(processedPath, failedPath , ATOMIC_MOVE);
                    }
                } catch (IOException e) {
                    logger.error("Error processing file: {}", file.getName(), e);
                }
            }
        }
    }
}
