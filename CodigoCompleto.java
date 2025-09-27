
// ------------------- CLASE CLIENTE -------------------//
package administrarcontratos;


import java.util.ArrayList;
import java.util.HashMap;


public class Cliente {
    // Atributos básicos del cliente 
   private String nombre;
   private int edad;
   private String rut; 
   
   // Lista de contratos que tiene este cliente
   private ArrayList<Contrato> contratos;
   // Mapa que relaciona el ID del contrato con el contrato mismo (para búsquedas rápidas)
   private HashMap<Integer,Contrato> mapaContratos; //corresponde a mapa anidado
   
   // Constructor: permite crear un cliente nuevo con nombre, edad y rut
   public Cliente(String nombre, int edad,String rut){
       this.nombre = nombre;
       this.edad = edad;
       this.rut = rut;
       this.contratos = new ArrayList<>();
       this.mapaContratos = new HashMap<>();
   }
   // Métodos getters y setters para acceder y modificar los datos del cliente
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
// Método para agregar un contrato al cliente
    public void agregarContrato(Contrato contratoNuevo){
// Si el contrato NO existe, lo agrega a la lista y al mapa
        if (!mapaContratos.containsKey(contratoNuevo.getIdContrato())){
            contratos.add(contratoNuevo); 
            mapaContratos.put(contratoNuevo.getIdContrato(), contratoNuevo); 
        }
        
        else
            System.out.println("El contrato ya existe");

    }
    // Obtener un contrato por su ID
    public Contrato getContratoPorId(int IdContrato){
        return mapaContratos.get(IdContrato);
    }
    // Métodos sobrecargados para actualizar los datos del cliente
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
    
    
 // Mostrar información del cliente y sus contratos   
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
    
// Versión mejorada: se puede elegir si mostrar o no los contratos    
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

// ------------------- CLASE CONTRATO ------------------- //
package administrarcontratos;

public class Contrato {
    
    private int idContrato;
    private Plan plan; // Un contrato siempre tiene un plan asociado
    private Producto producto; // Y también un producto (celular)
    private String estado; // Estado: vigente, suspendido, etc.

// Constructor
    public Contrato(int idContrato, Plan plan, Producto producto, String estado) {
        this.idContrato = idContrato;
        this.plan = plan;
        this.producto = producto;
        this.estado = estado;
    }
// Getters y setters
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

// Mostrar información completa del contrato (incluye plan y producto)    
    public void mostrarContrato(){
        System.out.println("ID del contrato: " + idContrato + "\nEstado del contrato: " + estado);
        plan.mostrarPlan();
        producto.mostrarProducto();
        
    }
}


// ------------------- CLASE PLAN ------------------- //

package administrarcontratos;

public class Plan {
    private int idPlan;
    private String tipoPlan;
    private String duracion;
    private int precio; // precio en pesos chilenos (CLP)
 // Constructor   
    public Plan(int idPlan,String tipoPlan, String duracion, int precio) {
        this.idPlan = idPlan;
        this.tipoPlan = tipoPlan;
        this.duracion = duracion;
        this.precio = precio;
    }
// Getters y setters
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
 // Mostrar información del plan  
    public void mostrarPlan(){
        System.out.println("Tipo de plan: " + tipoPlan + "\nDuracion del plan: " + duracion + "\nPrecio del plan: " + precio);
    }
    
}

// ------------------- CLASE PRODUCTO ------------------- //
package administrarcontratos;

public class Producto {
    private String marcaCelular;
    private String numeroCelular;
    
// Constructor    
    public Producto(String marcaCelular, String numeroCelular) {
        this.marcaCelular = marcaCelular;
        this.numeroCelular = numeroCelular;
    }
// Getters y setters
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
// Mostrar información del producto    
    public void mostrarProducto(){
        System.out.println("Marca del producto: " + marcaCelular + "\nNumero del producto: " + numeroCelular);
    }
    
}


// ------------------- CLASE VENTANA PRINCIPAL ------------------- //
package administrarcontratos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaPrincipal implements ActionListener {
    private JFrame frame;
    private JButton btnClientes, btnPlanes, btnContratos, btnSalir;

    public VentanaPrincipal() {
        // Crear ventana
        frame = new JFrame("Sistema de Contratos Telefónicos");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Panel principal con layout en cuadrícula (4 filas, 1 columna) para que sea mas simple, quizas luego la funcion extra tenga un menu extra
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));

