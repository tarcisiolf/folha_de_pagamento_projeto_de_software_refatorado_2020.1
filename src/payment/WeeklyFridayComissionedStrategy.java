package payment;

import employees.Employee;
import employees.Comissioned;

public class WeeklyFridayComissionedStrategy implements PaymentStrategy{
    @Override
    public void makePayment(Employee employee, String paymentAgenda, String date){
        System.out.println("Pagamento Vendedor | Agenda: Semanalmente\n");
        //System.out.println(employee.printComissionedInfo(employee));

        double baseSalary = ((Comissioned)employee).getBaseSalary();
        double comission = ((Comissioned)employee).getComission();
        double sales = ((Comissioned)employee).getSales().getValue();

        double salary = employee.getSalary().calcSalary(employee ,"Comissioned", baseSalary, comission, sales, null, 0.0f, employee.getFiliated());

        System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
        System.out.println("O valor do pagamento é de R$" + salary);

        ((Comissioned)employee).getSales().setValue(0.0f);
        employee.getSalary().setPaymentDate(date);
    }
}
