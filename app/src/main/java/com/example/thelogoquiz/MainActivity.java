package com.example.thelogoquiz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView question;
    Button answer_1, answer_2, answer_3, answer_4;

    List<QuestionItem> questionItems;

    int currentQuestion = 0, correct = 0, wrong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question);
        answer_1 = findViewById(R.id.answer1);
        answer_2 = findViewById(R.id.answer2);
        answer_3 = findViewById(R.id.answer3);
        answer_4 = findViewById(R.id.answer4);
        
        loadQuestion();

        Collections.shuffle(questionItems);
        
        setQuestion(currentQuestion);

        answer_1.setOnClickListener(view -> {
            if(questionItems.get(currentQuestion).getAnswer1().equals(questionItems.get(currentQuestion).getCorrect())){
                correct++;
                Toast.makeText( this, "Correct", Toast.LENGTH_LONG).show();
            } else{
                wrong++;
                Toast.makeText( this, "Incorrect! The Correct Answer is: " + questionItems.get(currentQuestion).getCorrect(), Toast.LENGTH_LONG).show();
            }

            if(currentQuestion < questionItems.size()-1){
                currentQuestion++;
                setQuestion(currentQuestion);
            } else {
                Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                intent.putExtra("correct", correct);
                intent.putExtra("incorrect", wrong);
                startActivity(intent);
                finish();
            }
        });

        answer_2.setOnClickListener(view -> {
            if(questionItems.get(currentQuestion).getAnswer2().equals(questionItems.get(currentQuestion).getCorrect())){
                correct++;
                Toast.makeText( this, "Correct", Toast.LENGTH_LONG).show();
            } else{
                wrong++;
                Toast.makeText( this, "Incorrect! The Correct Answer is: " + questionItems.get(currentQuestion).getCorrect(), Toast.LENGTH_LONG).show();
            }

            if(currentQuestion < questionItems.size()-1){
                currentQuestion++;
                setQuestion(currentQuestion);
            } else {
                Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                intent.putExtra("correct", correct);
                intent.putExtra("incorrect", wrong);
                startActivity(intent);
                finish();
            }
        });

        answer_3.setOnClickListener(view -> {
            if(questionItems.get(currentQuestion).getAnswer3().equals(questionItems.get(currentQuestion).getCorrect())){
                correct++;
                Toast.makeText( this, "Correct", Toast.LENGTH_LONG).show();
            } else{
                wrong++;
                Toast.makeText( this, "Incorrect! The Correct Answer is: " + questionItems.get(currentQuestion).getCorrect(), Toast.LENGTH_LONG).show();
            }

            if(currentQuestion < questionItems.size()-1){
                currentQuestion++;
                setQuestion(currentQuestion);
            } else {
                Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                intent.putExtra("correct", correct);
                intent.putExtra("incorrect", wrong);
                startActivity(intent);
                finish();
            }
        });

        answer_4.setOnClickListener(view -> {
            if(questionItems.get(currentQuestion).getAnswer4().equals(questionItems.get(currentQuestion).getCorrect())){
                correct++;
                Toast.makeText( this, "Correct", Toast.LENGTH_LONG).show();
            } else{
                wrong++;
                Toast.makeText( this, "Incorrect! The Correct Answer is: " + questionItems.get(currentQuestion).getCorrect(), Toast.LENGTH_LONG).show();
            }

            if(currentQuestion < questionItems.size()-1){
                currentQuestion++;
                setQuestion(currentQuestion);
            } else {
                Intent intent = new Intent(getApplicationContext(), EndActivity.class);
                intent.putExtra("correct", correct);
                intent.putExtra("incorrect", wrong);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setQuestion(int num) {
        question.setText(questionItems.get(num).getQuestion());
        answer_1.setText(questionItems.get(num).getAnswer1());
        answer_2.setText(questionItems.get(num).getAnswer2());
        answer_3.setText(questionItems.get(num).getAnswer3());
        answer_4.setText(questionItems.get(num).getAnswer4());
    }

    private void loadQuestion() {
        questionItems = new ArrayList<>();

        String jsonStr = loadJSON();

        try {
            JSONObject jsonObj = new JSONObject(jsonStr);
            JSONArray jsonArray = jsonObj.getJSONArray("questions");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject question = jsonArray.getJSONObject(i);

                String questionString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String answer3String = question.getString("answer3");
                String answer4String = question.getString("answer4");
                String correctString = question.getString("correct");

                questionItems.add(new QuestionItem(questionString, answer1String, answer2String, answer3String, answer4String, correctString));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    private String loadJSON() {
        // Start with Empty JSON
        String json = "";
        try {
            // Open asset
            InputStream is = getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            // Convert file to JSON
            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}