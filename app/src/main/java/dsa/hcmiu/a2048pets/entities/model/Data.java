package dsa.hcmiu.a2048pets.entities.model;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.Random;

import dsa.hcmiu.a2048pets.R;


public class Data {
    public static Data datagame;

    //Array1D: color for squares //
    private int[] Array1D_color;

    // ArrayList: display the number of items //
    private ArrayList<Integer> nItems = new ArrayList<>();

    // Array2D: better to manage the items (4x4) //
    private int[][] Array2D = new int[4][4];

    //Random number//
    private Random random = new Random();



    // Constructor //
    static{
        datagame = new Data();
    }
    public static Data getDatagame(){
        return datagame;
    }


    // Method //
    public void value16(Context context) {
        int i, j;

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                Array2D[i][j] = 0; // Set current value //
                nItems.add(0);
            }
        }
        // TypedArray: link to the file "res" //
        TypedArray rescolor = context.getResources().obtainTypedArray(R.array.ItdemColor); //Take the color in "drawable"//
        Array1D_color = new int[rescolor.length()];  //Get the number of color//

        for (i = 0; i < rescolor.length(); i++) {
            Array1D_color[i] = rescolor.getColor(i, 0); //getColor (int index, int defValue)//
        }
        rescolor.recycle(); //Close the res//

        createNumber();
        exchange();
    }


    public int color(int n){
        // If the value is 0, let it WHITE (We only have : 2,4,8,16,32,64,......) //
        if(n==0){
            return Color.WHITE;
        }
        else{
            int a = (int) (Math.log(n) / Math.log(2));  //Should have (int) because we have logarit as double//
            return Array1D_color [a-1];   //Position of color (first color=0) = the result - 1//
        }
    }

    public void createNumber(){
        int n0 = 0;
        int numbercreate;
        for(int i=0; i<16; i++){
            if( nItems.get(i)==0 ){
                n0++;
            }
        }

        if(n0>1){  //If it's full, don't create more Number//
            numbercreate = random.nextInt(2)+1;
        }
        else{
            if(n0==1){
                numbercreate = 1;
            }
            else{
                numbercreate = 0;
            }
        }

        while(numbercreate!=0){
            int i = random.nextInt(4);
            int j = random.nextInt(4);

            if(Array2D[i][j] == 0){
                Array2D[i][j] = 16;
                numbercreate--;
            }
        }
    }

    public void exchange() {
        nItems.clear();
        int i, j;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                nItems.add(Array2D[i][j]);
            }
        }
    }

    // Get ArrayList //
    public ArrayList<Integer> getnItems() {
        return nItems;
    }
}
