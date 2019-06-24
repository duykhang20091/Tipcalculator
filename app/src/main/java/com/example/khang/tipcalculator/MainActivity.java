package com.example.khang.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends Activity {
    private  TextView amountTextView;
    private  TextView percentLabelTextView;
    private  TextView tipTextView;
    private  TextView totalTextView;

    private double amount=0.0;
    private double percent =0.15;

    private double tip=0;
    private  double total=0;
    private  static  final NumberFormat numberCurrency = NumberFormat.getNumberInstance();
    private  static  final NumberFormat numberPercent = NumberFormat.getNumberInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       amountTextView = (TextView) findViewById(R.id.amountTextView);
       percentLabelTextView =(TextView) findViewById(R.id.percentLabelTextView);
       tipTextView=(TextView) findViewById(R.id.tipTextView);
       totalTextView=(TextView) findViewById(R.id.totalTextView);
       tipTextView.setText(numberCurrency.format(0));
       totalTextView.setText(numberPercent.format(0));
        final EditText amountEditText = (EditText)findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percenSeekBar=(SeekBar)findViewById(R.id.percentSeekBar);
        percenSeekBar.setOnSeekBarChangeListener(seekbarListener);
    }
    private  void calc(){
        double tip= amount*percent;
        double total=amount+tip;
        tipTextView.setText(numberCurrency.format(tip));
        totalTextView.setText(numberPercent.format(total));
    }
    private  final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            try {
                amount = Double.parseDouble(charSequence.toString()) / 100;
                amountTextView.setText((numberCurrency.format(amount)));
            } catch (NumberFormatException e) {
                amountTextView.setText("");
                amount = 0.0;
            }
            calc();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
    private final SeekBar.OnSeekBarChangeListener seekbarListener =new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        percent = i /100.0;
        percentLabelTextView.setText(numberPercent.format(percent));
        calc();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}
