
package administrarcontrato;

import java.util.*;
import java.io.*;

public class AdministrarContrato {

    static ArrayList<Cliente> clientes = new ArrayList();
    static HashMap<String, Cliente> clientesPorRut = new HashMap();
    static ArrayList<Plan> planesDisponibles = new ArrayList(); 
            
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
    
    public static void insertarCliente(BufferedReader br) throws IOException{
        System.out.println("Insertando nuevo Cliente...\n");
        System.out.println("Ingrese nombre del cliente: \n");
        String nombreNuevo = br.readLine();
        System.out.println("Ingrese edad del cliente: \n");
        int edadNueva = Integer.parseInt(br.readLine());
        System.out.println("Ingrese rut del cliente(11.111.111-1): \n");
        String rutNuevo = br.readLine();

        if (clientesPorRut.containsKey(rutNuevo)){
            System.out.println("Este cliente ya existe, cancelando insercion...\n");
            return;
        }
    
        Cliente clienteNuevo = new Cliente(nombreNuevo,edadNueva,rutNuevo);
        clientes.add(clienteNuevo);
        clientesPorRut.put(clienteNuevo.getRut(),clienteNuevo);
        System.out.println("Insercion de cliente ("+ clienteNuevo.getRut() + ") finalizada!\n");
    }
    
    static void listarClientes(
            ArrayList<Cliente> clientes,
            BufferedReader lector
    ) throws IOException {
        
        if(clientes.isEmpty()) 
            System.out.println("No hay clientes registrados!");

        else {
            System.out.println("====== LISTADO DE CLIENTES ======");
            System.out.println("\nMostrar tambien aquellos con contrato? (s/n):");
            String respuesta = lector.readLine();
            boolean mostrarContratos = respuesta.equalsIgnoreCase("S");
            
            for(Cliente c : clientes) {
                c.mostrarCliente(mostrarContratos);
                System.out.println("============");
            }            
        }
    }
    
    static void eliminarClientes(
            ArrayList<Cliente> clientes,
            HashMap<String, Cliente> clientesPorRut,
            BufferedReader lector
    ) throws IOException {
        
        System.out.println("Ingresa el rut a eliminar:");
        String rut = lector.readLine();
        
        if(!clientesPorRut.containsKey(rut)) {
            System.out.println("No se encuentra un cliente con ese rut");
            return;
        }
        
        Cliente clienteEliminar = clientesPorRut.get(rut);
        clientes.remove(clienteEliminar);
        
        clientesPorRut.remove(clienteEliminar.getRut());
        
        System.out.println("Cliente eliminado con exito: " +clienteEliminar.getNombre());
    }
    
    static void actualizarCliente(
            HashMap<String, Cliente> clientesPorRut,
            BufferedReader lector
    ) throws IOException {
        
        System.out.println("Ingresa el rut del cliente para actualizar sus datos: ");
        String rutIngresado = lector.readLine();
        Cliente cliente = clientesPorRut.get(rutIngresado);
        
        if(cliente == null) 
            System.out.println("No existe un cliente con ese rut");
        
        System.out.println("Cliente encontrado: " + cliente.getNombre() + " (Edad: " + cliente.getEdad() + ")");
        System.out.println("Que desea actualizar?");
        System.out.println("1. Solo nombre");
        System.out.println("2. Nombre y edad");
        System.out.println("3. Nombre, edad y rut");
    
        int opcion = Integer.parseInt(lector.readLine());

        switch(opcion) {
            
            case 1:
                System.out.println("Ingrese el nuevo nombre:");
                String nuevoNombre = lector.readLine();
                cliente.actualizarDatos(nuevoNombre);
                System.out.println("Nombre actualizado correctamente.");
                break;
                
            case 2:
                System.out.println("Ingrese el nuevo nombre:");
                nuevoNombre = lector.readLine();
                System.out.println("Ingrese la nueva edad:");
                int nuevaEdad = Integer.parseInt(lector.readLine());
                cliente.actualizarDatos(nuevoNombre, nuevaEdad);
                System.out.println("Datos actualizados correctamente.");
                break;

            case 3:
                System.out.println("Ingrese el nuevo nombre:");
                nuevoNombre = lector.readLine();
                System.out.println("Ingrese la nueva edad:");
                nuevaEdad = Integer.parseInt(lector.readLine());
                System.out.println("Ingrese el nuevo rut:");
                String nuevoRut = lector.readLine();
                clientesPorRut.remove(cliente.getRut()); 
                cliente.actualizarDatos(nuevoNombre, nuevaEdad, nuevoRut);
                clientesPorRut.put(nuevoRut, cliente);
                System.out.println("Datos actualizados correctamente (incluyendo rut).");
                break;

            default:
                System.out.println("Opción inválida.");
        }
    }
    
