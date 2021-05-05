package app;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import employees.Comissioned;
import employees.Employee;
import employees.Hourly;
import employees.Salaried;
import payment.CheckbyPost;
import payment.CheckinHands;
import payment.Deposity;
import payment.PaymentMethod;
import union.Syndicate;
import union.Taxes;
import wage.Paycheck;
import workedhours.Timecard;


public class EmployessFunction {

    public static Employee addEmployee() {

        Random random = new Random();
        Employee newEmployee = null;
        PaymentMethod newPaymentMethod = null;
        boolean filiated;
        int type = 0;
        Scanner input = new Scanner(System.in);
        String name = new String();
        String address = new String();
        int employeeID = 0;
        int employeeSyndicateID = 0;
        int paymentMethod = 0;
        String data = new String();

        System.out.println("Digite o nome do novo empregado:");
        name = input.nextLine();

        System.out.println("Digite o endereço do novo empregado:");
        address = input.nextLine();

        System.out.println("O novo empregado é sindicalizado? Digite 'true' para sim ou 'false' para não");
 		filiated = input.nextBoolean();

        System.out.println("Digite o nº da opção do tipo do empregado dentre as seguintes opções:\n '1 - Horista', ' 2 - Assalariado', '3 - Comissionado'");
        type = input.nextInt();
        
        switch (type) {

            case 1:
                System.out.println("Digite o salário hora desse empregado no formato '0,0'");
                double normalTaxe = input.nextDouble();

                newEmployee = new Hourly(name, address, filiated, normalTaxe);
                ((Hourly) newEmployee).setTimecard(null);

                employeeID = random.nextInt(100);
                newEmployee.setEmployeeID(employeeID);

                Paycheck newPaycheck = new Paycheck("Hourly", 0.0f, "Weekly");
                newPaycheck.setNormalTaxe(normalTaxe);
                newEmployee.setSalary(newPaycheck);

                break;

            case 2:
                System.out.println("Digite o salário base desse empregado no formato '0,0'");
                double baseSalary = input.nextDouble();

                newEmployee = new Salaried(name, address, filiated, baseSalary);

                employeeID = random.nextInt(100);
                newEmployee.setEmployeeID(employeeID);

                newPaycheck = new Paycheck("Salaried", baseSalary, "Monthly");
                newEmployee.setSalary(newPaycheck);
                
                break;

            case 3:
                System.out.println("Digite o salário base desse empregado no formato '0,0'");
                baseSalary = input.nextDouble();

                System.out.println("Digite a comissão desse empregado no formato '0,0'");
                double comission = input.nextDouble();

                newEmployee = new Comissioned(name, address, filiated, baseSalary, comission);
                ((Comissioned) newEmployee).setSales(null);

                employeeID = random.nextInt(100);
                newEmployee.setEmployeeID(employeeID);

                newPaycheck = new Paycheck("Comissioned", baseSalary, "Two-Weekly");
                newPaycheck.setComission(comission);
                newEmployee.setSalary(newPaycheck);
                
                break;
        
            default:
                break;
        }

        if(filiated){
            int syndicateID = -1;
            System.out.println("Digite o valor da taxa mensal (taxa sindical) no formato '0,0':");
            double monthlyTaxe = input.nextDouble();
            System.out.println("Digite o valor da taxa adicional no formato '0,0':");
            double additionalTaxe = input.nextDouble();
            input.nextLine();
            
            Syndicate newSyndicate = new Syndicate(syndicateID);
            Taxes newTaxe = new Taxes(monthlyTaxe, additionalTaxe);
            employeeSyndicateID = random.nextInt(100);

            newSyndicate.setTaxes(newTaxe);
            newEmployee.setSyndicate(newSyndicate);
            newEmployee.setEmployeeSyndicateID(employeeSyndicateID);
        }

        else{
            newEmployee.setSyndicate(null);
        }

        System.out.println("Digite o nº da opção do método de pagamento desejado dentre as seguintes opções:\n '1 - Cheque Correios', ' 2 - Cheque em Mãos', '3 - Depósito'");
        paymentMethod = input.nextInt();
        double value = 0.0f;

        switch (paymentMethod) {
            case 1:
                newPaymentMethod = new PaymentMethod("CheckbyPost", newEmployee.getEmployeeID(), newEmployee.getName());
                CheckbyPost newCheckbyPost = new CheckbyPost(newPaymentMethod.getType(), newPaymentMethod.getEmployeeID(), value, newEmployee.getAddress(), newEmployee.getName());
                newEmployee.setPaymentMethod(newCheckbyPost);
                break;
            
            case 2:
                newPaymentMethod = new PaymentMethod("CheckinHands", newEmployee.getEmployeeID(), newEmployee.getName());
                CheckinHands newCheckinHands = new CheckinHands(newPaymentMethod.getType(), newPaymentMethod.getEmployeeID(), newEmployee.getName(), value);
                newEmployee.setPaymentMethod(newCheckinHands);
                break;

            case 3:
                //Data vai receber o caracter " " enter 
                data = input.nextLine();
                newPaymentMethod = new PaymentMethod("Deposity", newEmployee.getEmployeeID(), newEmployee.getName());
                System.out.println("Digite o tipo da conta dentre as opções:\n 'Conta', 'Poupança'");
                String accountType = new String();
                accountType = input.nextLine();

                System.out.println("Digite o número da agência:");
                String agency = new String();
                agency = input.nextLine();

                System.out.println("Digite o número da conta:");
                String acconuntNumber = new String();
                acconuntNumber = input.nextLine();

                System.out.println("Digite o número do banco:");
                int bankNumber = -1;
                bankNumber = input.nextInt();

                Deposity newDeposity = new Deposity(newPaymentMethod.getType(), newPaymentMethod.getEmployeeID(), newEmployee.getName(), value, accountType, agency, acconuntNumber, bankNumber);
                newEmployee.setPaymentMethod(newDeposity);
                break;

            default:
                break;

            
        }
        
        return newEmployee;
    }

