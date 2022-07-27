package model;

public class Outsourced extends Part{
    /**
     *  declares companyName
     */
    private String companyName;

    /**
     *  Since Outsourced inherits form Part, all information will be
     *  placed under the inheritance and only thing is that handled is the
     *  companyName
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     *  We set the companyName with the given one
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }

    /**
     *  returning the current companyName
     */
    public String getCompanyName(){
        return this.companyName;
    }

}
