public class Coffee{
    //Vars
    private String name;
    private Double price;
    private String description;
    private int calories;
    private String extraIngr;
    private boolean isActive;

    //Constructor
    public Coffee(String name, Double price, String description, int calories, String extraIngr){
        this.name = name;
        this.price = price;
        this.description = description;
        this.calories = calories;
        this.extraIngr = extraIngr;
        this.isActive = true;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(Double price){
        this.price = price;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setCalories(int calories){
        this.calories = calories;
    }
    public void setExtraIngr(String extraIngr){
        this.extraIngr = extraIngr;
    }
    //Getters
    public String getName(){
        return name;
    }
    public Double getPrice(){
        return price;
    }
    public String getDescription(){
        return description;
    }
    public int getCalories(){
        return calories;
    }
    public String getExtraIngr(){
        return extraIngr;
    }

    //Status methods
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    //Prepare method
    public void prepare(){
        this.isActive = false;
    }
}