package workedhours;

import java.util.ArrayList;
import java.util.Random;

import app.EmployessFunction;
import app.SystemInput;
import employees.Employee;
import employees.Hourly;

public class Timecard {
    private int timecardNumber;
    private String timeIn;
    private String timeOut;
    private double numberHours = 0.0f;

    public Timecard (double numberHours){
        this.numberHours = numberHours;
    }
    
    public void setTimecardNumber(int timecardNumber) {
        this.timecardNumber = timecardNumber;
    }

    public int getTimecardNumber() {
        return timecardNumber;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setNumberHours(double time) {
        this.numberHours = time;
    }

    public double getNumberHours() {
        return numberHours;
    }
    
    public double numberWorkedHours(String checkInTime, String exitTime) {
        String[] parts1 = checkInTime.split(":");
        String[] parts2 = exitTime.split(":");

        int hour1 = Integer.parseInt(parts1[0]);
        int min1 = Integer.parseInt(parts1[1]);
        int hour2 = Integer.parseInt(parts2[0]);
        int min2 = Integer.parseInt(parts2[1]);

        double dhour1 = ((double) hour1);
        double dmin1 = ((double) min1);
        double dhour2 = ((double) hour2);
        double dmin2 = ((double) min2);
        double hours = ((dhour2 - dhour1) + ((dmin2 - dmin1)/60));

        return hours;
    }

    public void addWorkedHours(double numberofHours){
        this.numberHours = numberHours + numberofHours;
    }

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
