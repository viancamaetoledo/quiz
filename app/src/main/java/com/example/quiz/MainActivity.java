package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnA, btnB, btnC, btnD, btnSubmit;

    TextView txtQuestion;

    int score = 0 ;
    int totalQuestionaire = Questionaires.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer  ="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtQuestion = findViewById(R.id.questions);

        btnA = findViewById(R.id.ans_a);
        btnB = findViewById(R.id.ans_b);
        btnC = findViewById(R.id.ans_c);
        btnD = findViewById(R.id.ans_D);

        btnSubmit = findViewById(R.id.btn_submit);

        btnA.setOnClickListener(this);
        btnB.setOnClickListener(this);
        btnC.setOnClickListener(this);
        btnD.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);

        loadNewQuestion();


    }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.btn_submit){
            if(selectedAnswer.equals((Questionaires.correctAnswers[currentQuestionIndex]))){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }
    }

    void loadNewQuestion(){
        if(currentQuestionIndex == totalQuestionaire){
            finihQuiz();
            return;
        }
        txtQuestion.setText(Questionaires.question[currentQuestionIndex]);
        btnA.setText(Questionaires.choices[currentQuestionIndex][0]);
        btnB.setText(Questionaires.choices[currentQuestionIndex][1]);
        btnC.setText(Questionaires.choices[currentQuestionIndex][2]);
        btnD.setText(Questionaires.choices[currentQuestionIndex][3]);

    }

     void finihQuiz() {
        String passStatus = "";
        if(score >totalQuestionaire*0.75){
            passStatus = "passed";
        }else{
            passStatus ="failed";

        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " +  totalQuestionaire)
                .setPositiveButton("Restart", ((dialogInterface, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}