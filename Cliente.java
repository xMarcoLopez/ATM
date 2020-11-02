
public class Cliente {

	public String cedula;
	public String nombre;
	public double saldo;

	// Constructores
	public Cliente(String cedula, String nombre, double saldo) {
		this.cedula = cedula;
		this.nombre = nombre;
		this.saldo = saldo;
	}

	public Cliente(String cedula) {
		this.cedula = cedula;
	}

	// Getters y setters
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	// Métodos
	public void restarSaldo(double saldo, double valorResta) {
		this.saldo = saldo - valorResta;
	}

	public void sumarSaldo(double saldo, double valorSumar) {
		this.saldo = saldo + valorSumar;
	}

	public boolean iguales(Cliente cliente) {
		if (this.cedula.trim().equals(cliente.getCedula().trim())) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "\nCédula: " + cedula + "\nNombre: " + nombre + "\nSaldo: " + saldo;
	}
}
