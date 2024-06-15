import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Stage0 implements Game{	//starting interface
	public static String typedSeed="";	//seed that the typedSeed will enter in
	public static boolean finished=false;
	
	public static void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_0)
    		Stage0.typedSeed+="0";
    	else if(e.getKeyCode()==KeyEvent.VK_1)
    		Stage0.typedSeed+="1";
    	else if(e.getKeyCode()==KeyEvent.VK_2)
    		Stage0.typedSeed+="2";
    	else if(e.getKeyCode()==KeyEvent.VK_3)
    		Stage0.typedSeed+="3";
    	else if(e.getKeyCode()==KeyEvent.VK_4)
    		Stage0.typedSeed+="4";
    	else if(e.getKeyCode()==KeyEvent.VK_5)
    		Stage0.typedSeed+="5";
    	else if(e.getKeyCode()==KeyEvent.VK_6)
    		Stage0.typedSeed+="6";
    	else if(e.getKeyCode()==KeyEvent.VK_7)
    		Stage0.typedSeed+="7";
    	else if(e.getKeyCode()==KeyEvent.VK_8)
    		Stage0.typedSeed+="8";
    	else if(e.getKeyCode()==KeyEvent.VK_9)
    		Stage0.typedSeed+="9";
    	else if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
    		if(Stage0.typedSeed.length()>0)
    			Stage0.typedSeed=Stage0.typedSeed.substring(0, Stage0.typedSeed.length()-1);
    	if(e.getKeyCode()==KeyEvent.VK_ENTER)
    		if(Stage0.typedSeed.length()==6) {
    			Stage1.setUpSeed(Stage0.typedSeed);
    			finished=true;
    		}
	}
	public static void mousePressed(MouseEvent e) {
		if(30<e.getX()&&e.getX()<Visual.WIDE/2-15 && 300<e.getY()&&e.getY()<400) {//today
			Stage1.setUpToday();
			finished=true;
		}
		if(Visual.WIDE/2+15<e.getX()&&e.getX()<Visual.WIDE-30 && 300<e.getY()&&e.getY()<400) {//random
			Stage1.setUpRand();
			finished=true;
		}
	}
	public static void draw(Graphics g) {
		g.setColor(Color.black);
		//costom seed
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		Game.centeredText("Enter the seed you want:", Visual.WIDE/2, 80, g);
		g.drawRect(Visual.WIDE/2-75, 150, 150, 50);
		Game.centeredText(typedSeed, Visual.WIDE/2, 190, g);
		
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
		//todays seed
		g.drawRect(30, 300, Visual.WIDE/2-45, 100);
		Game.centeredText("Today's", Visual.WIDE/4, 370, g);
		
		//random seed
		g.drawRect(Visual.WIDE/2+15, 300, Visual.WIDE/2-45, 100);
		Game.centeredText("Random", 3*Visual.WIDE/4, 370, g);
	}
}
