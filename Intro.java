import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Intro extends Screen {
    private JButton nextButton, chooseBotton;
    private JTextField inputName;
    private String PlayerName,PokemonName,selectedStarter;
    private ActionListener NameType,chooseStarter,choose1,text1,PokemonType,end;
    private JComboBox<String> starterChoose;
    public Intro(Container c){
        super(c);
        String introText = " Welcome To World Of Pokemon, I'm Professor Oak Who Research About Pokemon So What Is Your Name ?";
    nextButton = new JButton("Next ->");
    nextButton.setBounds(400, 500, 170, 60);
    nextButton.setFont(normalFont);
    nextButton.setBackground(Color.white);
    nextButton.setForeground(Color.black);
    mainTextArea.setText(introText);
    //Create Next Botton 
        NameType = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("NameType");
                nextButton.setVisible(false);
                inputName = new JTextField(250);
                inputName.setBounds(400, 500, 250, 60);
                inputName.setFont(normalFont);
                text1 = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        PlayerName = e.getActionCommand();
                            System.out.println("text1");
                            nextButton.setVisible(true);
                            inputName.setVisible(false);
                            intro2(c);
                        }
                    };
                inputName.addActionListener(text1);
                c.add(inputName);
            }
        };
        nextButton.addActionListener(NameType);
        c.add(nextButton);
    }
    public void intro2(Container c){
        String introText2 = PlayerName+" That's a Wonderful Name !! , Now I Have 3 Pokemon I'll Give You One Of These";
        String[] starterList = {"Treecko","Torchic","Mudkip"};
        mainTextArea.setText(introText2);
        nextButton.removeActionListener(NameType);
        chooseStarter = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                    System.out.println("chooseStarter");
                    nextButton.setVisible(false);
                    starterChoose = new JComboBox<String>(starterList);
                    starterChoose.setBounds(200,500,400,50);
                    starterChoose.setFont(normalFont);
                    starterChoose.setBackground(Color.white);
                    starterChoose.setForeground(Color.black);
                    chooseBotton = new JButton("Choose !");
                    chooseBotton.setBounds(650,500,250,50);
                    chooseBotton.setFont(normalFont);
                    chooseBotton.setBackground(Color.white);
                    chooseBotton.setForeground(Color.black);
                    c.add(starterChoose);
                    c.add(chooseBotton);
                    choose1 = new ActionListener(){
                        public void actionPerformed(ActionEvent e){
                            System.out.println("choose1");
                            selectedStarter = (String)starterChoose.getSelectedItem();
                            chooseBotton.setVisible(false);
                            starterChoose.setVisible(false);
                            intro3(c);
                        }
                    };
                    chooseBotton.addActionListener(choose1);
                    chooseBotton.revalidate();
                    chooseBotton.repaint();
                    starterChoose.revalidate();
                    starterChoose.repaint();
                }
        };
        nextButton.addActionListener(chooseStarter);
    }

    public void intro3(Container c){
        mainTextArea.setText("Type Your Partner Name");
        inputName.setText("");
        inputName.setVisible(true);
        PokemonType = new ActionListener(){
            public void actionPerformed(ActionEvent e){
            System.out.println("PokemonType");    
            PokemonName = e.getActionCommand();
            nextButton.setVisible(true);
            inputName.setVisible(false);
            mainTextArea.setText(PlayerName+ " and "+ PokemonName + " Right ?");     
            }
            
        };
        inputNameremove();
        inputName.addActionListener(PokemonType);
        end = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nextButton.setVisible(false);
                c.removeAll();
                new Main(c,selectedStarter,PlayerName,PokemonName);
            }
        };
        nextButtonremove();
        nextButton.addActionListener(end);
        
    }
    private void inputNameremove(){
        for( ActionListener listener : inputName.getActionListeners() ) {
            inputName.removeActionListener(listener);
        }
    }
    private void nextButtonremove(){
        for( ActionListener listener : nextButton.getActionListeners() ) {
            nextButton.removeActionListener(listener);
        }
    }
}