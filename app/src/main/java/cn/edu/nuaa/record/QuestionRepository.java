package cn.edu.nuaa.record;

/**
 * Created by Meteor on 2017/12/20.
 */

public class QuestionRepository {
    private static final QuestionWrapper[] questionWrappers = new QuestionWrapper[]{
            new QuestionWrapper(R.string.question_oceans, true),
            new QuestionWrapper(R.string.question_mideast, false),
            new QuestionWrapper(R.string.question_africa, false),
            new QuestionWrapper(R.string.question_americas, true),
            new QuestionWrapper(R.string.question_asia, true)
    };

    private QuestionRepository() {

    }

    public static final QuestionWrapper[] getQuestionRepository() {
        return questionWrappers;
    }
}
