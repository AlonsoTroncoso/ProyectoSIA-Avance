package administrarcontratos;


//la idea de este exception seria meterlo en cada ventana que utilice una busqueda de clientes
// Excepción personalizada para clientes no encontrados
public class NoClienteException extends Exception {
    private String rut;

    public NoClienteException(String rut) {
        super("Cliente no encontrado con RUT: " + rut); // mensaje base
        this.rut = rut;
    }

    @Override
    public String getMessage() {
        // Sobreescribimos getMessage() para mostrar un mensaje más claro
        return " Error: No se encontró el cliente con RUT [" + rut + "]";
    }
}
