package com.knalx;

/**
 * @since 29.12.16
 **/
public class QuestionItem {
    private String quest;
    private String answer;

    public QuestionItem(String quest, String answer) {
        this.quest = quest;
        this.answer = answer;
    }

    public String getQuest() {
        return quest;
    }

    public void setQuest(String quest) {
        this.quest = quest;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
