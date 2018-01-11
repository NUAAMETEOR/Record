package cn.edu.nuaa.record;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Meteor on 2017/12/19.
 */

public class QuestionListener implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        Context context = MainActivity.getActivityFromView(view);
        if (context!=null && context instanceof MainActivity) {
            int buttonId = view.getId();
            if (buttonId == R.id.false_button || buttonId == R.id.true_button) {
                boolean choice = (buttonId == R.id.true_button ? true : false);
                ((MainActivity) context).showResult(choice);
            } else if (buttonId == R.id.prev_button || buttonId == R.id.next_button) {
                MainActivity mainActivity = (MainActivity) context;
                int currentIndex=mainActivity.getCurrentIndex();
                if (buttonId == R.id.prev_button) {
                    currentIndex = (currentIndex + QuestionRepository.getQuestionRepository().length - 1) % QuestionRepository.getQuestionRepository().length;
                } else {
                    currentIndex=(currentIndex+1)%QuestionRepository.getQuestionRepository().length;
                }
                mainActivity.setCurrentIndex(currentIndex);
                mainActivity.updateQuestion();
            }
        } else {
            Toast.makeText(context.getApplicationContext(), "context not correct", Toast.LENGTH_SHORT).show();
        }
    }
}
