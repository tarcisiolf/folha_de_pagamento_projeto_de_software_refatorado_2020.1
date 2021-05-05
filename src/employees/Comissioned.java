package employees;

public class Comissioned extends Employee{
    
    private Sales sale;
    private double comission;
    private double baseSalary;

    public Comissioned(String name, String address, boolean filiated, double baseSalary, double comission) {
        super(name, address, filiated);
        this.comission = comission;
        this.baseSalary = baseSalary;
    }

    public void setSales(Sales sale) {
        this.sale = sale;
    }

    public Sales getSales() {
        return sale;
    }

    public void setComission(double comission) {
        this.comission = comission;
    }

    public double getComission() {
        return comission;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
