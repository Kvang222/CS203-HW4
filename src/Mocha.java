public class Mocha extends Coffee{
    private String chocolateType;

    public Mocha(String name, double price, String description, int calories,String extraIngr,String chocolateType){
        super(name, price, description, calories,extraIngr);
        this.chocolateType = chocolateType;
    }

    public void setChocolateType(String chocolateType){
        this.chocolateType = chocolateType;
    }
    public String getChocolateType(){
        return chocolateType;
    }

    @Override
    public void prepare(){
        
    }
}   
