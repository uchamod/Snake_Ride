
package snake_game;

import java.awt.HeadlessException;
import javax.swing.JFrame;


public class GameFrame extends JFrame{

    public GameFrame()  {
        this.add(new GamePanel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("SNAKE RIDE(Chamod.Bros 2023.V2)");
        
        
        
    }
    
    
    
}
