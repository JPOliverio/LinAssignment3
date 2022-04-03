/**This class reads questions and records the question as a string for a specified 
 * question. The specified question is given by the position. The position is 
 * from the questionLocator.
 */

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.StringBuilder;

public class QuestionReader {


String entireQuestion;

public QuestionReader(File fileName, int qPosition){  //qPosition is the position of the desired question in the text file
    String line=" ";
    StringBuilder question = new StringBuilder();
    
    try{
        RandomAccessFile file = new RandomAccessFile(fileName, "r");
        file.seek(qPosition);
        while(line != null && !(line.startsWith("@A"))){ // stops if line is null or if we have gotten to the Answear section which mark the end of the questin.
            line = file.readLine();
            if(line != null){
                if((!(line.startsWith("*")) && !(line.trim().length()==0))){ //this line ignores * and blank spots in the text file
                    if(!(line.startsWith("@A"))){ // this looks for quesion end point
                        question.append(" "+line);
                    }  
                }   
            }     
        }
        file.close();
    } catch (IOException e){
        e.printStackTrace();
    }
    question.deleteCharAt(0);
    entireQuestion = question.toString();
}

public String getQuestion(){
    return entireQuestion;
}

    
}
