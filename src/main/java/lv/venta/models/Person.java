package lv.venta.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@MappedSuperclass
public class Person {
    @Column(name="idp")
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Setter(value= AccessLevel.NONE)
    private long idp;

    @Column(name="name")
    @Size(min=3, max=20)
    @Pattern(regexp ="^[A-Z][a-zA-Z ]{0,39}$", message = "1.burtam jabut lielam, tikai latinu burti, max 40 simboli")
    private String name;

    @Column(name="surname")
    @Size(min=3, max=20)
    @Pattern(regexp ="^[A-Z][a-zA-Z ]{0,39}$", message = "1.burtam jabut lielam, tikai latinu burti, max 40 simboli")
    private String surname;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
