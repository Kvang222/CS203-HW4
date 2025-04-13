public class FilteredCoffee extends Coffee{
    private String brewType;

    public FilteredCoffee(String name, Double price, String description, int calories, String extraIngr,String brewType){
        super(name, price, description, calories,extraIngr);
        this.brewType = brewType;
    }

    public void setBrewType(String brewType){
        this.brewType = brewType;
    }
    public String getBrewType(){
        return brewType;
    }

    @Override
    public void prepare(){
        
    }
}
