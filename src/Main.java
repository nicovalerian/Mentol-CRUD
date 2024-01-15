import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
	static Scanner scan = new Scanner(System.in);
	ArrayList<Employee> employees = new ArrayList<>();
	
	public Main() {
		
		while(true) {
			System.out.println("+-------------------------------+");
			System.out.println("|    PT. Mentol Management      |");
			System.out.println("|-------------------------------|");
			System.out.println("| 1. Input Data Karyawan        |");
			System.out.println("| 2. View Data Karyawan     	|");
			System.out.println("| 3. Update Data Karyawan      	|");
			System.out.println("| 4. Delete Data Karyawan     	|");
			System.out.println("| 5. Keluar                     |");
			System.out.println("+-------------------------------+");
			System.out.printf("Masukkan pilihanmu (1-5): ");
			
			int choice = scan.nextInt(); scan.nextLine();
			if (choice == 1) {
				newKaryawan();
			} else if (choice == 2) {
				viewTable();
			} else if (choice == 3) {
				updateKaryawan();
			} else if (choice == 4) {
				deleteKaryawan();
			} else if (choice == 5) {
				System.out.println("Program akan exit, terima kasih!");
				System.exit(0);
			} else {
				System.out.println("Input belum benar, coba lagi.");
			}
		}
	}
	
	public void bonusCalc(String jabatan) {
		int count = 0;
		for (Employee employee : employees) { // Iterate through the data to count the number of employees with the specified job position
			if (jabatan.equals(employee.getJabatan())) {
				count++;
			}
		}
		
		int minThreshold = 3; // At least 3 employees registered before is needed for them receive bonuses
		
		if (count <= minThreshold) {
			return;
		}
		
		double bonusGaji = 0;
		if (jabatan.equals("Manager")) {
			bonusGaji = 1.1;
		} else if (jabatan.equals("Supervisor")) {
			bonusGaji = 1.075;
		} else if (jabatan.equals("Admin")) {
			bonusGaji = 1.05;
		}

		if (jabatan.equals("Manager")) {
			System.out.printf("Bonus sebesar 10%% telah diberikan kepada karyawan dengan id ");
		} else if (jabatan.equals("Supervisor")) {
			System.out.printf("Bonus sebesar 7.5%% telah diberikan kepada karyawan dengan id ");
		} else if (jabatan.equals("Admin")) {
			System.out.printf("Bonus sebesar 5%% telah diberikan kepada karyawan dengan id ");
		}
		
		for (int i = 0; i < Employee.employeeCount - 1; i++) { // Employee.employeeCount - 1 is because we do not want to iterate through the newly registered employee
			Employee employee = employees.get(i);
			
			if (jabatan.equals(employee.getJabatan())) {
				int newGaji = (int) (employee.getGaji() * bonusGaji);
				employee.setGaji(newGaji);
				if (i != Employee.employeeCount - 2) System.out.printf("%s, ", employee.getId());
				else System.out.printf("%s\n", employee.getId());
			}
		}
	}
	
	public void newKaryawan() {
		Boolean validKode = false, validNama = false, validKelamin = false, validJabatan = false;
		String kode = "", nama = "", kelamin = "", jabatan = "";
		int gaji = 0;
		
		while (!validKode) {
			System.out.println("Input kode karyawan: ");
			String kodeTemp = scan.nextLine();
			// Untuk validasi kode karyawan kita gunakan regex (MM-XXXX) --> "^[A-Za-z]{2}-[0-9]{4}$"
			Pattern pattern = Pattern.compile("^[A-Za-z]{2}-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
			/* Menerima hanya string (case insensitive) yang dimulai 2 huruf "MM", 
			 * diikuti simbol '-',
			 * dan diakhiri 4 angka numeric "XXXX"
			 */
			Matcher matcher = pattern.matcher(kodeTemp);
			if (matcher.matches()) { // Kalau sesuai pola kode karyawan...
				kode = kodeTemp;
				validKode = true;
				System.out.println("Kode karyawan valid.");
			} else {
				System.out.println("Kode karyawan tidak valid, coba lagi.");
			}
		}
		
		while (!validNama) {
			System.out.println("Input nama karyawan [>=3]: ");
			String namaTemp = scan.nextLine();
			
			int alphabetCount = 0;
			// Nama yang valid adalah string yang mengandung setidaknya 3 huruf alphabet
			
			for (int i = 0; i < namaTemp.length(); i++) {
				char c = namaTemp.charAt(i);
				if (Character.isAlphabetic(c)) {
					alphabetCount++;
					
					if (alphabetCount >= 3) {
						validNama = true;
						nama = namaTemp;
						System.out.println("Nama karyawan valid.");
						break;
					}
				}
			}
			
			if (alphabetCount < 3) System.out.println("Nama karyawan tidak valid, coba lagi.");
		}
		
		while (!validKelamin) {
			System.out.println("Input jenis kelamin karyawan [\"Laki-laki\" | \"Perempuan\"] (Case Sensitive): ");
			String kelaminTemp = scan.nextLine();
			
			if (kelaminTemp.equals("Laki-laki")) {
				System.out.println("Jenis kelamin valid.");
				kelamin = "Laki-laki";
				validKelamin = true;
			} else if (kelaminTemp.equals("Perempuan")) {
				System.out.println("Jenis kelamin valid.");
				kelamin = "Perempuan";
				validKelamin = true;
			} else {
				System.out.println("Jenis kelamin tidak valid, coba lagi.");
			}
		}
		
		while (!validJabatan) {
			System.out.println("Input jabatan karyawan [\"Manager\" | \"Supervisor\" | \"Admin\"] (Case Sensitive): ");
			String jabatanTemp = scan.nextLine();
			
			if (jabatanTemp.equals("Manager")) {
				System.out.println("Jabatan valid.");
				jabatan = "Manager";
				gaji = 8000000;
				validJabatan = true;
			} else if (jabatanTemp.equals("Supervisor")) {
				System.out.println("Jabatan valid.");
				jabatan = "Supervisor";
				gaji = 6000000;
				validJabatan = true;
			} else if (jabatanTemp.equals("Admin")){
				System.out.println("Jabatan valid.");
				jabatan = "Admin";
				gaji = 4000000;
				validJabatan = true;
			} else {
				System.out.println("Jabatan tidak valid, coba lagi.");
			}
		}
		
		employees.add(new Employee(kode, nama, kelamin, jabatan, gaji));
		System.out.printf("Berhasil menambahkan karyawan \"%s\" dengan id %s\n", nama, kode);
		bonusCalc(jabatan);
	}
	
	public void viewTable() {
		Comparator<Employee> nameComparator = Comparator.comparing(Employee::getNama);
		Collections.sort(employees, nameComparator);
		
		int noCount = 1;
		System.out.println("|----|-----------------|------------------------------|-----------------|-----------------|-----------------|");
		System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin    |Jabatan          |Gaji Karyawan    |");
		System.out.println("|----|-----------------|------------------------------|-----------------|-----------------|-----------------|");
		
		if (Employee.employeeCount > 0) {
			for (int i = 0; i < Employee.employeeCount; i++) {
				Employee employee = employees.get(i);
				System.out.printf("|%4d|%17s|%30s|%17s|%17s|%17d|\n", noCount, employee.getId(), employee.getNama(), employee.getJenisKelamin(), employee.getJabatan(), employee.getGaji());
				noCount++;
			}
		} else {
			System.out.println("|    |                 |                Belum ada data|                 |                 |                 |");
		}
		
		System.out.println("|----|-----------------|------------------------------|-----------------|-----------------|-----------------|");
	}
	
	public void updateKaryawan() {
		viewTable();
		System.out.println("Pilih no karyawan yang ingin diupdate: ");
		int idx = scan.nextInt(); scan.nextLine();
		
		if (idx <= 0 || idx > Employee.employeeCount) {
			System.out.println("No. karyawan tidak valid, coba lagi.");
			return;
		}
		
		// Same code snippet from newKaryawan(), it's redundant (to be fixed)
		Boolean validKode = false, validNama = false, validKelamin = false, validJabatan = false;
		String kode = "", nama = "", kelamin = "", jabatan = "";
		int gaji = 0;
		
		while (!validKode) {
			System.out.println("Input kode karyawan: ");
			String kodeTemp = scan.nextLine();
			Pattern pattern = Pattern.compile("^[A-Za-z]{2}-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(kodeTemp);
			if (matcher.matches()) {
				kode = kodeTemp;
				validKode = true;
				System.out.println("Kode karyawan valid.");
			} else {
				System.out.println("Kode karyawan tidak valid, coba lagi.");
			}
		}
		
		while (!validNama) {
			System.out.println("Input nama karyawan [>=3]: ");
			String namaTemp = scan.nextLine();
			
			int alphabetCount = 0;
			
			for (int i = 0; i < namaTemp.length(); i++) {
				char c = namaTemp.charAt(i);
				if (Character.isAlphabetic(c)) {
					alphabetCount++;
					
					if (alphabetCount >= 3) {
						validNama = true;
						nama = namaTemp;
						System.out.println("Nama karyawan valid.");
						break;
					}
				}
			}
			
			if (alphabetCount < 3) System.out.println("Nama karyawan tidak valid, coba lagi.");
		}
		
		while (!validKelamin) {
			System.out.println("Input jenis kelamin karyawan [\"Laki-laki\" | \"Perempuan\"] (Case Sensitive): ");
			String kelaminTemp = scan.nextLine();
			
			if (kelaminTemp.equals("Laki-laki")) {
				System.out.println("Jenis kelamin valid.");
				kelamin = "Laki-laki";
				validKelamin = true;
			} else if (kelaminTemp.equals("Perempuan")) {
				System.out.println("Jenis kelamin valid.");
				kelamin = "Perempuan";
				validKelamin = true;
			} else {
				System.out.println("Jenis kelamin tidak valid, coba lagi.");
			}
		}
		
		while (!validJabatan) {
			System.out.println("Input jabatan karyawan [\"Manager\" | \"Supervisor\" | \"Admin\"] (Case Sensitive): ");
			String jabatanTemp = scan.nextLine();
			
			if (jabatanTemp.equals("Manager")) {
				System.out.println("Jabatan valid.");
				jabatan = "Manager";
				gaji = 8000000;
				validJabatan = true;
			} else if (jabatanTemp.equals("Supervisor")) {
				System.out.println("Jabatan valid.");
				jabatan = "Supervisor";
				gaji = 6000000;
				validJabatan = true;
			} else if (jabatanTemp.equals("Admin")){
				System.out.println("Jabatan valid.");
				jabatan = "Admin";
				gaji = 4000000;
				validJabatan = true;
			} else {
				System.out.println("Jabatan tidak valid, coba lagi.");
			}
		}
		
		Employee employee = employees.get(idx-1);
		employee.setGaji(gaji);
		employee.setId(kode);
		employee.setJabatan(jabatan);
		employee.setJenisKelamin(kelamin);
		employee.setNama(nama);
		System.out.printf("Karyawan telah diupdate menjadi \"%s dengan id %s\"\n", nama, kode);
	}
	
	public void deleteKaryawan() {
		viewTable();
		System.out.println("Pilih no karyawan yang ingin dihapus: ");
		int idx = scan.nextInt(); scan.nextLine();
		
		if (idx <= 0 || idx > Employee.employeeCount) {
			System.out.println("No. karyawan tidak valid, coba lagi.");
			return;
		}
		
		System.out.printf("Karyawan \"%s\" telah berhasil dihapus.\n", employees.get(idx-1).getNama());
		employees.remove(idx-1);
		Employee.employeeCount--;
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