    public static void infoEmployees(ArrayList <Employee> employeesList){

        int i = 0;
        for (Employee employee: employeesList){  
            System.out.println("Empregado - " + i);
            System.out.println(employee.printEmployeeInfo(employee));
            i++;
        }
    }

    public static void infoHourly(ArrayList <Employee> employeesList){

        Scanner input = new Scanner(System.in);
        String name = new String();
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado:");
        name = input.nextLine();

        indexOfEmployee = getIndexList(employeesList, name);
        Employee employee = employeesList.get(indexOfEmployee);

        System.out.println(employee.printHourlyInfo(employee));
    }

    public static void infoSalaried(ArrayList <Employee> employeesList){

        Scanner input = new Scanner(System.in);
        String name = new String();
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado:");
        name = input.nextLine();

        indexOfEmployee = getIndexList(employeesList, name);
        Employee employee = employeesList.get(indexOfEmployee);

        System.out.println(employee.printSalariedInfo(employee));
    }


    public static void infoComissioned(ArrayList <Employee> employeesList){

        Scanner input = new Scanner(System.in);
        String name = new String();
        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado:");
        name = input.nextLine();

        indexOfEmployee = getIndexList(employeesList, name);
        Employee employee = employeesList.get(indexOfEmployee);

        System.out.println(employee.printComissionedInfo(employee));
    }

    public static int getIndexList(ArrayList<Employee> employeesList, String employeeName){
        
        String name = new String();
        int indexOfEmployee = -1;
        
        int i = 0;

        for(Employee employee: employeesList){
            
            name = employee.getName();
            
            if(employeeName.intern() == name.intern()){     
                indexOfEmployee = i;
                break;
            }

            i++;   
        }

        return indexOfEmployee;
    }

    public static void removeEmployee(ArrayList<Employee> employeesList){
 
        if(employeesList.isEmpty()){
            System.out.println("Lista de empregados vazia \n");
        }

        else{

            Scanner input = new Scanner(System.in);
            int indexOfEmployee = -1;
            String employeeName = new String();

            System.out.println("Digite o nome do empregado a ser removido:");
            employeeName = input.nextLine();
            
            System.out.println("Empregado a ser removido: "+employeeName);
            indexOfEmployee = getIndexList(employeesList, employeeName);

            if (indexOfEmployee == -1) {
                System.out.println("Empregado não encontrado \n");
            }

            else{
                employeesList.remove(indexOfEmployee);
                System.out.println("Empregado removido! \n");
            }

            return;    
        }
    }

