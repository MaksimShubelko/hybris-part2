package com.questions.context;

import com.questions.model.TrainingEmailProcessModel;
import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.ModelService;

import javax.annotation.Resource;

public class TrainingEmailContext extends AbstractEmailContext<TrainingEmailProcessModel> {

    private String content;

    @Resource
    private ModelService modelService;

    public void init(final TrainingEmailProcessModel processModel, final EmailPageModel emailPageModel) {
        super.init(processModel, emailPageModel);
        put(EMAIL, processModel.getEmailAddressSendTo());
        put(DISPLAY_NAME, "System");
        setContent(processModel.getContent());

    }

    @Override
    protected BaseSiteModel getSite(TrainingEmailProcessModel businessProcessModel) {
        return businessProcessModel.getSite();
    }

    @Override
    protected CustomerModel getCustomer(TrainingEmailProcessModel businessProcessModel) {
        return businessProcessModel.getCustomer();
    }

    @Override
    protected LanguageModel getEmailLanguage(TrainingEmailProcessModel businessProcessModel) {
        return businessProcessModel.getLanguage();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
