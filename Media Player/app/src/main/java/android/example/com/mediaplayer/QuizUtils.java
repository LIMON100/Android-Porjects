package android.example.com.mediaplayer;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Collections;

public class QuizUtils {

    private static final String CURRENT_SCORE_KEY = "current_score";
    private static final String HIGH_SCORE_KEY = "high_score";
    private static final String GAME_FINISHED = "game_finished";
    private static final int NUM_ANSWERS = 4;

    static ArrayList<Integer> generateQuestion(ArrayList<Integer> remainingSampleIDs) {


        // Shuffle the remaining sample ID's.
        Collections.shuffle(remainingSampleIDs);
        ArrayList<Integer> answers = new ArrayList<>();

        for(int i = 0; i < NUM_ANSWERS; i++){
            if(i < remainingSampleIDs.size()) {
                answers.add(remainingSampleIDs.get(i));
            }
        }

        return answers;
    }


    static int getCurrentScore(Context context){
        SharedPreferences mPreferences = context.getSharedPreferences(context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        return mPreferences.getInt(CURRENT_SCORE_KEY , 0);
    }

    static void setCurrentScore(Context context, int currentScore){
        SharedPreferences mPreferences = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(CURRENT_SCORE_KEY, currentScore);
        editor.apply();
    }

}
