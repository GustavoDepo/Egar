package com.egar.service;

import com.egar.pojo.Response;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class ReportService {

    public void htmlReport(String path, Response response, String fileName) throws IOException {
        var folder = new File(path);
        Path errorReportPath = Path.of(path, "report" + folder.listFiles().length + ".html");
        var templatePath = ReportService.class.getResourceAsStream("/templates/errorReport.html");
        var template = new String(templatePath.readAllBytes(), StandardCharsets.UTF_8);
        String errorHtml = template.replace("{{fileName}}", fileName)
                            .replace("{{response}}", response.toString());
        Files.writeString(errorReportPath, errorHtml);
    }

}
