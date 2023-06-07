package lv.venta.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Table(name = "librarian_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Librarian extends Person {
    @ManyToMany(mappedBy="librarianIssue")
    @ToString.Exclude
    private Collection<ExemplarIssue> exemplarIssue;

    @ManyToMany(mappedBy="librarianReturn")
    @ToString.Exclude
    private Collection<ExemplarIssue> exemplarReturn;

    public Librarian(String name, String surname) {
        super(name, surname);
    }
    
    //addBook
    //removeBook
    //addExemplar
    //removeExemplar
    //addAuthor
    //removeAuthor
}
