import java.awt.Font;
import java.awt.Graphics;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Found {
	public static ArrayList<String> words=new ArrayList<String>();
	public static ArrayList<String> possibleWords=new ArrayList<String>();
	private static FileReader fr;

	public static boolean add(String word) {
		
		try {
			if(word.length()==5)
				fr = new FileReader("d5.txt");
			else if(word.length()==6)
				fr = new FileReader("d6.txt");
			else if(word.length()==7)
				fr = new FileReader("d7.txt");
			else if(word.length()==8)
				fr = new FileReader("d8.txt");
			else if(word.length()==9)
				fr = new FileReader("d9.txt");
			else if(word.length()>=10)
				fr = new FileReader("d10+.txt");
			else
				return false;
			if(search(word)) {
				words.add(word.toLowerCase());
				return true;
			}
			return false;
		}
		catch (Exception e){
			return false;
		}
	}
	/**
	 * returns if word is a valid word
	 */
	private static boolean search(String word) {
		
		if(words.contains(word))
			return false;
		char c;
		String temp="";
		while(true) {
			int i=-1;
			try {
				i = fr.read();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i==-1)
				return false;
			c=(char)i;
			if(c=='\n')	{
				if(word.equals(temp.toLowerCase()))
					return true;
				temp="";
			}
			else
				temp+=""+c;
		}
	}
	public static int score() {
		int s=0;
		for(int x=0; x<words.size(); x++) {
			int length=words.get(x).length();
			if(length==5)
				s+=2;
			else if(length==6)
				s+=3;
			else if(length==7)
				s+=5;
			else if(length>=8)
				s+=11;
				
			
		}
		return s;
	}
	public static void draw(Graphics g) {
        int size=50;
        while(size*words.size()>350)
        	size--;
        
        g.setFont(new Font("TimesRoman", Font.PLAIN, size));
        for(int x=0; x<words.size(); x++) {
        	String temp = words.get(x).substring(0,1).toUpperCase()+words.get(x).substring(1);
        	Game.centeredText(temp, 713, 120+(size)*x, g);
        }
	}
}