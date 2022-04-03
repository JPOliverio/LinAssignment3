import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Assignmentthreelin {
    static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Long timeLimit = null;
        int qask = 0;
        boolean quite = false;
        String username = null;
        String userchoice ="";

        File fileName = new File("sample");
        System.out.println("\n Hello and welcome to the interactive quiz program \n Below is a list of option you can perform.");
        help();

        while(quite==false){
            userchoice = scan.next();
            if(userchoice.contains("-help")){
                help();
            }else if(userchoice.contains("-setfile")){
                fileName = setfile();
            }else if(userchoice.contains("-settime")){
                timeLimit = settime();
            }else if(userchoice.contains("-startquiz")){
                if(username==null){
                    username=setuser();
                }
                startquiz(fileName, qask, timeLimit, username);
            }else if(userchoice.contains("-printLog")){
                printLog();
            }else if(userchoice.contains("-setqask")){
                qask=setqask();
            }else if(userchoice.contains("-setuser")){
                username=setuser();
            }else if(userchoice.contains("-quit")){
                quite =true;
            }else{
                System.out.println("That is not a valid command type -help for help");
            }

        }



    }


    public static void help(){
        System.out.println("-help");
        System.out.println("    -setfile       This will allow yu to set the quiz text file");
        System.out.println("    -settime       This will set the time limit for the quiz.");
        System.out.println("    -startquiz     This will start the quiz.");
        System.out.println("    -printLog      This will print the logfile of everyone who has taken the quiz and there results");
        System.out.println("    -setqask       This will allow you to set the number of questions to be asked.");
        System.out.println("    -setuser       This will allow you to set the user name");     
        System.out.println("-quit");
    }

    public static File setfile(){
        txtSelector txt = new txtSelector(scan);
        System.out.println("Below is a list of txt files found. Please enter the corrisponding number to choise that file.");
        txt.printTxtNames();
        int txtNumber = scan.nextInt();
        File txtName = new File (txt.getTxtName(txtNumber));
        return txtName;
    }

    public static void selectedtxt(String fileName){
        if(fileName==""){
            System.out.println("A file has not been selected yet. Use the option -setfile to set a file.");
        }else{
            System.out.println(fileName);
        }
    }

    public static Long settime(){
        System.out.println("Please enter the time limit in secounds");
        Long time = scan.nextLong();
        return time;
    }

    public static void printLog()throws Exception{
        System.out.println("Enter the password for the log file (try <cats>)");
        String password = scan.next();


        //creates logfile if it doesnt already exist.
        File logFile = new File(".LogFile.txt");
        try{
            logFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }

        encrypter enc = new encrypter();
        enc.decrypt(logFile, password);

        System.out.println("\nLog File Start");

        try (BufferedReader br = new BufferedReader(new FileReader(".LogFile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
         }

        System.out.println("Log File End\n");



        enc.encrypt(logFile,password);
    }

    public static int setqask(){
        int qask = 0;
        System.out.println("Please enter the number of questions to be asked from the quiz");
        qask = scan.nextInt();
        return qask;

    }

    public static void startquiz(File fileName, int qask, Long timeLimit, String username) throws Exception{
        new QuizLogic(fileName, qask, timeLimit, username, scan);
        String results = QuizLogic.getPlayerResults();
        System.out.println(results);


        File logFile = new File(".LogFile.txt");
        try{
            logFile.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }

        encrypter test = new encrypter();
        test.decrypt(logFile, "cats");
        try{
            Files.write(Paths.get(".LogFile.txt"),results.getBytes(),StandardOpenOption.APPEND);
        } catch(IOException e){
            e.printStackTrace();
        }


        test.encrypt(logFile, "cats");
        
    }

    public static String setuser(){
        String userName= null;
        System.out.println("please enter your name");
        userName = scan.next();
        return userName;
    }



    
}
