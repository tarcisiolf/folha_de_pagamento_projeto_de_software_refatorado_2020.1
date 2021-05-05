package employees;

public class Salaried extends Employee{

    private double baseSalary;

    public Salaried(String name, String address, boolean filiated,
    double baseSalary) {
        super(name, address, filiated);
        this.baseSalary = baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
