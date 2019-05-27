package be.pxl.budgetplanner.rest;

import be.pxl.budgetplanner.beans.Payment;
import be.pxl.budgetplanner.jpa.PaymentJPA;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

@Path("/")
public class RestApp {
    @Path("/payments")
    @GET
    public List<Payment> getPayments() throws Exception {
        try (PaymentJPA jpa = new PaymentJPA()) {
            return jpa.getPayments();
        }
    }

    @Path("/payments")
    @POST
    public Payment addPayment(Payment payment) throws Exception {
        try (PaymentJPA jpa = new PaymentJPA()) {
            jpa.addPayment(payment);
            return payment;
        }
    }
}
