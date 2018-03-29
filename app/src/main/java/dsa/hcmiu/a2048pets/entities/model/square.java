package dsa.hcmiu.a2048pets.entities.model;



import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
//import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;


public class square extends TextView {

    //CONSTRUCTOR//

    public square(Context context) {
        super(context);
    }

    public square(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public square(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    //  WIDTH - HEIGHT FOR A SQUARE //
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int a = getMeasuredWidth();
        setMeasuredDimension(a,a);
    }

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

        GradientDrawable draw = (GradientDrawable) this.getBackground();
        draw.setColor(data.getDatagame().color(n));
        setBackground(draw);


        if (n==0){
            setText(" ");
        }
        else{
            setText("" + n);
        }
    }
}

