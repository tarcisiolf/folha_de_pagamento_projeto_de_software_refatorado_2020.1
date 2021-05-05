package workedhours;

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

}
