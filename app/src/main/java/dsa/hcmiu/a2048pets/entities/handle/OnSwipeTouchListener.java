package dsa.hcmiu.a2048pets.entities.handle;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import dsa.hcmiu.a2048pets.PlayActivity;

/**
 * Created by Admin on 3/31/2018.
 */

public class OnSwipeTouchListener implements View.OnTouchListener {
    private float x1;
    private float y1;
    private Context context;

    public OnSwipeTouchListener(Context context) {
        this.context = context;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //nhấn vô
               x1 = event.getX();
               y1 = event.getY();
               break;
            case MotionEvent.ACTION_UP: //thả ra
                float x2 = event.getX();
                float y2 = event.getY();
                if (Math.abs(x2-x1) > Math.abs(y2-y1)) { //horizontal
                    if (x2 > x1) onSwipeRight();
                    else onSwipeLeft();
                }
                else {
                    if (y2 > y1) onSwipeDown();
                    else onSwipeUp();
                }
                break;
        }
        return true;
    }

    public void onSwipeRight() {
        //make text qua phải
    }

    public void onSwipeLeft() {

    }

    public void onSwipeUp() {
    }

    public void onSwipeDown() {
    }
}

