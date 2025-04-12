public abstract class Customer {
    //Vars
    private String name;
    private String address;
    private String phoneNum;
    private String type;

    //Constructor
    public Customer(String name, String address, String phoneNum, String type){
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.type = type;
    }

    //Setter
    public void setName(String name){
        this.name = name;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }
    public void setType(String type){
        this.type = type;
    }
    //Getters
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public String getType(){
        return type;
    }

    //Abstract method
    public abstract void payCoffee();
}
