/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4_7188;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Javier
 */
public class FileCompressor {
    private static Charset ENCODING = StandardCharsets.UTF_8;
    
    public static void compress(String src, String dest,
			Map<String, Character> dictionary){
        /* insert your code here */
        FileStats file = new FileStats(src);
        ArrayList<String> list = file.getWordList();
        File output = null;
        
        try{
            output = new File(dest);
            output.createNewFile();
            output.delete();
            output.createNewFile();
            
            Set<String> keySet = dictionary.keySet();
            for(String word:list){
                for(String key:keySet){
                    if(word.equals(key)){
                        list.set(list.indexOf(word), 
                                dictionary.get(word).toString());
                    }
                }
            }
            fillFile(list,dest);
        }catch(Exception e){
            e.printStackTrace();
        }
	}

	public static void decompress(String src, String dest,
			Map<String, String> dictionary){

            /* insert your code here */
            FileStats file = new FileStats(src);
            ArrayList<String> list = file.getWordList();
            File output = null;
        
            try{
                output = new File(dest);
                output.createNewFile();
                output.delete();
                output.createNewFile();
            
                Set<String> keySet = dictionary.keySet();
                for(String word:list){
                    for(String key:keySet){
                        if(word.equals(key)){
                            list.set(list.indexOf(word), 
                                    dictionary.get(word));
                        }
                    }
                }
                fillFile(list,dest);
            }catch(Exception e){
                e.printStackTrace();
            }

	}
        
        public static void fillFile(List<String> list, String file) throws IOException{
                Path path = Paths.get(file);
                try(BufferedWriter writer = Files.newBufferedWriter(path, ENCODING)){
                    for(String word : list){
                        if(word.equals("newLine")){
                            writer.newLine();
                        } else{
                            writer.write(word + " ");
                        }
                    }
                }
        }
}
