package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Pankaj on 09/05/18.
 */

public class CalcActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        etResult = findViewById(R.id.calc_results_et);

        findViewById(R.id.calc_btn_0).setOnClickListener(this);
        findViewById(R.id.calc_btn_1).setOnClickListener(this);
        findViewById(R.id.calc_btn_2).setOnClickListener(this);
        findViewById(R.id.calc_btn_3).setOnClickListener(this);
        findViewById(R.id.calc_btn_4).setOnClickListener(this);
        findViewById(R.id.calc_btn_5).setOnClickListener(this);
        findViewById(R.id.calc_btn_6).setOnClickListener(this);
        findViewById(R.id.calc_btn_7).setOnClickListener(this);
        findViewById(R.id.calc_btn_8).setOnClickListener(this);
        findViewById(R.id.calc_btn_9).setOnClickListener(this);

        findViewById(R.id.calc_btn_plus).setOnClickListener(this);
        findViewById(R.id.calc_btn_minus).setOnClickListener(this);
        findViewById(R.id.calc_btn_product).setOnClickListener(this);
        findViewById(R.id.calc_btn_divide).setOnClickListener(this);
        findViewById(R.id.calc_btn_equal).setOnClickListener(this);
        findViewById(R.id.calc_btn_c).setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        StringBuilder currResult = new StringBuilder(etResult.getText().toString());

        switch (id) {

            case R.id.calc_btn_0:
                currResult.append(0);
                break;

            case R.id.calc_btn_1:
                currResult.append(1);
                break;

            case R.id.calc_btn_2:
                currResult.append(2);
                break;

            case R.id.calc_btn_3:
                currResult.append(3);
                break;

            case R.id.calc_btn_4:
                currResult.append(4);
                break;

            case R.id.calc_btn_5:
                currResult.append(5);
                break;

            case R.id.calc_btn_6:
                currResult.append(6);
                break;

            case R.id.calc_btn_7:
                currResult.append(7);
                break;

            case R.id.calc_btn_8:
                currResult.append(8);
                break;

            case R.id.calc_btn_9:
                currResult.append(9);
                break;

            case R.id.calc_btn_c:
                currResult = new StringBuilder();
                break;

            case R.id.calc_btn_plus:
                // TODO: Add code for validation of user input at run time
                // Hint: user CurrResult last operator or use TextWatcher
                // Color for operator using SPAN
                currResult.append(" + ");
                break;

            case R.id.calc_btn_minus:
                currResult.append(" - ");
                break;

            case R.id.calc_btn_divide:
                currResult.append(" / ");
                break;

            case R.id.calc_btn_product:
                currResult.append(" x ");
                break;

            case R.id.calc_btn_equal:
                //TODO: Add code fo evaluate expression
                int result = evaluateExpression(currResult.toString());
                currResult.append(" = ");
                currResult.append(result);
                break;
            default:
                break;

        }

        etResult.setText(currResult);
        etResult.setSelection(currResult.length());

    }


    /**
     *
     * @param expression After evaluating INFIX expression
     * @return
     */
    private int evaluateExpression(String expression){

        return -1;

    }



}
