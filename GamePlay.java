                //JPanel-here we will run our game 
//we have to include this panel into the JFrame
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;//for using ActionListener Interface 
import java.awt.Rectangle;
import java.awt.event.ActionEvent;  //for using KeyListener Interface 
import java.awt.event.ActionListener;  //for using JPanel
import java.awt.event.KeyEvent; //for using Timer
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
//to make this class a Panel extend JPanel
//KeyListener-for detecting the arrow keys that will be pressed
//ActionListener- for moving the ball
/*all the abstract methods that reside in the interfaces have to be 
implemented inside the class that is implementing the 
KeyListener and ActionListener*/
public class GamePlay extends JPanel implements KeyListener,ActionListener
{
  private boolean play=false;//bcs when game starts it shud not strt with itself
  private int score=0; //starting score shd be zero
  private int totalBricks=21;// total no. of bricks
  
  private Timer timer; // we need a Timer class for setting the time of ball that how fast shd it move
  private int delay=8; // speed that we are going to give the timer in int\
  
  // setting properties for the bar and ball
  
  private int playerX=310;//starting position of slider
  
  private int ballPosX=120; //starting position of the ball on X-axis
  private int ballPosY=350; //starting position of the ball on Y-axis
  
  //setting direction of ball
  int ballXdir = -1; 
  int ballYdir = -2;
  
  private MapGenerator map;//creating object of MapGenerator class
  
  public GamePlay()
  {
      map=new MapGenerator(3,7);
      
      addKeyListener(this);//in order to work with key listeners we need to add them
      setFocusable(true);      
      setFocusTraversalKeysEnabled(false);
      timer = new Timer(delay,this);/*creating object for Timer class AND
      speed of it is in delay variable */
      timer.start();
  }
  
 //
  public void paint(Graphics g)//paint function receives graphics object
  {    
     // setting background
      g.setColor(Color.BLACK);
      g.fillRect(1,1,692,592);
      
      //drawing map
      map.draw((Graphics2D)g);
      
     // setting borders
      g.setColor(Color.YELLOW);
      g.fillRect(0,0,3,592);
      g.fillRect(0,0,692,3);
      g.fillRect(691,0,3,592);
      
      //scores
      g.setColor(Color.white);
      g.setFont(new Font("serif",Font.BOLD,25));
      g.drawString(""+  score,590,30);
      
     //setting paddle
      g.setColor(Color.GREEN);
      g.fillRect(playerX,550,100,8);
      
     //setting the ball
      g.setColor(Color.CYAN);
      g.fillOval(ballPosX,ballPosY,20,20);
      
      if(totalBricks <= 0)
      {
          play = false;
          ballXdir = 0;
          ballYdir = 0;
          g.setColor(Color.red);
          g.setFont(new Font("serif",Font.BOLD,30));
          g.drawString("You Won",260,300);
          
          g.setFont(new Font("serif",Font.BOLD,20));
          g.drawString("press enter to start",230,350);
       }
      if(ballPosY > 570)
      {
          play = false;
          ballXdir = 0;
          ballYdir = 0;
          g.setColor(Color.red);
          g.setFont(new Font("serif",Font.BOLD,30));
          g.drawString("Game over,scores:",190,300);
          
          
          g.setFont(new Font("serif",Font.BOLD,20));
          g.drawString("press enter to start",230,350);
      }
      g.dispose();
  }
  
  @Override
  public void keyReleased(KeyEvent e)
  {
  }
  @Override
  public void keyTyped(KeyEvent e)
  {
  }
  
  @Override
  public void actionPerformed(ActionEvent e) //this method will be automatically called
  {
      timer.start();//starting the timer
      if(play)
      {
          if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8)))
          {
              ballYdir = -ballYdir;
          }
       A: for(int i=0;i<map.map.length;i++)
          {
              for(int j=0;j<map.map[0].length;j++)
              {
                  if(map.map[i][j]>0)
                  {
                      int brickX= j* map.brickWidth + 80;
                      int brickY= i* map.brickHeight+ 50;
                      int brickWidth= map.brickWidth;
                      int brickHeight=map.brickHeight;
                      
                      Rectangle rect= new Rectangle(brickX,brickY,brickWidth,brickHeight);
                      Rectangle ballRect = new Rectangle(ballPosX,ballPosY,20,20);
                      Rectangle brickRect = rect;
                      
                      if(ballRect.intersects(brickRect))
                      {
                          map.setBrickValue(0,i,j);
                          totalBricks--;
                          score += 5;
                          
                          if(ballPosX + 19 <= brickRect.x || ballPosX + 1 >= brickRect.x + brickRect.width)
                          {
                              ballXdir = -ballXdir;
                          }
                          else
                          {
                              ballYdir = -ballYdir;
                          }
                          break A;
                      }
                              
                  }
              }
          }
          
          
          ballPosX += ballXdir;
          ballPosY += ballYdir;
          if(ballPosX < 0){
              ballXdir = -ballXdir;
          }
          if(ballPosY < 0){
              ballYdir = -ballYdir;
          }
          if(ballPosX > 670){
              ballXdir = -ballXdir;
          }
              
      }
      repaint(); //it will recall the paint method and draw each and everything again
             
         
  }
  
  @Override
  public void keyPressed(KeyEvent e)
   {
      if(e.getKeyCode() == KeyEvent.VK_RIGHT)//we have detected the right key
      {
          if (playerX >=600){
              playerX = 600;
          }
          else{
              moveRight();
          }
      }
       if(e.getKeyCode() == KeyEvent.VK_LEFT)//we have detected the left key
       {
          if (playerX <10){
              playerX = 10;
          }
          else{
              moveLeft();
          }
      }
       if(e.getKeyCode() == KeyEvent.VK_ENTER)
       {
           if(!play)
           {
               play = true;
               ballPosX = 120;
               ballPosY = 350;
               ballXdir = -1;
               ballYdir = -2;
               playerX = 310;
               score = 0;
               totalBricks = 21;
               map = new MapGenerator(3,7);
               
               repaint();
           }
       }
    }
  public void moveRight()
  {
      play = true;// bcs this variable was set to false at the top
      playerX += 20;//pressing right will move the player 20 pixels right
  }
  public void moveLeft()
  {
      play = true;
      playerX -= 20;// pressing left will move the player 20 pixels left
  }
  
}
