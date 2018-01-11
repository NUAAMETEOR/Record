package cn.edu.nuaa.record;

/**
 * Created by Meteor on 2017/12/19.
 */

public class QuestionWrapper {
    private int questionId;
    private boolean questionAnswer;

    public QuestionWrapper(int questionId, boolean questionAnswer) {
        this.questionId = questionId;
        this.questionAnswer = questionAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(boolean questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
