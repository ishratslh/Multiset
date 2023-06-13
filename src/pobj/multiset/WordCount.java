package pobj.multiset;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class WordCount {
	public static void wordcount(MultiSet<String> ms) throws IOException {
		
		String file = "data/test.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader( new FileReader(file) );
		} catch (IOException ex2) {ex2.printStackTrace();}
		finally {
			String line;
	

			while ( ( line = br.readLine() ) != null ) { 
				for (String word : line.split("\\P{L}+")) { 
			
					if (word.equals("")) continue; // ignore les mots vides 
		 
					ms.add(word);
				} 
			}br.close(); 
			
			
		
			List<String> mots = ms.elements(); //retourne la liste des éléments du multi-ensemble
			
			mots.sort( new Comparator<String>() {
								public int compare(String s1, String s2) {
									return Integer.compare(ms.count(s2), ms.count(s1));
								}
						   }   
			);
		
			//afficher les 10 derniers éléments de la liste
			int i = 0;
			while (i<10) {
				System.out.print(mots.get(i) + " ");
				i++;
			}
			
		}
		
	}
	
	public static void main(String[]args) throws IOException {
		HashMultiSet<String> multi = new HashMultiSet<String>();
		wordcount(multi);
		
	}
	
	
	
	

}