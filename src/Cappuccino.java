public class Cappuccino extends Coffee{
    private double foamLevel;

    public Cappuccino(String name, double price, String description, int calories,String extraIngr,double foamLevel){
        super(name, price, description, calories, extraIngr);
        this.foamLevel = foamLevel;
    }

    public void setFoamLevel(int foamLevel){
        this.foamLevel = foamLevel;
    }
    public double getFoamLevel(){
        return foamLevel;
    }

    @Override
    public void prepare(){
        
    }
}
