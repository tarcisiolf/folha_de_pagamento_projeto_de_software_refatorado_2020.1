package app;

import java.util.ArrayList;
import java.util.Random;

import employees.Hourly;
import workedhours.Timecard;
import employees.Employee;

public class TimecardLaunch {

    public static void setTimecard(ArrayList <Employee> employeesList) {

        Random random = new Random();
        double workedHoursNumber = 0.0f;
        int timecardNumber = 0;
        String name = new String();
        String timeIn = new String();
        String timeOut = new String();

        int indexOfEmployee = -1;

        System.out.println("Digite o nome do empregado onde serão anotadas as informações do cartão de ponto:");
        name = SystemInput.readString();

        System.out.println("Digite a hora de entrada no formato 00:00 :");
        timeIn = SystemInput.readString();

        System.out.println("Digite a hora de saída no formato 00:00 :");
        timeOut = SystemInput.readString();

        indexOfEmployee = EmployessFunction.getIndexList(employeesList, name);

        Employee employee = employeesList.get(indexOfEmployee);

        Hourly hourlyEmployee = ((Hourly) employee);

        if(hourlyEmployee.getTimecard() == null){

            Timecard newTimecard = new Timecard(0.0f);
            timecardNumber = random.nextInt(100);
            newTimecard.setTimecardNumber(timecardNumber);
    
            newTimecard.setTimeIn(timeIn);
            newTimecard.setTimeOut(timeOut);

            workedHoursNumber = newTimecard.numberWorkedHours(timeIn, timeOut);
            System.out.println("Horas Trabalhadas "+workedHoursNumber);

            newTimecard.setNumberHours(workedHoursNumber);
            hourlyEmployee.setTimecard(newTimecard);
        }

        else{
            Timecard newTimecard = hourlyEmployee.getTimecard();
            
            newTimecard.setTimeIn(timeIn);
            newTimecard.setTimeOut(timeOut);
            workedHoursNumber = newTimecard.numberWorkedHours(timeIn, timeOut);
            System.out.println("Horas Trabalhadas "+workedHoursNumber);
            newTimecard.addWorkedHours(workedHoursNumber);
            hourlyEmployee.setTimecard(newTimecard);
        }
        
        employeesList.set(indexOfEmployee, hourlyEmployee);
    }
}
