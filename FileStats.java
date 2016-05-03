/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw4_7188;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 *
 * @author Javier
 */
public class FileStats {

    /**
     * @param args the command line arguments
     */
        private Scanner input;
	private ArrayList <String> wordList=new ArrayList<String>();
	private HashSet <String> wordSet=new HashSet<String>();
	private ArrayList <Entry<String>> entryList=new ArrayList<Entry<String>>();
	private Map <String, Character> dictionary=new TreeMap<String, Character>();
        private Map <String, String> dictionary2 = new TreeMap<String, String>();
        private String newLine = "newLine";

	private class Entry <T> implements Comparable<Entry<T>>{
		public T s;
		public int frequency;
		public Entry(T s, int f){
			this.s=s;
			frequency=f;
		}
		public int compareTo(Entry<T> e){
                    return e.frequency - this.frequency;
		}
	}

	public FileStats(String path) {
            try{
                input = new Scanner(new File(path));
            }catch(FileNotFoundException e){
                System.out.println("error loading file");
                System.exit(1);
            }
            try{
                String line;
                while((line = input.nextLine()) != null){
                    StringTokenizer string = new StringTokenizer(line);
                    while(string.hasMoreTokens()){
                        String nextWord = string.nextToken().toLowerCase();
                        wordList.add(nextWord);
                        wordSet.add(nextWord);
                    }
                    wordList.add(newLine);
                }
            }catch(NoSuchElementException e){
                
            }

		/* insert your code here */
		/* open the file, named path */

            makeDictionaries();
            count();
	}

	/*
	 * This method is supposed to
	 * 1. find the frequency of each word in the file.
	 * 2. display the four most frequent words and their frequencies.
	 * 3. construct the dictionary that four key-value pairs. The keys
	 *    are the most frequent words and the values are the characters,
	 *    used to represent the words.
	 */
	private void count() {
            /* insert your code here */
            for(String word : wordSet){
                int count = Collections.frequency(wordList, word);
                Entry in = new Entry(word, count);
                entryList.add(in);
            }
            Collections.sort(entryList);
            for(int i=0;i<4;i++){
                System.out.println(entryList.get(i).s + " apears " + 
                        entryList.get(i).frequency + " time(s) ");
            }
	}

	public Map<String, Character> getDictionary(){
            return dictionary;
	}
        
        public Map<String, String> getDictionary2(){
            return dictionary2;
        }
        
        public void printDictionary(){
            System.out.println("Compressed key");
            for(String key: dictionary.keySet()){
                System.out.println(key + " : " + dictionary.get(key));
            }
            System.out.println("Decompresed  key");
            for(String key: dictionary2.keySet()){
                System.out.println(key + " : " + dictionary2.get(key));
            }
        }
        
        private void makeDictionaries(){
            dictionary.put("and", '#');
            dictionary.put("basketball", '$');
            dictionary.put("is", '*');
            dictionary.put("the", '%');
            
            dictionary2.put("#", "and");
            dictionary2.put("$", "basketball");
            dictionary2.put("*", "is");
            dictionary2.put("%", "the");
        }
        
        ArrayList<String> getWordList(){
            return wordList;
        }
}
