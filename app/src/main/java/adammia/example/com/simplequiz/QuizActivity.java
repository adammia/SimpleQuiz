package adammia.example.com.simplequiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.Toast;


public class QuizActivity extends AppCompatActivity {
    CollapsingToolbarLayout collapsingToolbarLayout;
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    private Button submitButton;
    private Button tryAgain;
    private EditText answerEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_quiz);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.activity_quiz);
        // recovering the instance state
        answerEditText = (EditText) findViewById(R.id.Q7answer);
        answerEditText.setText("");
        View button = (View) findViewById(R.id.button_submit_question);
        submitButton = (Button) findViewById(R.id.button_submit_question);
        tryAgain = (Button) findViewById(R.id.button_try_again);
        setActionOnScrollUp();
    }

    /**
     * Settings of collapsed toolbar and scrolling
     */
    public void setActionOnScrollUp() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.title));

        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapsedAppbar);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedAppbar);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        setActionOnScrollUp();
    }

    public void showResult(View view) {

        CheckBox Q1_firstCheckbox = (CheckBox) findViewById(R.id.Q11b);
        CheckBox Q1_secondCheckbox = (CheckBox) findViewById(R.id.Q12b);
        CheckBox Q1_thirdCheckbox = (CheckBox) findViewById(R.id.Q13b);
        CheckBox Q1_fourthCheckbox = (CheckBox) findViewById(R.id.Q14b);

        boolean Q1_A1 = Q1_firstCheckbox.isChecked();
        boolean Q1_A2 = Q1_secondCheckbox.isChecked();
        boolean Q1_A3 = Q1_thirdCheckbox.isChecked();
        boolean Q1_A4 = Q1_fourthCheckbox.isChecked();

        RadioButton Q2False = (RadioButton) findViewById(R.id.Q22f);
        boolean secondAnswer = Q2False.isChecked();

        CheckBox Q3_firstCheckbox = (CheckBox) findViewById(R.id.Q31b);
        CheckBox Q3_secondCheckbox = (CheckBox) findViewById(R.id.Q32b);
        CheckBox Q3_thirdCheckbox = (CheckBox) findViewById(R.id.Q33b);
        CheckBox Q3_fourthCheckbox = (CheckBox) findViewById(R.id.Q34b);

        boolean Q3_A1 = Q3_firstCheckbox.isChecked();
        boolean Q3_A2 = Q3_secondCheckbox.isChecked();
        boolean Q3_A3 = Q3_thirdCheckbox.isChecked();
        boolean Q3_A4 = Q3_fourthCheckbox.isChecked();

        RadioButton Q4Appbar = (RadioButton) findViewById(R.id.Q42r);
        boolean fourthAnswer = Q4Appbar.isChecked();

        CheckBox Q5_firstCheckbox = (CheckBox) findViewById(R.id.Q51b);
        CheckBox Q5_secondCheckbox = (CheckBox) findViewById(R.id.Q52b);
        CheckBox Q5_thirdCheckbox = (CheckBox) findViewById(R.id.Q53b);
        CheckBox Q5_fourthCheckbox = (CheckBox) findViewById(R.id.Q54b);

        boolean Q5_A1 = Q5_firstCheckbox.isChecked();
        boolean Q5_A2 = Q5_secondCheckbox.isChecked();
        boolean Q5_A3 = Q5_thirdCheckbox.isChecked();
        boolean Q5_A4 = Q5_fourthCheckbox.isChecked();

        RadioButton Q6Quick = (RadioButton) findViewById(R.id.Q62r);
        boolean sixthAnswer = Q6Quick.isChecked();

        EditText alertDialog = (EditText) findViewById(R.id.Q7answer);
        String seventhAnswerText = String.valueOf(alertDialog.getText());
        boolean seventhAnswer = false;
        if (seventhAnswerText.equals("alert")) {
            seventhAnswer = true;
        }


        CheckBox Q8_firstCheckbox = (CheckBox) findViewById(R.id.Q81b);
        CheckBox Q8_secondCheckbox = (CheckBox) findViewById(R.id.Q82b);
        CheckBox Q8_thirdCheckbox = (CheckBox) findViewById(R.id.Q83b);

        boolean Q8_A1 = Q8_firstCheckbox.isChecked();
        boolean Q8_A2 = Q8_secondCheckbox.isChecked();
        boolean Q8_A3 = Q8_thirdCheckbox.isChecked();

        RadioButton Q9false = (RadioButton) findViewById(R.id.Q92f);
        boolean ninethAnswer = Q9false.isChecked();

        RadioButton Q10true = (RadioButton) findViewById(R.id.Q101t);
        boolean tenthAnswer = Q10true.isChecked();
        int score = calculateScore(Q1_A1, Q1_A2, Q1_A3, Q1_A4, secondAnswer, Q3_A1, Q3_A2, Q3_A3, Q3_A4, fourthAnswer, Q5_A1, Q5_A2, Q5_A3, Q5_A4, sixthAnswer, seventhAnswer, Q8_A1, Q8_A2, Q8_A3, ninethAnswer, tenthAnswer);

        displayScore(score);

    }

    /**
     * Gets and calculate correct answer to question
     *
     * @return Int answer that user has put in CheckBox, RadioButton, EditText R.id.question_answer
     */
    public int calculateScore(boolean Q1_A1, boolean Q1_A2, boolean Q1_A3, boolean Q1_A4, boolean secondAnswer, boolean Q3_A1, boolean Q3_A2, boolean Q3_A3, boolean Q3_A4, boolean fourthAnswer, boolean Q5_A1, boolean Q5_A2, boolean Q5_A3, boolean Q5_A4, boolean sixthAnswer, boolean seventhAnswer, boolean Q8_A1, boolean Q8_A2, boolean Q8_A3, boolean ninethAnswer, boolean tenthAnswer) {

        int score = 0;

        if (Q1_A1 && Q1_A2 && !Q1_A3 && !Q1_A4) {
            score = score + 1;
        }
        ((CheckBox) findViewById(R.id.Q11b)).setTextColor(getResources().getColor(R.color.colorTrue));
        ((CheckBox) findViewById(R.id.Q12b)).setTextColor(getResources().getColor(R.color.colorTrue));

        if (secondAnswer) {
            score = score + 1;
        }
        ((RadioButton) findViewById(R.id.Q22f)).setTextColor(getResources().getColor(R.color.colorTrue));

        if (Q3_A1 && Q3_A2 && !Q3_A3 && !Q3_A4) {
            score = score + 1;
        }
        ((CheckBox) findViewById(R.id.Q31b)).setTextColor(getResources().getColor(R.color.colorTrue));
        ((CheckBox) findViewById(R.id.Q32b)).setTextColor(getResources().getColor(R.color.colorTrue));

        if (fourthAnswer) {
            score = score + 1;
        }
        ((RadioButton) findViewById(R.id.Q42r)).setTextColor(getResources().getColor(R.color.colorTrue));

        if (Q5_A1 && Q5_A2 && !Q5_A3 && !Q5_A4) {
            score = score + 1;
        }
        ((CheckBox) findViewById(R.id.Q51b)).setTextColor(getResources().getColor(R.color.colorTrue));
        ((CheckBox) findViewById(R.id.Q52b)).setTextColor(getResources().getColor(R.color.colorTrue));


        if (sixthAnswer) {
            score = score + 1;
        }
        ((RadioButton) findViewById(R.id.Q62r)).setTextColor(getResources().getColor(R.color.colorTrue));

        if (seventhAnswer) {
            score = score + 1;
            ((EditText) findViewById(R.id.Q7answer)).setTextColor(getResources().getColor(R.color.colorTrue));
        } else {
            ((EditText) findViewById(R.id.Q7answer)).setText(R.string.edittext_rightanswer);
        }

        if (Q8_A1 && !Q8_A2 && !Q8_A3) {
            score = score + 1;
        }
        ((CheckBox) findViewById(R.id.Q81b)).setTextColor(getResources().getColor(R.color.colorTrue));

        if (ninethAnswer) {
            score = score + 1;
        }
        ((RadioButton) findViewById(R.id.Q92f)).setTextColor(getResources().getColor(R.color.colorTrue));

        if (tenthAnswer) {
            score = score + 1;
        }

        ((RadioButton) findViewById(R.id.Q101t)).setTextColor(getResources().getColor(R.color.colorTrue));

        return score;
    }

    /**
     * Display score, set the result and different summary messages
     */
    public void displayScore(int score) {
         //disable to click on answers when show results
        ((CheckBox) findViewById(R.id.Q11b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q12b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q13b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q14b)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q21t)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q22f)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q31b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q32b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q33b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q34b)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q41r)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q42r)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q43r)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q44r)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q51b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q52b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q53b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q54b)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q61r)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q62r)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q63r)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q64r)).setEnabled(false);
        ((EditText) findViewById(R.id.Q7answer)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q81b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q82b)).setEnabled(false);
        ((CheckBox) findViewById(R.id.Q83b)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q91t)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q92f)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q101t)).setEnabled(false);
        ((RadioButton) findViewById(R.id.Q102f)).setEnabled(false);

