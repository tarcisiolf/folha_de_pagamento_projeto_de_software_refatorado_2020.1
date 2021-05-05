package payment;

public class PaymentMethod {
    private String type;
    private int employeeID;
    private String name;

    public PaymentMethod(String type, int employeeID, String name) {
        this.type = type;
        this.employeeID = employeeID;
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
