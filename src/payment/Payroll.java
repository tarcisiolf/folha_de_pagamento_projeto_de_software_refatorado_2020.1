package payment;

import java.util.ArrayList;

import app.EmployessFunction;
import app.SystemInput;
import employees.Employee;
import date.Day;


public class Payroll {
    
    public static void PaymentList(ArrayList<Employee> employeesList) {
        String date = new String();
        String dayWeekString = new String();
        int weekMonth = -1;
        int lastDayMonth = -1;
        int dayDate = -1;

        System.out.println("Digite a data de hoje no formato dd/MM/yyyy");
        date = SystemInput.readString();

        String paymentAgenda = new String();     
        String[] dateSplit = date.split("/");
        dayDate = Integer.parseInt(dateSplit[0]);

        for (Employee employee : employeesList){
            weekMonth = -1;
            lastDayMonth = -1;

            paymentAgenda = employee.getSalary().getPaymentAgenda();
            dayWeekString = Day.getWeekDayString(date);
            weekMonth = Day.WeekMonth(date);
            lastDayMonth = Day.LastDayMonth(date);

            Context context = null;

            System.out.println("\n");
            if (paymentAgenda.intern() == "Weekly".intern() && dayWeekString.intern() == "SEX".intern() && employee.getSalary().getEmployeeType().intern() == "Hourly".intern()){
                context = new Context(new WeeklyFridayHourlyStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

            else if(paymentAgenda.intern() == "Weekly".intern() && dayWeekString.intern() == "SEX".intern() && employee.getSalary().getEmployeeType().intern() == "Salaried".intern()){
                context = new Context(new WeeklyFridaySalariedStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

            else if(paymentAgenda.intern() == "Weekly".intern() && dayWeekString.intern() == "SEX".intern() && employee.getSalary().getEmployeeType().intern() == "Comissioned".intern()){
                context = new Context(new WeeklyFridayComissionedStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

            else if(paymentAgenda.intern() == "Monthly".intern() && dayDate == lastDayMonth && employee.getSalary().getEmployeeType().intern() == "Salaried".intern()){
                context = new Context(new MonthlyLastDaySalariedStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

            else if(paymentAgenda.intern() == "Monthly".intern() && dayDate == lastDayMonth && employee.getSalary().getEmployeeType().intern() == "Hourly".intern()){
                context = new Context(new MonthlyLastDayHourlyStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

            else if(paymentAgenda.intern() == "Monthly".intern() && dayDate == lastDayMonth && employee.getSalary().getEmployeeType().intern() == "Comissioned".intern()){
                context = new Context(new MonthlyLastDayComissionedStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

            else if(paymentAgenda.intern() == "Two-Weekly".intern() && (weekMonth == 2 || weekMonth == 4) && (dayWeekString.intern() == "SEX".intern()) && employee.getSalary().getEmployeeType().intern() == "Comissioned".intern()){
                context = new Context(new TwoWeeklyOrFridayComissionedStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

            else if(paymentAgenda.intern() == "Two-Weekly".intern() && (weekMonth == 2 || weekMonth == 4) && (dayWeekString.intern() == "SEX".intern()) && employee.getSalary().getEmployeeType().intern() == "Salaried".intern()){
                context = new Context(new TwoWeeklyOrFridaySalariedStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

            else if(paymentAgenda.intern() == "Two-Weekly".intern() && (weekMonth == 2 || weekMonth == 4) && (dayWeekString.intern() == "SEX".intern()) && employee.getSalary().getEmployeeType().intern() == "Hourly".intern()){
                context = new Context(new TwoWeeklyOrFridayHourlyStrategy());
                context.executePaymentStrategy(employee, paymentAgenda, date);
            }

        }
    }

    public static void changeEmployeePaymentAgenda(ArrayList<Employee> employeesList) {
        String name = new String();
        int paymentAgenda = -1;
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado onde será editada a agenda de pagamento");
        name = SystemInput.readString();

        System.out.println("Digite o nº da opção da nova agenda de pagamento do empregado dentre as seguintes opções:\n '1 - Semanalmente', '2 - Mensalmente', '3 - Bi-semanalmente'");
        paymentAgenda = SystemInput.readInt();

        indexOfEmployee = EmployessFunction.getIndexList(employeesList, name);
        Employee employee = employeesList.get(indexOfEmployee);

        switch (paymentAgenda) {
            case 1:
                employee.getSalary().setPaymentAgenda("Weekly");
                break;
            case 2:
                employee.getSalary().setPaymentAgenda("Monthly");
                break;
            case 3:
                employee.getSalary().setPaymentAgenda("Two-Weekly");
                break;
            default:
                break;
        }

        employeesList.set(indexOfEmployee, employee);
    }
}
