
package administrarcontratos;

import java.io.*;
import java.util.*;
import javax.swing.SwingUtilities;

// ------------------- CLASE PRINCIPAL ------------------- //
public class administrarContratos { 
// Estructuras principales de almacenamiento  
    static ArrayList<Cliente> clientes = new ArrayList(); // Lista de clientes
    static HashMap<String, Cliente> clientesPorRut = new HashMap(); // Búsqueda rápida por rut
    static ArrayList<Plan> planesDisponibles = new ArrayList(); // Lista de planes disponibles 
            
    public static void datosEjemplo(){
        
        Plan testPlan1 = new Plan(1,"Prepago", "30 días", 9990);
        Plan testPlan2 = new Plan(2,"Postpago", "12 meses", 15990);
        Plan testPlan3 = new Plan(3,"Prepago", "24 meses", 30000);

        Producto testProdu1 = new Producto("Samsung", "+56911111111");
        Producto testProdu2 = new Producto("Xiaomi", "+56922222222");
        Producto testProdu3 = new Producto("Huawei", "+56925468716");

        Cliente testCliente1 = new Cliente("Ana Reyes", 28, "19.345.678-9");
        Cliente testCliente2 = new Cliente("Luis Lopez", 34, "17.876.543-2");
        Cliente testCliente3 = new Cliente("Mario perez", 38, "14.256.531-4");

        clientes.add(testCliente1);
        clientes.add(testCliente2);
        clientes.add(testCliente3);
        
        clientesPorRut.put(testCliente1.getRut(), testCliente1);
        clientesPorRut.put(testCliente2.getRut(), testCliente2);
        clientesPorRut.put(testCliente3.getRut(), testCliente3);
        
        planesDisponibles.add(testPlan1);
        planesDisponibles.add(testPlan2);
        planesDisponibles.add(testPlan3);

        
        Contrato testContrato1 = new Contrato(1001, testPlan1, testProdu1, "Vigente");
        testCliente1.agregarContrato(testContrato1);
        
        Contrato testContrato2 = new Contrato(1002, testPlan2, testProdu2, "Suspendido");
        testCliente2.agregarContrato(testContrato2);
        
        Contrato testContrato3 = new Contrato(1003, testPlan3, testProdu3, "Vigente");
        testCliente3.agregarContrato(testContrato3);

    }
    
    // ================= FUNCIONES PARA CLIENTES ================= //
    
    public static boolean insertarCliente(String nombreNuevo, int edadNueva, String rutNuevo) throws IOException{
        
        if (clientesPorRut.containsKey(rutNuevo)){
            return false;
        }
    
        Cliente clienteNuevo = new Cliente(nombreNuevo,edadNueva,rutNuevo);
        clientes.add(clienteNuevo);
        clientesPorRut.put(clienteNuevo.getRut(),clienteNuevo);
        return true;
    }
    
    //los listar es bastante comodo usarlo que retorne string, así retorno el texto altiro que quiero que muestre
    //y al menos siento que se ve mas comodo y mas limpio
    
