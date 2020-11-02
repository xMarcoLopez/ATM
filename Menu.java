import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	Scanner sn = new Scanner(System.in);

	Banco banco = new Banco(10);

	String cedula;
	String cedulaDestino;
	String nombre;
	double saldo;
	double valorConsignacion;
	double valorTransferencia;

	Cliente cliente;

	public void ejecutarMenu() {

		boolean continuar = true;
		int opcion;

		while (continuar) {

			listarOpciones();
			opcion = sn.nextInt();

			try {

				if (opcion == 1) {

					sn.nextLine();

					boolean cedulaInvalida = true;
					while (cedulaInvalida) {

						System.out.println("Ingrese la cédula:");
						cedula = sn.nextLine();

						if (validarCedula(cedula)) {

							if (!banco.validarCedula(cedula)) {
								boolean nombreInvalido = true;
								while (nombreInvalido) {
									System.out.println("Ingrese el nombre:");
									nombre = sn.nextLine();

									if (validarNombre(nombre)) {

										boolean saldoInvalido = true;
										while (saldoInvalido) {
											System.out.println("Ingrese un valor para el saldo:");
											saldo = sn.nextDouble();

											if (saldo > 0) {
												cliente = new Cliente(cedula, nombre, saldo);
												banco.agregarCliente(cliente);
												nombreInvalido = false;
												cedulaInvalida = false;
												saldoInvalido = false;
											} else {
												System.out.println("Error, no se permiten valores negativos");
											}
										}
									} else {
										System.out.println("Error, nombre invalido");
									}
								}
							} else {
								System.out.println("Error, ya existe un cliente asociado a esa cédula");
							}
						} else {
							System.out.println("Error, la cédula debe tener de 6 a 10 dígitos");
						}
					}

				} else if (opcion == 2) {

					sn.nextLine();

					System.out.println("Ingrese la cédula del cliente al que desea consignar:");
					cedula = sn.nextLine();

					if (banco.validarCedula(cedula)) {
						System.out.println("Ingrese el saldo a consignar:");
						valorConsignacion = sn.nextDouble();

						banco.consignacion(cedula, valorConsignacion);
					} else {
						System.out.println("Error, no existe un cliente asociado a esa cédula");
					}

				} else if (opcion == 3) {

					sn.nextLine();

					System.out.println("Ingrese la cédula del cliente que desea realizar la transferencia:");
					cedula = sn.nextLine();

					if (banco.validarCedula(cedula)) {

						Cliente clienteQueTransfiere = new Cliente(cedula);

						System.out.println("Ingrese la cédula del cliente al que desea transferir:");
						cedulaDestino = sn.nextLine();

						if (banco.validarCedula(cedulaDestino)) {

							Cliente clienteDestino = new Cliente(cedulaDestino);

							if (cedula.equals(cedulaDestino)) {
								System.out.println("Error, no se puede transferir a la misma cuenta");
							} else {
								System.out.println("Ingrese el saldo a transferir");
								valorTransferencia = sn.nextDouble();

								banco.transferencia(clienteQueTransfiere, clienteDestino, valorTransferencia);
							}

						} else {
							System.out.println("Error, no existe un cliente asociado a esa cédula");
						}

					} else {
						System.out.println("Error, no existe un cliente asociado a esa cédula");
					}

				} else if (opcion == 4) {

					sn.nextLine();
					boolean clientePerdido = true;
					while(clientePerdido) {
						System.out.println("Ingrese la cédula de un cliente:");
						cedula = sn.nextLine();
						if(banco.validarCedula(cedula)) {
							banco.mostrarSaldo(cedula);
							clientePerdido = false;
						} else {
							System.out.println("No existe un cliente asociado a esa cédula");
						}
					}

				} else if (opcion == 5) {

					System.out.println("\nListando clientes...\n");
					banco.listarClientes();

				} else if (opcion == 6) {

					sn.nextLine();

					System.out.println("Ingrese la cédula de un cliente:");
					cedula = sn.nextLine();

					if (banco.validarCedula(cedula)) {
						cliente = new Cliente(cedula);
						banco.eliminarCliente(cliente);
					} else {
						System.out.println("Error, no existe un cliente asociado a esa cédula");
					}

				} else if (opcion == 7) {
					continuar = false;
					System.out.println("Ha salido del programa");
				} else {
					System.out.println("Opción no válida");
				}
			} catch (InputMismatchException ex) {
				System.out.println("Error, solo se permiten valores numéricos");
				sn.next();
				continuar = true;
			}
		}
	}

	public void listarOpciones() {
		System.out.println("\nOpciones del menú");
		System.out.println("---------------------------------");
		System.out.println("1. Registrar cliente");
		System.out.println("2. Realizar consignación");
		System.out.println("3. Transferencia de saldo");
		System.out.println("4. Mostrar saldo de un cliente");
		System.out.println("5. Mostrar todos los clientes");
		System.out.println("6. Eliminar cliente");
		System.out.println("7. Salir");
		System.out.println("---------------------------------");
		System.out.print("\nIngrese una opción:");
	}

	public boolean validarCedula(String cadena) {
		if (cadena.matches("[0-9]{6,10}")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validarNombre(String cadena) {
		if (cadena.matches("([a-zA-ZñÑáéíóúÁÉÍÓÚ]*[ ][a-zA-ZñÑáéíóúÁÉÍÓÚ]*){1,4}")) {
			return true;
		} else {
			return false;
		}
	}
}
