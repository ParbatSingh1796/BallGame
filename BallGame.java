import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class BallGame extends Applet implements MouseListener,MouseMotionListener,KeyListener{
	/* Variable declaration x,y(for cordinate of Ball),a(for rectangle's x cordinate),count(for counting points) */
	int x,y,a;
	int count;
	/* flag1,flag2 for change direction of Ball */
	boolean flag1=true;
	boolean flag2=true;
	boolean flag=true;
	public void init(){
		/*initialization of declared variable */
		count=0;
		x=0;
		y=300;
		a=0;
		/*mouse action listener*/
		addMouseListener(this);
		addMouseMotionListener(this);
		/*KeyListener*/
		addKeyListener(this);
	}
	/*Keypressed event to restart game after game over*/
	public void keyPressed(KeyEvent e){
		if(flag==false){
		flag=true;
		count=0;
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}
	public void mouseClicked(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	/*Mouse move event to get x cordinate of rectangle ,y is not changed*/
	public void mouseMoved(MouseEvent e){
		a=e.getX();
		repaint();
	}
	public void mouseDragged(MouseEvent e){}
	public void paint(Graphics g){
		/* boolean flag to restart game*/
		if(flag==true){
			/*Score of player*/
			g.drawString("Score="+count,20,20);
			/*Ball*/
			g.drawOval(x,y,5,5);
			/*Rectange to bounce Ball after collision on it*/
			g.drawRect(a,490,60,5);
			/*Bounce Ball after Collision to rectangle */
			if((x<=a+60)&&(x>=a)&&(y==485)){
				/*increase score of player*/
				count++;
				/*Change flag to change path of Ball*/
				if((flag1==false)&&(flag2==true)){
					flag2=false;
				}
				else if((flag1==true)&&(flag2==true)){
					flag2=false;
				}
			}
			/*If Ball collide at left edge of rectangle*/
			if((x>=(a-10))&&(x<a)&&(y==485)){
				flag1=false;
				flag2=false;
				count++;
			}
			/*If Ball collide at right edge of rectangle*/
			if((x<=(a+70))&&(x>(a+60))&&(y==485)){
				flag1=true;
				flag2=true;
				count++;
			}
			/*If Ball reach at left boundry of game*/
			if(x==0){
				flag1=true;
			}
			/*If Ball reach at right boundry of game*/
			else if(x==595){
				flag1=false;
			}
			/*If Ball not collide with rectangle(Game over)*/
			else if(y==495){
				flag=false;
				y=100;
			}
			/*If Ball reach at top boundry of game*/
			else if(y==0){
				flag2=true;
			}
			/*Path of Ball according to value of flag1 and flag2*/
			if((flag1==true)&&(flag2==true)){
				y=y+1;
				x=x+1;
			}
			else if((flag1==true)&&(flag2==false)){
				x=x+1;
				y=y-1;
			}
			else if((flag1==false)&&(flag2==true)){
				y=y+1;
				x=x-1;
			}
			else if((flag1==false)&&(flag2==false)){
				y=y-1;
				x=x-1;
			}
			/*To sleep current thread*/
			try{
				Thread.sleep(10);
			}
			/*catch interrupted exception*/
			catch(InterruptedException e){
			
			}
			/*calling repaint to paint after thread sleep*/
			repaint();
		}
		/*Display score after game over*/
		else{
			g.drawString("Game Over",250,250);
			g.drawString("Score="+count,255,270);
			g.drawString("Press any Key to restart game",220,290);
		}
	}
}
//<applet code="BallGame.class" width=600 height=500></applet>