package lv.venta.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@Table(name = "exemplar_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Exemplar {
    @Column(name="idex")
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Setter(value= AccessLevel.NONE)
    private long idex;

    @Column(name="isIssued")
    private boolean isIssued; //eksemplars ir panemts

    @ManyToOne
    @JoinColumn(name = "idb")
    private Book book;

    @OneToOne(mappedBy = "exemplar")
    private ExemplarIssue exemplarIssue;

    public Exemplar(Book book, boolean isIssued) {
        this.book = book;
        this.isIssued = isIssued;
    }
}
