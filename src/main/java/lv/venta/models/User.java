package lv.venta.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Collection;

@Table(name = "user_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AttributeOverride(name = "idp", column = @Column(name = "idu"))
public class User extends Person {
	@Column(name="email")
	@Pattern(regexp="[a-zA-Z0-9.]{0,20}@[a-z]+.[a-z]+")
	private String email;

	@Column(name="username")
	@Pattern(regexp="[a-z0-9.]{3,20}")
	private String username;

	@OneToMany(mappedBy = "user")
	private Collection<ExemplarIssue> exemplarIssue;

	@Column(name="fines")
	private float fines; //0.1 euro every week

	@Column(name="message")
	private String message;

	public User(String name, String surname, String email, String username) {
		super(name, surname);
		this.email = email;
		this.username = username;
	}
}
