package io.ai.comandside.runner;

import io.ai.comandside.command.task.status.CreateTaskStatusCommand;
import io.ai.comandside.constant.CommonConstant;
import io.ai.comandside.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppRunner implements ApplicationRunner {

    private final TaskService taskService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            taskService.createTaskStatus(new CreateTaskStatusCommand(CommonConstant.DEFAULT_TASK_STATUS, "GREY"));
        } catch (Exception e) {
            log.info("Статус по умолчанию уже существует в БД");
        }
    }
}
