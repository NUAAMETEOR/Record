package cn.edu.nuaa.record;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import cn.edu.nuaa.common.Common;

public class MainActivity extends AppCompatActivity {

    private Button   trueButton;
    private Button   falseButton;
    private Button   cheatButton;
    private TextView textView;
    private Button   prevButton;
    private static final String LOG_TAG = MainActivity.class.getName();
    private ImageButton nextButton;
    private              int     currentIndex                = 0;
    private static final String  CURRENT_INDEX               = "index";
    public static final  String  ANSWER_KEY                  = "answer";
    public static final  int     CHEAT_ACTIVITY_REQUEST_CODE = 1;
    private              boolean userCheated                 = false;
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, cn.edu.nuaa.common.Common.getParentaMethod() + " called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(LOG_TAG, Common.getParentaMethod() + " called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, Common.getParentaMethod() + " called");
    }

    /**
     * Dispatch onPause() to fragments.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(LOG_TAG, Common.getParentaMethod() + " called");
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOG_TAG, Common.getParentaMethod() + " called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, Common.getParentaMethod() + " called");
    }

    private void initControls() {
        QuestionListener listener = new QuestionListener();
        trueButton = findViewById(R.id.true_button);
        trueButton.setOnClickListener(listener);
        trueButton.setText(R.string.true_button);

        falseButton = findViewById(R.id.false_button);
        falseButton.setOnClickListener(listener);
        falseButton.setText(R.string.false_button);

        textView = findViewById(R.id.textView);
        updateQuestion();

        prevButton = findViewById(R.id.prev_button);
        prevButton.setOnClickListener(listener);
        prevButton.setText(R.string.prev_button);

        nextButton = findViewById(R.id.next_button);
        nextButton.setOnClickListener(listener);
        //nextButton.setText(R.string.next_button);
        cheatButton = findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CheatActivity.class);
                intent.putExtra(ANSWER_KEY, QuestionRepository.getQuestionRepository()[currentIndex].getQuestionAnswer());
                MainActivity.this.startActivityForResult(intent, CHEAT_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void showResult(boolean choice) {
        int tipId = 0;
        if (!userCheated) {
            tipId = (QuestionRepository.getQuestionRepository()[currentIndex].getQuestionAnswer() == choice ? R.string.right_tip : R.string.wrong_tip);
        } else {
            tipId = R.string.judgment_toast;
        }
        Toast.makeText(this, tipId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0);
        }
        setContentView(R.layout.activity_main);
        initControls();
        Log.d(LOG_TAG, Common.getParentaMethod() + " called");
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void updateQuestion() {
        textView.setText(QuestionRepository.getQuestionRepository()[currentIndex].getQuestionId());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_INDEX, currentIndex);
    }

    @Nullable
    public static Activity getActivityFromView(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (CHEAT_ACTIVITY_REQUEST_CODE == requestCode) {
            userCheated = data.getBooleanExtra(CheatActivity.USER_CHEAT_KEY, true);
        }
    }
}
