import javax.swing.*;
import java.awt.*;
public abstract class Screen{
    protected JTextArea mainTextArea;
    protected JPanel mainTextPanel;
    protected Font normalFont = new Font("",Font.PLAIN,35);
    public Screen(Container c){
        
        mainTextPanel = new JPanel();
        mainTextPanel.setBounds(50,150,900,270);
        mainTextPanel.setBackground(Color.white);
        
        mainTextArea = new JTextArea("");
        mainTextArea.setBounds(50,150,900,270);
        mainTextArea.setLineWrap(true);
        mainTextArea.setFont(normalFont);
        mainTextArea.setBackground(Color.white);
        mainTextArea.setForeground(Color.black);
        mainTextArea.setEditable(false); 
        mainTextPanel.add(mainTextArea);
        
        c.add(mainTextPanel);
    }
}