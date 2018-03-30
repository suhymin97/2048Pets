package dsa.hcmiu.a2048pets.entities.model;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

/**
 * Created by Admin on 3/30/2018.
 */

public class Square extends android.support.v7.widget.AppCompatTextView {
    //CONSTRUCTOR//

    public Square(Context context) {
        super(context);
    }

    public Square(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Square(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //  WIDTH - HEIGHT FOR A SQUARE //
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int a = getMeasuredWidth();
        setMeasuredDimension(a,a);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void textFormat(int n) {
        if (n < 100) {
            setTextSize(40);
        } else if (n < 1000) {
            setTextSize(35);
        } else {
            setTextSize(30);
        }
        if (n >= 8) {
            setTextColor(Color.WHITE);
        } else {
            setTextColor(Color.BLACK);
        }

 /*       GradientDrawable draw = (GradientDrawable) this.getBackground();
        draw.setColor(Data.getDatagame().color(n));
        setBackground(draw); */


        if (n==0){
            setText(" ");
        }
        else{
            setText("" + n);
        }
    }
}
