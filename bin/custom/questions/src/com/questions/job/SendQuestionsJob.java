package com.questions.job;

import com.questions.event.TrainingEmailEvent;
import com.questions.model.SendQuestionsCronJobModel;
import com.questions.service.QuestionService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;
import org.apache.log4j.Logger;
import org.questions.model.QuestionModel;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

public class SendQuestionsJob extends AbstractJobPerformable<SendQuestionsCronJobModel> {

    private LocalDateTime lastRunningTime = LocalDateTime.now();

    @Resource
    private EventService eventService;

    @Resource
    private QuestionService questionService;

    @Override
    public PerformResult perform(SendQuestionsCronJobModel cronJobModel) {
        List<QuestionModel> questions = questionService.findQuestionsByMinCreationDate(lastRunningTime);
        lastRunningTime = LocalDateTime.now();
        TrainingEmailEvent trainingEmailEvent = new TrainingEmailEvent(questions,
                cronJobModel.getEmailAddressSendTo());

        if (!questions.isEmpty()) {
            eventService.publishEvent(trainingEmailEvent);
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }
}
