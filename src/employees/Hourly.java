package employees;
import workedhours.Timecard;

public class Hourly extends Employee{
    
    private Timecard timecard;
    private double normalTaxe;

    public Hourly(String name, String address, boolean filiated, double normalTaxe) {
        super(name, address, filiated);
        this.normalTaxe = normalTaxe;
    }

    public void setTimecard(Timecard timecard) {
        this.timecard = timecard;
    }

    public Timecard getTimecard() {
        return timecard;
    }

    public void setNormalTaxe(double normalTaxe) {
        this.normalTaxe = normalTaxe;
    }

    public double getNormalTaxe() {
        return normalTaxe;
    }
}