    public static void insertarPlan(BufferedReader br) throws IOException {
        
        boolean idRepetido;
        int nuevoId;
        do {
            idRepetido = false;
            System.out.println("Ingrese id para el plan:\n");        
            nuevoId = Integer.parseInt(br.readLine());
            
            for(Plan p : planesDisponibles) {
                if(p.getIdPlan() == nuevoId) {
                    System.out.println("Este id ya existe...");
                    idRepetido = true;
                    break;
                }
            }
        } while(idRepetido);
        
        System.out.println("Ingrese tipo de plan(Prepago/Postpago/etc):\n");
        String nuevoTipo = br.readLine();
        System.out.println("Ingrese duracion del plan:\n");
        String duracionPlan = br.readLine();
        System.out.println("Ingrese precio del plan:\n");
        int nuevoPrecio = Integer.parseInt(br.readLine());
        Plan nuevoPlan = new Plan(nuevoId,nuevoTipo,duracionPlan,nuevoPrecio);
        planesDisponibles.add(nuevoPlan);
        System.out.println("Nuevo plan (" + nuevoId + ") insertado con exito...\n");
    }
    
    static void listarPlanes(
            ArrayList<Plan> planes
    ) throws IOException {
        
        if(planes.isEmpty()) 
            System.out.println("No hay planes!");
        
        else {
            System.out.println("=========== LISTA DE PLANES ===========");
            for(Plan p : planesDisponibles) {
                p.mostrarPlan();
            }
        }  
    }
    
    static void eliminarPlanes(
            ArrayList<Plan> planes,
            BufferedReader lector
    ) throws IOException {
        
        boolean encontrado = false;
        int idEliminar, i;
        
        System.out.println("Ingresa el id del plan que quieres eliminar: ");
        idEliminar = Integer.parseInt(lector.readLine());
        
        for(i=0; i<planes.size();i++) {
            if(planes.get(i).getIdPlan() == idEliminar) {
                planes.remove(i);
                System.out.println("Plan removido con exito");
                encontrado = true;
                break;
            }
        }
        
        if(!encontrado)
            System.out.println("No existe un plan con ese id");
   
    }
    
    static void insertarContrato(
            ArrayList<Cliente> clientes,
            ArrayList<Plan> planesDisponibles,
            HashMap<String, Cliente> clientesPorRut,
            BufferedReader lector
            
    ) throws IOException {
        
        System.out.println("Ingrese el rut del cliente que recibira el contrato: ");
        String rutIngresado = lector.readLine();
        Cliente clienteBuscado = clientesPorRut.get(rutIngresado);
        
        if(clienteBuscado == null) 
            System.out.println("El cliente no existe, se cancela la insercion...");
  
        else {
            System.out.println("Inserte ID del contrato: ");
            int idNuevoContrato = Integer.parseInt(lector.readLine());
            System.out.println("Planes disponibles: \n");
            
            for(int i = 0; i<planesDisponibles.size(); i++) {
                Plan planActual = planesDisponibles.get(i);
                System.out.println((i+1)+") ");
                planActual.mostrarPlan();
            }
            
            
            System.out.println("Selecciona el plan por numero: ");
            int opcionPlan = Integer.parseInt(lector.readLine());
            Plan planElegido = planesDisponibles.get(opcionPlan-1);
            
            System.out.println("Ingresa la marca del celular: ");
            String marcaCelular = lector.readLine();
            
            System.out.println("Ingresa el numero del celular: ");
            String numeroCelular = lector.readLine();
            
            Producto productoElegido = new Producto(marcaCelular, numeroCelular);
            
            Contrato nuevoContrato = new Contrato(idNuevoContrato, planElegido, productoElegido, "Vigente");
            
            clienteBuscado.agregarContrato(nuevoContrato);
        }
    }
    
