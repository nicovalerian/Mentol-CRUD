import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {
	static Scanner scan = new Scanner(System.in);
	ArrayList<Employee> employees = new ArrayList<>();
	
	public Main() {
		newKaryawan();
	}
	
	public void newKaryawan() {
		Boolean validKode = false, validNama = false, validKelamin = false, validJabatan = false, validGaji = false;
		String kode, nama, kelamin, jabatan;
		int gaji;
		
		while (!validKode) {
			System.out.println("Input kode karyawan: ");
			String kodeTemp = scan.nextLine();
			// Untuk validasi kode karyawan kita gunakan regex (MM-XXXX) --> "[A-Za-z]+-[0-9]+"
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
			System.out.println("Input nama karyawan: ");
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
			System.out.println("Input jenis kelamin karyawan (\"Laki-Laki\" / \"Perempuan\"): ");
			String kelaminTemp = scan.nextLine();
			
			if (kelaminTemp.equals("Laki-Laki")) {
				System.out.println("Nama valid.");
				kelamin = "Laki-Laki";
				validKelamin = true;
			} else if (kelaminTemp.equals("Perempuan")) {
				System.out.println("Nama valid.");
				kelamin = "Perempuan";
				validKelamin = true;
			} else {
				System.out.println("Jenis kelamin tidak valid, coba lagi.");
			}
		}

	}
	
	public static void main(String[] args) {
		new Main();
	}

}
