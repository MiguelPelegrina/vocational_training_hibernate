
package view;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import model.Cursos;
import model.Matriculas;
import static view.Principal.controlCurso;

/**
 *
 * @author migup
 */
public class GestionCursos extends javax.swing.JDialog {
    //Declaracion de variables
    private Principal padre;
    private DefaultTableModel dtmCursos;
    private String codigo;
    private String nombre;
    private short numeroExamenes;
    // Filtro de tabla
    private TableRowSorter<TableModel> filtroTabla;
    
    /**
     * Creates new form GestionCursos
     */
    public GestionCursos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        // Configuramos la tabla Cursos para rellenar los campos de texto
        dtmCursos = (DefaultTableModel) tablaCursos.getModel();
        tablaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tablaCursos.rowAtPoint(evt.getPoint());
                if (row >= 0) {
                    codigo = (String) tablaCursos.getValueAt(row, 0);
                    nombre = (String) tablaCursos.getValueAt(row, 1);
                    numeroExamenes =  (short) tablaCursos.getValueAt(row, 2);
                    txtCodigo.setText(codigo);
                    txtNombre.setText(nombre);
                    txtExamenes.setText(numeroExamenes+"");
                }
            }
        });
        
        // Inicializamos el filtroTabla con el modelo de la tabla
        filtroTabla = new TableRowSorter<>(dtmCursos);
        // Asignamos a la tabla el rowSorter
        tablaCursos.setRowSorter(filtroTabla);
         // Rellenamos la tabla Cursos
        padre = (Principal) parent;
        padre.rellenarTablaCursos(dtmCursos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        btnConsulta = new javax.swing.JButton();
        btnModificacion = new javax.swing.JButton();
        btnAlta = new javax.swing.JButton();
        btnBaja = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtExamenes = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Curso", "Nombre Curso", "Nº Examenes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaCursos);

        btnConsulta.setText("Consulta");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        btnModificacion.setText("Modificación");
        btnModificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificacionActionPerformed(evt);
            }
        });

        btnAlta.setText("Alta");
        btnAlta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAltaActionPerformed(evt);
            }
        });

        btnBaja.setText("Baja");
        btnBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajaActionPerformed(evt);
            }
        });

        jLabel1.setText("Nº Exámenes");

        jLabel2.setText("Nombre");

        jLabel3.setText("Código");

        jLabel4.setText("Curso");

        jLabel5.setText("Código para consultar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAlta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBaja)
                        .addGap(18, 18, 18)
                        .addComponent(btnModificacion))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(btnConsulta))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtExamenes, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                .addComponent(txtCodigo)
                                .addComponent(jLabel4))
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(316, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtExamenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlta)
                    .addComponent(btnBaja)
                    .addComponent(btnModificacion))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsulta)
                    .addComponent(jLabel5))
                .addGap(323, 323, 323))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAltaActionPerformed
        short auxExamenes = -1;  
        
        // Comprobamos que los campos de texto no esten vacios
        if(!txtCodigo.getText().trim().equals("") && !txtNombre.getText().trim().equals("") && !txtExamenes.getText().trim().equals("")){
            // Comprobamos que la nota introducida sea valida, sino avisamos al
            // usuario
            try{
                auxExamenes = comprobarExamenes(txtExamenes.getText());        
                if(auxExamenes < 0){
                    JOptionPane.showMessageDialog(null,"Debe introducir un "
                                + "numero de exámenes válido mayor a 0");
                    return;                
                }
            }catch(NumberFormatException | NullPointerException e){
                JOptionPane.showMessageDialog(null,"Debe introducir un número de "
                        + "exámenes válido"); 
                return;
            }
            // Damos de alta el curso
            controlCurso.altaCurso(new Cursos(txtCodigo.getText(), txtNombre.getText(), Short.parseShort(txtExamenes.getText())));
            // Actualizamos la tabla
            padre.rellenarTablaCursos(dtmCursos);
        }else{
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.");
        }        
    }//GEN-LAST:event_btnAltaActionPerformed

    private void btnBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajaActionPerformed
        // Comprobamos que se haya elegido una fila, sino informamos al usuario
        if(tablaCursos.getSelectedRow() != -1){
            // Pedimos al usuario que nos confirme su decision de borrar datos
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que"
                        + " quiere dar de baja el curso con el codigo " 
                        + codigo + "?", "Dar de baja", JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE,null);        
            if(opcion == 0){
                // Damos de baja el curso
                controlCurso.bajaCurso(new Cursos(codigo, nombre, numeroExamenes));
                // Actualizamos la tabla
                padre.rellenarTablaCursos(dtmCursos);
            }            
        } else{
            JOptionPane.showMessageDialog(null, "Debe elegir un curso de la tabla");
        }                 
    }//GEN-LAST:event_btnBajaActionPerformed

    private void btnModificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificacionActionPerformed
        short auxExamenes = -1;         
        
        // Comprobamos que los campos de texto no esten vacios, sino informamos
        // al usuario
        if(!txtCodigo.getText().trim().equals("") && !txtNombre.getText().trim().equals("") && !txtExamenes.getText().trim().equals("")){            
            // Comprobamos que la nota introducida sea valida
            try{
                auxExamenes = comprobarExamenes(txtExamenes.getText());        
                if(auxExamenes < 0){
                    JOptionPane.showMessageDialog(null,"Debe introducir un "
                                + "numero de exámenes válido mayor a 0");
                    return;                
                }
            }catch(NumberFormatException | NullPointerException e){
                JOptionPane.showMessageDialog(null,"Debe introducir un número de "
                    + "exámenes válido"); 
                return;
            }
            // Pedimos al usuario que nos confirme su decicion de modificar datos
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que"
                            + " quiere modificar los datos del curso con el codigo " 
                            + codigo + "?", "Modificar", JOptionPane.YES_NO_OPTION, 
                            JOptionPane.WARNING_MESSAGE,null);        
            if(opcion == 0){
                // Modificamos el curso
                controlCurso.modificarCurso(new Cursos(txtCodigo.getText(), txtNombre.getText(), Short.parseShort(txtExamenes.getText())));
                // Actualizamos la tabla
                padre.rellenarTablaCursos(dtmCursos);          
            }              
        }else{
            JOptionPane.showMessageDialog(this, "Los campos no pueden estar vacíos.");
        }
    }//GEN-LAST:event_btnModificacionActionPerformed

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        // Esta parte se diseñado como una vista maestro-detalle, siendo la 
        // tabla el maestro que muestra datos limitados y la consulta el detalle
        // que permite consultar una mayor cantidad de datos, como en este caso
        // los alumnos que estan matriculados en el curso
        String codigoConsulta = txtFiltro.getText().trim();
        // Comprobamos el campo no este vacio
        if(codigoConsulta.length() == 0){
            JOptionPane.showMessageDialog(this,"Debe introducir primero un "
                    + "codigo antes de poder consultar",
                    "Instrucciones",JOptionPane.INFORMATION_MESSAGE);
        }else{
            try{
                // Consultamos el curso deseado
                Cursos curso = controlCurso.consultarCurso(codigoConsulta);
                // Obtenemos las matriculas del curso
                ArrayList<Matriculas> listaMatriculas = curso.getListMatriculas();
                String matriculas = "";
                if(!listaMatriculas.isEmpty()){    
                    matriculas += "\nAlumnos matriculados:";
                    for(Matriculas m : listaMatriculas){
                        matriculas += "\n   " + m.getId().getCcodalu();
                    }  
                } 
                // Mostramos los datos obtenidos
                JOptionPane.showMessageDialog(null, "Datos del curso: \n" + 
                        "Codigo: " + curso.getCcodcurso() + "\nNombre: " + 
                        curso.getCnomcurso() + "\nNº Examenes: " + 
                        curso.getNnumexa() + matriculas);
                // Controlamos las excepciones                    
            }catch(NullPointerException npe){
                JOptionPane.showMessageDialog(null, "El curso con el " + 
                            "codigo " + codigoConsulta + " no existe");
            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Se ha producido un error",
                        "Error",JOptionPane.ERROR_MESSAGE);
            }            
        }
    }//GEN-LAST:event_btnConsultaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionCursos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionCursos dialog = new GestionCursos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlta;
    private javax.swing.JButton btnBaja;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnModificacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtExamenes;
    private javax.swing.JTextField txtFiltro;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    /**
     * Metodo que comprueba que se trata de un numero
     * @param campoTexto String que se comprueba
     * @return Devuelve un numero, -1 por defecto
     * @throws NullPointerException Se lanza si no se introducen datos
     * @throws NumberFormatException Se lanza si el formato es incorrecto
     */
    public short comprobarExamenes(String campoTexto) throws 
            NullPointerException,NumberFormatException{
        short numero = -1;        
        numero = Short.parseShort(campoTexto);        
        return numero;
    } 
}
