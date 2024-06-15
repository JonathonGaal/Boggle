import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cube {		//25 cubes that will be rolled to get what square will be used
	public static ArrayList<Cube> cubes =  new ArrayList<Cube>();
	public ArrayList<Character> letter = new ArrayList<Character>();
	
	public Cube(char l0, char l1, char l2, char l3, char l4, char l5) {
		letter.add(l0);
		letter.add(l1);
		letter.add(l2);
		letter.add(l3);
		letter.add(l4);
		letter.add(l5);
	}
	public static void setCubes() {
		cubes.add(new Cube('k', 'i', 'q', 'w', 'u', 'l'));
		cubes.add(new Cube('l', 'i', 'e', 'c', 't', 'i'));
		cubes.add(new Cube('m', 'e', 'a', 'u', 'g', 'e'));
		cubes.add(new Cube('m', 'a', 'n', 'e', 'g', 'n'));
		cubes.add(new Cube('q', 'b', 'k', 'x', 'j', 'z'));
		cubes.add(new Cube('s', 's', 's', 'n', 'e', 'u'));
		cubes.add(new Cube('d', 'd', 't', 'n', 'h', 'o'));
		cubes.add(new Cube('s', 'a', 'a', 'a', 'r', 'f'));
		cubes.add(new Cube('d', 'h', 'l', 'h', 'r', 'o'));
		cubes.add(new Cube('e', 't', 't', 'm', 'o', 't'));
		cubes.add(new Cube('a', 'n', 'n', 'n', 'e', 'd'));
		cubes.add(new Cube('l', 'o', 'r', 'n', 'h', 'd'));
		cubes.add(new Cube('f', 's', 'i', 'p', 'r', 'y'));
		cubes.add(new Cube('s', 'a', 'f', 'r', 'a', 'i'));
		cubes.add(new Cube('t', 'o', 'o', 't', 'o', 'u'));
		cubes.add(new Cube('w', 'o', 't', 'n', 'o', 'u'));
		cubes.add(new Cube('s', 'e', 'i', 'c', 't', 'p'));
		cubes.add(new Cube('s', 'c', 'e', 't', 'n', 'c'));
		cubes.add(new Cube('r', 'r', 'o', 'v', 'w', 'g'));
		cubes.add(new Cube('t', 'i', 'i', 't', 'i', 'e'));
		cubes.add(new Cube('r', 'f', 'i', 'a', 'y', 's'));
		cubes.add(new Cube('l', 'e', 'i', 'c', 't', 'p'));
		cubes.add(new Cube('e', 'e', 'a', 'e', 'e', 'a'));
		cubes.add(new Cube('e', 'e', 'm', 'e', 'e', 'a'));
		cubes.add(new Cube('h', 'o', 'l', 'd', 'n', 'h'));
		Collections.shuffle(cubes, new Random(Stage1.seed));
	}
	public char getChar() {	//get a random side
		Collections.shuffle(letter, new Random(Stage1.seed));
		return letter.get(0);
	}
}
