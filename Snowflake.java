import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/** public class Snowflake that will be used to create the snowflake instance in the main class.*/ 
public class Snowflake extends JPanel {
    
    private int level; // The level of depth of the snowflake
    
    public Snowflake(int level){
        this.level = level;
    }
/** prvate class drawSnow that caluclates that draws the Koch Snowflake recurviely based on the level data feild that comtrols the depth */
    private void drawSnow (Graphics2D g2d, int lev, double x1, double y1, double x5, double y5){
        double deltaX, deltaY, x2, y2, x3, y3, x4, y4;
// If the depth level is zero draw the base triangle else calculate the lenght of each side of the triangle, slit it into three and draw the new trangle
// in the middle and recursivly call itself until the depth is eqaul to zero.
        if (lev == 0){
            g2d.draw(new Line2D.Double(x1, y1, x5, y5));
        }
        else{
              deltaX = x5 - x1;
              deltaY = y5 - y1;

              x2 = x1 + deltaX / 3;
              y2 = y1 + deltaY / 3;

              x3 = (0.5 * (x1+x5) + Math.sqrt(3) * (y1-y5)/6);
              y3 = (0.5 * (y1+y5) + Math.sqrt(3) * (x5-x1)/6);

              x4 = x1 + 2 * deltaX /3;
              y4 = y1 + 2 * deltaY /3;

              drawSnow (g2d,lev-1, x1, y1, x2, y2);
              drawSnow (g2d,lev-1, x2, y2, x3, y3);
              drawSnow (g2d,lev-1, x3, y3, x4, y4);
              drawSnow (g2d,lev-1, x4, y4, x5, y5);
          }
      }
/** public paintComponent method that uses the drawSnow method to draw the Koch snowflake onto the panel. This method is set up so the Koch snowflake
 * can adjust its size and recall itself as we increase and decrease the size of the window suing the getWidth and getHeight methods
 */
      @Override
      public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        double width = getWidth(); // get width of the JFrame
        double height = getHeight(); // get height of the JFrame
    
        double x1 = (0.2 * width);
        double y1 = (0.8 * height);
        double x5 = (0.8 * width);
        double y5 = (0.8 * height);
    
        drawSnow(g2d,level, x1, y1, x5, y5);
    
        x1 = (0.8 * width);
        y1 = (0.8 * height);
        x5 = (0.5 * width);
        y5 = (0.2 * height);
    
        drawSnow(g2d,level, x1, y1, x5, y5);
    
        x1 = (0.5 * width);
        y1 = (0.2 * height);
        x5 = (0.2 * width);
        y5 = (0.8 * height);
    
        drawSnow(g2d,level, x1, y1, x5, y5);
      }
/** main method of the program to creat an instance of the snowFlake class and create the JFrame to pack verything on. */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Koch Snowflake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Snowflake kochSnowflake = new Snowflake(4);
        frame.add(kochSnowflake, BorderLayout.CENTER);
      
        // JButton to increase the depth of the snowflake and uses an action listener to do that then repaint the Koch snowflake
        JButton increaseButton = new JButton("Increase Level");
        increaseButton.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kochSnowflake.level++;
                kochSnowflake.repaint();
            }
        });
        frame.add(increaseButton, BorderLayout.NORTH);

        // JButton to decrease the depth of the Koch snowflake and uses an action listener to do that then repaint the snowflake
        JButton decreaseButton = new JButton("Decrease Level");
        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kochSnowflake.level > 0) {
                    kochSnowflake.level--;
                    kochSnowflake.repaint();
                }
            }
        });
        frame.add(decreaseButton, BorderLayout.SOUTH);

        // JButton colour to set a new colour of the snowflake based on the users choice and redraws the snowflake
        JButton colourButton = new JButton("Colour");
        colourButton.setPreferredSize(new Dimension(80,30));
        colourButton.addActionListener(new ActionListener() {
         @Override
    public void actionPerformed(ActionEvent e) {
        Color newColor = JColorChooser.showDialog(null, "Choose Color", Color.WHITE);
        kochSnowflake.setForeground(newColor);
    }
});
frame.add(colourButton, BorderLayout.WEST);

// JButton to set the background of the JFrame based on the user chocie
    JButton fillButton = new JButton("fill");
    fillButton.setPreferredSize(new Dimension(80,30));
    fillButton.addActionListener(new ActionListener() {
    @Override
 public void actionPerformed(ActionEvent e) {
        Color newColor = JColorChooser.showDialog(null, "Choose Color", Color.WHITE);
        kochSnowflake.setBackground(newColor);
        kochSnowflake.repaint();
    }
});
frame.add(fillButton, BorderLayout.EAST);
        
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
