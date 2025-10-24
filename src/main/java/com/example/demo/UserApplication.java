package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
@EnableJpaAuditing
public class UserApplication implements CommandLineRunner {
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(UserApplication.class, args);

	}
	//	@PostConstruct
	//	public void print() {
	//		System.out.println("aaa");
	//	}

	@Override
	public void run(String... args) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("aaa");
		//		User user = new User("", "", 0, LocalDate.now());
		//		userRipository.save(user);

		for (int i = 0; i < 100; i++) {
//			Random random = new Random();

			String[] firstNames = { "Emma", "Liam", "Olivia", "Noah", "Ava", "Elijah", "Sophia", "James", "Isabella",
					  "Benjamin", "Mia", "Lucas", "Charlotte", "Henry", "Amelia", "Ethan", "Harper",
					  "Alexander", "Evelyn", "Daniel", "Ella", "Matthew", "Scarlett", "Jackson",
					  "Abigail", "Sebastian", "Emily", "Carter", "Elizabeth", "Michael", "Avery",
					  "Samuel", "Sofia", "David", "Grace", "Joseph", "Chloe", "Owen", "Victoria",
					  "Gabriel", "Riley", "Caleb", "Aria", "Wyatt", "Lily", "Jack", "Hannah",
					  "Luke", "Aurora", "Ryan", "Zoe", "Nathan", "Nora", "Isaac", "Lillian",
					  "Levi", "Addison", "Christian", "Stella", "Andrew", "Natalie", "Joshua", "Violet",
					  "Jonathan", "Brooklyn", "Thomas", "Bella", "Charles", "Claire", "Eli", "Skylar",
					  "Dylan", "Lucy", "Cameron", "Paisley", "Aaron", "Everly", "Henry", "Anna",
					  "Nicholas", "Emilia", "Jaxon", "Hazel", "Lincoln", "Ellie", "Grayson", "Madelyn",
					  "Mateo", "Ruby", "Ezra", "Penelope", "Ryan", "Luna", "Asher", "Kennedy",
					  "Leo", "Samantha", "Maverick", "Peyton", "Elias", "Willow", "Christian", "Camila",
					  "Hunter", "Kinsley", "Jordan", "Violet" };
			String[] lastNames = { "Smith", "Johnson", "Brown", "Davis", "Wilson", "Miller", "Taylor", "Anderson",
					  "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez",
					  "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen",
					  "Young", "King", "Wright", "Lopez", "Hill", "Scott", "Green", "Adams", "Baker",
					  "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips",
					  "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris",
					  "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera",
					  "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray",
					  "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett",
					  "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell",
					  "Long", "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster",
					  "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes", "Myers",
					  "Ford", "Hamilton", "Graham", "Sullivan", "Wallace", "Woods", "Cole", "West",
					  "Jordan", "Owens", "Reynolds", "Fisher" };
//			String randomFirstName = firstNames[random.nextInt(firstNames.length)];
//			String randomLastName = lastNames[random.nextInt(lastNames.length)] + i;
			String firstName = firstNames[i];
			String lastName = lastNames[i];
//			int randomGenderCode = random.nextInt(Gender.values().length - 1) + 1;
			int genderCode = i % 3 + 1;

//			int year = 1925 + random.nextInt(100);
//			int month = 1 + random.nextInt(12);
//			int day = 1 + random.nextInt(LocalDate.of(year, month, 1).lengthOfMonth());
//			LocalDate ramdomBirthday = LocalDate.of(year, month, day);
			LocalDate birthday = LocalDate.now().minusYears(i);

			User user = new User(firstName, lastName, genderCode, birthday);
			userRepository.save(user);
		}
	}
}
