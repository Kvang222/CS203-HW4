public class RegularCustomer extends Customer{
    //Constructor
    public RegularCustomer(String name, String address, String phoneNum, String type){
        super(name, address, phoneNum, type);
    }

    //Abstract method
    public String payCoffee(){
        return "Regular customer can pay for the coffee using credit card or cash.";
    }
}
