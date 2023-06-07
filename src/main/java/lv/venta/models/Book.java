package lv.venta.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;

@Table(name = "book_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Book {
    @Column(name="idb")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Setter(value= AccessLevel.NONE)
    private long idb;

    @Column(name="title")
    @Size(min=3, max=40)
    @Pattern(regexp ="^[A-Z][a-zA-Z ]{0,39}$", message = "1.burtam jabut lielam, tikai latinu burti, max 40 simboli")
    private String title;

    @ManyToMany
    @JoinTable(name="book_author_table", joinColumns = @JoinColumn(name = "idp"), inverseJoinColumns = @JoinColumn(name = "idb"))
    @ToString.Exclude
    private Collection<Author> author;

    @Column(name="genre")
    @NotNull
    private BookGenre genre;

    @Column(name="description")
    @Size(max=40)
    @Pattern(regexp ="^[A-Z][a-zA-Z ]{0,39}$", message = "1.burtam jabut lielam, tikai latinu burti, max 40 simboli")
    private String description;

    @Column(name="writingYear")
    @NotNull
    @Past
    private LocalDate writingYear;

    @Column(name="quantity")
    @Min(0)
    @Max(100)
    private int quantity;

    @OneToMany(mappedBy = "book")
    private Collection<Exemplar> exemplars;

    public Book(String title, Collection<Author> author, BookGenre genre, String description, LocalDate writingYear, int quantity) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.writingYear = writingYear;
        this.quantity = quantity;

//        exemplars = new ArrayList<>();
       /* for (int i = 1; i <= quantity; i++) {
            Exemplar exemplar = new Exemplar(title, author, genre, description, writingYear, quantity, false);
            exemplars.add(exemplar);
        }*/
    }
}
