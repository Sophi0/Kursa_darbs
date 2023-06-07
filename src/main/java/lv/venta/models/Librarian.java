package lv.venta.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "librarian_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Librarian extends Person {

    public Librarian(String name, String surname) {
        super(name, surname);
    }
}
