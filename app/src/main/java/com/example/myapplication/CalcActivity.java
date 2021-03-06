package com.example.myapplication;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.io.PipedOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pankaj on 09/05/18.
 */

public class CalcActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etResult;

    Equation equation = null;

    private static final String TAG = "CALCACT_";

    private static final char ADDITION = '+';
    private static final char SUBTRACTION = '-';
    private static final char DIVISION = '/';
    private static final char PRODUCT = 'x';

    Spinner spnrEquations;

    class Equation{

        double operandLeft;
        double operandRight;
        char operator;

        public Equation() {
            setOperandLeftInvalid();
            setOperandRightInvalid();
            setOperatorInvalid();
        }

        private void setOperandLeft(String operandLeft) {

            try{
                double opLeft = Double.parseDouble(operandLeft);
                this.operandLeft = opLeft;
            } catch (Exception e) {
                e.printStackTrace();
                setOperandLeftInvalid();
            }

        }

        private void setOperandRight(String operandRight) {

            try{
                double opRight = Double.parseDouble(operandRight);
                this.operandRight = opRight;
            } catch (Exception e) {
                e.printStackTrace();
                setOperandRightInvalid();
            }

        }

        private void setOperandLeftInvalid(){
            this.operandLeft = Double.NEGATIVE_INFINITY;
        }

        private void setOperandRightInvalid(){
            this.operandRight = Double.NEGATIVE_INFINITY;
        }

        private void setOperatorInvalid(){
            operator = Character.MIN_VALUE;
        }

        public void setOperand(String operand){

            if(this.operandLeft!=Double.NEGATIVE_INFINITY)
                setOperandRight(operand);
            else
                setOperandLeft(operand);

        }

        public void setOperator(char operator) {
            this.operator = operator;
        }

        /**
         *
         * @return is equation can be evaluated
         */
        public boolean isEquationComplete(){
            return this.operandLeft != Double.NEGATIVE_INFINITY
                    && this.operandRight != Double.NEGATIVE_INFINITY
                    && operator != Character.MIN_VALUE;
        }

        /**
         *
         * @return result of equation evaluation
         */
        public double getEvaluatedResult(){

            if(!isEquationComplete())
                return Double.MIN_VALUE;

            switch (this.operator) {

                case '+':
                    return this.operandLeft + this.operandRight;
                case '-':
                    return this.operandLeft - this.operandRight;
                case 'x':
                    return this.operandLeft * this.operandRight;
                case '/':
                    return this.operandLeft / this.operandRight;
                default:
                    return Double.MIN_VALUE;

            }

        }

        @Override
        public String toString() {
            return "Equation{" +
                    "operandLeft=" + operandLeft +
                    ", operandRight=" + operandRight +
                    ", operator=" + operator +
                    '}';
        }

        public void resetEquation(){
            setOperandLeftInvalid();
            setOperandRightInvalid();
            setOperatorInvalid();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        // RECYCLER VIEW

        // TODO: HTTP Requests; POST GET; -> GET using Volley

        // TODO: SHOW SHIFT REPORT Working - HTTP Request Response - JSON -> Show only

        // TODO: Local Storage - SQLITE

        // TODO: Background Services

        // TODO: ASYNC Tasks

        equation = new Equation();

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

        CheckBox cb1 = findViewById(R.id.calc_checkbox_1);
        CheckBox cb2 = findViewById(R.id.calc_checkbox_2);

        initSpinner();

        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {
                    etResult.setBackgroundColor(ContextCompat.getColor(CalcActivity.this, R.color.colorAccent));
                    spnrEquations.setVisibility(View.GONE);
                }
                else {
                    etResult.setBackgroundColor(ContextCompat.getColor(CalcActivity.this, R.color.colorWhite));
                    spnrEquations.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    private void initSpinner(){

        spnrEquations = findViewById(R.id.calc_spinner);

        final List<String> mDataset = Arrays.asList("A", "B", "C", "D", "E");

        BaseAdapter bAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return mDataset.size();
            }

            @Override
            public Object getItem(int position) {
                return mDataset.get(position);
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater li = (CalcActivity.this).getLayoutInflater();
                View row = li.inflate(R.layout.row_spinner_1, parent, false);
                ((CheckBox)(row.findViewById(R.id.row_spinner_checkbox))).setText((String)getItem(position));
//                row.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Log.d(TAG, "Clicked CheckBox");
//                    }
//                });
                return row;
            }

        };

        spnrEquations.setAdapter(bAdapter);

        spnrEquations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Log.d(TAG, String.format("Clicked - %d", position));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
                equation.resetEquation();
                break;

            case R.id.calc_btn_plus:
                // TODO: Add code for validation of user input at run time
                // Hint: user CurrResult last operator or use TextWatcher
                // Color for operator using SPAN
                equation.setOperand(currResult.toString().trim());
                currResult.append(" "+ADDITION+" ");
                equation.setOperator(ADDITION);

                break;

            case R.id.calc_btn_minus:
                equation.setOperand(currResult.toString().trim());
                currResult.append(" "+SUBTRACTION+" ");
                equation.setOperator(SUBTRACTION);
                break;

            case R.id.calc_btn_divide:
                equation.setOperand(currResult.toString().trim());
                currResult.append(" "+DIVISION+" ");
                equation.setOperator(DIVISION);
                break;

            case R.id.calc_btn_product:

                equation.setOperand(currResult.toString().trim());
                currResult.append(" "+PRODUCT+" ");
                equation.setOperator(PRODUCT);
                break;

            default:
                break;

        }

        if(id==R.id.calc_btn_equal) {
            currResult.append(" = ");
            etResult.setText(currResult);
            evaluateExpression();
        } else {
            etResult.setText(currResult);
            etResult.setSelection(currResult.length());
        }

    }

    private String getLastNumberEntered(){

        String equation = etResult.getText().toString();

//        "111 + 34 = ";


        Log.d(TAG, "Curr String: "+equation);

        if(equation.length()>0
            && (equation.trim().endsWith(String.valueOf(ADDITION))
                || equation.trim().endsWith(String.valueOf(SUBTRACTION))
                || equation.trim().endsWith(String.valueOf(PRODUCT))
                || equation.trim().endsWith(String.valueOf(DIVISION))
                || equation.trim().endsWith("="))) {

            equation = equation.trim();
            equation = equation.substring(0, equation.length()-2);
            equation = equation.trim();
            equation = equation.substring(equation.lastIndexOf(' '));
            Log.d(TAG, "After trim: "+equation);

            return equation;

        }

        throw new IllegalArgumentException("Invalid last number");

    }

    /**
     *  After evaluating INFIX expression two vars at a time
     *
     * @param
     * @return
     */
    private void evaluateExpression(){

        Log.d(TAG, "BEFORE: "+equation.toString());
        String lastNumber = getLastNumberEntered();
        equation.setOperandRight(lastNumber);
        Log.d(TAG, "AFTER: "+equation.toString());

        if(equation.isEquationComplete()) {

            double result = equation.getEvaluatedResult();
            etResult.setText(String.valueOf(result));
            etResult.setSelection(etResult.getText().toString().length());
            equation.resetEquation();
            equation.setOperandLeft(String.valueOf(result));

        } else {

            etResult.append(" ERROR! ");

        }

    }

}
