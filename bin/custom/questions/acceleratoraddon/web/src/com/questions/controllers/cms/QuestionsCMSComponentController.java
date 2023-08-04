package com.questions.controllers.cms;

import com.questions.controllers.QuestionsControllerConstants;
import com.questions.model.QuestionsCMSComponentModel;
import de.hybris.platform.addonsupport.controllers.cms.AbstractCMSAddOnComponentController;
import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static de.hybris.platform.commercefacades.product.ProductOption.QUESTIONS;

@Controller("QuestionsCMSComponentController")
@RequestMapping(value = QuestionsControllerConstants.Actions.Cms.QuestionsCmsComponent)
public class QuestionsCMSComponentController extends AbstractCMSAddOnComponentController<QuestionsCMSComponentModel> {

    @Resource
    private ProductFacade productFacade;

    @Override
    protected void fillModel(HttpServletRequest request, Model model, QuestionsCMSComponentModel component) {
        final ProductModel currentProduct = getRequestContextData(request).getProduct();

        if (Objects.nonNull(currentProduct)) {
            List<ProductOption> options = new ArrayList<>(List.of(QUESTIONS));
            final int numberQuestionsToShow = component.getNumberOfQuestionsToShow();
            ProductData productData = productFacade.getProductForCodeAndOptions(currentProduct.getCode(), options);

            model.addAttribute("questions", productData.getQuestions());
            model.addAttribute("fontSize", component.getFontSize());
            model.addAttribute("numberOfQuestionsToShow", numberQuestionsToShow);
        }
    }

}
