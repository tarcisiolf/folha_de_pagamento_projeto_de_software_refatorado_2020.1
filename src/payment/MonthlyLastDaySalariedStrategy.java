package payment;

import employees.Employee;
import employees.Salaried;

public class MonthlyLastDaySalariedStrategy implements PaymentStrategy{
    @Override
    public void makePayment(Employee employee, String paymentAgenda, String date){
        System.out.println("Pagamento Salariado | Agenda: Mensalmente\n");
        //System.out.println(employee.printSalariedInfo(employee));

        double salary = employee.getSalary().calcSalary(employee, "Salaried", ((Salaried)employee).getBaseSalary(), 0.0f, 0.0f, null, 0.0f, employee.getFiliated());

        System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
        System.out.println("O valor do pagamento é de R$" + salary);

        employee.getSalary().setPaymentDate(date);
    }
}
