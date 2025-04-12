public class PremiumCustomer extends Customer{
    //Constructor
    public PremiumCustomer(String name, String address, String phoneNum, String type){
        super(name, address, phoneNum, type);
    }
    
    //Abstract method
    public String payCoffee(){
        return "Premium customer can pay for the coffee using bitcoin, credit card, or cash. A 10% discount will be applied to the price.";
    }
}
