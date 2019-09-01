import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sentence {
	
	//int arr length?
	
	
	ArrayList<String>Words = new ArrayList<String>();
	ArrayList<Integer>WordFrequency = new ArrayList<Integer>();
	String S = "";
	String[] cut;
	
	
	boolean found_most_frequent = false;
	
	ArrayList<String> mostFrequentWords = new ArrayList<String>();
	int max_frequency;
	
	sentence(String sent){
		S = sent;
		doWork();
	}
	
	String getSentence() {
		return S;
	}
	
	int getMaxFrequency() {
		return max_frequency;
	}
	
	int getArrLen() {
		return Words.size();
	}
	
	String getWord(int index) {//Returns word
		return Words.get(index);
	}
	int getWordFrequency(int index) {//Returns words frequency
		return WordFrequency.get(index);
	}
	
	/*
	 * 	THis is a helper function that will assign the most frequent words to a
	 * 	global counter and array list.  This way if we need to get it again at a
	 *  later time (or we need the max frequency count) it is already assigned
	 *  and we never have to recompute it again!
	 */
	void _assignMostFrequent() {
		found_most_frequent = true;
		max_frequency = 0;
		for(String word : Words) {
			int this_frequency = getWordFrequency(word);
			if(this_frequency > max_frequency) {
				mostFrequentWords.clear();
				max_frequency = this_frequency;
				mostFrequentWords.add(word);
			} else if (this_frequency == max_frequency) {
				mostFrequentWords.add(word);
			}
		}
	}
	
	ArrayList<String> getMostFrequent() {
		if(! found_most_frequent) {
			_assignMostFrequent();
		}
		return mostFrequentWords;
	}
	
	void printMostFrequent() {
		System.out.printf("Printing most frequent words for sentence '%s'\n", S);
		for(String wrd: getMostFrequent()) {
			System.out.printf("'%s' has a frequency of '%d'\n", wrd, max_frequency);
		}
	}
	/*String getWord(String wrd) {
		
	}*/
	int getWordFrequency(String wrd) {//Return words frequency
		for(int i = 0; i < Words.size(); i++) {
			if(Words.get(i).equals(wrd)) {
				return WordFrequency.get(i);
			}
		}
		return 0;
	}
	
	void print_word_frequencies() {
		System.out.printf("Printing frequencies for sentence: %s\n", S);
		// In this case an enhanced for loop wouldn't be good because we have to loop through two serperate lists by the same index
		// In python you can zip two different lists together and iterate through that combination with the 'zip' function... but sadly this isn't python
		for(int ii = 0; ii < Words.size(); ii++) {
			System.out.printf("Word: '%s' has a count of: '%d'\n", Words.get(ii), WordFrequency.get(ii));
		}
	}
	
	//Pattern is catching words with these parts in them
			Pattern bt = Pattern.compile("but the");
			Pattern iw = Pattern.compile("it was");
			Pattern im = Pattern.compile("in my");
	
	void doWork() {
		// You could have an array of special strings and then iterate over that rather than having a loop for each of the special cases
		//Special Cases start
		Matcher bt_m = bt.matcher(S);
		while(bt_m.find()) {
			if(Words.contains("but the")) {//get index
				for(int i = 0; i < Words.size(); i++) {
					if(Words.get(i).equals("but the")) {
						WordFrequency.set(i, WordFrequency.get(i) + 1);
					}
				}
			} else {
				Words.add("but the");
				WordFrequency.add(1);
			}
		}
		Matcher iw_m = iw.matcher(S);
		while(iw_m.find()) {
			if(Words.contains("it was")) {//get index
				for(int i = 0; i < Words.size(); i++) {
					if(Words.get(i).equals("it was")) {
						WordFrequency.set(i, WordFrequency.get(i) + 1);
					}
				}
			} else {
				Words.add("it was");
				WordFrequency.add(1);
			}
		}
		Matcher im_m = im.matcher(S);
		while(im_m.find()) {
			if(Words.contains("in my")) {//get index
				
				
				/*
				 *	I think your bug was here because of a misplaced } ? not really sure
				 */
				for(int i = 0; i < Words.size(); i++) {
					Words.add("in my");
					WordFrequency.add(1);
				}
			}
		}
		//Special cases end
		
		
		// ENHANCED FOR LOOP!!!!!!!!
		cut = S.split(" ");
		for(int i = 0; i < cut.length; i++) {
			if(Words.contains(cut[i])) {//get index
				for(int j = 0; j < Words.size(); j++) {
					if(Words.get(j).equals(cut[i])) {
						WordFrequency.set(j, WordFrequency.get(j) + 1);
					}
				}
			} else {
				Words.add(cut[i]);
				WordFrequency.add(1);
			}
		}
	}
	
	
}
