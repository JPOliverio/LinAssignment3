/**This class records the start position for question answers. */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class AnswerLocator {
    int [] aLocations;// start locations

    public AnswerLocator(File fileName) throws FileNotFoundException{

        LinkedList<Integer> queLocations = new LinkedList<Integer>();
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        String line;
        long aLocation;

        try{
            while ((line = file.readLine()) != null) {
                if(line.startsWith("@A")){  // searches for answer in file
                    aLocation = file.getFilePointer(); // gets answer location
                    queLocations.add((int)aLocation); // stores answer location
                }
            } 
            file.close();
        } catch (IOException e){
            e.printStackTrace();  
        }  
        aLocations = queLocations.stream().mapToInt(i->i).toArray(); // converts linked list to int array.
    }

    // returns the array of answer start locations
    public int[] getAllAnswerLocations(){
        return aLocations;
    }

    public int getAnswerLocation(int answer){
        answer = answer -1;
        return aLocations[answer];
    }
    
    // returns the number of answer start locations in the file.
    public int getAnswerCount(){
        int qCount = 0;
        qCount = aLocations.length;
        return qCount;
    }


    
}
