import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.Date;

public class Stage1 implements Game{
	private final static int TIMETOPLAY = 180;
	private static long startTime;
    private static int timeLeft;
    
    public static boolean finished = false;
	
	public static int seed;

    public static Point mouse;
    public static Point lastMouse;	//(0,-1) null		(-1,-1) backspace		(n,n) square
    public static boolean mouseHeld;	
    
    public static void setUpRand() {
		seed=(int)(Math.random()*1000000);
		setUpFinish();
	}
	public static void setUpToday() {
		String temp=LocalDate.now().toString();
		temp=temp.substring(2, 4)+temp.substring(5, 7)+temp.substring(8, 10);
		seed=Integer.parseInt(temp);
		setUpFinish();
	}
	public static void setUpSeed(String s) {
		seed=Integer.parseInt(s);
		setUpFinish();
	}
	private static void setUpFinish() {
		Cube.setCubes();	//makes and shuffles cubes
		//fills grid
		int c=0;
		for(int x=0; x<grid.length; x++)
			for(int y=0; y<grid[0].length; y++) {
				grid[x][y]=new Square(Cube.cubes.get(c), x, y);//gets random side of cube based on seed
				Cube.cubes.set(c, null); //to save memory
				c++;
			}
		Stage1.startTime = new Date().getTime();
	}
	
    public static void update() {
    	long now = new Date().getTime();
        timeLeft=TIMETOPLAY-(int)(now-startTime)/1000;
    	if(timeLeft==0) 
    		finished=true;
    	if(finished)
    		timeLeft=0;
    	
    	for(int i=currentGuess.size()-1; i>=0; i--)
    		currentGuess.get(i).update();
    	
    	if(mouseHeld)
    		mousePressed();
    	else
    		lastMouse=new Point(0, -1);	//null
    }
    public static void keyPressed(KeyEvent e) {
    	if(e.getKeyCode()==KeyEvent.VK_ENTER) {
    		String strGuess="";
    		for(Square s:currentGuess)
    			strGuess+=s.toString().toLowerCase();
    		
			Found.add(strGuess);
			currentGuess.clear();
    	}
    	else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE) {
    		if(currentGuess.size()>0)
    			currentGuess.remove(currentGuess.size()-1);
    	}
    	else if(e.getKeyCode()==KeyEvent.VK_SPACE) {
    		currentGuess.clear();
    	}


    }
	public static void mousePressed() {
		if(STRX<mouse.getX()&&mouse.getX()<STRX+GAP*4+SIZE&&STRY<mouse.getY()&&mouse.getY()<STRY+GAP*4+SIZE) {
			int x=-1;
	    	int y=-1;
	    	for(int n=0; n<5; n++) {
	    		if(STRX+GAP*n<mouse.getX() && mouse.getX()<STRX+GAP*n+SIZE) {
	    			x=n;
	    			n=5;
	    		}
	    	}
	    	for(int n=0; n<5; n++) {
	    		if(STRY+GAP*n<mouse.getY() && mouse.getY()<STRY+GAP*n+SIZE) {
	    			y=n;
	    			n=5;
	    		}
	    	}
	    	if(x==-1||y==-1) {
	    		return;
	    	}
	    	if(x==lastMouse.x && y==lastMouse.y)
	    		return;
	    	lastMouse.x=x;
	    	lastMouse.y=y;
	    	if(currentGuess.contains(grid[x][y]))
	    		currentGuess.remove(grid[x][y]);
	    	else
	    		currentGuess.add(grid[x][y]);
		}
		else {
//	        //enter button
//	        g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
//	        g.drawRect(565, 575, 295, 90);
//	        Game.centeredText("Enter", 565+295/2, 575+90-12, g);
//	        //backspace button
//	        g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
//	        g.drawRect(40, 575, 247, 90);
//	        Game.centeredText("Back", 40+247/2, 575+90-12, g);
//	        //clear button
//	        g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
//	        g.drawRect(40+247+16, 575, 247, 90);
//	        Game.centeredText("Clear", 40+247+16+247/2, 575+90-12, g);
			
			if(565<mouse.getX() && mouse.getX()<565+295 && 575<mouse.getY() && mouse.getY()<575+90) {//enter
				String strGuess="";
	    		for(Square s:currentGuess)
	    			strGuess+=s.toString().toLowerCase();
	    		
				Found.add(strGuess);
				currentGuess.clear();
			}
			else if(40<mouse.getX() && mouse.getX()<40+247 && 575<mouse.getY() && mouse.getY()<575+90) {//back
				if(lastMouse.x==-1)
					return;
				lastMouse = new Point(-1,-1);
				if(currentGuess.size()>0)
	    			currentGuess.remove(currentGuess.size()-1);
			}
			else if(40+247+16<mouse.getX() && mouse.getX()<40+247+16+247 && 575<mouse.getY() && mouse.getY()<575+90) {//clear
				currentGuess.clear();
			}
		}
		
	}
	public static void draw(Graphics g) {
		/*
		 * 			board	side
		 * min x	40		600
		 * max x	565		900
		 */
		//seed and time
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.drawString("Seed: " + seed, 40, 30);
        g.drawString("Time: " + timeLeft, 550-g.getFontMetrics().stringWidth("Time: "+ timeLeft), 30);
        
        //current guess
        g.setFont(new Font("TimesRoman", Font.PLAIN, 80));
        String strGuess="";
        for(int x=0; x<currentGuess.size(); x++)
        	strGuess+=currentGuess.get(x).toString();
        
        if(strGuess.length()>0) {
        	int size=80;
        	g.setFont(new Font("TimesRoman", Font.PLAIN, size));
        	strGuess=strGuess.substring(0, 1).toUpperCase() + strGuess.substring(1);
        	while(g.getFontMetrics().stringWidth(strGuess)>300) {
        		size--;    
        		g.setFont(new Font("TimesRoman", Font.PLAIN, size));
        	}
        	Game.centeredText(strGuess, 730, 60, g);
        }
        
        //score
        g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        Game.centeredText("Score: "+Found.score(), 713, 550, g);
        
        //enter button
        g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
        g.drawRect(565, 575, 295, 90);
        Game.centeredText("Enter", 565+295/2, 575+90-12, g);
        //backspace button
        g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
        g.drawRect(40, 575, 247, 90);
        Game.centeredText("Back", 40+247/2, 575+90-12, g);
        //clear button
        g.setFont(new Font("TimesRoman", Font.PLAIN, 90));
        g.drawRect(40+247+16, 575, 247, 90);
        Game.centeredText("Clear", 40+247+16+247/2, 575+90-12, g);
        
        g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
        for(int x=0; x<grid.length; x++) {
        	for(int y=0; y<grid[0].length; y++) {
        		g.setColor(Color.white);
        		if(currentGuess.contains(grid[x][y])) {
        			g.setColor(tilePicked);
        		}
        		else if(currentGuess.size()==0||currentGuess.get(currentGuess.size()-1).nextPossiblePicks.contains(grid[x][y])) {
        			g.setColor(tileCanBePicked);
        		}
        		g.fillRect(STRX+GAP*x, STRY+GAP*y, SIZE, SIZE);
    			g.setColor(Color.black);
        		Game.centeredText(grid[x][y].toString(), STRX+GAP*x+SIZE/2, STRY+GAP*y+SIZE/2+20, g);
        	}
        }
        Found.draw(g);
	}
}