    public static String listarClientes(boolean mostrarContratos) {
        if (clientes.isEmpty()) {
            return "No hay clientes registrados.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE CLIENTES ===\n");

        for (Cliente c : clientes) {
            sb.append("Nombre: ").append(c.getNombre())
              .append(", Edad: ").append(c.getEdad())
              .append(", RUT: ").append(c.getRut()).append("\n");

             // Creao un StringBuilder temporal para los contratos, esto podia ser con el tipico "i" 
             //pero ya que estamos usando este tipo de ciclo, mejor ser consistente
                StringBuilder contratosStr = new StringBuilder();
            if (mostrarContratos && !c.getContratos().isEmpty()) {
                sb.append("  Contratos:\n");

                for (Contrato cont : c.getContratos()) {
                    if (contratosStr.length() > 0){ //o sea que si el actual tiene 1 contrato o mas
                        contratosStr.append(" | "); //es como en lo que finaliza, y como son varios contratos esto los separa
                    }
                    contratosStr.append("(").append(cont.getIdContrato())
                               .append(",").append(cont.getEstado()).append(")");
                }

                sb.append("   - ").append(contratosStr.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    
    public static boolean eliminarClientes(String rut) throws NoClienteException{
   
        if (clientesPorRut.containsKey(rut)){
            Cliente clienteEliminar = clientesPorRut.get(rut);
            clientes.remove(clienteEliminar);
            clientesPorRut.remove(rut);
            return true;
            }
        throw new NoClienteException(rut);
    }

    public static boolean actualizarCliente(String rut, String nuevoNombre, int nuevaEdad) throws NoClienteException{
        Cliente cliente = clientesPorRut.get(rut);
        if (cliente == null) {
            throw new NoClienteException(rut);
        }
        cliente.actualizarDatos(nuevoNombre, nuevaEdad);
        return true;
    }

    // ================= FUNCIONES PARA PLANES ================= //

    public static boolean insertarPlan(int id, String tipo, String duracion, int precio) {
        // Verificar ID repetido
        for (Plan p : planesDisponibles) {
            if (p.getIdPlan() == id) {
                return false;
            }
        }
        Plan nuevoPlan = new Plan(id, tipo, duracion, precio);
        planesDisponibles.add(nuevoPlan);
        return true; 
    }
    
    public static String listarPlanes() {
        if (planesDisponibles.isEmpty()) {
            return "No hay planes registrados.";
        }

        //el stringbuilder es como la forma que utilza la ventana para mostrar mensajes, es el printf
        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE PLANES ===\n");

        for (Plan p : planesDisponibles) {
            sb.append("ID: ").append(p.getIdPlan())
              .append(", Tipo: ").append(p.getTipoPlan())
              .append(", Duración: ").append(p.getDuracion())
              .append(", Precio: ").append(p.getPrecio())
              .append("\n");
            }
            return sb.toString();
    }
    
    public static boolean eliminarPlan(int id) throws NoPlanException {
    for (int i = 0; i < planesDisponibles.size(); i++) {
        if (planesDisponibles.get(i).getIdPlan() == id) {
            planesDisponibles.remove(i);
            return true;
            }
        }
        throw new NoPlanException(id); 
    }
    
    public static boolean actualizarPlan(int id, String nuevoTipo, String nuevaDuracion, int nuevoPrecio) throws NoPlanException {
        for (Plan p : planesDisponibles) { 
            if (p.getIdPlan() == id) { //se encuentra y se hace todo lo bacan
                p.setTipoPlan(nuevoTipo);
                p.setDuracion(nuevaDuracion);
                p.setPrecio(nuevoPrecio);
                return true;
                }
            }
        throw new NoPlanException(id); // no se encontró
    }

    
    // ================= FUNCIONES PARA CONTRATOS ================= //
    
    public static boolean insertarContrato(String rutCliente, int idContrato, int idPlan, String marca, String numero) {
    Cliente cliente = clientesPorRut.get(rutCliente);
    if (cliente == null) {
        return false; 
    }

    // buscar plan que le quieres dar al contrato y producto, despues de todo un producto siempre esta linkeado a su contrato
    // elegido por un tema de logica, de que me sirve tener un telefono que no tiene contrato en el sistema
    Plan planElegido = null;
    for (Plan p : planesDisponibles) {
        if (p.getIdPlan() == idPlan) {
            planElegido = p;
            break;
        }
    }
    if (planElegido == null) {
        return false;
    }
    //por lo mencionado anteriormentes es porque se crea el producto en la misma funcion
    Producto producto = new Producto(marca, numero);
    Contrato nuevoContrato = new Contrato(idContrato, planElegido, producto, "Vigente");

    cliente.agregarContrato(nuevoContrato);
    return true; 
    }

    //lo mismo con los listar de clientes y de planes, en string
    public static String listarContratos() {
    if (clientes.isEmpty()) {
        return "No hay clientes registrados.";
    }

    StringBuilder sb = new StringBuilder();
    sb.append("=== LISTA DE CONTRATOS ===\n");

    boolean existen = false;
    for (Cliente c : clientes) {
        if (!c.getContratos().isEmpty()) {
            //aqui no hay ni un problema todo es de facil accesos
            sb.append("Cliente: ").append(c.getNombre()).append(" (").append(c.getRut()).append(")\n");
            for (Contrato cont : c.getContratos()) {
                //aqui se complica un poco, tengo que hacer gets de gets ya que tengo clases dentro de contrato
                //entonces le hago get a producto y a plan
                sb.append("   - Contrato ID: ").append(cont.getIdContrato()).append("\n");
                sb.append("     Estado: ").append(cont.getEstado()).append("\n");
                sb.append("     Plan: (").append(cont.getPlan().getIdPlan()).append(", ").append(cont.getPlan().getTipoPlan()).append(")\n");
                sb.append("     Producto: (").append(cont.getProducto().getMarcaCelular()).append(", ").append(cont.getProducto().getNumeroCelular()).append(")\n");
                sb.append("\n");
            }
            existen = true;
        }
    }

    if (!existen) {
        return "No hay clientes con contratos registrados.";
    }
    return sb.toString();
    }

    
    public static boolean eliminarContrato(String rutCliente, int idContrato) {
        Cliente cliente = clientesPorRut.get(rutCliente);
        if (cliente == null) {
            return false; 
        }

        Contrato contratoEliminar = cliente.getMapaContratos().get(idContrato);
        if (contratoEliminar == null) {
            return false; 
        }

        cliente.getContratos().remove(contratoEliminar);
        cliente.getMapaContratos().remove(idContrato);
        return true; 
        }

        public static boolean actualizarContrato(String rutCliente, int idContrato, int nuevoIdPlan, String nuevaMarca, String nuevoNumero, String nuevoEstado) {
        Cliente cliente = clientesPorRut.get(rutCliente);
        if (cliente == null) {
            return false; 
        }

        Contrato contrato = cliente.getMapaContratos().get(idContrato);
        if (contrato == null) {
            return false; 
        }

        // Buscar el nuevo plan
        Plan planElegido = null;
        for (Plan p : planesDisponibles) {
            if (p.getIdPlan() == nuevoIdPlan) {
                planElegido = p;
                break;
            }
        }
        if (planElegido == null) {
            return false;
        }

        // Actualizar datos
        contrato.setPlan(planElegido);
        contrato.getProducto().setMarcaCelular(nuevaMarca);
        contrato.getProducto().setNumeroCelular(nuevoNumero);
        contrato.setEstado(nuevoEstado);

        return true;
    }
        
    public static boolean cambiarEstadoContrato(String rutCliente, int idContrato, String nuevoEstado) {
        Cliente cliente = clientesPorRut.get(rutCliente);
        if (cliente == null) {
            return false; // no existe cliente
        }

        Contrato contrato = cliente.getMapaContratos().get(idContrato);
        if (contrato == null) {
            return false; // no existe contrato
        }

        contrato.setEstado(nuevoEstado); // aquí solo cambiamos el estado
        return true;
    }

// FUNCION EXTRA DE UTILIDAD // 
    
    //La logica del codigo es bastante simple, entre todos los planes que tengo guardados busco el que mas plata junta
    //primero de todo recorre los planes, luego por cada plan recorre los clientes los cuales contienen los contratos
    //hay mucho for entre for pero es muy simple, despues va comparando la id del plan actual con el del contrato
    //si es el mismo se le suma al contador recaudado, al final de recorrerlos a todos compara el "recaudado" con el mayor
    //igual esta hecho pa que si no hay un mayor, el primero nomas es el que queda como mayor, es muy estructura de datos esta funcion
    //hice lo de siempre, que devuelva un string asi es mucho mas comodo en la ventana de planes
    //asi muestra el mensaje altiro

    public static String planMasRecaudador() {
        if (planesDisponibles.isEmpty() || clientes.isEmpty()) {
            return "No hay planes o clientes registrados.";
        }

        Plan planMayor = null;
        int montoMayor = 0;

        // recorrer todos los planes
        for (Plan plan : planesDisponibles) {
            int recaudado = 0;

            // recorrer todos los clientes y sus contratos
            for (Cliente c : clientes) {
                for (Contrato cont : c.getContratos()) {
                    if (cont.getPlan().getIdPlan() == plan.getIdPlan()) {
                        recaudado += cont.getPlan().getPrecio();
                    }
                }
            }

            // verificar si este plan es mayor que el que teníamos
            if (planMayor == null || recaudado > montoMayor) {
                planMayor = plan;
                montoMayor = recaudado;
            }
        }

        if (planMayor == null) {
            return "No se encontraron contratos asociados a los planes.";
        }

        return "El plan que más ha recaudado es:\n" +
               "ID: " + planMayor.getIdPlan() +
               ", Tipo: " + planMayor.getTipoPlan() +
               ", Duración: " + planMayor.getDuracion() +
               ", Precio: $" + planMayor.getPrecio() + "\n" +
               "Recaudación total: $" + montoMayor;
    }

    
// ------------------- MÉTODO MAIN -------------------    
    public static void main(String[] args) throws IOException {

    // Cargar datos iniciales de ejemplo
    datosEjemplo();
 // Crear lector para leer desde consola
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.mostrar();
        });
  }
}

