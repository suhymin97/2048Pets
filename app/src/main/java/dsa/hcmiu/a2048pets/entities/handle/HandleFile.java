package dsa.hcmiu.a2048pets.entities.handle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Admin on 3/25/2018.
 */

public class HandleFile {
    private static String url = "highscore.txt";

    public static <ListScore> void writeFile(ArrayList<ListScore> listscore){
        File file = new File(url);
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listscore);
            fos.close();
            oos.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static <ListScore>ArrayList<ListScore> readFile(){
        ArrayList<ListScore> arrayList = new ArrayList<>();
        File file = new File(url);
        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList = (ArrayList<ListScore>) ois.readObject();
            fis.close();
            ois.close();
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        return arrayList;
    }
}