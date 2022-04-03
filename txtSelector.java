/** This Class opens a user interface that searchs and allows users
 * to select a desired txt file.
 */



import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class txtSelector{

    String [] contents;

    
    txtSelector(Scanner scan){
        LinkedList<String> textFiles;
        String txtPath = null;
        System.out.println("please type the folder path to where you quiz text file(s) are stored or type -sample to run with sample files");
        txtPath = scan.next();
        if(txtPath.contains("-sample")){
            File dir = new File(System.getProperty("user.dir"));
            textFiles = new LinkedList<String>();
            for(File file: dir.listFiles()){
                if(file.getName().endsWith((".txt"))){
                    textFiles.add(file.getName());
                }
            }

        }else{
            File dir = new File(txtPath);
            textFiles = new LinkedList<String>();
            for(File file: dir.listFiles()){
                if(file.getName().endsWith((".txt"))){
                    textFiles.add(file.getName());
                }
            }
        }
        contents = textFiles.toArray(new String[textFiles.size()]);
    }

public synchronized String[] getTxt(){
    return contents;
}

public int getNumberOfTxt(){
    return contents.length;
}

public String getTxtName(int j){
    return contents[j];
}

public void printTxtNames(){
    for(int i=0; i<contents.length; i++){
        System.out.println(i+") "+contents[i].replace(".txt", ""));
    }

}

}