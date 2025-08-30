


package administrarcontrato;

import java.util.*;

public class Cliente {
   private String nombre;
   private int edad;
   private String rut; 
   private ArrayList<Contrato> contratos;
   private HashMap<Integer,Contrato> mapaContratos; //corresponde a mapa anidado
   
   public Cliente(String nombre, int edad,String rut){
       this.nombre = nombre;
       this.edad = edad;
       this.rut = rut;
       this.contratos = new ArrayList<>();
       this.mapaContratos = new HashMap<>();
   }
   
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public String getRut() {
        return rut;
    }
    
    public void setRut(String rut) {
        this.rut = rut;
    }
    
    public ArrayList<Contrato> getContratos() {
        return contratos;
    }
    
    public void setContratos(ArrayList<Contrato> contratos) {
        this.contratos = contratos;
    }
    
    public HashMap<Integer, Contrato> getMapaContratos() {
        return mapaContratos;
    }
    
    public void setMapaContratos(HashMap<Integer, Contrato> mapaContratos) {
        this.mapaContratos = mapaContratos;
    }   

    public void agregarContrato(Contrato contratoNuevo){

        if (!mapaContratos.containsKey(contratoNuevo.getIdContrato())){
            contratos.add(contratoNuevo); 
            mapaContratos.put(contratoNuevo.getIdContrato(), contratoNuevo); 
        }
        
        else
            System.out.println("El contrato ya existe");

    }
    
    public Contrato getContratoPorId(int IdContrato){
        return mapaContratos.get(IdContrato);
    }
    
    public void actualizarDatos(String nuevoNombre){
        nombre = nuevoNombre;
    }
    
    public void actualizarDatos(String nuevoNombre, int nuevaEdad){
        nombre = nuevoNombre;
        edad = nuevaEdad;
    }
    
    public void actualizarDatos(String nuevoNombre, int nuevaEdad, String nuevoRut){
        nombre = nuevoNombre;
        edad = nuevaEdad;
        rut = nuevoRut;
    }
    
    
    
    public void mostrarCliente(){
        System.out.println("Cliente: "+ nombre + "\nEdad:"+ edad +"\nRut: " + rut);
        
        if (!contratos.isEmpty()){
            System.out.println("Contratos:");
       
            for (int i = 0; i< contratos.size(); i++){
            contratos.get(i).mostrarContrato(); 
            }
        }
        else{
            System.out.println("El cliente NO presenta contratos");
        }
    }
    public void mostrarCliente(boolean mostrarContratos) {
       System.out.println("Cliente: " + nombre + "\nEdad:" + edad +"\nRut:" + rut);
       
       if(mostrarContratos) {
           if(!contratos.isEmpty()) {
               System.out.println("Contratos: ");
               for(int i = 0; i< contratos.size();i++) {
                   Contrato c = contratos.get(i);
                   c.mostrarContrato();
               }
           }
           else
               System.out.println("El cliente NO registra contratos");
       }
       
   }
}