        // Crear botones
        btnClientes = new JButton("Clientes");
        btnPlanes = new JButton("Planes");
        btnContratos = new JButton("Contratos");
        btnSalir = new JButton("Salir");

        // Añadir escuchador (this porque implementamos ActionListener)
        btnClientes.addActionListener(this);
        btnPlanes.addActionListener(this);
        btnContratos.addActionListener(this);
        btnSalir.addActionListener(this);

        // Agregar botones al panel
        panel.add(btnClientes);
        panel.add(btnPlanes);
        panel.add(btnContratos);
        panel.add(btnSalir);

        // Agregar panel a la ventana
        frame.add(panel);
    }

    // Método para mostrar la ventana, esto es lo que se llama al final en main
    public void mostrar() {
        frame.setVisible(true);
    }

    // Acción de los botones
    //por ahora no abren nada, luego deberian abrir los otros menus
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); //esto es lo que verifica cual boton se presionó

        if (source == btnClientes) {
            VentanaClientes ventanaClientes = new VentanaClientes();
            ventanaClientes.mostrar();
            frame.dispose();
        } 
        else if (source == btnPlanes) {
            VentanaPlanes ventanaPlanes = new VentanaPlanes();
            ventanaPlanes.mostrar();
            frame.dispose();
            
        } 
        else if (source == btnContratos) {
            VentanaContratos ventanaContratos = new VentanaContratos();
            ventanaContratos.mostrar();
            frame.dispose();
        } 
        else if (source == btnSalir) {
            System.exit(0);
        }
    }
}

// ------------------- CLASE VENTANA CLIENTES ------------------- //
package administrarcontratos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//entre cada ventana van a haber muchas similitudes, despues de todo se diferencian en los procesos que tienen
//todas requieren la creacion de botones, crear el frame,permitir visibilidad, etc
//entonces estas ventanas se diferencian en el actionperformed, o que boton se presiona y que hace cada


public class VentanaClientes implements ActionListener {
    
    //la ventana en si, donde estaran los botones
    private JFrame frame;
    //text fields para el agregar ademas de botones confirmar y cancelar, así es mas entendible
    private JTextField txtNombre, txtEdad, txtRut;
    private JButton btnCancelar;
    private JButton btnConfirmarAgregar,btnConfirmarEliminar,btnConfirmarActualizar;
    //los botones, si no se incluye una extra en ventanaprincipal para los extras, entonces dentro de este o plan o contrato
    private JButton btnAgregar, btnListar, btnEliminar, btnActualizar, btnVolver;
    
