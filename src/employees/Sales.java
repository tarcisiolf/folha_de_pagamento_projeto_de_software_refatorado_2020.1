package employees;

public class Sales {
    
    private double value = 0.0f;

    public Sales(double value) {
        this.value = value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void addSale(double saleValue){
        this.value = value + saleValue;
    }
}
