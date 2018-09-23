package sv.unab.edu.Presentacion;

import sv.unab.edu.Dominio.mascota;
import sv.unab.edu.Infraestructura.MascotaBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class MostrarDatos extends JFrame
{

    private JTable tablaDatos;
    private JButton btnEliminar;
    public JPanel panelRais;
    private JTextField txtFiltro;

    private List<mascota> personaListado;
    DefaultTableModel modelo = new DefaultTableModel();
    Long cod = null;
    AtomicLong aton = new AtomicLong();

    public MostrarDatos()
    {
        this.setContentPane(panelRais);
        this.pack();
        initComponents();
       /* try
        {
            actualisarTabla(new MascotaBL().BuscarM());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }*/
        tablaDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int y = tablaDatos.getSelectedRow();
                cod = Long.valueOf(tablaDatos.getValueAt(y,0).toString());
                JOptionPane.showMessageDialog(null, cod);
            }
        });
        txtFiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e)
            {
                try
                {
                    actualisarTabla(new MascotaBL().BuscarMascota(txtFiltro.getText()));
                }
                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void actualisarTabla(List<mascota> mas)
    {
        reiniciarJTable(tablaDatos);
        mas.stream().sorted(Comparator.comparing(mascota::getId)).forEach(r ->
                mas.stream().filter(h -> r.getId().equals(h.getId())).forEach(p ->

                        modelo.addRow(new Object[] {

                                r.getId(),
                                p.getNombreM(),
                                p.getEdad(),
                                p.getPeso(),
                                p.getRasa(),
                                p.getFechaNacimientoM(),
                                p.getNombreD(),
                                p.getApellidoPaterno(),
                                p.getApellidoMaterno(),
                                p.getDui(),
                                p.getTelefono(),
                                p.getDireccion(),
                                p.getFechaNacimientoD(),

                        })));

            /*modelo.addRow(new Object[]{
                    cod,nombre,apellidoP,apellidoM,dui,telefono,email,direccion,fechaN
            });*/
        //}
        tablaDatos.setModel(modelo);
    }
    private void reiniciarJTable(JTable jTable) {
        DefaultTableModel modelo = (DefaultTableModel) jTable.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

    }
    /*public void campo(DefaultTableModel mo, JTable tab) {
        mo.addColumn("Id");
        mo.addColumn("NombreM");
        mo.addColumn("Edad");
        mo.addColumn("Peso");
        mo.addColumn("Rasa");
        mo.addColumn("FechaNacimientoM");
        mo.addColumn("NombreD");
        mo.addColumn("ApellidoPaterno");
        mo.addColumn("ApellidoMaterno");
        mo.addColumn("Dui");
        mo.addColumn("Telefono");
        mo.addColumn("Direccion");
        mo.addColumn("FechaNacimientoD");
        tab.setModel(mo);
    }*/
    public void initComponents()
    {
        tablaDatos.setFillsViewportHeight(true);
        new FormMascota().campo(modelo, tablaDatos);

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //if(!lblId.getText().equals("Id"))
                //{
                if (JOptionPane.showConfirmDialog(null, "Se eliminará el registro, ¿desea continuar?", "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    //Integer Id = Integer.parseInt(lblId.getText());
                    try {
                        Integer N = new MascotaBL().Eliminar(cod);
                        JOptionPane.showMessageDialog(null, N);
                        if (N > 0) {
                            JOptionPane.showMessageDialog(null, "Los Datos de la mascota y Dueño se han eliminado correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                            //Limpiar();
                            //DesHabilitar();
                            //txtFiltro.setText(null);
                            try
                            {
                                actualisarTabla(new MascotaBL().BuscarM());

                            }
                            catch (SQLException x)
                            {
                                x.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se a podido eliminar los Datos de la mascota y Dueño", "Error", JOptionPane.ERROR_MESSAGE);
                            //txtNombre.requestFocus();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                /*}
                else
                {
                    JOptionPane.showMessageDialog(null, "Debes buscar primero los datos de la Persona para realizar la accion", "Error", JOptionPane.ERROR_MESSAGE);
                    txtNombre.requestFocus();
                }*/
            }
        });
    }




}
