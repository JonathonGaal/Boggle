import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public interface Game {
	public ArrayList<Square> currentGuess = new ArrayList<Square>();
	public Square[][] grid= new Square[5][5];	//board of squares
	
	public int STRX=40;
    public int STRY=50;
    public int GAP=105;
    public int SIZE=90;
    
	public Color tilePicked=new Color(68, 252, 84);
	public Color tileCanBePicked=new Color(182, 252, 188);
	
	public static void centeredText(String print, int x, int y, Graphics g) {
		g.drawString(print, x-g.getFontMetrics().stringWidth(print)/2, y);
	}
}
