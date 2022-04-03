import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import java.util.Scanner;

public class QuizLogic {

    static String playerResults;


    QuizLogic(File txtName, int questionsToAsk, Long timeLimit, String userName, Scanner scan)throws FileNotFoundException{

        //Scanner scan = new Scanner(System.in);
        AnswerLocator aLocator = new AnswerLocator(txtName);
        QuestionLocator qLocator = new QuestionLocator(txtName);
        int qCount = qLocator.getQuestionCount();


        //************Question place lis*******************/
        // creates list with values 1 to number of questions then shuffles it.
        // This is used to pull random questions.
        ArrayList<Integer> list = new ArrayList<Integer>(qCount);
        for(int i = 1; i <= qCount; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        ListIterator<Integer> iterator = list.listIterator();

        double correct = 0;
        double wrong = 0;
        long startTime = System.currentTimeMillis();
        long endTime = 0;
        long time = 0;
        timeLimit = timeLimit!=null ? timeLimit*1000:null; //converts sec to milisec if timeLimit is not null.

        int count = questionsToAsk !=0 ? questionsToAsk:qCount;

        for(int j=0; j<count;j++){
            if(timeLimit==null || time<timeLimit){
                int place = iterator.next();

                AnswerReader aReader = new AnswerReader(txtName, aLocator.getAnswerLocation(place));
                QuestionReader qReader = new QuestionReader(txtName, qLocator.getQuestionLocation(place));

                System.out.println("Question:");
                System.out.println(qReader.getQuestion());
                System.out.println("");
                System.out.println("Choices:");


                for(int i=1; i<=aReader.getNumberofChoices(); i++){
                    System.out.println(i+") "+aReader.getChoice(i));
                }
                int choice = scan.nextInt();

                if(choice == aReader.getSolution()){
                    System.out.println("Correct");
                    correct++;
                }else{
                    System.out.println("Wrong");
                    wrong++;
                }
                endTime = System.currentTimeMillis();
                time = endTime - startTime;
                System.out.println(time);
            }
        }
    
    //scan.close();
    double totalQuestions = wrong+correct;
    double percentCorrect = (correct/totalQuestions)*100;

    playerResults = "\n"+
    "*******************Quiz Statistics****************\n"+
    userName +"\n"+
    "play time: "+(endTime-startTime)/1000+" secounds\n"+
    "Questions Answerd: "+totalQuestions+"\n"+
    "Questions Answerd correctly: "+correct+"\n"+
    "Precent correct: "+percentCorrect+"%\n"+
    "**************************************************";

    }

    public static String getPlayerResults(){
        return playerResults;
    }


    
}
