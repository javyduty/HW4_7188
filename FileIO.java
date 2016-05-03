/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4_7188;

import java.io.IOException;
import java.util.Map;

/**
 *
 * locate file path and call methods to compress and decompress files
 * prints dictionary definitions
 */
public class FileIO {
    public static void main(String args[]) throws IOException{
		FileStats fs=new FileStats("basketball.txt");
		fs.printDictionary();

		Map <String, Character> m1=fs.getDictionary();
		FileCompressor.compress("basketball.txt", "compressed.txt",m1);

		/* insert your code here */
		/* create another dictionary for decompress and name it m2 */
                Map <String, String> m2=fs.getDictionary2(); 
		FileCompressor.decompress("compressed.txt", "decompressed.txt",m2);
	}
}
