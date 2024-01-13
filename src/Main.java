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
				
			} else if (choice == 3) {
				
			} else if (choice == 4) {
				
			} else if (choice == 5) {
				System.out.println("Program akan exit, terima kasih!");
				System.exit(0);
			} else {
				System.out.println("Input salah, coba lagi.");
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
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
