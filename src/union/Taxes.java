package union;

import java.util.ArrayList;

import app.EmployessFunction;
import app.SystemInput;
import employees.Employee;
import union.Taxes;

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

    public static void setServiceTaxe(ArrayList <Employee> employeesList) {
        
        String name = new String();
        double serviceTaxeValue = 0.0f;
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado a ser cobrado pela taxa de serviço adicional:");
        name = SystemInput.readString();

        System.out.println("Digite o valor da taxa de serviço adicional:");
        serviceTaxeValue = SystemInput.readDouble();

        indexOfEmployee = EmployessFunction.getIndexList(employeesList, name);

        Employee employee = employeesList.get(indexOfEmployee);

        Syndicate newSyndicate = employee.getSyndicate();

        Taxes newTaxe = newSyndicate.getTaxes();

        newTaxe.setAdditionalTaxe(serviceTaxeValue);

        newSyndicate.setTaxes(newTaxe);
        employee.setSyndicate(newSyndicate);

        employeesList.set(indexOfEmployee, employee);
    }
}
