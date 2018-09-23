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

public class FormMascota {
    public JPanel panelRais;
    private JTextField txtNombreM;
    private JTextField txtEdadM;
    private JTextField txtPeso;
    private JTextField txtRasa;
    private JTextField txtFechaM;
    private JTextField txtNombreD;
    private JTextField txtApellidoP;
    private JTextField txtApellidoM;
    private JTextField txtDui;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtFechaD;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnEditar;
    private JTextField txtFiltro;
    private JTable tablaMascota;
    private JButton btnMostrar;

    private List<mascota> personaListado;
    DefaultTableModel modelo = new DefaultTableModel();
    Long cod = null;
    AtomicLong aton = new AtomicLong();

    public FormMascota()
    {
        initComponents();
        try
        {
            actualisarTabla(new MascotaBL().BuscarM());
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        tablaMascota.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int y = tablaMascota.getSelectedRow();
                cod = Long.valueOf(tablaMascota.getValueAt(y,0).toString());
                try
                {
                    new MascotaBL().BuscarM().stream().filter(x -> x.getId().equals(cod)).forEach(p->{
                        aton.set(p.getId());
                        try
                        {
                            new MascotaBL().cargarMascota(p.getId()).forEach(pe -> {
                                txtNombreM.setText(pe.getNombreM());
                                txtEdadM.setText(Integer.toString(pe.getEdad()));
                                txtPeso.setText(pe.getPeso());
                                txtRasa.setText(pe.getRasa());
                                txtFechaM.setText(pe.getFechaNacimientoM());
                                txtNombreD.setText(pe.getNombreD());
                                txtApellidoP.setText(pe.getApellidoPaterno());
                                txtApellidoM.setText(pe.getApellidoMaterno());
                                txtDui.setText(pe.getDui());
                                txtTelefono.setText(pe.getTelefono());
                                txtDireccion.setText(pe.getDireccion());
                                txtFechaD.setText(pe.getFechaNacimientoD());
                            });
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    });
                }
                catch (Exception exx)
                {
                    exx.printStackTrace();
                }
            }
        });
        /*txtFiltro.addKeyListener(new KeyAdapter() {
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
        });*/
        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MostrarDatos md = new MostrarDatos();
                md.pack();
                md.setVisible(true);
            }
        });
    }
    public void campo(DefaultTableModel mo, JTable tab) {
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
    }
    private void actualisarTabla(List<mascota> mas)
    {
        reiniciarJTable(tablaMascota);
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
        tablaMascota.setModel(modelo);
    }
    private void reiniciarJTable(JTable jTable) {
        DefaultTableModel modelo = (DefaultTableModel) jTable.getModel();
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }

    }

    public void initComponents()
    {

        tablaMascota.setFillsViewportHeight(true);
        campo(modelo, tablaMascota);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //if(!txtNombre.getText().isEmpty() || !txtApellidoP.getText().isEmpty() || !txtApellidoM.getText().isEmpty() || !txtDUI.getText().isEmpty() || !txtFecha.getText().isEmpty() || !txtDireccion.getText().isEmpty()) {
                    mascota m = new mascota();
                    m.setNombreM(txtNombreM.getText());
                    m.setEdad(Integer.parseInt(txtEdadM.getText()));
                    m.setPeso(txtPeso.getText());
                    m.setRasa(txtRasa.getText());
                    m.setFechaNacimientoM(txtFechaM.getText());
                    m.setNombreD(txtNombreD.getText());
                    m.setApellidoPaterno(txtApellidoP.getText());
                    m.setApellidoMaterno(txtApellidoM.getText());
                    m.setDui(txtDui.getText());
                    m.setTelefono(txtTelefono.getText());
                    m.setDireccion(txtDireccion.getText());
                    m.setFechaNacimientoD(txtFechaD.getText());
                    try {
                        int N = new MascotaBL().GuardarMascota(m);
                        if (N > 0) {
                            JOptionPane.showMessageDialog(null, "Datos de la Mascota y el dueño registrados correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                           // Limpiar();
                            //DesHabilitar();
                            /*try
                            {
                                actualisarTabla(new PersonaBL().BuscarC() , new PersonaBL().Buscar());

                            }
                            catch (SQLException x)
                            {
                                x.printStackTrace();
                            }*/
                            try
                            {
                                actualisarTabla(new MascotaBL().BuscarM());
                            }
                            catch (SQLException ex)
                            {
                                ex.printStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido registrar los Datos de la Mascota y el dueño", "Error", JOptionPane.ERROR_MESSAGE);
                            txtNombreM.requestFocus();
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                /*}
                else
                {
                    JOptionPane.showMessageDialog(null, "Debes llenar todos los datos de la Persona", "Error", JOptionPane.ERROR_MESSAGE);
                    txtNombre.requestFocus();
                }*/
            }
        });
        btnEditar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int y = tablaMascota.getSelectedRow();
                cod = Long.valueOf(tablaMascota.getValueAt(y, 0).toString());
                //if (!lblId.getText().equals("Id"))
                //{
                if (btnEditar.getText().equals("Editar"))
                {
                   //Habilitar();
                    btnEditar.setText("Actualizar");
                }
                else if (btnEditar.getText().equals("Actualizar"))
                {

                    try {
                        new MascotaBL().BuscarM().stream().filter(n -> n.getId().equals(aton.get())).forEach(p ->
                        {
                            //Personas p = new Personas();
                            //p.setId(cod);
                            p.setNombreD(txtNombreM.getText());
                            p.setEdad(Integer.parseInt(txtEdadM.getText()));
                            p.setPeso(txtPeso.getText());
                            p.setRasa(txtRasa.getText());
                            p.setFechaNacimientoM(txtFechaM.getText());
                            p.setNombreD(txtNombreD.getText());
                            p.setApellidoPaterno(txtApellidoP.getText());
                            p.setApellidoMaterno(txtApellidoM.getText());
                            p.setDui(txtDui.getText());
                            p.setTelefono(txtTelefono.getText());

                            p.setDireccion(txtDireccion.getText());
                            p.setFechaNacimientoD(txtFechaD.getText());


                            int N = 0;
                            try
                            {
                                N = new MascotaBL().EditarMascota(p);
                            }
                            catch (SQLException e1)
                            {
                                e1.printStackTrace();
                            }
                            if (N > 0) {
                                JOptionPane.showMessageDialog(null, "Los datos de la Persona actualizados correctamente", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                                //Limpiar();
                                //DesHabilitar();
                                btnEditar.setText("Editar");
                                //txtFiltro.setText(null);
                                try
                                {
                                    actualisarTabla(new MascotaBL().BuscarM());
                                }
                                catch (SQLException ex)
                                {
                                    ex.printStackTrace();
                                }
                            } else
                            {
                                JOptionPane.showMessageDialog(null, "No se ha podido actualizar los datos de la Persona", "Error", JOptionPane.ERROR_MESSAGE);
                                txtNombreM.requestFocus();
                            }

                        });
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
                //}
                /*else
                {
                    JOptionPane.showMessageDialog(null, "Debes buscar primero los datos de la Persona para realizar la accion", "Error", JOptionPane.ERROR_MESSAGE);
                    txtNombre.requestFocus();
                }*/
            }
        });
    }
}
