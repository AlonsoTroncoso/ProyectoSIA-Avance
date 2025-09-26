package administrarcontratos;

// Excepción personalizada para planes no encontrados
//lo mismo que en clientes pero de planes, tambien se deberia usar para cada menu que use busqueda de plan
public class NoPlanException extends Exception {
    private int idPlan;

    public NoPlanException(int idPlan) {
        super("Plan no encontrado con ID: " + idPlan); // mensaje base
        this.idPlan = idPlan;
    }

    @Override
    public String getMessage() {
        // Sobreescribimos getMessage() para dar más detalle
        return "Error: No se encontró el plan con ID [" + idPlan + "]";
    }
}
