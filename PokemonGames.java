import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class PokemonGames {
    JFrame window;
    Container c;
    JButton startButton;
    JLabel startTitleLabel;

    Font titleFont = new Font("MS Gothic",Font.PLAIN,155);
    Font startButtonFont = new Font("MS UI Gothic",Font.PLAIN,50);
    Font normalFont = new Font("MS UI Gothic",Font.PLAIN,40);

    public static void main(String args[]){
        new PokemonGames();
    }
    public PokemonGames(){

        window = new JFrame("Pokemon Java");
        c = window.getContentPane();
        window.setSize(1000,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.white);
        window.setLayout(null);
        
        startTitleLabel = new JLabel("Pokemon");
        startTitleLabel.setBounds(220,150,700,150);
        startTitleLabel.setForeground(Color.black);
        startTitleLabel.setFont(titleFont);
        
        startButton = new JButton("Start");
        startButton.setBounds(400,500,200,100);
        startButton.setFont(startButtonFont);
        startButton.setBackground(Color.white);
        startButton.setForeground(Color.black);
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startButton.setVisible(false);
                startTitleLabel.setVisible(false);
                new Intro(c);
            }
        });
        
        c.add(startTitleLabel);
        c.add(startButton);
        window.setVisible(true);
        
        //after Starting is finished
    
    }
    
}