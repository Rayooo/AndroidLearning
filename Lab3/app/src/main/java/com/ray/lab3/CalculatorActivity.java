package com.ray.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private String expression = "0";
    private TextView expressionText;
    private TextView answerText;
    //1,2,3,4,5,6,7,8,9,0,.,被按下时
    private class numClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            String buttonText = button.getText().toString();
            if(expression.equals("0") && !buttonText.equals(".")){
                expression = buttonText;
            }
            else{
                expression += buttonText;
            }
            expressionText.setText(expression);
        }
    }
    //+,-,*,/,被按下时
    private class operatorClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Button button = (Button)v;
            String buttonText = button.getText().toString();
            if(expression.length() > 0
                    && !expression.substring(expression.length()-1).equals("+")
                    && !expression.substring(expression.length()-1).equals("-")
                    && !expression.substring(expression.length()-1).equals("×")
                    && !expression.substring(expression.length()-1).equals("÷")){
                expression += buttonText;
            }
            expressionText.setText(expression);
        }
    }

    //=被按下时
    public void getResult(View v){
        Calculator calculator = new Calculator();
        calculator.setExpression(expression + "!");
        calculator.expToIpn();
        calculator.cal();
        String re = calculator.getResultString();
        answerText.setText(re);
    }

    //ce按下
    public void clear(View v){
        expression = "0";
        expressionText.setText(expression);
    }

    //backspace按下
    public void backspace(View v){
        if(expression.length() > 1){
            expression= expression.substring(0, expression.length() - 1);
        }
        else if(expression.length() == 1){
            expression = "0";
        }
        expressionText.setText(expression);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        setTitle("计算器");
        Button oneButton = (Button)findViewById(R.id.oneButton);
        Button twoButton = (Button)findViewById(R.id.twoButton);
        Button threeButton = (Button)findViewById(R.id.threeButton);
        Button fourButton = (Button)findViewById(R.id.fourButton);
        Button fiveButton = (Button)findViewById(R.id.fiveButton);
        Button sixButton = (Button)findViewById(R.id.sixButton);
        Button sevenButton = (Button)findViewById(R.id.sevenButton);
        Button eightButton = (Button)findViewById(R.id.eightButton);
        Button nineButton = (Button)findViewById(R.id.nineButton);
        Button zeroButton = (Button)findViewById(R.id.zeroButton);
        Button dotButton = (Button)findViewById(R.id.dotButton);
        Button ceButton = (Button)findViewById(R.id.ceButton);
        Button backspaceButton = (Button)findViewById(R.id.backspaceButton);
        Button plusButton = (Button)findViewById(R.id.plusButton);
        Button divideButton = (Button)findViewById(R.id.divideButton);
        Button multiplyButton = (Button)findViewById(R.id.multiplyButton);
        Button minusButton = (Button)findViewById(R.id.minusButton);

        expressionText = (TextView)findViewById(R.id.expressionText);
        answerText = (TextView)findViewById(R.id.answerText);

        oneButton.setOnClickListener(new numClickListener());
        twoButton.setOnClickListener(new numClickListener());
        threeButton.setOnClickListener(new numClickListener());
        fourButton.setOnClickListener(new numClickListener());
        fiveButton.setOnClickListener(new numClickListener());
        sixButton.setOnClickListener(new numClickListener());
        sevenButton.setOnClickListener(new numClickListener());
        eightButton.setOnClickListener(new numClickListener());
        nineButton.setOnClickListener(new numClickListener());
        zeroButton.setOnClickListener(new numClickListener());
        dotButton.setOnClickListener(new numClickListener());

        plusButton.setOnClickListener(new operatorClickListener());
        divideButton.setOnClickListener(new operatorClickListener());
        multiplyButton.setOnClickListener(new operatorClickListener());
        minusButton.setOnClickListener(new operatorClickListener());

    }
}
