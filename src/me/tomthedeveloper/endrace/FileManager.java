package me.tomthedeveloper.endrace;

import java.io.File;
import java.io.IOException;

/**
 * Created by TomVerschueren on 30/07/2017.
 */
public class FileManager {


    public static void delete(File file)
            throws IOException{
        if(!file.exists()){
            System.out.print("File " + file.getAbsolutePath() + " does not exist!");
        }
        if(file.isDirectory()){

            //directory is empty, then delete it
            if(file.list().length==0){

                file.delete();
                System.out.println("Directory is deleted : "
                        + file.getAbsolutePath());

            }else{

                //list all the directory contents
                String files[] = file.list();

                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);

                    //recursive delete
                    delete(fileDelete);
                }

                //check the directory again, if empty then delete it
                if(file.list().length==0){
                    file.delete();
                    System.out.println("Directory is deleted : "
                            + file.getAbsolutePath());
                }
            }

        }else{
            //if file, then delete it
            file.delete();
            System.out.println("File is deleted : " + file.getAbsolutePath());
        }
    }
}
