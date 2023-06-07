package lv.venta;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;

import jakarta.validation.constraints.AssertFalse.List;
import lv.venta.models.Author;
import lv.venta.models.BookGenre;
import lv.venta.models.Librarian;
import lv.venta.models.User;
import lv.venta.repos.IAuthorRepo;
import lv.venta.repos.IBookRepo;
import lv.venta.repos.IExemplarIssueRepo;
import lv.venta.repos.IExemplarRepo;
import lv.venta.repos.ILibrarianRepo;
import lv.venta.repos.IUserRepo;

@SpringBootApplication
public class JavaKursaDarbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaKursaDarbsApplication.class, args);
	}

	@Bean
	public CommandLineRunner testModel(IAuthorRepo auRepo, IBookRepo bkRepo, IExemplarIssueRepo exiRepo, IExemplarRepo exRepo, ILibrarianRepo lbRepo, IUserRepo usRepo) {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				
				Author au1 = new Author("Vasiliy", "Uljanov", LocalDate.of(1957, 5, 20), LocalDate.of(2010, 4, 10));
				Author au2 = new Author("Elena", "Letuchaya", LocalDate.of(1949, 6, 3), LocalDate.of(2000, 3, 23));
				auRepo.save(au1);
				auRepo.save(au2);
				
				Librarian lb2 = new Librarian("Galina", "Liepina");
				Librarian lb3 = new Librarian("Ivans", "Putilov");
				lbRepo.save(lb2);
				lbRepo.save(lb3);
				
				User u4 = new User("Olegs", "Volkovs", "ole9@gmail.com", "olvol2015");
				User u5 = new User("Inessa", "Ziedina", "inesl23@gmail.com", "inezie441");
				usRepo.save(u4);
				usRepo.save(u5);
				
				//Book bk6 = new Book("Gore ot uma", new ArrayList(List.of(au1)), BookGenre.Classic, " ", LocalDate.of(1980, 4, 23), 10);

			}

		};
	}
}
