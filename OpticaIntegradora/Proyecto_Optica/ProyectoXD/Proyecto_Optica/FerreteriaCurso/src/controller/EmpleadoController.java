/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.Empleado;
import model.EmpleadoDAB;
import view.systemView;

/**
 *
 * @author FELIPE.HERRERA
 */
public class EmpleadoController implements ActionListener {

    private Empleado emp;
    private EmpleadoDAB empDAB;
    private systemView view;

    public EmpleadoController(Empleado emp, EmpleadoDAB empDAB, systemView view) {
        this.emp = emp;
        this.empDAB = empDAB;
        this.view = view;

        this.view.btnInsertP.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.btnInsertP) {

            if (view.txtNumEmpleado.getText().equals("") || view.txtNombre.getText().equals("")
                    || view.txtUserName.getText().equals("")
                    || view.comboRol.getSelectedItem().toString().equals("")
                    || view.txtAddress.getText().equals("")
                    || view.txtTelefono.getText().equals("")
                    || view.txtCorreo.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Todos los datos deben estar lllenos");
                
            } else {
                emp.setIdEmpleado(Integer.parseInt(view.txtNumEmpleado.getText()));
                emp.setNombre(view.txtNombre.getText());
                emp.setUserName(view.txtUserName.getText());
                emp.setRol(view.comboRol.getSelectedItem().toString());
                emp.setDireccion(view.txtAddress.getText());
                emp.setTelefono(view.txtTelefono.getText());
                emp.setCorreo(view.txtCorreo.getText());
                emp.setContrasenia(String.valueOf(view.txtPassword.getPassword()));

                if (empDAB.insertarEmpleado(emp)) {
                    JOptionPane.showMessageDialog(null, "Empleado insertado");
                } else {
                    JOptionPane.showMessageDialog(null, "El usuarip no ha sido insertado");
                }
            }

        }

    }

}
