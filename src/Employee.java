
public class Employee {
	public String id, nama, jenisKelamin, jabatan;
	public int gaji;
	
	static int employeeCount;
	
	public Employee(String id, String nama, String jenisKelamin, String jabatan, int gaji) {
		super();
		this.id = id;
		this.nama = nama;
		this.jenisKelamin = jenisKelamin;
		this.jabatan = jabatan;
		this.gaji = gaji;
		employeeCount++;
	}

}
