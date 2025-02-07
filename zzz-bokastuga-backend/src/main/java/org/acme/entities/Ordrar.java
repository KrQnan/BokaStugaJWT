package org.acme.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "ORDRAR")
@Table(name = "ORDRAR")
public class Ordrar
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    public Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "KUNDID")
    public Anvandare kundId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "StugId")
    public Stuga stugId;

    @Column(name = "BOKNINGFROM")
    public Date bokningFrom;

    @Column(name = "BOKNINGTOM")
    public Date bokningTom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Anvandare getKundId() {
        return kundId;
    }

    public void setKundId(Anvandare kundId) {
        this.kundId = kundId;
    }

    public Stuga getStugId() {
        return stugId;
    }

    public void setStugId(Stuga stugId) {
        this.stugId = stugId;
    }

    public Date getBokningFrom() {
        return bokningFrom;
    }

    public void setBokningFrom(Date bokningFrom) {
        this.bokningFrom = bokningFrom;
    }

    public Date getBokningTom() {
        return bokningTom;
    }

    public void setBokningTom(Date bokningTom) {
        this.bokningTom = bokningTom;
    }

    public Ordrar() {
    }

    public Ordrar(Integer id, Anvandare kundId, Stuga stugId, Date bokningFrom, Date bokningTom) {
        this.id = id;
        this.kundId = kundId;
        this.stugId = stugId;
        this.bokningFrom = bokningFrom;
        this.bokningTom = bokningTom;
    }
}
