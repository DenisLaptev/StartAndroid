package com.a5androidintern2.book_1_geoquiz;

/**
 * Created by A5 Android Intern 2 on 23.11.2016.
 */

public class Question {
    //Класс Question содержит два вида данных:
    //текст вопроса и правильный ответ (да/нет).

    private int mTextResId;
    private boolean mAnswerTrue;

    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }
}
