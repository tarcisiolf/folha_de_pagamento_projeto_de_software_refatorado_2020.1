package payment;

public class CheckinHands extends PaymentMethod{

    private double value;

    public CheckinHands(String type, int employeeID, String name, double value) {
        super(type, employeeID, name);
        this.value = value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
