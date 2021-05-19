package payment;

import employees.Employee;

public class Context {

    private PaymentStrategy paymentStrategy = null;

    public Context(PaymentStrategy paymentStrategy){
        this.paymentStrategy = paymentStrategy;
    }

    public void executePaymentStrategy(Employee employee, String paymentAgenda ,String date){
        paymentStrategy.makePayment(employee, paymentAgenda, date);
    }
    
}
