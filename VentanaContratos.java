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
