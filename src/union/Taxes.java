package union;

public class Taxes {
    private double monthlyTaxe;
    private double additionalTaxe;

    public Taxes(double monthlyTaxe, double additionalTaxe) {
        this.monthlyTaxe = monthlyTaxe;
        this.additionalTaxe = additionalTaxe;
    }

    public void setMonthlyTaxe(double monthlyTaxe) {
        this.monthlyTaxe = monthlyTaxe;
    }

    public double getMonthlyTaxe() {
        return monthlyTaxe;
    }

    public void setAdditionalTaxe(double additionalTaxe) {
        this.additionalTaxe = additionalTaxe;
    }

    public double getAdditionalTaxe() {
        return additionalTaxe;
    }
}
