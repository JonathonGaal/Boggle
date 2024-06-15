
import java.awt.Color; 
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.Timer; 

 
public class Visual implements ActionListener, KeyListener, MouseListener {
 
    private JFrame frame;       //The outside shell of the window
    public DrawingPanel panel;  //The interior window
    private Timer visualtime;   //Runs/Refreshes the screen. 

    public static final int WIDE = 900;
    public static final int HIGH = 700;
    
    public int stage;	//0=menu, 1=playing, 2=finished

    
    public Visual()
    {
        frame = new JFrame("Boggle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new DrawingPanel();
        panel.setPreferredSize(new Dimension(WIDE, HIGH));
        frame.getContentPane().add(panel);
        panel.setFocusable(true);
        panel.requestFocus();
        panel.addKeyListener(this);
        panel.addMouseListener(this);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true); 
        
        Initialize();
        
        visualtime = new Timer(20, this);     
        visualtime.start();
    } 
 
    public void Initialize(){
        stage=0;
    }
    
    
    public void actionPerformed(ActionEvent e)
    {    
        //Once the new Visual() is launched, this method runs an infinite loop
    	if(stage==0) {
    		if(Stage0.finished)
    			stage=1;
    	}
    	if(stage==1) {
	    	Stage1.update();
	    	Stage1.mouse=panel.getMousePosition();
	    	if(Stage1.finished)
    			stage=2;
    	}
    	if(stage==2) {
	    	Stage1.update();
	    	if(Stage1.finished)
    			stage=2;
    	}
    	
        panel.repaint();
    }
    
    
    public void keyPressed(KeyEvent e)
    {      
    	
        if(stage==0)
        	Stage0.keyPressed(e);
        
        else if(stage==1)
        	Stage1.keyPressed(e);
        
    }
    
    public void keyTyped(KeyEvent e) {  }
    public void keyReleased(KeyEvent e) {  }
        
	public void mousePressed(MouseEvent e) {
		if(stage==0)
			Stage0.mousePressed(e);
		if(stage==1)
			Stage1.mouseHeld=true;
	}
	public void mouseReleased(MouseEvent e) {
		if(stage==1)
			Stage1.mouseHeld=false;
	}
	public void mouseEntered(MouseEvent e) {	}
	public void mouseExited(MouseEvent e) {		}
	public void mouseClicked(MouseEvent e) {	}

    private class DrawingPanel extends JPanel { 
//        /**
//		 * 
//		 */
//		private static final long serialVersionUID = 1L;

		public void paintComponent(Graphics g)         
        {
            super.paintComponent(g);
            panel.setBackground(Color.white);
            g.setColor(Color.black);
            
            if(stage==0) 
            	Stage0.draw(g);
            
            else {
	            Stage1.draw(g);
            }
        }
    }

	
}
  

