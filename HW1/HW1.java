import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class HW1 {

	public static void main(String[] args) throws Exception{
		//FInal 
		
		String Input_File = args[0];
		String Output_File = args[1];
				
		//BufferedReader reader = new BufferedReader(new FileReader("tiny1.txt"));//set to input_file when done
		
		BufferedReader reader = new BufferedReader(new FileReader(Input_File));
		
		String line;
		
		ArrayList<String>Lines = new ArrayList<String>();

		while((line = reader.readLine()) != null) {
			String[] everything = line.split("\\.");
			for(int i = 0; i < everything.length; i++) {
				Lines.add(everything[i].toString().toLowerCase().trim());
			}
		}
		
		ArrayList<String>LinesEdited = Lines;
		
		//List of special words
		ArrayList<String>SpecialWords = new ArrayList<String>();
			SpecialWords.add("the");
			SpecialWords.add("of");
			SpecialWords.add("was");
			SpecialWords.add("but the");
			SpecialWords.add("it was");
			SpecialWords.add("in my");
			
		//ArrayList of sentence objects
		// This could be enhanced with a for loop, even better you could create the sentences list when you first pass through the file
		ArrayList<sentence>Sentences = new ArrayList<sentence>();
		for(int i = 0; i < LinesEdited.size(); i++) {
			Sentences.add(new sentence(LinesEdited.get(i)));
		}
		
		int max1 = 0;
		int max2 = 0;
		int max3 = 0;
		int highestfrequency = 0;
		int arrLen = 0;
		
		//ArrayList<ArrayList>Global = new ArrayList<ArrayList>();
		ArrayList<String>Words = new ArrayList<String>();
		ArrayList<Integer>WordCount = new ArrayList<Integer>();
		//Global.add(Words);
		//Global.add(WordCount);
		
		// Again an enhanced for loop would reduce code size
		for(int i = 0; i < Sentences.size(); i++) {//Fill in global
			arrLen = Sentences.get(i).getArrLen();
			sentence this_sentence = Sentences.get(i);
			// If you switch the order of the above two lines you could decrease code size, but also enhanced fro loop would do this even more
			for(int j = 0; j < arrLen; j++) {
				// ^^^^ enhanced for loop over all words in the sentence (sentence's array of words)
				if(Words.contains(this_sentence.getWord(j))) {//Add wordcount to word
					//int index = 0;
					int this_index = 0;
					int this_freq = 0;
					int add_freq = 0;
					for(int k = 0; k < Words.size(); k++) {
						if(Words.get(k).equals(this_sentence.getWord(j))) {
							this_index = k;
						}
					}
					this_freq = WordCount.get(this_index);
					add_freq = this_sentence.getWordFrequency(this_sentence.getWord(j));
					
					//int this_index = Words.indexOf(this_sentence.getWord(j));
					//int this_freq = Words.indexOf(this_sentence.getWordFrequency(j));
					//int this_new_freq = this_sentence.getWordFrequency(Words.indexOf(this_sentence.getWord(j)));
					WordCount.set(this_index, this_freq + add_freq);
				} else {//Add word and wordcount
					Words.add(Sentences.get(i).getWord(j));
					WordCount.add(Sentences.get(i).getWordFrequency(j));
				}
			}
		}
		
		//shift max's
		//Find max's
		for(int i = 0; i < WordCount.size(); i++) {
			//int temp = 0;
			if(WordCount.get(i) > max3) {
				//temp = max3;
				max3 = WordCount.get(i);
			}
			if(WordCount.get(i) > max2) {
				max3 = max2;
				max2 = WordCount.get(i);
			}
			if(WordCount.get(i) > max1) {
				max2 = max1;
				max1 = WordCount.get(i);
			}
		}
		
		//Find highest frequency
		for(int i = 0; i < Sentences.size(); i++) {
			arrLen = Sentences.get(i).getArrLen();
			for(int j = 0; j < arrLen; j++) {
				if(Sentences.get(i).getWordFrequency(j) > highestfrequency) {
					highestfrequency = Sentences.get(i).getWordFrequency(j);
				}
			}
		}
		
		//1
		/*
		System.out.println("1");
		for(int i = 0; i < WordCount.size(); i++) {
			if(WordCount.get(i) == max1) {
				System.out.println(Words.get(i) + ":" + WordCount.get(i));
			}
		}
		*/
		//2
				/*
				System.out.println("2");
				for(int i = 0; i < WordCount.size(); i++) {
					if(WordCount.get(i) == max3) {
						System.out.println(Words.get(i) + ":" + WordCount.get(i));
					}
				}
				*/
		//3
				/*
				System.out.println("3");
				for(int i = 0; i < Sentences.size(); i++) {
						for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
							if(Sentences.get(i).getWordFrequency(j) == highestfrequency) {
								System.out.println(Sentences.get(i).getWord(j) + ":" + highestfrequency + ":" + Sentences.get(i).getSentence());
							}
						}
				}
				*/
				//int max = 0;
		//4
				/*
				System.out.println("4");
				max = 0;
				for(int i = 0; i < Sentences.size(); i++) {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("the")){
							if(Sentences.get(i).getWordFrequency(j) > max) {
								max = Sentences.get(i).getWordFrequency(j);
							}
						}
					}
				}
				for(int i = 0; i < Sentences.size(); i++) {
					if (max == 0) {
						System.out.println("the:" + max + ":" + Sentences.get(i).getSentence());
					} else {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("the") && Sentences.get(i).getWordFrequency(j) == max){
							System.out.println("the:" + max + ":" + Sentences.get(i).getSentence());
						}
					}}
				}*/
		//5
				/*
				System.out.println("5");
				max = 0;
				for(int i = 0; i < Sentences.size(); i++) {
					
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("of")){
							if(Sentences.get(i).getWordFrequency(j) > max) {
								max = Sentences.get(i).getWordFrequency(j);
							}
						}
					}
				}
				for(int i = 0; i < Sentences.size(); i++) {
					if (max == 0) {
						System.out.println("of:" + max + ":" + Sentences.get(i).getSentence());
					} else {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("of") && Sentences.get(i).getWordFrequency(j) == max){
							System.out.println("of:" + max + ":" + Sentences.get(i).getSentence());
						}
					}
					}
				}
				*/
		//6
				/*
				System.out.println("6");
				max = 0;
				for(int i = 0; i < Sentences.size(); i++) {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("was")){
							if(Sentences.get(i).getWordFrequency(j) > max) {
								max = Sentences.get(i).getWordFrequency(j);
							}
						}
					}
				}
				for(int i = 0; i < Sentences.size(); i++) {
					if (max == 0) {
						System.out.println("but the:" + max + ":" + Sentences.get(i).getSentence());
					} else {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("was") && Sentences.get(i).getWordFrequency(j) == max){
							System.out.println("was:" + max + ":" + Sentences.get(i).getSentence());
						}
					}
					}
				}
				*/
		//7
				/*
				System.out.println("7");
				max = 0;
				for(int i = 0; i < Sentences.size(); i++) {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("but the")){
							if(Sentences.get(i).getWordFrequency(j) > max) {
								max = Sentences.get(i).getWordFrequency(j);
							}
						}
					}
				}
				for(int i = 0; i < Sentences.size(); i++) {
					if (max == 0) {
						System.out.println("but the:" + max + ":" + Sentences.get(i).getSentence());
					} else {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("but the") && Sentences.get(i).getWordFrequency(j) == max){
							System.out.println("but the:" + max + ":" + Sentences.get(i).getSentence());
						}
					}
					}
				}
				*/
		//8
				/*
				System.out.println("8");
				max = 0;
				for(int i = 0; i < Sentences.size(); i++) {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("it was")){
							if(Sentences.get(i).getWordFrequency(j) > max) {
								max = Sentences.get(i).getWordFrequency(j);
							}
						}
					}
				}
				for(int i = 0; i < Sentences.size(); i++) {
					if (max == 0) {
						System.out.println("it was:" + max + ":" + Sentences.get(i).getSentence());
					} else {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("it was") && Sentences.get(i).getWordFrequency(j) == max){
							System.out.println("it was:" + max + ":" + Sentences.get(i).getSentence());
						}
					}
					}
				}
				*/
		//9
				/*
				System.out.println("9");
				max = 0;
				for(int i = 0; i < Sentences.size(); i++) {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("in my")){
							if(Sentences.get(i).getWordFrequency(j) > max) {
								max = Sentences.get(i).getWordFrequency(j);
							}
						}
					}
				}
				for(int i = 0; i < Sentences.size(); i++) {
					if (max == 0) {
						System.out.println("in my:" + max + ":" + Sentences.get(i).getSentence());
					} else {
					for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
						if(Sentences.get(i).getWord(j).equals("in my") && Sentences.get(i).getWordFrequency(j) == max){
							System.out.println("in my:" + max + ":" + Sentences.get(i).getSentence());
						}
					}
					}
				}
				*/
		
				
				//OUTPUT
				
				//1
				FileWriter x = new FileWriter(Output_File + "_" + Integer.toString(1) + ".txt");
				BufferedWriter z = new BufferedWriter(x);
				for(int i = 0; i < WordCount.size(); i++) {
					if(WordCount.get(i) == max1) {
						z.write(Words.get(i) + ":" + WordCount.get(i));
						z.newLine();
					}
				}
				z.close();
				
				//2
						
						x = new FileWriter(Output_File + "_" + Integer.toString(2) + ".txt");
						z = new BufferedWriter(x);
						for(int i = 0; i < WordCount.size(); i++) {
							if(WordCount.get(i) == max3) {
								z.write(Words.get(i) + ":" + WordCount.get(i));
								z.newLine();
							}
						}
						z.close();
						
				//3
						
						x = new FileWriter(Output_File + "_" + Integer.toString(3) + ".txt");
						z = new BufferedWriter(x);
						for(int i = 0; i < Sentences.size(); i++) {
								for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
									if(Sentences.get(i).getWordFrequency(j) == highestfrequency) {
										z.write(Sentences.get(i).getWord(j) + ":" + highestfrequency + ":" + Sentences.get(i).getSentence());
										z.newLine();
									}
								}
						}
						z.close();
						int max = 0;
						
						
						
				//Simple Function Written near bottom	
				//4
						
					/*	x = new FileWriter(Output_File + "_" + Integer.toString(4) + ".txt");
						z = new BufferedWriter(x);
						max = 0;
						for(int i = 0; i < Sentences.size(); i++) {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("the")){
									if(Sentences.get(i).getWordFrequency(j) > max) {
										max = Sentences.get(i).getWordFrequency(j);
									}
								}
							}
						}
						for(int i = 0; i < Sentences.size(); i++) {
							if (max == 0) {
								z.write("the:" + max + ":" + Sentences.get(i).getSentence());
								z.newLine();
							} else {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("the") && Sentences.get(i).getWordFrequency(j) == max){
									z.write("the:" + max + ":" + Sentences.get(i).getSentence());
									z.newLine();
								}
							}}
						}
						z.close();
				//5
						
						x = new FileWriter(Output_File + "_" + Integer.toString(5) + ".txt");
						z = new BufferedWriter(x);
						max = 0;
						for(int i = 0; i < Sentences.size(); i++) {
							
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("of")){
									if(Sentences.get(i).getWordFrequency(j) > max) {
										max = Sentences.get(i).getWordFrequency(j);
									}
								}
							}
						}
						for(int i = 0; i < Sentences.size(); i++) {
							if (max == 0) {
								z.write("of:" + max + ":" + Sentences.get(i).getSentence());
								z.newLine();
							} else {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("of") && Sentences.get(i).getWordFrequency(j) == max){
									z.write("of:" + max + ":" + Sentences.get(i).getSentence());
									z.newLine();
								}
							}
							}
						}
						z.close();
						
				//6
						
						x = new FileWriter(Output_File + "_" + Integer.toString(6) + ".txt");
						z = new BufferedWriter(x);
						max = 0;
						for(int i = 0; i < Sentences.size(); i++) {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("was")){
									if(Sentences.get(i).getWordFrequency(j) > max) {
										max = Sentences.get(i).getWordFrequency(j);
									}
								}
							}
						}
						for(int i = 0; i < Sentences.size(); i++) {
							if (max == 0) {
								z.write("but the:" + max + ":" + Sentences.get(i).getSentence());
								z.newLine();
							} else {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("was") && Sentences.get(i).getWordFrequency(j) == max){
									z.write("was:" + max + ":" + Sentences.get(i).getSentence());
									z.newLine();
								}
							}
							}
						}
						z.close();
				//7
						
						x = new FileWriter(Output_File + "_" + Integer.toString(7) + ".txt");
						z = new BufferedWriter(x);
						max = 0;
						for(int i = 0; i < Sentences.size(); i++) {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("but the")){
									if(Sentences.get(i).getWordFrequency(j) > max) {
										max = Sentences.get(i).getWordFrequency(j);
									}
								}
							}
						}
						for(int i = 0; i < Sentences.size(); i++) {
							if (max == 0) {
								z.write("but the:" + max + ":" + Sentences.get(i).getSentence());
								z.newLine();
							} else {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("but the") && Sentences.get(i).getWordFrequency(j) == max){
									z.write("but the:" + max + ":" + Sentences.get(i).getSentence());
									z.newLine();
								}
							}
							}
						}
						z.close();
				//8
						
						x = new FileWriter(Output_File + "_" + Integer.toString(8) + ".txt");
						z = new BufferedWriter(x);
						max = 0;
						for(int i = 0; i < Sentences.size(); i++) {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("it was")){
									if(Sentences.get(i).getWordFrequency(j) > max) {
										max = Sentences.get(i).getWordFrequency(j);
									}
								}
							}
						}
						for(int i = 0; i < Sentences.size(); i++) {
							if (max == 0) {
								z.write("it was:" + max + ":" + Sentences.get(i).getSentence());
								z.newLine();
							} else {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("it was") && Sentences.get(i).getWordFrequency(j) == max){
									z.write("it was:" + max + ":" + Sentences.get(i).getSentence());
									z.newLine();
								}
							}
							}
						}
						z.close();
				//9
						
						x = new FileWriter(Output_File + "_" + Integer.toString(9) + ".txt");
						z = new BufferedWriter(x);
						max = 0;
						for(int i = 0; i < Sentences.size(); i++) {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("in my")){
									if(Sentences.get(i).getWordFrequency(j) > max) {
										max = Sentences.get(i).getWordFrequency(j);
									}
								}
							}
						}
						for(int i = 0; i < Sentences.size(); i++) {
							if (max == 0) {
								z.write("in my:" + max + ":" + Sentences.get(i).getSentence());
								z.newLine();
							} else {
							for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
								if(Sentences.get(i).getWord(j).equals("in my") && Sentences.get(i).getWordFrequency(j) == max){
									z.write("in my:" + max + ":" + Sentences.get(i).getSentence());
									z.newLine();
								}
							}
							}
						}
						z.close();*/
						
						//Easy do-er for 4-9
				
						for(int p = 4; p < 10; p++) {
							String wrd = SpecialWords.get(p - 4);
							x = new FileWriter(Output_File + "_" + p + ".txt");
							z = new BufferedWriter(x);
							max = 0;
							for(int i = 0; i < Sentences.size(); i++) {
								for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
									if(Sentences.get(i).getWord(j).equals(wrd)){
										if(Sentences.get(i).getWordFrequency(j) > max) {
											max = Sentences.get(i).getWordFrequency(j);
										}
									}
								}
							}
							for(int i = 0; i < Sentences.size(); i++) {
								if (max == 0) {
									z.write(wrd + max + ":" + Sentences.get(i).getSentence());
									z.newLine();
								} else {
								for(int j = 0; j < Sentences.get(i).getArrLen(); j++) {
									if(Sentences.get(i).getWord(j).equals(wrd) && Sentences.get(i).getWordFrequency(j) == max){
										z.write(wrd + max + ":" + Sentences.get(i).getSentence());
										z.newLine();
									}
								}}
							}
							z.close();
						}
			
		reader.close();
		
		//debugger
		/*for(int ii = 0; ii < Words.size(); ii++) {
			if(WordCount.get(ii) > 6) {
				System.out.printf("%s: %d\n", Words.get(ii), WordCount.get(ii));
			}
		}
		
		for(sentence s: Sentences) {
			//s.print_word_frequencies();
			s.printMostFrequent();
		}*/
		
	}

}
