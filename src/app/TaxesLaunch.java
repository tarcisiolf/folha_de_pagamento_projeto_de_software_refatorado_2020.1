package app;

import java.util.ArrayList;
import java.util.Scanner;

import employees.Employee;
import union.Syndicate;
import union.Taxes;

public class TaxesLaunch {

    public static void setServiceTaxe(ArrayList <Employee> employeesList) {
        Scanner input = new Scanner(System.in);
        String name = new String();
        double serviceTaxeValue = 0.0f;
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado a ser cobrado pela taxa de serviço adicional:");
        name = input.nextLine();

        System.out.println("Digite o valor da taxa de serviço adicional:");
        serviceTaxeValue = input.nextDouble();

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
