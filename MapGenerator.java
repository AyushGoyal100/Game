
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator
{
  public int map[][];//2-D array that will contain all the bricks
  public int brickWidth;
  public int brickHeight;
  public MapGenerator(int row,int col)
  {
      map = new int[row][col];
      for(int i=0;i<map.length;i++)
      {
          for(int j=0;j<map[0].length;j++){
              map[i][j]=1;//1 will detect that tne particular brick has not been intersected by the ball
          }
      }
      brickWidth = 540/col;//setting brickWidht
      brickHeight = 150/row;//setting brickHeight
  }
  public void draw(Graphics2D g)//function for drawing bricks
  {
       for(int i=0;i<map.length;i++)
      {
          for(int j=0;j<map[0].length;j++){
              if(map[i][j] > 0)
              {
                  g.setColor(Color.WHITE);
                  g.fillRect(j*brickWidth + 80,i*brickHeight + 50,brickWidth,brickHeight);
                  
                  g.setStroke(new BasicStroke(3));
                  g.setColor(Color.black);
                  g.drawRect(j*brickWidth + 80,i*brickHeight + 50,brickWidth,brickHeight);
                  
              }
          }
      }
  }
  public void setBrickValue(int value,int row,int col)
  {
      map[row][col]=value;
  }
}
