package employees;

import java.util.ArrayList;

import app.EmployessFunction;
import app.SystemInput;

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

    public static void addSale(ArrayList <Employee> employeesList) {
        
        String name = new String();
        double saleValue = 0.0f;
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado que realizou a venda:");
        name = SystemInput.readString();

        System.out.println("Digite o valor da venda no formato 0,0:");
        saleValue = SystemInput.readDouble();
        
        indexOfEmployee = EmployessFunction.getIndexList(employeesList, name);

        Employee employee = employeesList.get(indexOfEmployee);

        Comissioned comissionedEmployee = ((Comissioned) employee);
        if (comissionedEmployee.getSales() == null) {
            Sales newSale = new Sales(saleValue);
            comissionedEmployee.setSales(newSale);
        }

        else{
            Sales newSale = comissionedEmployee.getSales();
            newSale.addSale(saleValue);
            comissionedEmployee.setSales(newSale);
        }
        
        employeesList.set(indexOfEmployee, comissionedEmployee);

    }
}