    public static void editEmployee(ArrayList<Employee> employeesList){

        if(employeesList.isEmpty()){
            System.out.println("Lista de empregados vazia \n");
        }

    else{
            Scanner input = new Scanner(System.in);
            int indexOfEmployee = -1;
            String employeeName = new String();
            int edit = 0;
            Employee employee = null;
            String data = new String();

            System.out.println("Digite o nome do empregado onde os dados serão editados:");
            employeeName = input.nextLine();

            System.out.println("Digite o nº da opção do dado que será editado dentre a seguinte opções:\n1-Nome, 2-Endereço, 3-Tipo, 4-Método de Pagamento, 5-Sindicato, 6-Identificação no Sindicato e 7-Taxa Sindical");            
            edit = input.nextInt();
            // Data vai receber o caracter " " enter 
            data = input.nextLine();

            indexOfEmployee = getIndexList(employeesList, employeeName);

            switch (edit) {
                case 1:
                    System.out.println("Digite o nome do empregado:");
                    data = input.nextLine();

                    employee = employeesList.get(indexOfEmployee);
                    employee.setName(data);
                    employeesList.set(indexOfEmployee, employee);
                    break;
                
                case 2:
                    System.out.println("Digite o endereço do empregado:");
                    data = input.nextLine();

                    employee = employeesList.get(indexOfEmployee);
                    employee.setAddress(data);
                    employeesList.set(indexOfEmployee, employee);
                    break;

                case 3:
                    int type = -1;
                    System.out.println("Digite o nº da opção de empregado:\n '1 - Horista', ' 2 - Assalariado', '3 - Comissionado'");
                    type = input.nextInt();

                    employee = employeesList.get(indexOfEmployee);

                    if (type == 1) {
                        System.out.println("Digite o salário hora desse empregado no formato '0,0'");
                        double normalTaxe = input.nextDouble();

                        Employee newHoruly = new Hourly(employee.getName(), employee.getAddress(), employee.getFiliated(), normalTaxe);

                        if (newHoruly.getFiliated()) {
                            Syndicate syndicate = employee.getSyndicate();
                            int employeeSyndicateID = employee.getEmployeeSyndicateID();
                            
                            newHoruly.setSyndicate(syndicate);
                            newHoruly.setEmployeeSyndicateID(employeeSyndicateID);
                        }

                        int employeeID = employee.getEmployeeID();
                        newHoruly.setEmployeeID(employeeID);

                        Paycheck newPaycheck = new Paycheck("Hourly", 0.0f, "Weekly");
                        newPaycheck.setNormalTaxe(normalTaxe);
                        newHoruly.setSalary(newPaycheck);

                        employeesList.set(indexOfEmployee, newHoruly);

                    }

                    else if(type == 2){
                        System.out.println("Digite o salário base desse empregado no formato '0,0'");
                        double baseSalary = input.nextDouble();

                        Employee newSalaried = new Salaried(employee.getName(), employee.getAddress(), employee.getFiliated(), baseSalary);

                        if (newSalaried.getFiliated()) {
                            Syndicate syndicate = employee.getSyndicate();
                            int employeeSyndicateID = employee.getEmployeeSyndicateID();
                       
                            newSalaried.setSyndicate(syndicate);            
                            newSalaried.setEmployeeSyndicateID(employeeSyndicateID);
                        }

                        int employeeID = employee.getEmployeeID();
                        newSalaried.setEmployeeID(employeeID);

                        Paycheck newPaycheck = new Paycheck("Salaried", baseSalary, "Monthly");
                        newSalaried.setSalary(newPaycheck);

                        employeesList.set(indexOfEmployee, newSalaried);

                    }

                    else if(type == 3){
                        System.out.println("Digite o salário base desse empregado no formato '0,0'");
                        double baseSalary = input.nextDouble();
        
                        System.out.println("Digite a comissão desse empregado no formato '0,0'");
                        double comission = input.nextDouble();

                        Employee newComissioned = new Comissioned(employee.getName(), employee.getAddress(), employee.getFiliated(), baseSalary, comission);
                        ((Comissioned) newComissioned).setSales(null);

                        if (newComissioned.getFiliated()) {
                            Syndicate syndicate = employee.getSyndicate();
                            int employeeSyndicateID = employee.getEmployeeSyndicateID();
                                                       
                            newComissioned.setSyndicate(syndicate);
                            newComissioned.setEmployeeSyndicateID(employeeSyndicateID);
                        }

                        int employeeID = employee.getEmployeeID();
                        newComissioned.setEmployeeID(employeeID);

                        Paycheck newPaycheck = new Paycheck("Comissioned", baseSalary, "Two-Weekly");
                        newPaycheck.setComission(comission);
                        newComissioned.setSalary(newPaycheck);

                        employeesList.set(indexOfEmployee, newComissioned);

                    }
                    break;

                case 4:
                    PaymentMethod newPaymentMethod = null;
                    double value = 0.0f;

                    System.out.println("Digite o método de pagamento desejado dentre as opções:\n '1 - Cheque Correios', ' 2 - Cheque em Mãos', '3 - Depósito'");
                    edit = input.nextInt();
                    // Data vai receber o caracter " " enter 
                    data = input.nextLine();

                    indexOfEmployee = getIndexList(employeesList, employeeName);

                    switch (edit) {
                        case 1:
                            employee = employeesList.get(indexOfEmployee);

                            newPaymentMethod = new PaymentMethod("CheckbyPost", employee.getEmployeeID(), employee.getName());
                            CheckbyPost newCheckbyPost = new CheckbyPost(newPaymentMethod.getType(), newPaymentMethod.getEmployeeID(), value, employee.getAddress(), employee.getName());
                            employee.setPaymentMethod(newCheckbyPost);

                            employeesList.set(indexOfEmployee, employee);
                            break;

                        case 2:
                            employee = employeesList.get(indexOfEmployee);

                            newPaymentMethod = new PaymentMethod("CheckinHands", employee.getEmployeeID(), employee.getName());
                            CheckinHands newCheckinHands = new CheckinHands(newPaymentMethod.getType(), newPaymentMethod.getEmployeeID(), employee.getName(), value);
                            employee.setPaymentMethod(newCheckinHands);

                            employeesList.set(indexOfEmployee, employee);
                            break;

                        case 3:

                            employee = employeesList.get(indexOfEmployee);

                            newPaymentMethod = new PaymentMethod("Deposity", employee.getEmployeeID(), employee.getName());
                            System.out.println("Digite o tipo da conta dentre as opções:\n 'Conta', 'Poupança'");
                            String accountType = new String();
                            accountType = input.nextLine();

                            System.out.println("Digite o número da agência:");
                            String agency = new String();
                            agency = input.nextLine();

                            System.out.println("Digite o número da conta:");
                            String acconuntNumber = new String();
                            acconuntNumber = input.nextLine();

                            System.out.println("Digite o número do banco:");
                            int bankNumber = -1;
                            bankNumber = input.nextInt();

                            Deposity newDeposity = new Deposity(newPaymentMethod.getType(), newPaymentMethod.getEmployeeID(), employee.getName(), value, accountType, agency, acconuntNumber, bankNumber);
                            employee.setPaymentMethod(newDeposity);

                            employeesList.set(indexOfEmployee, employee);
                            break;

                        default:
                            break;
                    }

                    break;

                case 5:
                    System.out.println("O empregado é sindicalizado: Digite '1' para sim e '0' para não");
                    edit = input.nextInt();
                    System.out.println("Se o empregado for sindicalizado ele será removido do sindicato");
                    System.out.println("Se o empregado não for sindicalizado ele será adicionado ao sindicato");

                    indexOfEmployee = getIndexList(employeesList, employeeName);
                    int employeeSyndicateID = -1;

                    if (edit == 1) {
                        employee = employeesList.get(indexOfEmployee);

                        Syndicate newSyndicate = null;

                        employee.setEmployeeSyndicateID(employeeSyndicateID);
                        employee.setSyndicate(newSyndicate);
                        employee.setFiliated(false);
                        employeesList.set(indexOfEmployee, employee);
                    }

                    else if (edit == 0){
                        employee = employeesList.get(indexOfEmployee);
                        
                        Random random = new Random();
                        int syndicateID = -1;
                        
                        System.out.println("Digite o valor da taxa mensal (taxa sindical) no formato '0,0':");
                        double monthlyTaxe = input.nextDouble();
                        System.out.println("Digite o valor da taxa adicional no formato '0,0':");
                        double additionalTaxe = input.nextDouble();
                        input.nextLine();
                        
                        Syndicate newSyndicate = new Syndicate(syndicateID);
                        Taxes newTaxe = new Taxes(monthlyTaxe, additionalTaxe);
                        employeeSyndicateID = random.nextInt(100);

                        newSyndicate.setTaxes(newTaxe);
                        employee.setSyndicate(newSyndicate);
                        employee.setEmployeeSyndicateID(employeeSyndicateID);
                        employee.setFiliated(true);

                        employeesList.set(indexOfEmployee, employee);
                    }

                    break;

                case 6:
                    System.out.println("Digite o ID do empregado no Sindicato:");
                    employeeSyndicateID = input.nextInt();

                    employee = employeesList.get(indexOfEmployee);
                    employee.setEmployeeSyndicateID(employeeSyndicateID);
                    employeesList.set(indexOfEmployee, employee);
                    break;
                
                case 7:
                    System.out.println("Digite o valor da taxa mensal (taxa sindical) no formato '0,0':");
                    double monthlyTaxe = input.nextDouble();

                    employee = employeesList.get(indexOfEmployee);
                    employee.getSyndicate().getTaxes().setMonthlyTaxe(monthlyTaxe);
                    employeesList.set(indexOfEmployee, employee);
                    break;

                default:
                    break;
            }
        }
           
    }
}

