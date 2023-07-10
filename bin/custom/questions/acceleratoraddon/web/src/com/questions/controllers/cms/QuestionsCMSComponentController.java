package com.questions.controllers.cms;

import com.questions.controllers.QuestionsControllerConstants;
import com.questions.facade.impl.CustomProductFacadeImpl;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import org.questions.model.QuestionsCMSComponentModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller("QuestionsCMSComponentController")
@RequestMapping(value = QuestionsControllerConstants.Actions.Cms.QuestionsCmsComponent)
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionsCMSComponentModel> {

    @Resource(name = "defaultCustomProductFacade")
    private CustomProductFacadeImpl customProductFacade;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionsCMSComponentModel component) {
        final ProductModel currentProduct = getRequestContextData(request).getProduct();

        if (Objects.nonNull(currentProduct)) {
            final int numberQuestionsToShow = component.getNumberOfQuestionsToShow();
            ProductData productData = customProductFacade.getProductWithQuestions(currentProduct);

            model.addAttribute("questions", productData.getQuestions());
            model.addAttribute("fontSize", component.getFontSize());
            model.addAttribute("numberOfQuestionsToShow", numberQuestionsToShow);
        }
    }

}
