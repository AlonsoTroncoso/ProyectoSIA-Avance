
package administrarcontratos;

import java.io.*;
import java.util.*;
import javax.swing.SwingUtilities;

// ------------------- CLASE PRINCIPAL ------------------- //
public class administrarContratos {
    
    private static final String RUTA_CLIENTES = "data/clientes.csv";
    private static final String RUTA_PLANES = "data/planes.csv";
    private static final String RUTA_CONTRATOS = "data/contratos.csv";
    
    // Estructuras principales de almacenamiento  
    static ArrayList<Cliente> clientes = new ArrayList(); // Lista de clientes
    static HashMap<String, Cliente> clientesPorRut = new HashMap(); // Búsqueda rápida por rut
    static ArrayList<Plan> planesDisponibles = new ArrayList(); // Lista de planes disponibles 
    
    
    //Para generar el reporte de datos de clientes, planes, contratos
    public static void generarReporteTabulado() {
        try {
            File folder = new File("data");
            if (!folder.exists()) 
                folder.mkdir();
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/reporte.txt"));
            
            // === PLANES ===
            bw.write("=== PLANES ===\n");
            bw.write(String.format("%-5s %-12s %-10s %-10s\n", "ID", "TIPO", "DURACION", "PRECIO"));
            bw.write("--------------------------------------\n");
            
            if (planesDisponibles.isEmpty()) 
                bw.write("No hay planes registrados.\n");
            
            else {
                for (Plan p : planesDisponibles) {
                    bw.write(String.format("%-5d %-12s %-10s $%-10d\n",
                            p.getIdPlan(), p.getTipoPlan(), p.getDuracion(), p.getPrecio()));
                }
            }
            bw.write("\n");
            
            // === CLIENTES ===
            bw.write("=== CLIENTES ===\n");
            bw.write(String.format("%-20s %-5s %-12s\n", "NOMBRE", "EDAD", "RUT"));
            bw.write("--------------------------------------\n");
            
            if (clientes.isEmpty()) 
            bw.write("No hay clientes registrados.\n\n");
            
            else {
                for (Cliente c : clientes) {
                    bw.write(String.format("%-20s %-5d %-12s\n", c.getNombre(), c.getEdad(), c.getRut()));
                    
                    if (!c.getContratos().isEmpty()) {
                        bw.write("  Contratos:\n");
                        bw.write(String.format("  %-5s %-10s %-5s %-12s %-15s\n", "ID", "ESTADO", "PLAN", "TIPO PLAN", "PRODUCTO"));
                        bw.write("  -------------------------------------------------\n");
                        
                        for (Contrato cont : c.getContratos()) {
                            bw.write(String.format("  %-5d %-10s %-5d %-12s %-15s (%s)\n",
                                    cont.getIdContrato(),
                                    cont.getEstado(),
                                    cont.getPlan().getIdPlan(),
                                    cont.getPlan().getTipoPlan(),
                                    cont.getProducto().getMarcaCelular(),
                                    cont.getProducto().getNumeroCelular()));
                        }
                        bw.write("\n");
                    }
                }
            }
            bw.close();
            System.out.println("Reporte tabulado generado correctamente en data/reporte.txt");
        } catch (IOException e) {
            System.out.println("Error al generar el reporte: " + e.getMessage());
        }
    }
    
    /*public static void datosEjemplo(){
        
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

    }*/
    
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
    public static void main(String[] args) {
        cargarDatos(); // carga CSV al inicio
        SwingUtilities.invokeLater(() -> {
        VentanaPrincipal ventana = new VentanaPrincipal();
        ventana.mostrar();
        });

    // Puedes guardar datos al cerrar con un shutdown hook
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        guardarDatos();
    }));
}

    
    // --------------- CARGAR DATOS -------------------------
    public static void cargarDatos() {
        cargarClientes();
        cargarPlanes();
        cargarContratos();
    }

    public static void cargarClientes() {
        File file = new File(RUTA_CLIENTES);
        if (!file.exists()) 
            return; // si no existe, nada que cargar
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nombre = partes[0];
                    int edad = Integer.parseInt(partes[1]);
                    String rut = partes[2];

                    Cliente cliente = new Cliente(nombre, edad, rut);
                    clientes.add(cliente);
                    clientesPorRut.put(rut, cliente);
                }
            }
            System.out.println("Clientes cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar clientes: " + e.getMessage());
        }
    }
    
    public static void cargarPlanes() {
        File file = new File(RUTA_PLANES);
        if (!file.exists())
            return; // si no existe, nada que cargar

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0]);
                    String tipo = partes[1];
                    String duracion = partes[2];
                    int precio = Integer.parseInt(partes[3]);

                    Plan plan = new Plan(id, tipo, duracion, precio);
                    planesDisponibles.add(plan);
                }
            }
            System.out.println("Planes cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar planes: " + e.getMessage());
        }
    }
    
    public static void cargarContratos() {
        File file = new File(RUTA_CONTRATOS);
        if (!file.exists()) 
            return; // si no existe, nada que cargar
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 6) {
                    int idContrato = Integer.parseInt(partes[0]);
                    String rutCliente = partes[1];
                    int idPlan = Integer.parseInt(partes[2]);
                    String marca = partes[3];
                    String numero = partes[4];
                    String estado = partes[5];
                    // Crear contrato si existe el cliente y el plan
                    insertarContrato(rutCliente, idContrato, idPlan, marca, numero);
                    // Cambiar el estado si es distinto de "Vigente" (ya que insertarContrato pone "Vigente")
                    cambiarEstadoContrato(rutCliente, idContrato, estado);
                }
            }
            System.out.println("Contratos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar contratos: " + e.getMessage());
        }
    }

    // ---------------------GUARDAR DATOS-------------------
    public static void guardarDatos() {
        guardarClientes();
        guardarPlanes();
        guardarContratos();
    }
    
    public static void guardarClientes() {
        try {
            File folder = new File("data");
            if (!folder.exists()) 
                folder.mkdir();

            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_CLIENTES));
            for (Cliente c : clientes) {
                // Formato: nombre,edad,rut
                bw.write(c.getNombre() + "," + c.getEdad() + "," + c.getRut());
                bw.newLine();
            }
            bw.close();
            System.out.println("Clientes guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }
    }

    
    public static void guardarPlanes() {
        try {
            File folder = new File("data");
            if (!folder.exists()) folder.mkdir();
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_PLANES));
            for (Plan p : planesDisponibles) {
                // Formato: id,tipo,duracion,precio
                bw.write(p.getIdPlan() + "," + p.getTipoPlan() + "," + p.getDuracion() + "," + p.getPrecio());
                bw.newLine();
            }
            bw.close();
            System.out.println("Planes guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar planes: " + e.getMessage());
        }
    }

    
    public static void guardarContratos() {
        try {
            File folder = new File("data");
            if (!folder.exists()) 
                folder.mkdir();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_CONTRATOS));
            for (Cliente c : clientes) {
                for (Contrato cont : c.getContratos()) {
                    // Formato: idContrato,rutCliente,idPlan,marca,numero,estado
                    bw.write(cont.getIdContrato() + "," + c.getRut() + "," +
                            cont.getPlan().getIdPlan() + "," +
                            cont.getProducto().getMarcaCelular() + "," +
                            cont.getProducto().getNumeroCelular() + "," +
                            cont.getEstado());
                    bw.newLine();
                }
            }
            bw.close();
            System.out.println("Contratos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar contratos: " + e.getMessage());
        }
    }
}

