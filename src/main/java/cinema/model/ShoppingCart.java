package cinema.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCart {
    @Id
    private Long id;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ticket> tickets;
    private LocalDateTime orderDate;
    @OneToOne
    @MapsId
    private User user;

    public ShoppingCart() {
    }

    public ShoppingCart(List<Ticket> tickets, LocalDateTime orderDate, User user) {
        this.tickets = tickets;
        this.orderDate = orderDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ShoppingCart{id=" + id + ", tickets=" + tickets + "\n, orderDate=" + orderDate
                + ", user=" + user + '}';
    }
}
