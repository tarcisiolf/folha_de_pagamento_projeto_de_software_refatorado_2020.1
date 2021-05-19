package payment;

import employees.Employee;
import employees.Hourly;

public class TwoWeeklyOrFridayHourlyStrategy implements PaymentStrategy{
    @Override
    public void makePayment(Employee employee, String paymentAgenda, String date){
        System.out.println("Pagamento Horista | Agenda: Bi-semanalmente\n");
        //System.out.println(employee.printHourlyInfo(employee));

        double normalTaxe = employee.getSalary().getNormalTaxe();
        
        double salary = employee.getSalary().calcSalary(employee, "Hourly", 0.0f, 0.0f, 0.0f, ((Hourly)employee).getTimecard(), normalTaxe, employee.getFiliated());

        System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
        System.out.println("O valor do pagamento é de R$" + salary);

        ((Hourly)employee).getTimecard().setNumberHours(0.0f);
        employee.getSalary().setPaymentDate(date);
    }
}
