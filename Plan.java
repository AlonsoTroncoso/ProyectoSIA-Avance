
package administrarcontrato;


public class Plan {
    private int idPlan;
    private String tipoPlan;
    private String duracion;
    private int precio; // En CLP
    
    public Plan(int idPlan,String tipoPlan, String duracion, int precio) {
        this.idPlan = idPlan;
        this.tipoPlan = tipoPlan;
        this.duracion = duracion;
        this.precio = precio;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }
    
    public String getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(String tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
   
    public void mostrarPlan(){
        System.out.println("Tipo de plan: " + tipoPlan + "\nDuracion del plan: " + duracion + "\nPrecio del plan: " + precio);
    }
    
}