    static void listarContratos(ArrayList<Cliente> clientes) {
        
        if(clientes.isEmpty()) {
            System.out.println("No hay clientes registrados");
            return;
        }
        
        boolean existenContratos = false;
        System.out.println("====== LISTADO DE CONTRATOS =======");
        for(Cliente c : clientes) {
            if(!c.getContratos().isEmpty()) {
                System.out.println("Cliente: " +c.getNombre()+ " (" +c.getRut()+ ")");
                for(Contrato cont : c.getContratos())
                    cont.mostrarContrato();
                System.out.println("---------------------");
                existenContratos = true;
            }
        }
        
        if(!existenContratos)
            System.out.println("No hay clientes con contratos registrados");
    }
    
    static void eliminarContrato(
            HashMap<String, Cliente> clientesPorRut,
            BufferedReader lector
    ) throws IOException {
        
        System.out.println("Ingresa rut del cliente: ");
        String rutIngresado = lector.readLine();
        
        Cliente cliente = clientesPorRut.get(rutIngresado);
        if(cliente == null)
            
            System.out.println("No existe un cliente con ese rut");
        
        if (cliente.getContratos().isEmpty()) 
            System.out.println("El cliente no tiene contratos.");
        
        System.out.println("Contratos del cliente " + cliente.getNombre() + ":");  
        
        for (Contrato c : cliente.getContratos()) 
            c.mostrarContrato();
    
        System.out.println("Ingrese el ID del contrato a eliminar:");
        int idEliminar = Integer.parseInt(lector.readLine());
        Contrato contratoEliminar = cliente.getMapaContratos().get(idEliminar);

        if (contratoEliminar == null) 
            System.out.println("El contrato con ID " + idEliminar + " no existe en este cliente.");
        
        else {
            cliente.getContratos().remove(contratoEliminar); 
            cliente.getMapaContratos().remove(idEliminar);
            System.out.println("Contrato eliminado correctamente.");
        }
    }
    public static void main(String[] args) throws IOException {

    //Inicializar datos de ejemplo
    datosEjemplo();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    MostrarMenu menu = new MostrarMenu();

    boolean salir = false;

    while (!salir) {
       
        menu.principal();
        System.out.println("Selecciona una opcion (1 a 4):");

        int eleccion = Integer.parseInt(br.readLine());

        switch (eleccion) {

            
            case 1:
                boolean salirClientes = false;
                while (!salirClientes) {
                    menu.clientes(); 
                    int opcionCliente = Integer.parseInt(br.readLine());

                    switch (opcionCliente) {
                        
                        case 1:
                            insertarCliente(br);
                            break;
                            
                        case 2:
                            listarClientes(clientes, br);
                            break;
                            
                        case 3:
                            eliminarClientes(clientes, clientesPorRut, br);
                            break;
                            
                        case 4:
                            actualizarCliente(clientesPorRut, br);
                            break;
                            
                        case 5:
                            salirClientes = true;
                            break;
                            
                        default:
                            System.out.println("Opcion Invalida...");
                            break;
                            
                    }
                }
                break;
                
            case 2:
                boolean salirPlan = false;
                while (!salirPlan) {
                    menu.planes(); 
                    int opcionPlan = Integer.parseInt(br.readLine());

                    switch (opcionPlan) {
                        
                        case 1:
                            insertarPlan(br);
                            break;
                            
                        case 2:
                            listarPlanes(planesDisponibles);
                            break;
                            
                        case 3:
                            eliminarPlanes(planesDisponibles, br);
                            break;
                            
                        case 4:
                            salirPlan = true;
                            break;
                            
                        default:
                            System.out.println("Opcion invalida...");
                            break;
                    }
                }
                break;

            case 3:
                boolean salirContrato = false;
                while (!salirContrato) {
                    menu.contratos(); 
                    int opcionContrato = Integer.parseInt(br.readLine());

                    switch (opcionContrato) {
                        
                        case 1:
                            insertarContrato(clientes, planesDisponibles, clientesPorRut, br);
                            break;
                            
                        case 2:
                            listarContratos(clientes);
                            break;
                            
                        case 3:
                            eliminarContrato(clientesPorRut, br);
                            break;
                            
                        case 4:
                            salirContrato = true;
                            break;
                            
                        default:
                            System.out.println("Opcion invalida...");
                            break;
                            
                    }
                }
    
            case 4:
                System.out.println("Saliendo del sistema!");
                salir = true; 
                break;

            default:
                System.out.println("Opcion invalida...");
                break;
        }
    }
  }
    
}

