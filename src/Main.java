import java.util.*;

public class Main {
	static Scanner scan = new Scanner(System.in);
	ArrayList<Employee> employees = new ArrayList<>();
	
	public Main() {
		newKaryawan();
	}
	
	public void newKaryawan() {
		Boolean validKode = false, validNama = false, validKelamin = false, validJabatan = false, validGaji = false;
		while (!validKode) {
			System.out.println("Input kode karyawan: ");
			String kode = scan.nextLine();
		}
		
	}
	
	public static void main(String[] args) {
		new Main();
	}

}
