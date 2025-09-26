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
