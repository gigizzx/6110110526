abstract class Pokemon implements Interface{
        public String Name;
        public int BASEHP;
        public int CURHP;
        public int BASEATK;
        public int BASEDEF;
        public int Level = 1;
        public int Exp = 0;
        public int levelUpExp=10;
        public int levelStack = 0;
        public String species;
    
        public void changeName(String inputName)
        {
            Name = inputName;
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
        public void statusUp()
        {
            BASEHP +=2;
            BASEATK +=2;
            BASEDEF +=2;
        }
        public void DMGCal(int recieveDMG)
        {   int damage = recieveDMG - BASEDEF ;
            if(damage <= 0) {
                CURHP -= 1;
            }
            else {
                CURHP -= damage;
            }
            if(CURHP <=0){
                CURHP = 0;
            }
        }
        public void RECOVERYCal(int amount){
            CURHP += amount;
            if(CURHP > BASEHP ){
                CURHP = BASEHP;
             }   
        }
        public void wildlvl(int amount){ //when lvl up
            Level += amount;
            for(int i = 0 ; i <= amount ; i++){
                levelUpExp *= 1.2;
                statusUp();
            }
            CURHP = getmaxhp();
        }
        public int getmaxhp(){
            return BASEHP;
        }
        public int getcurhp(){
            return CURHP;
        }
        public int getatk(){
            return BASEATK;
        }
        public int getdef(){
            return BASEDEF;
        }
        public String getname(){
            return Name;
        }
        public String getspecies(){
            return species;
        }
        public int getlvl(){
            return Level;
        }
        public int getexp(){
            return Exp;
        }
        public int getexptolvl(){
            return levelUpExp-Exp ;
        }
        public void ITEMHP(int amount){ 
            BASEHP += amount;
        }
        public void ITEMATK(int amount){ 
            BASEATK += amount;
        }
        public void ITEMDEF(int amount){ 
            BASEDEF += amount;
        }
    }

    
    