package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("=== TEST 1: seller findById ===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);

		System.out.println("\n=== TEST 2: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		list.forEach(System.out::println);
		System.out.println("\n=== TEST 3: seller findAll ===");
		list = sellerDao.findAll();
		list.forEach(System.out::println);

		System.out.println("\n=== TEST 4: seller insert ===");
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department );
		sellerDao.insert(newSeller);
		list = sellerDao.findAll();
		System.out.println("New seller ID: "+newSeller.getId());
		list.forEach(System.out::println);

		System.out.println("\n=== TEST 5: seller update ===");

		seller = sellerDao.findById(12);
		seller.setName("Arthur Oliveira Gomes");
		seller.setDepartment(new Department(1, null));
		seller.setBaseSalary(10500.0);
		seller.setEmail("arthurbonitao@demais.com.br");
		sellerDao.update(seller);

		seller = sellerDao.findById(13);
		seller.setName("Roberta Magda");
		seller.setDepartment(new Department(1, null));
		seller.setBaseSalary(10500.0);
		seller.setEmail("robertabonitona@demais.com.br");
		sellerDao.update(seller);

		
		System.out.println("Update completed");
		list = sellerDao.findAll();
		list.forEach(System.out::println);
		
		System.out.println("\n=== TEST 6: seller delete ===");
		
		System.out.print("Enter id for delete (type '0'to exit): ");
		Integer deleteId = sc.nextInt();
		while (deleteId != 0) {
			sellerDao.deleteById(deleteId);
			list = sellerDao.findAll();
			list.forEach(System.out::println);
			System.out.print("Enter next id for delete (type '0'to exit): ");
			deleteId = sc.nextInt();
			sellerDao.deleteById(deleteId);
		}
		

		
		
	}

}
