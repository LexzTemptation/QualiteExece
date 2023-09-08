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
import view.loginView;
import view.systemView;

/**
 *
 * @author FELIPE.HERRERA
 */
public class loginController implements ActionListener {

    private Empleado emp;
    private EmpleadoDAB empDAB;
    private loginView loginView;

    public loginController(Empleado emp, EmpleadoDAB empDAB, loginView loginView) {
        this.emp = emp;
        this.empDAB = empDAB;
        this.loginView = loginView;

        this.loginView.btnLogin.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == loginView.btnLogin) {
            String user = loginView.txtUser.getText().trim();
            String password = String.valueOf(loginView.txtPass.getPassword());

            if (!user.equals("") || !password.equals("")) {

                emp = empDAB.login(user, password);

                if (emp.getUserName() != null) {
                    if (emp.getRol().equals("Administrador")) {
                        systemView admin = new systemView();
                        admin.setVisible(true);

                    } else {
                        systemView aux = new systemView();
                        aux.setVisible(true);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }

                //cerrar ventana de login
                this.loginView.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "Los datos estan vacios");
            }
        }
    }

}
