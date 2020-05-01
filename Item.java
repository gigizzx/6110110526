import java.util.Random;
public class Item{
    private int obpiece = 0 ; //piece of Oranberry
    private int lbpiece = 0 ; //piece of Leichi Berry
    private int gbpiece = 0 ; //piece of Gallan Berry
    private int potionpiece = 5 ; //bottle of potion
    private int pokeballamount = 5 ;

    public void getItem(){
        // item can be get multiple at once
        if(rand.nextInt(4) == 1){ 
            obpiece += 1;
        }
        else if(rand.nextInt(4) == 1){
            lbpiece += 1;
        }
        else if(rand.nextInt(4) == 1){
            gbpiece+= 1;
        }
        else if(rand.nextInt(4) == 1){
            lbpiece += 1;
        }
        else if(rand.nextInt(4) == 1){
            potionpiece += 1;
        }
        else if(rand.nextInt(4) == 1){
            pokeballamount += 1;
        }
        
    }
    private Random rand = new Random();  
    
    public void useob(Pokemon target){
        target.ITEMHP(5);
        obpiece -= 1;
    }
    public void uselb(Pokemon target){
        target.ITEMATK(2);
        lbpiece -= 1;
    }
    public void usegb(Pokemon target){
        target.ITEMDEF(2);
        lbpiece -= 1;
    }
    public void usepotion(Pokemon target){
        if(target.getmaxhp()-target.getcurhp() <= 20){
            target.RECOVERYCal(target.getmaxhp()-target.getcurhp() );
        }
        else {
            target.RECOVERYCal(20);
        }
        potionpiece -= 1;
    }
    public boolean usepokeball(Pokemon target){
       pokeballamount -=1; 
        if(rand.nextInt(3)==1){
            return true;
        }
        else{
            return false;
        }
        
    }
    public int getob() {
        return obpiece;
    }
    public int getlb() {
        return lbpiece;
    }
    public int getgb() {
        return gbpiece;
    }
    public int getpotion() {
        return potionpiece;
    }
    public int getpokeball() {
        return pokeballamount;
    }
    public int getAllItem(){
        return obpiece+lbpiece+gbpiece+potionpiece+pokeballamount;
    }
}