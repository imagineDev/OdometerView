package com.imaginedev.odometerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

/**
 * @author batman
 * https://github.com/imagineDev
 * Its an Odometer view
 * which takes reading till 9 lakhs and has one decimal point
 */
public class OdometerView extends LinearLayout {
    private static final int NUM_DIGITS = 6; //6 digits excluding one decimal place

    private double mCurrentValue;
    private int animationCompleteCounter = 0;

    private FlipmeterSpinner[] mDigitSpinners;
    private FlipmeterSpinner mDigitSpinnerDecimal;

    public OdometerView(Context context) {
        super(context);
        initialize();
    }

    public OdometerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();

        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OdometerView, 0, 0);
        double val = attributes.getFloat(R.styleable.OdometerView_reading, 0);
        setValue(val, true);

    }

    private void initialize() {
        mDigitSpinners = new FlipmeterSpinner[NUM_DIGITS];

        // Inflate the view from the layout resource.
        String infService = Context.LAYOUT_INFLATER_SERVICE;
        LayoutInflater li;
        li = (LayoutInflater) getContext().getSystemService(infService);
        li.inflate(R.layout.widget_flipmeter, this, true);

        mDigitSpinners[0] = findViewById(R.id.widget_flipmeter_spinner_1);
        mDigitSpinners[1] = findViewById(R.id.widget_flipmeter_spinner_10);
        mDigitSpinners[2] = findViewById(R.id.widget_flipmeter_spinner_100);
        mDigitSpinners[3] = findViewById(R.id.widget_flipmeter_spinner_1k);
        mDigitSpinners[4] = findViewById(R.id.widget_flipmeter_spinner_10k);
        mDigitSpinners[5] = findViewById(R.id.widget_flipmeter_spinner_100k);

        mDigitSpinnerDecimal = findViewById(R.id.widget_flipmeter_spinner_decimal);

    }

    public synchronized void setValue(double value, boolean withAnimation) {

        if (value == mCurrentValue) {
            return;
        }


        setValueImpl(value, withAnimation);

    }


    /**
     * Should always be called from inside OdometerView.setValue(....)
     */
    private synchronized void setValueImpl(double value, boolean withAnimation) {

        mCurrentValue = value;
        int tempValue = (int) value;

        for (int i = 5; i > 0; --i) {
            int factor = (int) Math.pow(10, i);

            int digitVal = (int) Math.floor(tempValue / factor);
            tempValue -= (digitVal * factor);
            mDigitSpinners[i].setDigit(digitVal, withAnimation);
            changeAnimationCompleteCounter(withAnimation);
        }

        mDigitSpinners[0].setDigit(tempValue, withAnimation);
        changeAnimationCompleteCounter(withAnimation);

        mDigitSpinnerDecimal.setDigit((int) Math.round(((value - Math.floor(value)) * 10)), withAnimation);
        changeAnimationCompleteCounter(withAnimation);

    }

    private synchronized int changeAnimationCompleteCounter(Boolean increment) {
        if (increment == null)
            return animationCompleteCounter;
        else if (increment)
            return ++animationCompleteCounter;
        else
            return --animationCompleteCounter;
    }

    /**
     * @return
     */
    public double getValue() {
        return mCurrentValue;
    }

    public boolean isAnimating() {
        return mDigitSpinners[0].isAnimating() ||
                mDigitSpinners[1].isAnimating() ||
                mDigitSpinners[2].isAnimating() ||
                mDigitSpinners[3].isAnimating() ||
                mDigitSpinners[4].isAnimating() ||
                mDigitSpinners[5].isAnimating() ||
                mDigitSpinnerDecimal.isAnimating();
    }

    public interface OnValueChangeListener {
        void onValueChange(OdometerView sender, int newValue);
    }


}
