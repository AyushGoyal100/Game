                 //JFrame
import javax.swing.JFrame;  //for using JFrame
public class BrickBreaker   //in this class we are creating JFrame
{
  public static void main(String args[])
    {
      JFrame obj=new JFrame();//creating object of JFrame
      
      GamePlay game=new GamePlay();//creating object of GamePlay class
      
      //setting proprerties of JFrame
      obj.setBounds(10,10,700,600); //setting size of frame
      obj.setTitle("BrickBreaker");   //setting title
      obj.setResizable(false);      //setting resizable
      obj.setVisible(true);       //setting visible
      obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//property of JFrame
      obj.add(game);//adding object of GamePlay class into object of JFrame
      /* it will show an error saying that object of this class i.e "game" 
      is not the panel AND for removing the error we have to extend the 
      GamePlay class by JPanel */         
     }
}
