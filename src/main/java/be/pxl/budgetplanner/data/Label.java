package be.pxl.budgetplanner.data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Label {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;

    // mappedBy is op de property naam. (Dit is de niet-owner kant.)
    @OneToMany(mappedBy = "label")
    private Set<Payment> payment;

    public Label(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Label() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Payment> getPayment() {
        return payment;
    }

    public void setPayment(Set<Payment> payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Label label = (Label) o;
        return id == label.id &&
                Objects.equals(name, label.name) &&
                Objects.equals(description, label.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
