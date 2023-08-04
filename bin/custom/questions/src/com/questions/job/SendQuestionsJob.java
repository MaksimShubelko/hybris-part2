package com.questions.job;

import com.questions.model.SendQuestionsCronJobModel;
import com.questions.model.TrainingEmailProcessModel;
import com.questions.service.QuestionService;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import com.questions.model.QuestionModel;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class SendQuestionsJob extends AbstractJobPerformable<SendQuestionsCronJobModel> {

    @Resource
    private EventService eventService;

    @Resource
    private QuestionService questionService;

    @Resource
    private BusinessProcessService businessProcessService;

    @Resource
    private KeyGenerator processCodeGenerator;

    @Resource
    private BaseSiteService baseSiteService;

    @Resource
    private BaseStoreService baseStoreService;

    @Resource
    private ModelService modelService;

    @Resource
    private CommerceCommonI18NService commerceCommonI18NService;

    @Override
    public PerformResult perform(SendQuestionsCronJobModel cronJobModel) {
        List<QuestionModel> questions = questionService
                .findQuestionsByMinCreationDate(convertToLocaleDateTime(cronJobModel.getLastRunnedTime()));
        cronJobModel.setLastRunnedTime(getNowTime());

        if (!questions.isEmpty()) {
            final TrainingEmailProcessModel trainingEmailProcessModel = businessProcessService
                    .createProcess("trainingEmail-" + processCodeGenerator.generate(), "trainingEmailProcess");

            trainingEmailProcessModel.setEmailAddressSendTo(cronJobModel.getEmailAddressSendTo());
            trainingEmailProcessModel.setSite(baseSiteService.getBaseSiteForUID("electronics"));
            trainingEmailProcessModel.setStore(baseStoreService.getBaseStoreForUid("electronics"));
            trainingEmailProcessModel.setLanguage(commerceCommonI18NService.getCurrentLanguage());
            trainingEmailProcessModel.setQuestions(questions);
            modelService.save(trainingEmailProcessModel);
            modelService.save(cronJobModel);
            businessProcessService.startProcess(trainingEmailProcessModel);
        }

        return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
    }

    private LocalDateTime convertToLocaleDateTime(Date lastStartDate) {
        return lastStartDate
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private Date getNowTime() {
        return Date.from(LocalDateTime
                .now()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }
}
