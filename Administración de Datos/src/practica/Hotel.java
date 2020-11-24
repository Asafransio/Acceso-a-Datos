package practica;

public class Hotel {
	public static void main(String[] args) {
		int id = ClientePersistencia.createCliente("María José", "Martínez", "mjmartinez@grupostudium.com", "12345678Z", "Studium2020");
		System.out.println("se ha creado:" + ClientePersistencia.readCliente(id, "apellidos"));
 
		ClientePersistencia.updateCliente(id, "apellidos", "Martínez Navas");
		System.out.println("se ha modificado:" + ClientePersistencia.readCliente(id, "apellidos"));
 
		System.out.println("se ha borrado:" + ClientePersistencia.readCliente(id, "apellidos"));
		ClientePersistencia.deleteCliente(id);
		}
}
