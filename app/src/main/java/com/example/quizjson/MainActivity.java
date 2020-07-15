package com.example.quizjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Button b1,b2,b3,b4;

    List<QuestionItems> questionItems;

    int currentQuestion =0;

    int correct = 0,wrong = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.question);
        b1 = findViewById(R.id.answer1);
        b2 = findViewById(R.id.answer2);
        b3 = findViewById(R.id.answer3);
        b4 = findViewById(R.id.answer4);

        //get all the questions
        loadAllQuestions();
        //shuffle the question
        Collections.shuffle(questionItems);
        //load first question
        setQuestionScreen(currentQuestion);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the answer is correct
                if(questionItems.get(currentQuestion).getAnswer1().equals(questionItems.get(currentQuestion).getCorrect())){
                    //correct
                    correct++;
                    Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    //wrong
                    wrong++;
                    Toast.makeText(MainActivity.this,"Wrong! Correct Answer: "+questionItems.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }
                //load next question if any
                if(currentQuestion < questionItems.size()-1){
                    currentQuestion++;
                    setQuestionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent=new Intent(getApplicationContext(),EndActivity.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("wrong",wrong);
                    startActivity(intent);
                    finish();

                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the answer is correct
                if(questionItems.get(currentQuestion).getAnswer2().equals(questionItems.get(currentQuestion).getCorrect())){
                    //correct
                    correct++;
                    Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    //wrong
                    wrong++;
                    Toast.makeText(MainActivity.this,"Wrong! Correct Answer: "+questionItems.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }
                //load next question if any
                if(currentQuestion < questionItems.size()-1){
                    currentQuestion++;
                    setQuestionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent=new Intent(getApplicationContext(),EndActivity.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("wrong",wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the answer is correct
                if(questionItems.get(currentQuestion).getAnswer3().equals(questionItems.get(currentQuestion).getCorrect())){
                    //correct
                    correct++;
                    Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    //wrong
                    wrong++;
                    Toast.makeText(MainActivity.this,"Wrong! Correct Answer: "+questionItems.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }
                //load next question if any
                if(currentQuestion < questionItems.size()-1){
                    currentQuestion++;
                    setQuestionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent=new Intent(getApplicationContext(),EndActivity.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("wrong",wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if the answer is correct
                if(questionItems.get(currentQuestion).getAnswer4().equals(questionItems.get(currentQuestion).getCorrect())){
                    //correct
                    correct++;
                    Toast.makeText(MainActivity.this,"Correct",Toast.LENGTH_SHORT).show();
                }else{
                    //wrong
                    wrong++;
                    Toast.makeText(MainActivity.this,"Wrong! Correct Answer: "+questionItems.get(currentQuestion).getCorrect(),Toast.LENGTH_SHORT).show();
                }
                //load next question if any
                if(currentQuestion < questionItems.size()-1){
                    currentQuestion++;
                    setQuestionScreen(currentQuestion);
                }else{
                    //game over
                    Intent intent=new Intent(getApplicationContext(),EndActivity.class);
                    intent.putExtra("correct",correct);
                    intent.putExtra("wrong",wrong);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    //set question to screen
    private void setQuestionScreen(int number){
        txt.setText(questionItems.get(number).getQuestion());
        b1.setText(questionItems.get(number).getAnswer1());
        b2.setText(questionItems.get(number).getAnswer2());
        b3.setText(questionItems.get(number).getAnswer3());
        b4.setText(questionItems.get(number).getAnswer4());
    }
    //make list with all the questions
    private void loadAllQuestions() {
        questionItems = new ArrayList<>();
        //load all question with json string

        String jsonStr = loadJSONFromAsset("questions.json");
        //load all data
        try {
            JSONObject object = new JSONObject(jsonStr);
            JSONArray ar = object.getJSONArray("questions");
            for (int i = 0; i < ar.length(); i++) {

                JSONObject question = ar.getJSONObject(i);

                String questionString = question.getString("question");
                String answer1String = question.getString("answer1");
                String answer2String = question.getString("answer2");
                String answer3String = question.getString("answer3");
                String answer4String = question.getString("answer4");
                String correctString = question.getString("correct");

                questionItems.add(new QuestionItems(
                        questionString,
                        answer1String,
                        answer2String,
                        answer3String,
                        answer4String,
                        correctString
                ));
            }
        } catch (
                JSONException e) {
            e.printStackTrace();
        }
    }
    //load the json file from asset folder
    private String loadJSONFromAsset(String file){
        String json = "";
        try
        {
            // Opening data.json file
            InputStream inputs = getAssets().open(file);
            int size = inputs.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputs.read(buffer);
            inputs.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        }

        catch (IOException e)
        {
            e.printStackTrace();
            return json;
        }
        return json;
    }
}
