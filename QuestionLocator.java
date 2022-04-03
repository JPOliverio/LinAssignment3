/**This class records the start position for questions. */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public class QuestionLocator {
    int [] qLocations; // start locations

    public QuestionLocator(File fileName) throws FileNotFoundException{

        LinkedList<Integer> queLocations = new LinkedList<Integer>();
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        String line;
        long qLocation;

        try{
            while ((line = file.readLine()) != null) {
                if(line.startsWith("@Q")){  // sarches for questions in file
                    qLocation = file.getFilePointer(); // gets question location
                    queLocations.add((int)qLocation); // stores question location
                }
            } 
            file.close();
        } catch (IOException e){
            e.printStackTrace();  
        }  
        qLocations = queLocations.stream().mapToInt(i->i).toArray(); // converts linked list to int array.
    }

    // returns the array of question locations
    public int[] getAllQuestionLocations(){
        return qLocations;
    }

    public int getQuestionLocation(int question){
        question = question-1;
        return qLocations[question];
    }
    
    // returns the number of questions in the file.
    public int getQuestionCount(){
        int qCount = 0;
        qCount = qLocations.length;
        return qCount;
    }
}