//Answers evaluate when show results
        switch (score) {
            case 10:
                Toast.makeText(this, getString(R.string.best_100) + score + getString(R.string.congrat), Toast.LENGTH_LONG).show();
                break;
            case 9:
                Toast.makeText(this, getString(R.string.perfect_90) + score + getString(R.string.congrats), Toast.LENGTH_LONG).show();

                break;
            case 8:
                Toast.makeText(this, getString(R.string.excellent_80) + score + getString(R.string.justperfect), Toast.LENGTH_LONG).show();
                break;
            case 7:
                Toast.makeText(this, getString(R.string.excellent_70) + score + getString(R.string.dobetter), Toast.LENGTH_LONG).show();
                break;
            case 6:
                Toast.makeText(this, getString(R.string.welldone_60) + score + getString(R.string.tryagain), Toast.LENGTH_LONG).show();
                break;
            case 5:
                Toast.makeText(this, getString(R.string.goodjob_50) + score + getString(R.string.candobetter), Toast.LENGTH_LONG).show();
                break;
            case 4:
            case 3:
            case 2:
            case 1:
                Toast.makeText(this, getString(R.string.lessthan_30) + score + getString(R.string.candobeter), Toast.LENGTH_LONG).show();
                break;
            case 0:
                Toast.makeText(this, R.string.choose_forall, Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(this, R.string.youdid, Toast.LENGTH_LONG).show();
                break;
        }
        //when show results, it go back to the top of the page
        NestedScrollView scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        boolean fullScroll;
        if (scroll_view.fullScroll(ScrollView.FOCUS_UP)) fullScroll = true;
        else fullScroll = false;


        //change buttons visibility
        submitButton.setVisibility(View.GONE);
        tryAgain.setVisibility(View.VISIBLE);
    }


    /**
     * If tryAgain button is pressed, it redirects the user to start page, and game can start againRestart the quiz and try again
     *
     * @param v
     */
    public void tryAgain(View v) {
        Intent intent = new Intent(QuizActivity.this, MainActivity.class);
        startActivity(intent);
    }

    //when touch outside of EditText, this method hides the keyboard
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * If back button is pressed, it redirects the user to start page
     */
    @Override
    public void onBackPressed() {
        askToClose();
    }

    private void askToClose() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        builder.setMessage(R.string.want_toquit);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}




