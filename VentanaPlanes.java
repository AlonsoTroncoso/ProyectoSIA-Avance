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