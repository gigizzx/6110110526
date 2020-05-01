
public interface Interface {
    //Action
    public void changeName(String inputName); //Change Name Method
    public void recieveExp(int rexp); //recive exp when pokemon win the battle
    public void levelUp(); //when exp over level up exp then change pokemon level
    public void statusUp(); //when level up increase pokemon status
    public void DMGCal(int recieveDMG); //pokemon battle then recieve damage this method will calcuclate hp after battle
    public void RECOVERYCal(int amount); //when you use item or visit pokemon center this method will recovery you current hp to your max hp
    
    //Get Value to show in Status bar
    public int getmaxhp();
    public int getcurhp();
    public int getatk();
    public int getdef();
    public String getname();
    public int getlvl();
    public int getexp();
    public int getexptolvl();
    
    //Use Item
    public void ITEMHP(int amount);
    public void ITEMATK(int amount);
    public void ITEMDEF(int amount);

}