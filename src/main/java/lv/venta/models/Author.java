package lv.venta.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Collection;
@Table(name = "author_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Author extends Person {
    @Column(name="dateOfBirth")
    @NotNull
    @Past
	private LocalDate dateOfBirth;

    @Column(name="dateOfDeath")
    @NotNull
    @PastOrPresent
	private LocalDate dateOfDeath;

    @ManyToMany(mappedBy="author")
    @ToString.Exclude
	private Collection<Book> books;

    public Author(String name, String surname, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        super(name, surname);
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    public void addBook(Book inputBook){
        if(!books.contains(inputBook)){
            books.add(inputBook);
        }
    }

    public void removeBook(Book inputBook){
        books.remove(inputBook);
    }
}
