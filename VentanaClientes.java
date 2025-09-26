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
