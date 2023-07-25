package com.questions.event;

import de.hybris.platform.servicelayer.event.events.AbstractEvent;
import org.questions.model.QuestionModel;

import java.util.List;
import java.util.Set;

public class TrainingEmailEvent extends AbstractEvent {

    private List<QuestionModel> questions;
    private String emailAddressSendTo;

    public TrainingEmailEvent(final List<QuestionModel> questions, final String emailAddressSendTo) {
        this.questions = questions;
        this.emailAddressSendTo = emailAddressSendTo;
    }

    public List<QuestionModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        this.questions = questions;
    }

    public String getEmailAddressSendTo() {
        return emailAddressSendTo;
    }

    public void setEmailAddressSendTo(String emailAddressSendTo) {
        this.emailAddressSendTo = emailAddressSendTo;
    }
}
