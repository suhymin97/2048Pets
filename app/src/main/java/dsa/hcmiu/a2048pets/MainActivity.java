package dsa.hcmiu.a2048pets;

import dsa.hcmiu.a2048pets.entities.adapter.itemAdapter;
import dsa.hcmiu.a2048pets.entities.model.data;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;



public class MainActivity extends Activity{
//public class MainActivity extends AppCompatActivity {
    private GridView gameplay;
    private itemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create();
        show();
        setData();
    }

    private void create(){
        data.getDatagame().value16(MainActivity.this);
        adapter = new itemAdapter(MainActivity.this,0, data.getDatagame().getnItems());
    }

    private void show(){
        gameplay = (GridView)findViewById(R.id.gameplay);
    }

    private void setData(){
        gameplay.setAdapter(adapter);
    }
}

