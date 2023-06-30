package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result,solution;
    MaterialButton buttonC, buttonopen,buttonclose;
    MaterialButton buttondivide,buttonmultiply,buttonminus,buttonplus;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC, buttondot,buttonequals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result= findViewById(R.id.resulttextView);
        solution=findViewById(R.id.soltextView);
        assignid(buttonC,R.id.button_c);
        assignid(buttonopen,R.id.button_openbracket);
        assignid(buttonclose,R.id.button_closebracket);
        assignid(buttondivide,R.id.button_divide);
        assignid(buttonmultiply,R.id.button_multiply);
        assignid(buttonminus,R.id.button_subtract);
        assignid(buttonplus,R.id.button_add);
        assignid(button0,R.id.button_0);
        assignid(button1,R.id.button_1);
        assignid(button2,R.id.button_2);
        assignid(button3,R.id.button_3);
        assignid(button4,R.id.button_4);
        assignid(button5,R.id.button_5);
        assignid(button6,R.id.button_6);
        assignid(button7,R.id.button_7);
        assignid(button8,R.id.button_8);
        assignid(button9,R.id.button_9);
        assignid(buttonAC,R.id.button_AC);
        assignid(buttondot,R.id.button_decimal);
        assignid(buttonequals,R.id.button_equal);

    }

    void assignid( MaterialButton btn,int id)
    {
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        MaterialButton button= (MaterialButton) view;
        String buttontext= button.getText().toString();
        String datacal= solution.getText().toString();

        if(buttontext.equals("AC"))
        {
            solution.setText("");
            result.setText("0");
            return;
        }

        if(buttontext.equals("="))
        {
            solution.setText(result.getText());
            return;
        }
        if(buttontext.equals("C"))
        {
            datacal=datacal.substring(0,datacal.length()-1);
        }
        else
        {
            datacal=datacal+buttontext;
        }

        solution.setText(datacal);
        String finalresult=getresult(datacal);
        if(!finalresult.equals("error"))
        {
            result.setText(finalresult);
        }

    }

  String getresult(String data)
  {
      try{
          Context context= Context.enter();
          context.setOptimizationLevel(-1);
          Scriptable scriptable=context.initStandardObjects();
          String finalresult= context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
          if(finalresult.endsWith(".0"))
          {
              finalresult=finalresult.replace(".0","");
          }
          return finalresult;
      }catch (Exception e)
      {
          return "error";
      }
  }
}