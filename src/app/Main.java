package app;

import java.util.ArrayList;
import employees.Employee;
import employees.Sales;
import payment.Payroll;
import union.Taxes;
import workedhours.Timecard;

public class Main {

    public static void main(String [] args) {
        int opition = -1;

        ArrayList<Employee> employeesList = new ArrayList<Employee>();

        while (opition != 0) {
            System.out.println("\n ---- Menu do Sistema da Folha de Pagamento ---- \n");
            System.out.println("Por favor, escolha uma opção: ");
            System.out.println("1 - Adicionar um novo empregado");
            System.out.println("2 - Informações dos empregados");
            System.out.println("3 - Remover um empregado");
            System.out.println("4 - Lançar um cartão de ponto");
            System.out.println("5 - Lançar um resultado de venda");
            System.out.println("6 - Lançar uma taxa de serviço");
            System.out.println("7 - Editar dados de um funcionário");
            System.out.println("8 - Rodar a folha de pagamento");
            System.out.println("9 - Mudar a agenda do empregado");
            System.out.println("10 - Informações do empregado Horista");
            System.out.println("11 - Informações do empregado Salariado");
            System.out.println("12 - Informações do empregado Comissionado");
            System.out.println("0 - Fechar o menu.\n");
            System.out.print("  -> ");

            opition = SystemInput.readInt();

            switch (opition) {
                case 1:
                    Employee newEmployee = EmployessFunction.addEmployee();
                    employeesList.add(newEmployee);
                    break;
                
                case 2:
                    EmployessFunction.infoEmployees(employeesList);
                    break;

                case 3:
                    EmployessFunction.removeEmployee(employeesList);
                    break;

                case  4:
                    Timecard.setTimecard(employeesList);
                    break;

                case 5:
                    Sales.addSale(employeesList);
                    break;
                    
                case 6:
                    Taxes.setServiceTaxe(employeesList);
                    break;
                
                case 7:
                    EmployessFunction.editEmployee(employeesList);
                    break;

                case 8:
                    Payroll.PaymentList(employeesList);
                    break;

                case 9:
                    Payroll.changeEmployeePaymentAgenda(employeesList);
                    break;

                case 10:
                    EmployessFunction.infoHourly(employeesList);
                    break;

                case 11:
                    EmployessFunction.infoSalaried(employeesList);
                    break;

                case 12:
                    EmployessFunction.infoComissioned(employeesList);
                    break;
                        
                default:
                    System.out.println("Digite o número de uma opção válida");
                    break;
            }            
        }
    }
}
