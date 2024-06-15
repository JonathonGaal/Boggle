import java.util.ArrayList;

public class Square implements Game {	//the squares with the letters
	public char letter;
	public int x;
	public int y;
	public ArrayList<Square> nextPossiblePicks;

	public Square(Cube c, int px, int py) {
		letter=c.getChar();
		x=px;
		y=py;
		update();
	}
	public void update() {
		nextPossiblePicks = new ArrayList<>();
		nextPossiblePicks.add(grid[Math.max(x-1, 0)]						[Math.max(y-1, 0)]);
		nextPossiblePicks.add(grid[Math.max(x-1, 0)]						[y]);
		nextPossiblePicks.add(grid[Math.max(x-1, 0)]						[Math.min(y+1, grid.length-1)]);
		nextPossiblePicks.add(grid[x]										[Math.max(y-1, 0)]);
		nextPossiblePicks.add(grid[x]										[Math.min(y+1, grid.length-1)]);
		nextPossiblePicks.add(grid[Math.min(x+1, grid[0].length-1)]	[Math.max(y-1, 0)]);
		nextPossiblePicks.add(grid[Math.min(x+1, grid[0].length-1)]	[y]);
		nextPossiblePicks.add(grid[Math.min(x+1, grid[0].length-1)]	[Math.min(y+1, grid.length-1)]);
		nextPossiblePicks.remove(this);

//		for(int x=nextPossiblePicks.size()-1; x>=0; x--)
//			if(currentGuess.contains(nextPossiblePicks.get(x)))
//				nextPossiblePicks.remove(x);
		
		int ind = currentGuess.indexOf(this);
		if(ind!=currentGuess.size()-1) {
			if(!nextPossiblePicks.contains(currentGuess.get(ind+1))) {
				while(currentGuess.size()>ind+1)
					currentGuess.remove(ind+1);
			}
			
		}
	}
	
	public String toString() {
		if(letter=='q')
			return "Qu";
		else
			return (""+letter).toUpperCase();
	}
}
