package app;

import java.util.ArrayList;
import java.util.Scanner;

import employees.Comissioned;
import employees.Employee;
import employees.Sales;

public class SalesResult {

    public static void addSale(ArrayList <Employee> employeesList) {
        Scanner input = new Scanner(System.in);
        String name = new String();
        double saleValue = 0.0f;
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado que realizou a venda:");
        name = input.nextLine();

        System.out.println("Digite o valor da venda no formato 0,0:");
        saleValue = input.nextDouble();

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
