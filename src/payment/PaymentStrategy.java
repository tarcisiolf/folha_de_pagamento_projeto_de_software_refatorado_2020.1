package payment;

import employees.Employee;

public interface PaymentStrategy {
    void makePayment(Employee employee, String paymentAgenda, String date);
}
