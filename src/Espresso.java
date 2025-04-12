public class Espresso extends Coffee{
    private double numShots;
    private String milkType;

    public Espresso(String name, double price, String description, int calories, String extraIngr, double numShots, String milkType){
        super(name, price, description, calories,extraIngr);
        this.numShots = numShots;
        this.milkType = milkType;
    }

    public void setNumShots(int numShots){
        this.numShots = numShots;
    }
    public void setMilkType(String milkType){
        this.milkType = milkType;
    }
    public double getNumShots(){
        return numShots;
    }
    public String getMilkType(){
        return milkType;
    }

    @Override
    public void prepare(){
        
    }
}
