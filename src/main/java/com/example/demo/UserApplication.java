package com.example.demo;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.example.demo.entity.Customer;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

/**
 * Spring Boot アプリケーションのエントリーポイント。
 *
 * <p>
 * アプリケーション起動時に {@link CommandLineRunner} を利用して
 * サンプルユーザー100件をデータベースに登録します。
 * 名前・性別・誕生日などをループで生成し、 {@link UserRepository} を介して保存します。
 * </p>
 *
 * <p>
 * JPA Auditing 機能を有効化しており、作成日時・更新日時などの自動管理が可能です。
 * </p>
 */
@SpringBootApplication
@EnableJpaAuditing
public class UserApplication implements CommandLineRunner {

	/** ユーザーデータアクセス用リポジトリ */
	@Autowired
	private UserRepository userRepository;

	/**
	 * アプリケーションのメインメソッド。
	 * 
	 * @param args コマンドライン引数
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	/**
	 * アプリケーション起動後に自動実行される処理。
	 * <p>
	 * 100件のサンプルユーザーを生成し、データベースに保存します。
	 * 名前・姓・性別・誕生日を順番に割り当てています。
	 * </p>
	 *
	 * @param args コマンドライン引数
	 * @throws Exception 実行中に例外が発生した場合
	 */
	@Override
	public void run(String... args) throws Exception {

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

		for (int i = 0; i < 100; i++) {
			String firstName = firstNames[i];
			String lastName = lastNames[i];
			int genderCode = i % 3 + 1;
			LocalDate birthday = LocalDate.now().minusYears(i);
			long point = i;

			User user = new Customer(firstName, lastName, genderCode, birthday, point);
			userRepository.save(user);
		}
	}
}