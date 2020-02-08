    public class Pokemon
    {
        private String Name;
        private int HP;
        private int ATK;
        private int DEF;
        private int Level;
        private int Exp;
        private int levelUpExp;
        private int levelStack = 0;
        private int BaseHP = 10;
        private int BaseATK = 5;
        private int BaseDEF = 3;
    
        public Pokemon(String name)
        {
            Name = name;
            Level = 1;
            Exp = 0;
            levelUpExp = 5;
            HP = (int)(2*BaseHP)*(Level/100)+Level+10;
            ATK = (int)(2*BaseATK)*(Level/5);
            DEF = (int)(2*BaseDEF)*(Level/5);
        }
    
        public String ShowStats()
        {
            return "----------Pokemon Stats----------\n" +
                    "Name : " + Name +
                    "\nLevel : " + Level + "\tExp : " + Exp + "/" + levelUpExp +
                    "\nHP : " + HP +
                    "\nATK : " + ATK +
                    "\nDEF : " + DEF +
                    "\n---------------------------------";
        }
    
    
        public void recieveExp(int rexp)
        {
            Exp += rexp;
            if (Exp >= levelUpExp) {
                levelStack = Exp / levelUpExp;
                Exp = Exp % levelUpExp;
                while(levelStack != 0){
                    levelUp();
                    levelStack--;
                }
                 
            }
        }
    
        public void levelUp()
        {
            Level++;
            levelUpExp += 5;
            statusUp();
            System.out.println("Level up!");
        }
    
    }

    
    