
package administrarcontrato;


public class Contrato {
    
    private int idContrato;
    private Plan plan;
    private Producto producto;
    private String estado;

    public Contrato(int idContrato, Plan plan, Producto producto, String estado) {
        this.idContrato = idContrato;
        this.plan = plan;
        this.producto = producto;
        this.estado = estado;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public void mostrarContrato(){
        System.out.println("ID del contrato: " + idContrato + "\nEstado del contrato: " + estado);
        plan.mostrarPlan();
        producto.mostrarProducto();
        
    }
}
