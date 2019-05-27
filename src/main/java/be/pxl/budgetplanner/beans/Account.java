package be.pxl.budgetplanner.beans;

import javax.persistence.*;
import java.util.Objects;

@Entity
//@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "accountId")
    private int id;
    private String number;
    private String IBAN;
    private String name;

    public Account(int id, String number, String IBAN, String name) {
        this.id = id;
        this.number = number;
        this.IBAN = IBAN;
        this.name = name;
    }

    public Account() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", IBAN='" + IBAN + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
                Objects.equals(number, account.number) &&
                Objects.equals(IBAN, account.IBAN) &&
                Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, IBAN, name);
    }
}
