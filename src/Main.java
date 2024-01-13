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
			// Untuk validasi kode karyawan kita bakal gunakan regex (MM-XXXX) --> "[A-Za-z]+-[0-9]+"
			Pattern pattern = Pattern.compile("^[A-Za-z]{2}-[0-9]{4}$", Pattern.CASE_INSENSITIVE);
			/* Menerima hanya string (case insensitive) yang dimulai 2 huruf "MM", 
			 * diikuti simbol '-',
			 * dan diakhiri 4 angka numeric "XXXX"
			 */
			Matcher matcher = pattern.matcher(kodeTemp);
			if (matcher.matches()) { // Kalau sesuai pola kode karyawan...
				
			} else {

			}
		}
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
