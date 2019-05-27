package be.pxl.budgetplanner.beans;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private float amount;
    private String currency;
    private String detail;

    // Payment is de owner van account.
    // Payment moet dus @ManyToOne hebben.
    /**
     * The account that sent the money.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId")
    private Account account;

    // Zoals de vorige.
    /**
     * The account that received the money.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "counterAccountId")
    private Account counterAccount;

    // Weer zoals de vorige.
    // JoinColumn is op de kolom naam.
    /**
     * Er is een vaste set van labels die gebruikt
     * kunnen worden om een payment aan te duiden.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "labelId")
    private Label label;

    public Payment(int id, LocalDateTime date, float amount, String currency, String detail, Account account, Account counterAccount, Label label) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.detail = detail;
        this.account = account;
        this.counterAccount = counterAccount;
        this.label = label;
    }

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getCounterAccount() {
        return counterAccount;
    }

    public void setCounterAccount(Account counterAccount) {
        this.counterAccount = counterAccount;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id &&
                Float.compare(payment.amount, amount) == 0 &&
                Objects.equals(date, payment.date) &&
                Objects.equals(currency, payment.currency) &&
                Objects.equals(detail, payment.detail) &&
                Objects.equals(account, payment.account) &&
                Objects.equals(counterAccount, payment.counterAccount) &&
                Objects.equals(label, payment.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, amount, currency, detail, account, counterAccount, label);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", detail='" + detail + '\'' +
                ", account=" + account +
                ", counterAccount=" + counterAccount +
                ", label=" + label +
                '}';
    }
}
