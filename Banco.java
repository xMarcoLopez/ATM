
public class Banco {

	private Cliente[] clientes;

	// Constructores
	public Banco() {
		this.clientes = new Cliente[10];

	}

	public Banco(int size) {
		this.clientes = new Cliente[size];

		Cliente cliente0 = new Cliente("0123456789", "Rogelio López", 400000);

		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] == clientes[0]) {
				clientes[i] = cliente0;

			}
		}

		Cliente cliente1 = new Cliente("9876543210", "Patricio Villanueva", 800000);
		
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] == clientes[1]) {
				clientes[i] = cliente1;

			}
		}
	}

	// Métodos
	public void agregarCliente(Cliente cliente) {

		if (bancoLleno()) {
			System.out.println("No hay espacio para registrar el cliente");
		} else {
			boolean found = false;
			for (int i = 0; i < clientes.length && !found; i++) {
				if (clientes[i] == null) {
					clientes[i] = cliente;
					found = true;
				}
			}

			if (found) {
				System.out.println("El registro ha sido exitoso!");
			} else {
				System.out.println("No se pudo hacer el registro");
			}
		}
	}

	public boolean validarCedula(String cedula) {
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].getCedula().equals(cedula)) {
				return true;
			}
		}
		return false;
	}

	public void listarClientes() {
		if (espacioLibre() == clientes.length) {
			System.out.println("No hay clientes para mostrar");
		} else {
			for (int i = 0; i < clientes.length; i++) {
				if (clientes[i] != null) {
					System.out.println(clientes[i]);
				}
			}
		}
	}

	public int espacioLibre() {
		int contadorLibre = 0;
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] == null) {
				contadorLibre++;
			}
		}
		return contadorLibre;
	}

	public void mostrarSaldo(String cedula) {
		boolean encontrado = false;
		for (int i = 0; i < clientes.length && !encontrado; i++) {
			if (clientes[i] != null && clientes[i].getCedula().equals(cedula)) {
				System.out.println("El saldo del cliente es: " + clientes[i].getSaldo());
				encontrado = true;
			}
		}
		if (!encontrado) {
			System.out.println("No se ha encontrado el cliente");
		}
	}
	
	public Cliente encontrarCliente(Cliente cliente) {
		for(Cliente c: clientes) {
			if(c != null && c.iguales(cliente)) {
				cliente = c;
			}
		}		
		return cliente;
	}

	public void consignacion(String cedula, double valorConsignacion) {
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].getCedula().equals(cedula)) {

				if (valorConsignacion > 0) {

					clientes[i].sumarSaldo(clientes[i].getSaldo(), valorConsignacion);
					System.out.println("Consignación exitosa!");

				} else if (valorConsignacion <= 0) {
					System.out.println("El valor debe ser mayor a 0");
				}
			}
		}
	}
	
	public void transferencia(Cliente cliente, Cliente clienteDestino, double valorTransferencia) {
		
		cliente = encontrarCliente(cliente);
		if(cliente.getSaldo() >= valorTransferencia && valorTransferencia > 0) {
			clienteDestino = encontrarCliente(clienteDestino);
			clienteDestino.sumarSaldo(clienteDestino.getSaldo(), valorTransferencia);
			cliente.restarSaldo(cliente.getSaldo(), valorTransferencia);
			System.out.println("Transferencia exitosa!");
			System.out.println("Su saldo actual es: " + cliente.getSaldo());
		} else if(valorTransferencia > cliente.getSaldo()) {
			System.out.println("Saldo insuficiente");
		} else if(valorTransferencia < 0) {
			System.out.println("No se permiten valores negativos");
		} else {
			System.out.println("Error en la transacción");
		}
	}

	public boolean bancoLleno() {
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] == null) {
				return false;
			}
		}
		return true;
	}

	public void eliminarCliente(Cliente cliente) {
		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i] != null && clientes[i].iguales(cliente)) {
				clientes[i] = null;
				System.out.println("Se eliminó el cliente");
			}
		}
	}
}
