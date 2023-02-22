package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static String keyWord = "99614410";
    public static String extension = ".htm";
    public static void main(String[] args) throws Exception {
        List<File> filesToAnalize = new ArrayList<>();
        List<File> results = new ArrayList<>();
        filesToAnalize = walk("C:\\Users\\Adriana\\SortFiles", extension, filesToAnalize);
        System.out.println("\n *****Iniciando Ejecucion******");
        for(File file : filesToAnalize){
            System.out.println("Revisando archivo "+file.getAbsoluteFile());
            String content = readFileAsString(file.getAbsoluteFile().toString());
            if(content.contains(keyWord)){
                results.add(file);
                System.out.println("****Se encontro coincidencia en: " + file.getAbsoluteFile());
            }
        }
        System.out.println("\n*************Resultados************");
        System.out.println("Coincidencias para: " + keyWord);
        if(results.isEmpty()){
            System.out.println("No se encontraron coincidencias");
        }
        for(File result: results){

            System.out.println(result.getAbsoluteFile());
        }
    }

    public static void lookFor(File file, String keyWord) throws IOException {
        FileReader fr = new FileReader(file.getAbsoluteFile());

        // Declaring loop variable
        int i;
        // Holds true till there is nothing to read
        while ((i = fr.read()) != -1)

            // Print all the content of a file
            System.out.print((char)i);
    }

    public static String readFileAsString(String fileName)throws Exception
    {
        String data = "";
        data = new String(Files.readAllBytes(Paths.get(fileName)));
        return data;
    }

    public static List<File> walk(String path , String extension, List<File> filesToAnalize) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return filesToAnalize;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                walk( f.getAbsolutePath(),  extension, filesToAnalize);
                System.out.println( "Dir:" + f.getAbsoluteFile() );
            }
            else {
                System.out.println( "File:" + f.getAbsoluteFile() );
                if(f.getAbsoluteFile().toString().endsWith(extension))
                    filesToAnalize.add(f);
            }
        }

        return filesToAnalize;
    }

}