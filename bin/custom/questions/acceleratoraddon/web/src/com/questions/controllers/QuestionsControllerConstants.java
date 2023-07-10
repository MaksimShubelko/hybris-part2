/*
 * Copyright (c) 2019 SAP SE or an SAP affiliate company. All rights reserved.
 */
package com.questions.controllers;

import org.questions.model.QuestionsCMSComponentModel;

/**
 */
public interface QuestionsControllerConstants
{
    interface Actions
    {
        interface Cms
        {
            String _Prefix = "/view/";
            String _Suffix = "Controller";
            String QuestionsCmsComponent = _Prefix + QuestionsCMSComponentModel._TYPECODE + _Suffix;
        }
    }
}
