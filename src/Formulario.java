import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Formulario {
    private JPanel rootPanel;
    private JLabel main_label;
    private JTextField codigo_in;
    private JTextField cedula_in;
    private JTextField nombre_in;
    private JTextField fechaNac_in;
    private JComboBox signo_select;
    private JLabel codigo_label;
    private JLabel cedula_label;
    private JLabel nombre_label;
    private JLabel fechaNac_label;
    private JLabel signo_label;
    private JButton buscarPorCodigoButton;
    private JButton buscarPorNombreButton;
    private JButton buscarPorSignoButton;
    private JButton borrarElPresenteRegistroButton;
    private JButton actualizarElPresenteRegistroButton;
    private JButton ingresarElPresenteRegistroButton;
    private JButton limpiarFormularioButton;

    //conexion con MySQL
    final static String DB_URL="jdbc:mysql://localhost/registropersonas"; //cadena de conexion
    final static String USER="root"; //usuario
    final static String PASS="root_bas3"; //password

    public Formulario() {
        buscarPorCodigoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String QUERY = "SELECT * from personas where codigo_pers='"+codigo_in.getText()+"'";
                try(
                        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(QUERY);
                ){
                    while (rs.next()){
                        //Recorre y muestra las columnas indicadas
                        cedula_in.setText(rs.getString("cedula_pers"));
                        nombre_in.setText(rs.getString("nombre_pers"));
                        fechaNac_in.setText(rs.getString("fechaNac_pers"));
                        signo_select.setSelectedItem(rs.getObject("signoZod_pers"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarPorNombreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String QUERY = "SELECT * from personas where nombre_pers='"+nombre_in.getText()+"'";
                try(
                        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(QUERY);
                ){
                    while (rs.next()){
                        //Recorre y muestra las columnas indicadas
                        codigo_in.setText(rs.getString("codigo_pers"));
                        cedula_in.setText(rs.getString("cedula_pers"));
                        fechaNac_in.setText(rs.getString("fechaNac_pers"));
                        signo_select.setSelectedItem(rs.getObject("signoZod_pers"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        buscarPorSignoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String QUERY = "SELECT * from personas where signoZod_pers='"+signo_select.getSelectedItem()+"'";
                try(
                        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(QUERY);
                ){
                    while (rs.next()){
                        //Recorre y muestra las columnas indicadas
                        codigo_in.setText(rs.getString("codigo_pers"));
                        cedula_in.setText(rs.getString("cedula_pers"));
                        nombre_in.setText(rs.getString("nombre_pers"));
                        fechaNac_in.setText(rs.getString("fechaNac_pers"));
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        borrarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String deleteQuery = "DELETE FROM personas WHERE codigo_pers="+codigo_in.getText();
                modificarRegistro(deleteQuery);
                JOptionPane.showMessageDialog(null,"Registro borrado exitosamente","Borrado Correcto",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        actualizarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombre_in.getText();
                String fechaNac = fechaNac_in.getText();
                String signoZod = String.valueOf(signo_select.getSelectedItem());

                String updateQuery = "UPDATE personas SET nombre_pers='"+nombre+"',fechaNac_pers='"+fechaNac+"',signoZod_pers='"+signoZod+"'WHERE codigo_pers='"+codigo_in.getText()+"'";

                modificarRegistro(updateQuery);

                JOptionPane.showMessageDialog(null,"Registro actualizado exitosamente","Actualización Correcta",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        ingresarElPresenteRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = codigo_in.getText();
                String cedula = cedula_in.getText();
                String nombre = nombre_in.getText();
                String fechaNac = fechaNac_in.getText();
                String signoZod = String.valueOf(signo_select.getSelectedItem());

                String insertQuery = "INSERT INTO personas VALUES ('"+codigo+"','"+cedula+"','"+nombre+"','"+fechaNac+"','"+signoZod+"')";

                modificarRegistro(insertQuery);

                JOptionPane.showMessageDialog(null,"Persona registrada exitosamente","Registro Correcto",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        limpiarFormularioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                codigo_in.setText(null);
                cedula_in.setText(null);
                nombre_in.setText(null);
                fechaNac_in.setText(null);
                signo_select.setSelectedItem(null);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Formulario");
        frame.setContentPane(new Formulario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void modificarRegistro(String QUERY){
        try(Connection connection = DriverManager.getConnection(DB_URL,USER,PASS)){
            try(Statement statement = connection.createStatement()){
                statement.executeUpdate(QUERY);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
