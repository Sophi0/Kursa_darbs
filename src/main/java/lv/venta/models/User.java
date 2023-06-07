package lv.venta.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Table(name = "user_table")
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User extends Person {
	//TODO what we do with password?
/*	@Pattern(regexp="[A-Za-z0-9]{8,20}")
	@Size(min=8, max=20)
	private String encodedPassword;*/

	@Column(name="email")
	@Pattern(regexp="[a-z]+[0-9]{2}[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+[a-z$]")
	private String email;

	@Column(name="username")
	@Size(min=8, max=20)
	@Pattern(regexp="[a-z0-9.]{8,20}")
	private String username;

	public User(String name, String surname, String email, String username) {
		super(name, surname);
		this.email = email;
		this.username = username;
	}

	//TODO bookABook()
	//TODO ExtendExpiringDate()

}
