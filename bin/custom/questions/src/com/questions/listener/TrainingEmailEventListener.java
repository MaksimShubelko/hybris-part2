package com.questions.listener;

import com.questions.event.TrainingEmailEvent;
import com.questions.model.TrainingEmailProcessModel;
import de.hybris.platform.commerceservices.i18n.CommerceCommonI18NService;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.keygenerator.KeyGenerator;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;
import org.apache.log4j.Logger;
import org.questions.model.QuestionModel;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

public class TrainingEmailEventListener extends AbstractEventListener<TrainingEmailEvent> {

    @Resource
    private ModelService modelService;

    @Resource
    private BusinessProcessService businessProcessService;

    @Resource
    private KeyGenerator processCodeGenerator;

    @Resource
    private BaseSiteService baseSiteService;

    @Resource
    private BaseStoreService baseStoreService;

    @Resource
    private CommerceCommonI18NService commerceCommonI18NService;

    @Override
    protected void onEvent(TrainingEmailEvent event) {
        final TrainingEmailProcessModel trainingEmailProcessModel =
                businessProcessService.createProcess("trainingEmail-" + processCodeGenerator.generate(), "trainingEmailProcess");

        trainingEmailProcessModel.setEmailAddressSendTo(event.getEmailAddressSendTo());
        trainingEmailProcessModel.setSite(baseSiteService.getBaseSiteForUID("electronics"));
        trainingEmailProcessModel.setStore(baseStoreService.getBaseStoreForUid("electronics"));
        trainingEmailProcessModel.setLanguage(commerceCommonI18NService.getCurrentLanguage());
        trainingEmailProcessModel.setContent(createContent(event.getQuestions()));
        modelService.save(trainingEmailProcessModel);
        businessProcessService.startProcess(trainingEmailProcessModel);
    }

    protected String createContent(List<QuestionModel> questionModelList) {
        return questionModelList
                .stream()
                .map(this::createStringContent)
                .collect(Collectors.joining("<br><br>"));
    }

    private String createStringContent(QuestionModel questionModel) {
        return  "Product code: " + questionModel.getProduct().getCode() +
                "<br>Question code: " + questionModel.getCode() +
                "<br>Q: " + questionModel.getQuestion() +
                "<br>A: " + questionModel.getAnswer();
    }

}