    public VentanaClientes(){
        frame = new JFrame("Menú Clientes"); //el titulo de la ventana, obvio sera menu clientes
        frame.setSize(400, 300); //el tamaño, esto es como lo mas general
        frame.setLocationRelativeTo(null); // Centrar, o sea que aparezca en medio de la pantalla
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Solo cierra esta ventana
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10)); //lo mismo que en ppal pero con 5 botones, y 1 columna igualmente

        // Crear botones, aun sin funcionalidad, ni se ven en el panel
        btnAgregar = new JButton("Agregar Cliente");
        btnListar = new JButton("Listar Clientes");
        btnEliminar = new JButton("Eliminar Cliente");
        btnActualizar = new JButton("Actualizar Cliente");
        btnVolver = new JButton("Volver");
        
        //este espacio es para botones de submenus, menus muy pequeños que es mejor tener aqui
        
        
        // Asignar listeners, ahora los botones detectan cuando se presionan
        btnAgregar.addActionListener(this);
        btnListar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnVolver.addActionListener(this);
        
        // Agregar al panel, y ahora se ven los botones
        panel.add(btnAgregar);
        panel.add(btnListar);
        panel.add(btnEliminar);
        panel.add(btnActualizar);
        panel.add(btnVolver);
        //finalmente todo se agrega al frame para ser visible
        frame.add(panel);
        
    }
    // Mostrar ventana
    public void mostrar() {
        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ============ COMIENZA EL CASO AGREGAR ============ //
        if (source == btnAgregar) {
            frame.getContentPane().removeAll(); // limpiar ventana
            JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));

            panelForm.add(new JLabel("Nombre:"));
            txtNombre = new JTextField();
            panelForm.add(txtNombre);

            panelForm.add(new JLabel("Edad:"));
            txtEdad = new JTextField();
            panelForm.add(txtEdad);

            panelForm.add(new JLabel("RUT:"));
            txtRut = new JTextField();
            panelForm.add(txtRut);

            btnConfirmarAgregar = new JButton("Agregar");
            btnCancelar = new JButton("Cancelar");

            btnConfirmarAgregar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarAgregar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        }
        //aqui la autenticacion para los botones de agregacion
        //este es el caso en que el cliente se intenta insertar
        else if (source == btnConfirmarAgregar) {
            String nombre = txtNombre.getText().trim();
            String edadStr = txtEdad.getText().trim();
            String rut = txtRut.getText().trim();
            

            if (nombre.isEmpty() || edadStr.isEmpty() || rut.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
                return;
            }

            // Verificar que la edad sea número
            boolean esNumero = true;
            for (int i = 0; i < edadStr.length(); i++) {
                if (!Character.isDigit(edadStr.charAt(i))) {
                    esNumero = false;
                    break;
                }
            }

            if (!esNumero) {
                JOptionPane.showMessageDialog(frame, "Edad inválida, debe ser un número");
                return;
            }

            int edad = Integer.parseInt(edadStr);
            boolean agregado = false;
            try {
                agregado = administrarContratos.insertarCliente(nombre, edad, rut);
            } catch (IOException ex) {
                Logger.getLogger(VentanaClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (agregado) {
                JOptionPane.showMessageDialog(frame, "Cliente agregado con éxito");
                frame.dispose();
                new VentanaClientes().mostrar();
            } else {
                JOptionPane.showMessageDialog(frame, "El cliente ya existe, no se agregó.");
            }
        }
        //y este el caso en el que se cancele, vuelve directamente
        else if (source == btnCancelar) {
            frame.dispose();
        }
        // TERMINA CASO FORMULARIO DE AGREGAR //
        
        // ============ COMIENZA EL CASO LISTAR ============ //
        
        else if (source == btnListar) {
            int opcion = JOptionPane.showConfirmDialog(
                frame, "¿Mostrar también los contratos?", "Listar", JOptionPane.YES_NO_OPTION
            );
            boolean mostrarContratos = (opcion == JOptionPane.YES_OPTION);
            String lista = administrarContratos.listarClientes(mostrarContratos);
            JOptionPane.showMessageDialog(frame, lista);
        }
        // TERMINA CASO DE LISTAR //
        
        // ============ COMIENZA EL CASO ELIMINAR ============ //
        if (source == btnEliminar) {
            //esto de removeALL es para limpiar el menu y poner este nuevo encima, el dispose tambien puede servir
            frame.getContentPane().removeAll();
            JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 10));

            panelForm.add(new JLabel("RUT del cliente a eliminar:"));
            txtRut = new JTextField();
            panelForm.add(txtRut);

            btnConfirmarEliminar = new JButton("Eliminar");
            btnCancelar = new JButton("Cancelar");
            btnConfirmarEliminar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarEliminar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        }
        
        else if (source == btnConfirmarEliminar) {
            String rut = txtRut.getText().trim();
            if (rut.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Debe ingresar un RUT");
                return;
            }
            
            try {
                // aqui el try catch del throws exception de cliente, hay que cambiar lo de boolean ya no me sirve
                boolean eliminado = administrarContratos.eliminarClientes(rut);
                JOptionPane.showMessageDialog(frame, "Cliente eliminado correctamente");
            }
            catch (NoClienteException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }

            frame.dispose();
            new VentanaClientes().mostrar();
        }
        
        // FIN DE CASO DE ELIMINAR //
        
        
        else if (source == btnActualizar) {
            frame.getContentPane().removeAll();
            JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 10));

            panelForm.add(new JLabel("RUT del cliente:"));
            txtRut = new JTextField();
            panelForm.add(txtRut);

            panelForm.add(new JLabel("Nuevo nombre:"));
            txtNombre = new JTextField();
            panelForm.add(txtNombre);

            panelForm.add(new JLabel("Nueva edad:"));
            txtEdad = new JTextField();
            panelForm.add(txtEdad);

            btnConfirmarActualizar = new JButton("Actualizar");
            btnCancelar = new JButton("Cancelar");
            btnConfirmarActualizar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarActualizar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        } 
        
        else if (source == btnConfirmarActualizar) {
            String rut = txtRut.getText().trim();
            String nombre = txtNombre.getText().trim();
            String edadStr = txtEdad.getText().trim();

            if (rut.isEmpty() || nombre.isEmpty() || edadStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
                return;
            }

            boolean esNumero = true;
            for (int i = 0; i < edadStr.length(); i++) {
                if (!Character.isDigit(edadStr.charAt(i))) {
                    esNumero = false; break;
                }
            }
            if (!esNumero) {
                JOptionPane.showMessageDialog(frame, "Edad inválida, debe ser un número");
                return;
            }

            int edad = Integer.parseInt(edadStr);
            try {
                // aqui el try catch del throws exception de cliente
                boolean actualizado = administrarContratos.actualizarCliente(rut, nombre, edad);
                JOptionPane.showMessageDialog(frame, "Cliente actualizado correctamente");
            } catch (NoClienteException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
            
            frame.dispose();
            new VentanaClientes().mostrar();
        }
   
        else if (source == btnVolver) {
            new VentanaPrincipal().mostrar();
            frame.dispose();
        }
    }
}

