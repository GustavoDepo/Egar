package com.egar.scheduler;

import com.egar.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JsonSchedulerTask {

    private final FileService fileService;

    @Scheduled(cron = "${timeExpression}")
    public void cronTask() {
        fileService.execute();
    }

}
