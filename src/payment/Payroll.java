package payment;

import java.util.ArrayList;

import app.EmployessFunction;
import app.SystemInput;
import employees.Employee;
import employees.Hourly;
import employees.Comissioned;
import employees.Salaried;
import date.Day;

public class Payroll {
    
    public static void PaymentList(ArrayList<Employee> employeesList) {
        String date = new String();
        String dayWeekString = new String();
        double salary = 0.0f;
        int dayWeekInt = -1;
        int weekMonth = -1;
        int lastDayMonth = -1;
        int dayDate = -1;

        System.out.println("Digite a data de hoje no formato dd/MM/yyyy");
        date = SystemInput.readString();

        String paymentAgenda = new String();     
        String[] dateSplit = date.split("/");
        dayDate = Integer.parseInt(dateSplit[0]);

        for (Employee employee : employeesList){
            salary = -1.0f;
            dayWeekInt = -1;
            weekMonth = -1;
            lastDayMonth = -1;

            paymentAgenda = employee.getSalary().getPaymentAgenda();
            dayWeekString = Day.getWeekDayString(date);
            weekMonth = Day.WeekMonth(date);
            lastDayMonth = Day.LastDayMonth(date);

            System.out.println("\n");
            if (paymentAgenda.intern() == "Weekly".intern() && dayWeekString.intern() == "SEX".intern()){

                if (employee.getSalary().getEmployeeType().intern() == "Hourly".intern()) {

                    System.out.println("Pagamento Horista | Agenda: Semanalmente\n");
                    //System.out.println(employee.printHourlyInfo(employee));
    
                    double normalTaxe = employee.getSalary().getNormalTaxe();
                    
                    salary = employee.getSalary().calcSalary(employee, "Hourly", 0.0f, 0.0f, 0.0f, ((Hourly)employee).getTimecard(), normalTaxe, employee.getFiliated());
    
                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);
    
                    ((Hourly)employee).getTimecard().setNumberHours(0.0f);
                    employee.getSalary().setPaymentDate(date);
                }

                else if (employee.getSalary().getEmployeeType().intern() == "Salaried".intern()) {
                    
                    System.out.println("Pagamento Salariado | Agenda: Semanalmente\n");
                    //System.out.println(employee.printSalariedInfo(employee));
    
                    double baseSalary = employee.getSalary().getBaseSalary();
    
                    salary = employee.getSalary().calcSalary(employee, "Salaried", ((Salaried)employee).getBaseSalary(), 0.0f, 0.0f, null, 0.0f, employee.getFiliated());
    
                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);
    
                    employee.getSalary().setPaymentDate(date);
                }


                else if (employee.getSalary().getEmployeeType().intern() == "Comissioned".intern()) {
                    System.out.println("Pagamento Vendedor | Agenda: Semanalmente\n");
                    //System.out.println(employee.printComissionedInfo(employee));

                    double baseSalary = ((Comissioned)employee).getBaseSalary();
                    double comission = ((Comissioned)employee).getComission();
                    double sales = ((Comissioned)employee).getSales().getValue();

                    salary = employee.getSalary().calcSalary(employee ,"Comissioned", baseSalary, comission, sales, null, 0.0f, employee.getFiliated());

                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);

                    ((Comissioned)employee).getSales().setValue(0.0f);
                    employee.getSalary().setPaymentDate(date);
                }

            
            }

            else if(paymentAgenda.intern() == "Monthly".intern() && dayDate == lastDayMonth){

                if (employee.getSalary().getEmployeeType().intern() == "Salaried".intern()) {

                    System.out.println("Pagamento Salariado | Agenda: Mensalmente\n");
                    //System.out.println(employee.printSalariedInfo(employee));
    
                    double baseSalary = employee.getSalary().getBaseSalary();
    
                    salary = employee.getSalary().calcSalary(employee, "Salaried", ((Salaried)employee).getBaseSalary(), 0.0f, 0.0f, null, 0.0f, employee.getFiliated());
    
                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);
    
                    employee.getSalary().setPaymentDate(date);
                }

                else if (employee.getSalary().getEmployeeType().intern() == "Hourly".intern()) {

                    System.out.println("Pagamento Horista | Agenda: Mensalmente\n");
                    //System.out.println(employee.printHourlyInfo(employee));
    
                    double normalTaxe = employee.getSalary().getNormalTaxe();
                    
                    salary = employee.getSalary().calcSalary(employee, "Hourly", 0.0f, 0.0f, 0.0f, ((Hourly)employee).getTimecard(), normalTaxe, employee.getFiliated());
    
                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);
    
                    ((Hourly)employee).getTimecard().setNumberHours(0.0f);
                    employee.getSalary().setPaymentDate(date);
                }

                else if (employee.getSalary().getEmployeeType().intern() == "Comissioned".intern()) {

                    System.out.println("Pagamento Vendedor | Agenda: Mensalmente\n");
                    //System.out.println(employee.printComissionedInfo(employee));

                    double baseSalary = ((Comissioned)employee).getBaseSalary();
                    double comission = ((Comissioned)employee).getComission();
                    double sales = ((Comissioned)employee).getSales().getValue();

                    salary = employee.getSalary().calcSalary(employee ,"Comissioned", baseSalary, comission, sales, null, 0.0f, employee.getFiliated());

                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);

                    ((Comissioned)employee).getSales().setValue(0.0f);
                    employee.getSalary().setPaymentDate(date);
                }
 

            }

            else if(paymentAgenda.intern() == "Two-Weekly".intern() && (weekMonth == 2 || weekMonth == 4) && (dayWeekString.intern() == "SEX".intern())){

                if (employee.getSalary().getEmployeeType().intern() == "Comissioned".intern()) {

                    System.out.println("Pagamento Vendedor | Agenda: Bi-semanalmente\n");
                    //System.out.println(employee.printComissionedInfo(employee));
    
                    double baseSalary = ((Comissioned)employee).getBaseSalary();
                    double comission = ((Comissioned)employee).getComission();
                    double sales = ((Comissioned)employee).getSales().getValue();
    
                    salary = employee.getSalary().calcSalary(employee ,"Comissioned", baseSalary, comission, sales, null, 0.0f, employee.getFiliated());
    
                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);
    
                    ((Comissioned)employee).getSales().setValue(0.0f);
                    employee.getSalary().setPaymentDate(date);
                }

                else if (employee.getSalary().getEmployeeType().intern() == "Salaried".intern()) {

                    System.out.println("Pagamento Salariado | Agenda: Bi-Semanalmente\n");
                    //System.out.println(employee.printSalariedInfo(employee));
    
                    double baseSalary = employee.getSalary().getBaseSalary();
    
                    salary = employee.getSalary().calcSalary(employee, "Salaried", ((Salaried)employee).getBaseSalary(), 0.0f, 0.0f, null, 0.0f, employee.getFiliated());
    
                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);
    
                    employee.getSalary().setPaymentDate(date);
                }

                else if (employee.getSalary().getEmployeeType().intern() == "Hourly".intern()) {
                    
                    System.out.println("Pagamento Horista | Agenda: Bi-semanalmente\n");
                    //System.out.println(employee.printHourlyInfo(employee));
    
                    double normalTaxe = employee.getSalary().getNormalTaxe();
                    
                    salary = employee.getSalary().calcSalary(employee, "Hourly", 0.0f, 0.0f, 0.0f, ((Hourly)employee).getTimecard(), normalTaxe, employee.getFiliated());
    
                    System.out.println("Foi feito o pagamento do empregado: "+employee.getName());
                    System.out.println("O valor do pagamento é de R$" + salary);
    
                    ((Hourly)employee).getTimecard().setNumberHours(0.0f);
                    employee.getSalary().setPaymentDate(date);
                }


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