// ------------------- CLASE VENTANA CONTRATOS ------------------- //
package administrarcontratos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VentanaContratos implements ActionListener{
    
    //lo tipico para los botoncitos
    
    private JFrame frame;
    private JButton btnAgregar, btnListar, btnEliminar,btnActualizar, btnVolver;
    
    private JButton btnCambiarEstado; //este boton es unico, es para la posible opcion de cambiar solo el estado del contrato
    
    private JTextField txtRut, txtIdContrato, txtIdPlan, txtMarca, txtNumero, txtEstado;

    // botones confirmación
    private JButton btnConfirmarAgregar, btnConfirmarEliminar,btnConfirmarActualizar, btnCancelar;

    public VentanaContratos() {
        frame = new JFrame("Menú Contratos");
        frame.setSize(350, 220);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        btnAgregar = new JButton("Agregar Contrato");
        btnListar = new JButton("Listar Contratos");
        btnEliminar = new JButton("Eliminar Contrato");
        btnActualizar = new JButton("Actualizar Plan");
        btnCambiarEstado = new JButton("Cambiar estado");
        btnVolver = new JButton("Volver");

        btnAgregar.addActionListener(this);
        btnListar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnVolver.addActionListener(this);
        btnCambiarEstado.addActionListener(this);

        panel.add(btnAgregar);
        panel.add(btnListar);
        panel.add(btnEliminar);
        panel.add(btnActualizar);
        panel.add(btnCambiarEstado);
        panel.add(btnVolver);
        

        frame.add(panel);
    }

    public void mostrar() {
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ================= AGREGAR =================
        if (source == btnAgregar) {
            frame.getContentPane().removeAll();
            JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 10));

            panelForm.add(new JLabel("RUT Cliente:"));
            txtRut = new JTextField();
            panelForm.add(txtRut);

            panelForm.add(new JLabel("ID Contrato:"));
            txtIdContrato = new JTextField();
            panelForm.add(txtIdContrato);

            panelForm.add(new JLabel("ID Plan:"));
            txtIdPlan = new JTextField();
            panelForm.add(txtIdPlan);

            panelForm.add(new JLabel("Marca Celular:"));
            txtMarca = new JTextField();
            panelForm.add(txtMarca);

            panelForm.add(new JLabel("Número Celular:"));
            txtNumero = new JTextField();
            panelForm.add(txtNumero);

            btnConfirmarAgregar = new JButton("Confirmar Agregar");
            btnCancelar = new JButton("Cancelar");

            btnConfirmarAgregar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarAgregar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        }
        else if (source == btnConfirmarAgregar) {
            String rut = txtRut.getText().trim();
            String idContratoStr = txtIdContrato.getText().trim();
            String idPlanStr = txtIdPlan.getText().trim();
            String marca = txtMarca.getText().trim();
            String numero = txtNumero.getText().trim();

            if (rut.isEmpty() || idContratoStr.isEmpty() || idPlanStr.isEmpty() || marca.isEmpty() || numero.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
                return;
            }

            boolean idContratoOk = idContratoStr.chars().allMatch(Character::isDigit);
            boolean idPlanOk = idPlanStr.chars().allMatch(Character::isDigit);

            if (!idContratoOk || !idPlanOk) {
                JOptionPane.showMessageDialog(frame, "ID Contrato e ID Plan deben ser numéricos");
                return;
            }

            int idContrato = Integer.parseInt(idContratoStr);
            int idPlan = Integer.parseInt(idPlanStr);

            boolean agregado = administrarContratos.insertarContrato(rut, idContrato, idPlan, marca, numero);

            if (agregado) {
                JOptionPane.showMessageDialog(frame, "Contrato agregado con éxito");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo agregar: cliente o plan inexistente");
            }

            frame.dispose();
            new VentanaContratos().mostrar();
        }

        //termina el agregar //
        
        // ================= LISTAR =================
        else if (source == btnListar) {
            String lista = administrarContratos.listarContratos();
            JOptionPane.showMessageDialog(frame, lista);
        }
        
        //termina el listar //

        // ================= ELIMINAR =================
        else if (source == btnEliminar) {
            frame.getContentPane().removeAll();
            JPanel panelForm = new JPanel(new GridLayout(3, 2, 10, 10));

            panelForm.add(new JLabel("RUT Cliente:"));
            txtRut = new JTextField();
            panelForm.add(txtRut);

            panelForm.add(new JLabel("ID Contrato:"));
            txtIdContrato = new JTextField();
            panelForm.add(txtIdContrato);

            btnConfirmarEliminar = new JButton("Confirmar Eliminar");
            btnCancelar = new JButton("Cancelar");

            btnConfirmarEliminar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarEliminar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        }
        //este es para el confirmar el eliminar, es mas comodo con un boton personal para cada
        else if (source == btnConfirmarEliminar) {
            String rut = txtRut.getText().trim();
            String idContratoStr = txtIdContrato.getText().trim();

            if (rut.isEmpty() || idContratoStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
                return;
            }

            boolean idOk = idContratoStr.chars().allMatch(Character::isDigit);
            if (!idOk) {
                JOptionPane.showMessageDialog(frame, "El ID Contrato debe ser numérico");
                return;
            }

            int idContrato = Integer.parseInt(idContratoStr);
            boolean eliminado = administrarContratos.eliminarContrato(rut, idContrato);

            if (eliminado) {
                JOptionPane.showMessageDialog(frame, "Contrato eliminado correctamente");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo eliminar: cliente o contrato inexistente");
            }

            frame.dispose();
            new VentanaContratos().mostrar();
        }

        //aqui termina el eliminar //
        
        // ================= Metodo de actualizar =================
        
        else if (source == btnActualizar) {
            frame.getContentPane().removeAll();
            JPanel panelForm = new JPanel(new GridLayout(7, 2, 10, 10));

            panelForm.add(new JLabel("RUT Cliente:"));
            txtRut = new JTextField();
            panelForm.add(txtRut);

            panelForm.add(new JLabel("ID Contrato:"));
            txtIdContrato = new JTextField();
            panelForm.add(txtIdContrato);

            panelForm.add(new JLabel("Nuevo ID Plan:"));
            txtIdPlan = new JTextField();
            panelForm.add(txtIdPlan);

            panelForm.add(new JLabel("Nueva Marca:"));
            txtMarca = new JTextField();
            panelForm.add(txtMarca);

            panelForm.add(new JLabel("Nuevo Número:"));
            txtNumero = new JTextField();
            panelForm.add(txtNumero);

            panelForm.add(new JLabel("Nuevo Estado:"));
            txtEstado = new JTextField();
            panelForm.add(txtEstado);

            btnConfirmarActualizar = new JButton("Confirmar Actualizar");
            btnCancelar = new JButton("Cancelar");

            btnConfirmarActualizar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarActualizar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        }

        else if (source == btnConfirmarActualizar) {
            String rut = txtRut.getText().trim();
            String idContratoStr = txtIdContrato.getText().trim();
            String idPlanStr = txtIdPlan.getText().trim();
            String marca = txtMarca.getText().trim();
            String numero = txtNumero.getText().trim();
            String estado = txtEstado.getText().trim();

            if (rut.isEmpty() || idContratoStr.isEmpty() || idPlanStr.isEmpty() ||
                marca.isEmpty() || numero.isEmpty() || estado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
                return;
            }

            if (!idContratoStr.chars().allMatch(Character::isDigit) || !idPlanStr.chars().allMatch(Character::isDigit)) {
                JOptionPane.showMessageDialog(frame, "ID Contrato e ID Plan deben ser numéricos");
                return;
            }

            int idContrato = Integer.parseInt(idContratoStr);
            int idPlan = Integer.parseInt(idPlanStr);

            boolean actualizado = administrarContratos.actualizarContrato(rut, idContrato, idPlan, marca, numero, estado);

            if (actualizado) {
                JOptionPane.showMessageDialog(frame, "Contrato actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(frame, "No se pudo actualizar: cliente, contrato o plan inexistente");
            }

            frame.dispose();
            new VentanaContratos().mostrar();
        }
        //aqui termina el actualizar //
        
        // ================= Metodo de cambiar solo estado =================
        
        else if (source == btnCambiarEstado) {
            String rut = JOptionPane.showInputDialog(frame, "Ingrese el RUT del cliente:");
            if (rut == null) { // El usuario presionó Cancelar
                return; // Sale inmediatamente sin hacer nada más y vuelve altiro al menu de contratos
            }
            
            String idContratoStr = JOptionPane.showInputDialog(frame, "Ingrese el ID del contrato:");
            if (idContratoStr == null) { // El usuario presionó Cancelar
                return; // Sale inmediatamente sin hacer nada más y vuelve al menu de contratos
            }
            String nuevoEstado = JOptionPane.showInputDialog(frame, "Ingrese el nuevo estado (ej: Vigente, Suspendido):");
            if (nuevoEstado == null) { // El usuario presionó Cancelar
                return;
            }

            try {
                int idContrato = Integer.parseInt(idContratoStr);
                boolean cambiado = administrarContratos.cambiarEstadoContrato(rut, idContrato, nuevoEstado);
                if (cambiado) {
                    JOptionPane.showMessageDialog(frame, "Estado cambiado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se encontró el contrato o cliente.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "El ID del contrato debe ser un número.");
            }
        }
        
        //aqui termina el actualizar solo estado
        
        // ================= CANCELAR y volver =================
        else if (source == btnCancelar) {
            frame.dispose();
            new VentanaContratos().mostrar();
        }
        
        else if (source == btnVolver) {
            frame.dispose();
            new VentanaPrincipal().mostrar();
        }
        
        
    }
    
    
}

// ------------------- CLASE VENTANA PLANES ------------------- //
package administrarcontratos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VentanaPlanes implements ActionListener {
    //primero lo tipico de siempre, los botones y los espacios
    
    private JFrame frame;
    private JButton btnAgregar, btnListar, btnEliminar,btnActualizar, btnVolver, btnCancelar;

    private JTextField txtId, txtTipo, txtDuracion, txtPrecio;
    
    private JButton btnMayorRecaudador; //este boton es unico de esta clase, es para la funcion extra, la del SIA 2.5
    
    //botones especiales para cada mini ventana
    private JButton btnConfirmarAgregar,btnConfirmarEliminar,btnConfirmarActualizar;

    public VentanaPlanes() {
        frame = new JFrame("Menú Planes");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        btnAgregar = new JButton("Agregar Plan");
        btnListar = new JButton("Listar Planes");
        btnEliminar = new JButton("Eliminar Plan");
        btnActualizar = new JButton("Actualizar Plan");
        btnMayorRecaudador = new JButton("Plan Mas Lucrativo");
        btnVolver = new JButton("Volver");

        btnAgregar.addActionListener(this);
        btnListar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnActualizar.addActionListener(this);
        btnMayorRecaudador.addActionListener(this);
        btnVolver.addActionListener(this);

        panel.add(btnAgregar);
        panel.add(btnListar);
        panel.add(btnEliminar);
        panel.add(btnActualizar);
        panel.add(btnMayorRecaudador);
        panel.add(btnVolver);

        frame.add(panel);
    }
    
    //la tipica funcion para mostrar
    public void mostrar() {
        frame.setVisible(true);
    }
    
    // y aqui cuando se presionan estos botones
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // ================= AGREGAR =================
        if (source == btnAgregar) {
            frame.getContentPane().removeAll();
            JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));

            panelForm.add(new JLabel("ID:"));
            txtId = new JTextField();
            panelForm.add(txtId);

            panelForm.add(new JLabel("Tipo:"));
            txtTipo = new JTextField();
            panelForm.add(txtTipo);

            panelForm.add(new JLabel("Duración:"));
            txtDuracion = new JTextField();
            panelForm.add(txtDuracion);

            panelForm.add(new JLabel("Precio:"));
            txtPrecio = new JTextField();
            panelForm.add(txtPrecio);

            btnConfirmarAgregar = new JButton("Confirmar Agregar");
            btnCancelar = new JButton("Cancelar");
            btnConfirmarAgregar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarAgregar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        }
        
        if (source == btnConfirmarAgregar){
            String idStr = txtId.getText().trim();
            String tipo = txtTipo.getText().trim();
            String duracion = txtDuracion.getText().trim();
            String precioStr = txtPrecio.getText().trim();

            if (idStr.isEmpty() || tipo.isEmpty() || duracion.isEmpty() || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
                return;
                }

            //esto de aqui es para comprobar que los datos ingresados tanto en id como en precio, sean numeros
            //como vamos a tomar pesos en vez de dolares nos haremos la vida un poco mas simple ya que son enteros y no floats
            boolean idReal = idStr.chars().allMatch(Character::isDigit);
            boolean precioReal = precioStr.chars().allMatch(Character::isDigit);

            if (!idReal || !precioReal) {
                JOptionPane.showMessageDialog(frame, "ID y Precio deben ser numéricos");
                return;
                }

            int id = Integer.parseInt(idStr);
            int precio = Integer.parseInt(precioStr);

            boolean agregado = administrarContratos.insertarPlan(id, tipo, duracion, precio);

            if (agregado) {
                JOptionPane.showMessageDialog(frame, "Plan agregado con éxito");
                }
            else{
                JOptionPane.showMessageDialog(frame, "El ID ya existe, no se agregó");
                }

            frame.dispose();
            new VentanaPlanes().mostrar();
            }
        
        // finaliza el modo de agregar //
        
        // ================= LISTAR =================
        else if (source == btnListar) {
            String lista = administrarContratos.listarPlanes();
            JOptionPane.showMessageDialog(frame, lista);
        }
        // finaliza el modo listar, bastante simple //
        
            // ================= ELIMINAR =================
        else if (source == btnEliminar) {
            frame.getContentPane().removeAll();
            JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 10));

            panelForm.add(new JLabel("ID del plan a eliminar:"));
            txtId = new JTextField();
            panelForm.add(txtId);

            btnConfirmarEliminar = new JButton("Confirmar Eliminar");
            btnCancelar = new JButton("Cancelar");

            btnConfirmarEliminar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarEliminar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        }
        
        else if (source == btnConfirmarEliminar) {
            String idStr = txtId.getText().trim();

            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Debe ingresar un ID");
                return;
            }

            boolean idOk = idStr.chars().allMatch(Character::isDigit);
            if (!idOk) {
                JOptionPane.showMessageDialog(frame, "El ID debe ser numérico");
                return;
            }

            int id = Integer.parseInt(idStr);
            
            try {
                // aqui el try catch del throws exception de plan
                boolean eliminado = administrarContratos.eliminarPlan(id);
                JOptionPane.showMessageDialog(frame, "Plan eliminado correctamente");
            } catch (NoPlanException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
            
            frame.dispose();
            new VentanaPlanes().mostrar();
        }
        
        // Finaliza el modo de eliminar //
        
        // ================= Metodo de actualizar =================
        else if (source == btnActualizar) {
            frame.getContentPane().removeAll();
            JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));

            panelForm.add(new JLabel("ID del plan a actualizar:"));
            txtId = new JTextField();
            panelForm.add(txtId);

            panelForm.add(new JLabel("Nuevo Tipo:"));
            txtTipo = new JTextField();
            panelForm.add(txtTipo);

            panelForm.add(new JLabel("Nueva Duración:"));
            txtDuracion = new JTextField();
            panelForm.add(txtDuracion);

            panelForm.add(new JLabel("Nuevo Precio:"));
            txtPrecio = new JTextField();
            panelForm.add(txtPrecio);

            btnConfirmarActualizar = new JButton("Confirmar Actualizar");
            btnCancelar = new JButton("Cancelar");

            btnConfirmarActualizar.addActionListener(this);
            btnCancelar.addActionListener(this);

            panelForm.add(btnConfirmarActualizar);
            panelForm.add(btnCancelar);

            frame.add(panelForm);
            frame.revalidate();
            frame.repaint();
        }

        else if (source == btnConfirmarActualizar) {
            String idStr = txtId.getText().trim();
            String tipo = txtTipo.getText().trim();
            String duracion = txtDuracion.getText().trim();
            String precioStr = txtPrecio.getText().trim();

            if (idStr.isEmpty() || tipo.isEmpty() || duracion.isEmpty() || precioStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios");
                return;
            }

            if (!idStr.chars().allMatch(Character::isDigit) || !precioStr.chars().allMatch(Character::isDigit)) {
                JOptionPane.showMessageDialog(frame, "ID y Precio deben ser numéricos");
                return;
            }

            int id = Integer.parseInt(idStr);
            int precio = Integer.parseInt(precioStr);

            try {
                // aqui el try catch del throws exception de plan
                boolean actualizado = administrarContratos.actualizarPlan(id, tipo, duracion, precio);
                JOptionPane.showMessageDialog(frame, "Plan actualizado correctamente");
            } catch (NoPlanException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }

            frame.dispose();
            new VentanaPlanes().mostrar();
        }

        // Finaliza el modo de actualizar //
        
        // ================= NUEVA FUNCIONALIDAD =================
        else if (source == btnMayorRecaudador) {
            String resultado = administrarContratos.planMasRecaudador();
            JOptionPane.showMessageDialog(frame, resultado);
        }
        
        // ================= CANCELAR y volver =================
        else if (source == btnCancelar) {
            frame.dispose();
            new VentanaPlanes().mostrar();
        }
        else if (source == btnVolver) {
            frame.dispose();
            new VentanaPrincipal().mostrar();
        }
        
        //ahi termina ambos el cancelar y el volver, estos se pueden hacer en uno pero la verdad es divertido hacer botones //
    }
        
}

