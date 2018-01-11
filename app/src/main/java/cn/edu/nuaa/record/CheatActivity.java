package cn.edu.nuaa.record;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Meteor on 2017/12/27.
 */

public class CheatActivity extends AppCompatActivity {

    private boolean currentAnswer = false;
    private Button showAnswerButton;
    public static final String USER_CHEAT_KEY = "cheated";
    private TextView answerTextView;
    private boolean userCheated = false;

    private void setActivityResult() {
        Intent intent = new Intent();
        intent.putExtra(USER_CHEAT_KEY, userCheated);
        setResult(RESULT_OK, intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Intent intent = getIntent();
        if (intent != null) {
            currentAnswer = intent.getBooleanExtra(MainActivity.ANSWER_KEY, false);
        }
        if (savedInstanceState != null) {
            userCheated = savedInstanceState.getBoolean(USER_CHEAT_KEY);
        }
        setActivityResult();
        answerTextView = findViewById(R.id.answerTextView);
        showAnswerButton = findViewById(R.id.showAnswerButton);
        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answerTextView.setText(currentAnswer ? R.string.true_button : R.string.false_button);
                userCheated = true;
                setActivityResult();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(USER_CHEAT_KEY, userCheated);
    }
}
