import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class Main extends Screen{
    private int i=0,numStarter,turn;
    private Item items = new Item();
    private Pokemon starter,wildPokemon;
    private ArrayList<Pokemon> bags = new ArrayList<Pokemon>();
    private Random rand = new Random();
    private JTextField inputNameTextField;
    private JLabel playerNameLabel,pokemonPartnerLabel;
    private JPanel playerStatusPanel;
    private JButton returnButton,berryButton,selectButton,statButton,battleButton,attackButton,potionButton,berryComboBoxBotton,pokemonCenterButton,catchButton,battleNextButton ;
    private JButton closeButton;
    private JComboBox<String> berryComboBox,selectComboBox;
    private String[] berryList  = {"Oran Berry","Liechi Berry","Ganlon Berry","Potion"};
    private ActionListener NextFinishedAction,NextMenuAction,NextWildAction,NextPlayerAction,battleAttackAction,battleComboBoxAction,battleAction,closeAction,inputNameAction,CenterAction;
    private ActionListener CenterComboAction,itemUseAcion,backItemAction,itemSelectAction,itemAction,returnStatAction,statComboAction,statAction,potionAction,battlePokeballAction;
    public Main(Container c,String selectedStarter,String PlayerName,String PokemonName){
        super(c);
        switch(selectedStarter){
            case "Treecko" :
                starter = new Treecko();
                break;
            case "Torchic" :
                starter = new Torchic();
                break;
            case "Mudkip" :
                starter = new Mudkip();
                break;
        }
        starter.changeName(PokemonName);
        starter.wildlvl(4);
        //Select Combobox
        selectComboBox = new JComboBox<String>();
        ComboboxUpdate();
        bags.add(starter);
        selectComboBox.setVisible(false);
        selectComboBox.setBounds(50,500,350,50);
        selectComboBox.setBackground(Color.white);
        selectComboBox.setForeground(Color.black);
        selectComboBox.setFont(normalFont);
        c.add(selectComboBox);
        //Select Button
        selectButton = new JButton("Select");
        selectButton.setVisible(false);
        selectButton.setBounds(450,500,200,50);
        selectButton.setBackground(Color.white);
        selectButton.setForeground(Color.black);
        selectButton.setFont(normalFont);
        c.add(selectButton);

        playerStatusPanel = new JPanel();
        playerStatusPanel.setBounds(40, 40, 900, 50);
        playerStatusPanel.setBackground(Color.white);
        playerStatusPanel.setLayout(new GridLayout(1,4));
        c.add(playerStatusPanel);
       //Create status bar on top 
        playerNameLabel = new JLabel(PlayerName);
        playerNameLabel.setForeground(Color.black);
        playerNameLabel.setFont(normalFont);
        playerStatusPanel.add(playerNameLabel);

        pokemonPartnerLabel = new JLabel(" Partner " + PokemonName  +"("+ selectedStarter +")");
        pokemonPartnerLabel.setForeground(Color.black);
        pokemonPartnerLabel.setFont(normalFont);
        playerStatusPanel.add(pokemonPartnerLabel);

        //Create return button 
        returnButton = new JButton("Back");
        returnButton.setBounds(50, 670, 150, 50);
        returnButton.setBackground(Color.white);
        returnButton.setForeground(Color.black);
        returnButton.setVisible(false);
        returnButton.setFont(normalFont);
        c.add(returnButton);

        //Create Berry button
        berryButton = new JButton("Use Item");
        berryButton.setBounds(300, 600, 400, 50);
        berryButton.setBackground(Color.white);
        berryButton.setForeground(Color.black);
        berryButton.setFont(normalFont);
        c.add(berryButton);
        
        //Create item combo box
        berryComboBox = new JComboBox<String>(berryList);
        berryComboBox.setVisible(false);
        berryComboBox.setBounds(50,500,350,50);
        berryComboBox.setBackground(Color.white);
        berryComboBox.setForeground(Color.black);
        berryComboBox.setFont(normalFont);
        c.add(berryComboBox);

        //Create item combo box botton
        berryComboBoxBotton = new JButton("Select");
        berryComboBoxBotton.setVisible(false);
        berryComboBoxBotton.setBounds(450, 500, 200, 50);
        berryComboBoxBotton.setBackground(Color.white);
        berryComboBoxBotton.setForeground(Color.black);
        berryComboBoxBotton.setFont(normalFont);
        c.add(berryComboBoxBotton);

        //back to main menu
        backItemAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TurnOnMainMenu();
                returnButton.setVisible(false);
                berryComboBox.setVisible(false);
                berryComboBoxBotton.setVisible(false);
             }
        };
        
        //Item Action
        itemUseAcion = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                switch ((String)berryComboBox.getSelectedItem()){
                    case "Oran Berry" :
                        if(items.getob() <= 0){
                            mainTextArea.setText("Don't Have Any Oran Berry");
                        }
                        else{
                            items.useob(starter);
                            mainTextArea.setText("Your Partner Have Boosted HP");
                        }
                        break;
                    case "Liechi Berry" :
                        if(items.getlb() <= 0){
                            mainTextArea.setText("Don't Have Any Liechi Berry");
                        }
                        else{
                            items.uselb(starter);
                            mainTextArea.setText("Your Partner Have Boosted ATK ");
                        }
                        break;
                        case "Ganlon Berry" :
                        if(items.getgb() <= 0){
                            mainTextArea.setText("Don't Have Any Ganlon Berry");
                        }
                        else{
                            items.uselb(starter);
                            mainTextArea.setText("Your Partner Have Boosted DEF ");
                        }
                        break;   
                    default :
                        mainTextArea.setText("Error. Please Back To Main Menu");                    
                }
                berryComboBox.setVisible(false);
                berryComboBoxBotton.setVisible(false);
            }

			


        };
        berryComboBoxBotton.addActionListener(itemUseAcion);
        //combo box item selection
        itemSelectAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(i=0;i<bags.size();i++){
                    if(bags.get(i).getname().equals((String)selectComboBox.getSelectedItem())){
                        mainTextArea.setText(
                        "- Orlan Berry ["+items.getob()+"] " + 
                        "\n- Liechi Berry["+items.getlb()+"] " +
                        "\n- Ganlon Berry["+items.getgb()+"] " +
                        "\n- Potion ["+items.getpotion()+"] " +
                        "\n- Pokeball ["+items.getpokeball()+"] " );
                        TurnOffSelectButton();
                        berryComboBox.setVisible(true);
                        berryComboBoxBotton.setVisible(true);
                        starter = bags.get(i);
                        returnButton.setVisible(false);
                    }
                }
                returnButton.setVisible(true);
                returnButton.addActionListener(backItemAction);
            }
        };
        itemAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TurnOffMainMenu();
                mainTextArea.setText("Which Pokemon do you want to use item to?");
                TurnOnSelectButton();
                RemoveComboActionListener();
                RemoveReturnActionListener();
                selectButton.addActionListener(itemSelectAction);
                returnButton.setVisible(false);
            }
        };
        
        berryButton.addActionListener(itemAction);
        //creating statbutton
        statButton = new JButton("Pokemon Stats");
        statButton.setBounds(300, 500, 400, 50);
        statButton.setFont(normalFont);
        statButton.setBackground(Color.white);
        statButton.setForeground(Color.black);
        //create returnbutton action (return to main menu ) 
        returnStatAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TurnOnMainMenu();
                returnButton.setVisible(false);
            }
        };
        //combobox stat action
        statComboAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TurnOffSelectButton();
                for(i=0;i<bags.size();i++){
                    if(bags.get(i).getname().equals((String)selectComboBox.getSelectedItem())){
                        mainTextArea.setText("Name     : "+bags.get(i).getname() +  
                        "\nLVL        : "+bags.get(i).getlvl()+
                        "                     EXP      : " +bags.get(i).getexp() + "/" + bags.get(i).getexptolvl()+
                        "\nHP         : "+bags.get(i).getcurhp()+"/"+bags.get(i).getmaxhp()+
                        "\nATK       : "+bags.get(i).getatk() +
                        "                   DEF      : "+bags.get(i).getdef() 
                        );
                        RemoveReturnActionListener();
                        returnButton.setVisible(true);
                        returnButton.addActionListener(returnStatAction);
                        break;
                    }
                }
            }
        };
        //create stat button action
        statAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TurnOffMainMenu();
                mainTextArea.setText("Which Pokemon do you want to see status?");
                TurnOnSelectButton();
                RemoveComboActionListener();
                RemoveReturnActionListener();
                selectButton.addActionListener(statComboAction);
                returnButton.setVisible(false);
            }
        };
        statButton.addActionListener(statAction);

        //create pokemon center
        pokemonCenterButton = new JButton("Pokemon Center");
        pokemonCenterButton.setBounds(300,550,400,50);
        pokemonCenterButton.setFont(normalFont);
        pokemonCenterButton.setBackground(Color.white);
        pokemonCenterButton.setForeground(Color.black);
        //create combo box pokemon center action
        CenterComboAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TurnOffSelectButton();
                for(i=0;i<bags.size();i++){
                        bags.get(i).RECOVERYCal(bags.get(i).getmaxhp()); 
                        mainTextArea.setText("All Pokemon Have Restored");
                     
            }
            returnButton.setVisible(true);
            returnButton.addActionListener(returnStatAction);
        }
    };
    //Pokemon Center Button
        CenterAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                TurnOffMainMenu();
                TurnOnSelectButton();
                RemoveComboActionListener();
                mainTextArea.setText("Which Pokemon do you want to rest ?");
                selectButton.addActionListener(CenterComboAction);
                RemoveReturnActionListener();
                returnButton.setVisible(false);
            }
        };
        pokemonCenterButton.addActionListener(CenterComboAction);

        //Create Battle Button
        battleButton = new JButton("Go Battle!!!");
        battleButton.setBounds(300,450,400,50);
        battleButton.setFont(normalFont);
        battleButton.setBackground(Color.white);
        battleButton.setForeground(Color.black);
        //Create battle attack button
        attackButton = new JButton("Attack");
        attackButton.setBounds(300,450,400,50);
        attackButton.setFont(normalFont);
        attackButton.setBackground(Color.white);
        attackButton.setForeground(Color.black);
        attackButton.setVisible(false);
        c.add(attackButton);

        //Create battle potion button
        potionButton = new JButton("Use Potion");
        potionButton.setBounds(300,500,400,50);
        potionButton.setFont(normalFont);
        potionButton.setBackground(Color.white);
        potionButton.setForeground(Color.black);
        potionButton.setVisible(false);
        potionAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(items.getpotion()<=0){
                    mainTextArea.setText("You don't have a potion!");
                }   
                else{
                    items.usepotion(starter);
                    RemoveNextActionListener();
                    mainTextArea.setText("You've use a Potion!"+"\n"+starter.getname()+" HP is restored to "+starter.getcurhp()+"/"+starter.getmaxhp()+"!");            
                }
                battleNextButton.addActionListener(NextMenuAction);
            }
        };
        potionButton.addActionListener(potionAction);
        c.add(potionButton);

        //Create Catch Pokemon Button
        catchButton = new JButton("Catch Pokemon");
        catchButton.setBounds(300,550,400,50);
        catchButton.setFont(normalFont);
        catchButton.setBackground(Color.white);
        catchButton.setForeground(Color.black);
        catchButton.setVisible(false);
        inputNameTextField = new JTextField(250);
        inputNameTextField.setBounds(200,500,600,50);
        inputNameTextField.setText("");
        inputNameTextField.setVisible(false);
        inputNameTextField.setFont(normalFont);
        inputNameAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                wildPokemon.changeName(e.getActionCommand());
                bags.add(wildPokemon);
                inputNameTextField.setVisible(false);
                mainTextArea.setText(wildPokemon.getname()+" is your partner now!");
                battleNextButton.setVisible(true);
                RemoveNextActionListener();
                battleNextButton.addActionListener(NextFinishedAction);
            }
        };
        inputNameTextField.addActionListener(inputNameAction);        
        battlePokeballAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(items.getpokeball()<=0){
                    mainTextArea.setText(" don't have any Pokeball!");
                }
                else{
                    if(items.usepokeball(wildPokemon)){
                        mainTextArea.setText("You've caught a "+wildPokemon.getspecies());
                        inputNameTextField.setVisible(true);
                        TurnOffBattleMenu();
                    }
                    else{
                        mainTextArea.setText("Oh no! "+ wildPokemon.getspecies()+" broke free!");
                    }
                   }
            }
        };
        catchButton.addActionListener(battlePokeballAction);
        c.add(inputNameTextField);
        c.add(catchButton);
        battleNextButton = new JButton("Next ->");
        battleNextButton.setBounds(300,450,400,50);
        battleNextButton.setFont(normalFont);
        battleNextButton.setBackground(Color.white);
        battleNextButton.setForeground(Color.black);
        battleNextButton.setVisible(false);
        c.add(battleNextButton);
        //when finished battle action
        NextFinishedAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                battleNextButton.setVisible(false);
                TurnOnMainMenu();
            }
        };
        //when all pokemon take turn action
        NextMenuAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                battleNextButton.setVisible(false);
                TurnOnBattleMenu();
            }
        };
        //wild pokemon's attack
        NextWildAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                wildAttack();
            }
        };
        //player pokemon's attack
        NextPlayerAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                playerAttack();
            }
        };
        //battleattackbutton action
        battleAttackAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                playerAttack();
                wildAttack();
                battleNextButton.setVisible(true);
            }
        };
        //battleCombobox action
        battleComboBoxAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                for(i=0;i<bags.size();i++){
                    if(bags.get(i).getname().equals((String)selectComboBox.getSelectedItem())){
                        starter = bags.get(i);
                        numStarter = rand.nextInt(3);
                        switch(numStarter){
                            case 0 :
                                wildPokemon = new Treecko();
                                break;
                            case 1 :
                                wildPokemon = new Mudkip();
                                break;
                            case 2 : 
                                wildPokemon = new Torchic();
                                break;
                            default :
                                wildPokemon = new Treecko();
                                break;
                        }
                        turn = 0;
                        wildPokemon.wildlvl(starter.getlvl()-2);

                        }
                        TurnOffSelectButton();
                        TurnOnBattleMenu();
                        mainTextArea.setText("A wild " + wildPokemon.getspecies() + " has appered!\n\n"
                        + starter.getname() + " HP : "+ starter.getcurhp() + "/" + starter.getmaxhp() + "   LVL : " + starter.getlvl() +
                        "\n\nWild " + wildPokemon.getspecies() + " HP : " + wildPokemon.getcurhp() + "/" + wildPokemon.getmaxhp() + "   LVL : " + wildPokemon.getlvl()
                        );
                    }
            }
        };
        attackButton.addActionListener(battleAttackAction);
        //battlebutton action
        battleAction = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainTextArea.setText("Which Pokemon do you want to battle?");
                TurnOffMainMenu();
                TurnOnSelectButton();
                RemoveComboActionListener();
                RemoveReturnActionListener();
                selectButton.addActionListener(battleComboBoxAction);
                returnButton.setVisible(false);
            }
        };
        battleButton.addActionListener(battleAction);
        //creating close button
        closeButton = new JButton("Close Game");
        closeButton.setBounds(300,650,400,50);
        closeButton.setFont(normalFont);
        closeButton.setBackground(Color.white);
        closeButton.setForeground(Color.black);
        closeAction = new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        closeButton.addActionListener(closeAction);
        c.add(closeButton);
        c.add(berryButton);
        c.add(battleButton);
        c.add(pokemonCenterButton);
        c.add(statButton);
        closeButton.revalidate();
        closeButton.repaint();
        berryButton.revalidate();
        berryButton.repaint();
        playerStatusPanel.revalidate();
        playerStatusPanel.repaint();
        mainTextArea.revalidate();
        mainTextArea.repaint();
    }
    private void wildAttack(){
        TurnOffBattleMenu();
        battleNextButton.setVisible(true);
        starter.DMGCal(wildPokemon.getatk());
        //wild pokemon's win case
        if(starter.getcurhp() <= 0){
            mainTextArea.setText(starter.getname() + " is fainted!");
            RemoveNextActionListener();
            battleNextButton.addActionListener(NextFinishedAction);
        }
        //if not yet
        else{
            mainTextArea.setText(
                "Wild "+wildPokemon.getspecies()+" is attacking" 
                +"\n\n" + starter.getname() + " HP is " + starter.getcurhp()+"/" + starter.getmaxhp()+" now!"
            );
            RemoveNextActionListener();
            if(turn < 1){
                battleNextButton.addActionListener(NextPlayerAction);
                turn += 1;
            }
            else{
                battleNextButton.addActionListener(NextMenuAction);
                turn = 0;
            }
        }
    }
    private void playerAttack(){
        TurnOffBattleMenu();
        battleNextButton.setVisible(true);
        wildPokemon.DMGCal(starter.getatk());
        //player's win case
        if(wildPokemon.getcurhp() <= 0){
            items.getItem();
            //all pokemon get exp when they win the battle
            for(Pokemon s : bags){
                s.recieveExp(5*wildPokemon.getlvl());
            }
            if(items.getAllItem() > numStarter){
                mainTextArea.setText("Wild "+wildPokemon.getspecies() + " is fainted!" +
                "\nYou've got items. Check them in your bag");
            }
            else{
                mainTextArea.setText("Wild "+wildPokemon.getspecies() + " is fainted!" + 
                "\nNo item gained.");
            }
            RemoveNextActionListener();
            battleNextButton.addActionListener(NextFinishedAction);
        }
        //if not win yet
        else{
            mainTextArea.setText(
                starter.getname() + " is attacking" 
                +"\n\n" + wildPokemon.getspecies() + " HP is " + wildPokemon.getcurhp() + "/" + wildPokemon.getmaxhp() + " now!\n"
            );
            RemoveNextActionListener();
            if(turn < 1){
                battleNextButton.addActionListener(NextWildAction);
                turn += 1;
            }
            else{
                battleNextButton.addActionListener(NextMenuAction);
                turn = 0;
            }
        } 
    }
    private void TurnOffBattleMenu(){
        attackButton.setVisible(false);
        potionButton.setVisible(false);
        catchButton.setVisible(false);   
    }
    private void TurnOnBattleMenu(){
        mainTextArea.setText(starter.getname() + " HP : "+ starter.getcurhp() + "/" + starter.getmaxhp() + "   LVL : " + starter.getlvl() +
        "\n\nWild " + wildPokemon.getspecies() + " HP : " + wildPokemon.getcurhp() + "/" + wildPokemon.getmaxhp() + "   LVL : " + wildPokemon.getlvl()
        );
        attackButton.setVisible(true);
        potionButton.setVisible(true);
        catchButton.setVisible(true);    
        RemoveReturnActionListener();
    }
    private void ComboboxUpdate(){
        selectComboBox.removeAllItems();
        for(i=0;i<bags.size();i++){
            selectComboBox.addItem(bags.get(i).getname());
        }
    }
    private void TurnOnMainMenu(){
        closeButton.setVisible(true);
        statButton.setVisible(true);
        pokemonCenterButton.setVisible(true);
        battleButton.setVisible(true);
        berryButton.setVisible(true);
       mainTextArea.setText("What do you want to do?");    
    }
    private void TurnOffMainMenu(){
        statButton.setVisible(false);
        pokemonCenterButton.setVisible(false);
        battleButton.setVisible(false);
        berryButton.setVisible(false);   
        closeButton.setVisible(false);
    }
    private void TurnOffSelectButton(){
        selectButton.setVisible(false);
        selectComboBox.setVisible(false);
    }
    private void TurnOnSelectButton(){
        ComboboxUpdate();
        selectButton.setVisible(true);
        selectComboBox.setVisible(true);
    }
    private void RemoveNextActionListener(){
        for( ActionListener listener : battleNextButton.getActionListeners() ) {
            battleNextButton.removeActionListener(listener);
        }
    }
    private void RemoveReturnActionListener(){
        for( ActionListener listener : returnButton.getActionListeners() ) {
            returnButton.removeActionListener(listener);
        }
    }
    private void RemoveComboActionListener(){
        for( ActionListener listener : selectButton.getActionListeners() ) {
            selectButton.removeActionListener(listener);
        }
    }
}