// ------------------- CLASE EXCEPCION CLIENTE ------------------- //
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

// ------------------- CLASE EXCEPCION PLAN ------------------- //

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

// ------------------- CLASE MENÚS ------------------- //
package administrarcontratos;

public class MostrarMenu {
// Menú principal
    public void principal() {
        System.out.println("============= MENU PRINCIPAL =============");
        System.out.println("==== SISTEMA DE CONTRATOS TELEFONICOS ====");
        System.out.println("========= SELECCIONE UNA OPCION ==========");
        System.out.println("1. Clientes");
        System.out.println("2. Planes");
        System.out.println("3. Contratos");
        System.out.println("4. Salir del sistema");
        System.out.println("==========================================");
    }
// Menú clientes
    public void clientes() {
        System.out.println("========= MENU CLIENTES =========");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Listar clientes");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Actualizar datos cliente");
        System.out.println("5. Volver al menu principal");
        System.out.println("=================================");
    }
// Menú planes
    public void planes() {
        System.out.println("========== MENU PLANES ==========");
        System.out.println("1. Agregar plan");
        System.out.println("2. Listar planes");
        System.out.println("3. Eliminar plan");
        System.out.println("4. Volver al menu principal");
        System.out.println("=================================");
    }
// Menú contratos
    public void contratos() {
        System.out.println("======== MENU CONTRATOS =========");
        System.out.println("1. Insertar contrato a cliente");
        System.out.println("2. Listar contratos");
        System.out.println("3. Eliminar contratos");
        System.out.println("2. Volver al menu principal");
        System.out.println("=================================");
    }
}



// ------------------- CLASE PRINCIPAL ------------------- //
package administrarcontratos;

import java.io.*;
import java.util.*;
import javax.swing.SwingUtilities;


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




