
package administrarcontrato;


public class Producto {
    private String marcaCelular;
    private String numeroCelular;
    
    public Producto(String marcaCelular, String numeroCelular) {
        this.marcaCelular = marcaCelular;
        this.numeroCelular = numeroCelular;
    }

    public String getMarcaCelular() {
        return marcaCelular;
    }

    public void setMarcaCelular(String marcaCelular) {
        this.marcaCelular = marcaCelular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }
    
    public void mostrarProducto(){
        System.out.println("Marca del producto: " + marcaCelular + "\nNumero del producto: " + numeroCelular);
    }
    
}