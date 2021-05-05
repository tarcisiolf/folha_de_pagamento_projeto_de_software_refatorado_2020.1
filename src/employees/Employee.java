package employees;
import java.util.ArrayList;

import payment.PaymentMethod;
import union.Syndicate;
import wage.Paycheck;

/**
 * Employee
 */

public class Employee {

    private int employeeID;
    private String name;
    private String address;
    private boolean filiated;
    private Paycheck salary;
    private Syndicate syndicate;
    private int employeeSyndicateID = -1;
    private PaymentMethod paymentMethod;

    public Employee(String name, String address, boolean filiated)
    {
        this.name = name;
        this.address = address;
        this.filiated = filiated;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAddress() {
        return address;
    }

    public void setFiliated(boolean filiated) {
        this.filiated = filiated;
    }

    public boolean getFiliated() {
        return filiated;
    }

    public void setSalary(Paycheck salary)
	{
		this.salary = salary;
	}
    
    public Paycheck getSalary()
	{
		return this.salary;
	}

    public void setSyndicate(Syndicate syndicate)
	{
		this.syndicate = syndicate;
	}
    
    public Syndicate getSyndicate()
	{
		return this.syndicate;
	}

    public String printEmployeeInfo(Employee employee){ 
        return  "Name: " + employee.name + 
                "\nAddress: " + employee.address + 
                "\nEmployeeID: " + employee.employeeID +
                "\nFiliated: " + employee.filiated +
                "\nEmployeeSyndicateID: "+ employee.employeeSyndicateID +
                "\nPaymentMethod: " +employee.getPaymentMethod().getType() +
                "\nPaymentAgenda: "+ employee.getSalary().getPaymentAgenda();
    }

    public String printHourlyInfo(Employee employee){ 
        Hourly hourlyEmployee = ((Hourly)employee);
        return  "Name: " + employee.name + 
                "\nAddress: " + employee.address + 
                "\nEmployeeID: " + employee.employeeID +
                "\nFiliated: " + employee.filiated +
                "\nEmployeeSyndicateID: "+ employee.employeeSyndicateID +
                "\nPaymentMethod: " +employee.getPaymentMethod().getType()+
                "\nNormalTaxe: "+hourlyEmployee.getNormalTaxe()+
                "\nNumberHours: "+hourlyEmployee.getTimecard().getNumberHours();


    }

    public String printSalariedInfo(Employee employee){ 
        Salaried salariedEmployee = ((Salaried)employee);
        return  "Name: " + employee.name + 
                "\nAddress: " + employee.address + 
                "\nEmployeeID: " + employee.employeeID +
                "\nFiliated: " + employee.filiated +
                "\nEmployeeSyndicateID: "+ employee.employeeSyndicateID +
                "\nPaymentMethod: " +employee.getPaymentMethod().getType()+
                "\nBaseSalary: "+salariedEmployee.getBaseSalary();
    }

    public String printComissionedInfo(Employee employee){ 
        Comissioned comissionedEmployee = ((Comissioned)employee);
        return  "Name: " + employee.name + 
                "\nAddress: " + employee.address + 
                "\nEmployeeID: " + employee.employeeID +
                "\nFiliated: " + employee.filiated +
                "\nEmployeeSyndicateID: "+ employee.employeeSyndicateID +
                "\nPaymentMethod: " +employee.getPaymentMethod().getType()+
                "\nBaseSalary: "+comissionedEmployee.getBaseSalary()+
                "\nComission: "+comissionedEmployee.getComission()+
                "\nSales: "+comissionedEmployee.getSales().getValue();
    }

    public void setEmployeeSyndicateID(int employeeSyndicateID) {
        this.employeeSyndicateID = employeeSyndicateID;
    }
  
    public int getEmployeeSyndicateID(){
        return employeeSyndicateID;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod(){
        return this.paymentMethod;
    }
  
}