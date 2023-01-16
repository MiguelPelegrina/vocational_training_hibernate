/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Ctrl_Alumno;
import controller.Ctrl_BDD;
import controller.Ctrl_Curso;
import controller.Ctrl_Examen;
import controller.Ctrl_Matricula;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Alumnos;
import model.Cursos;
import model.Examenes;
import model.Matriculas;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author migup
 */
public class Principal extends javax.swing.JFrame {    
    //Declaracion de variables
    public static Ctrl_Alumno controlAlumno = new Ctrl_Alumno();
    public static Ctrl_Curso controlCurso = new Ctrl_Curso();
    public static Ctrl_BDD controlDatos = new Ctrl_BDD();
    public static Ctrl_Matricula controlMatriculas = new Ctrl_Matricula();
    public static Ctrl_Examen controlExamen = new Ctrl_Examen();
    
    private DefaultTableModel dtmAlumnos;
    private DefaultTableModel dtmCursos;    
    private DefaultTableModel dtmMatriculas;
    private DefaultTableModel dtmExamenes;
    
    private String codAlumno;
    private String codCurso;
    private String nombreAlumno;
    private String nombreCurso;
    private short numExamen;
    private Short numExamenes;
    
    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        setTitle("Academia de idiomas");
        setLocationRelativeTo(null);
        // Inicializacion de los modelos
        dtmAlumnos = (DefaultTableModel) tablaAlumnos.getModel();
        dtmCursos = (DefaultTableModel) tablaCursos.getModel();
        dtmMatriculas = (DefaultTableModel) tablaMatriculas.getModel();
        dtmExamenes = (DefaultTableModel) tablaExamenes.getModel();
        
        // Configuramos la tabla Alumnos para rellenar la tabla Matriculas
        tablaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = tablaAlumnos.rowAtPoint(evt.getPoint());  
                if (row >= 0) {
                    codAlumno = (String) tablaAlumnos.getValueAt(row, 0);                
                    nombreAlumno = (String) tablaAlumnos.getValueAt(row, 1);                      
                    rellenarTablaMatriculasAlumno();
                }
            }
        }); 
        // Configuramos la tabla Cursos para rellenar la tabla Matriculas
        tablaCursos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = tablaCursos.rowAtPoint(evt.getPoint());  
                if (row >= 0) {
                    codCurso = (String) tablaCursos.getValueAt(row, 0);                
                    nombreCurso = (String) tablaCursos.getValueAt(row, 1);    
                    numExamenes = (Short) tablaCursos.getValueAt(row, 2); 
                    rellenarTablaMatriculasCurso();
                }
            }
        }); 
        // Configuramos la tabla Matricula para rellenar la tabla Examenes
        tablaMatriculas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaMatriculas.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = tablaMatriculas.rowAtPoint(evt.getPoint());
                if(row >= 0){
                    codAlumno = (String) tablaMatriculas.getValueAt(row, 0);
                    codCurso = (String) tablaMatriculas.getValueAt(row, 2);
                    rellenarTablaExamenes();                    
                }
            }
        });
        // Configuramos la tabla Examenes para rellenar los campos de texto
        tablaExamenes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaExamenes.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                int row = tablaExamenes.rowAtPoint(evt.getPoint());
                if(row >= 0){
                    numExamen = (short) tablaExamenes.getValueAt(row,0);
                    txtFecha.setText(""+tablaExamenes.getValueAt(row, 1));
                    txtNota.setText(""+tablaExamenes.getValueAt(row, 2));
                }
            }
        }); 
        // Rellenamoss las tablas al iniciarlizar el programa
        rellenarTablaAlumnos(dtmAlumnos);
        rellenarTablaCursos(dtmCursos);
    }    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        btnCursos = new javax.swing.JButton();
        btnAlumnos = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaMatriculas = new javax.swing.JTable();
        btnMatricula = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaExamenes = new javax.swing.JTable();
        lblFechaExamen = new javax.swing.JLabel();
        lblNota = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtNota = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnJSON = new javax.swing.JButton();
        btnXML = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Alumno", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);

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

        btnCursos.setText("Gestión cursos");
        btnCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCursosActionPerformed(evt);
            }
        });

        btnAlumnos.setText("Gestión alumnos");
        btnAlumnos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlumnosActionPerformed(evt);
            }
        });

        tablaMatriculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Alumno", "Nombre Alumno", "Codigo Curso", "Nombre Curso", "Nota Media"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaMatriculas);

        btnMatricula.setText("Matricular Alumno en Curso");
        btnMatricula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMatriculaActionPerformed(evt);
            }
        });

        tablaExamenes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero Examen", "Fecha Examen", "Nota"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaExamenes);

        lblFechaExamen.setText("Fecha examen");

        lblNota.setText("Nota");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnJSON.setText("Boletin JSON");
        btnJSON.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJSONActionPerformed(evt);
            }
        });

        btnXML.setText("Listado Matriculas XML");
        btnXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXMLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAlumnos)
                        .addGap(190, 190, 190)
                        .addComponent(btnMatricula)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCursos))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFechaExamen)
                                    .addComponent(lblNota))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtFecha)
                                        .addComponent(txtNota, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                    .addComponent(btnActualizar)))
                            .addComponent(btnJSON)
                            .addComponent(btnXML))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCursos)
                    .addComponent(btnAlumnos)
                    .addComponent(btnMatricula))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFechaExamen)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNota)
                            .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnActualizar)
                        .addGap(19, 19, 19)
                        .addComponent(btnJSON)
                        .addGap(18, 18, 18)
                        .addComponent(btnXML)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlumnosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlumnosActionPerformed
        // Instanciamos un dialogo para gestionar los Alumnos
        GestionAlumnos dialogAlumnos = new GestionAlumnos(this, true);
        dialogAlumnos.setLocationRelativeTo(null);
        dialogAlumnos.setTitle("Gestión alumnos");
        dialogAlumnos.setVisible(true);
        // Actualizamos la tabla
        rellenarTablaAlumnos(dtmAlumnos);
    }//GEN-LAST:event_btnAlumnosActionPerformed

    private void btnCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCursosActionPerformed
        // Instanciamos un dialogo para gestionar los Cursos
        GestionCursos dialogCursos = new GestionCursos(this, true);
        dialogCursos.setLocationRelativeTo(null);
        dialogCursos.setTitle("Gestión cursos");
        dialogCursos.setVisible(true);
        // Actualizamos la tabla
        rellenarTablaCursos(dtmCursos);
    }//GEN-LAST:event_btnCursosActionPerformed

    private void btnMatriculaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMatriculaActionPerformed
        // Comprobamos que se haya elegido un alumno y un curso 
        if(codAlumno != null && codCurso != null){
            controlDatos.altaMatricula(codAlumno, codCurso);      
            // Actualizamos la tabla
            rellenarTablaMatriculasAlumno();
        }else{
            JOptionPane.showMessageDialog(this, "Debe elegir tanto una fila "
                + "de la tabla Alumnos como de la tabla Cursos");
        }
    }//GEN-LAST:event_btnMatriculaActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        short auxNota = -1;        
        Date fecha = null;
        // Comprobamos que este seleccionada una fila
        if(tablaExamenes.getSelectedRow() != -1){
            // Comprobamos que la fecha introducida es valida                 
            try {
                fecha = comprobarFecha(txtFecha.getText()) ;
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null,"Debe introducir una fecha "
                        + "válida con el siguiente formato yyyy-MM-dd");
                return;
            } 
            
            try{
                // Comprobamos que la nota introducida es valida
                auxNota = comprobarNota(txtNota.getText());
                if(auxNota < 0 || auxNota > 10){
                    JOptionPane.showMessageDialog(null,"Debe introducir una nota "
                            + "entre 0 y 10");
                    return;
                }
            }catch(NumberFormatException | NullPointerException e){
                JOptionPane.showMessageDialog(null,"Debe introducir un nota válida"); 
                return;
            }
            // Le pedimos confirmacion al usuario al querer modificar datos
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que"
                            + " quiere modificar la nota del examen " 
                            + numExamen + "?", "Modificar nota", 
                            JOptionPane.YES_NO_OPTION, 
                            JOptionPane.WARNING_MESSAGE,null);        
            if(opcion == 0){
                // Obtenemos el examen de la BDD
                Examenes examen = controlExamen.consultarExamen(codAlumno, codCurso, numExamen);
                // Modificamos los datos del examen elegido
                examen.setDfecexam(fecha);
                examen.setNnotaexam(Long.parseLong(txtNota.getText()));
                // Modificamos el examen en la BDD
                controlExamen.modificarExamen(examen);

                // Obtenemos la lista de todos los examenes de la matricula
                ArrayList<Object[]> examenes = controlExamen.consultarExamenes(codAlumno, codCurso);
                long notaMedia = 0;
                int examenesRealizados = 0;
                // Recorremos todos los examenes para sumar todas las notas y 
                // averiguar cuantos examenes se han realizado ya para poder
                // calcular posteriormente la nota media
                for(Object[] e : examenes){
                    long nota = (long) e[4];
                    if(nota != 0){
                        notaMedia += nota;
                        examenesRealizados++;
                    }
                }
                // Obtenemos la matricula 
                Matriculas matricula = controlMatriculas.consultarMatricula(codAlumno, codCurso); 
                // Modificamos la nota media
                matricula.setNnotamedia((short) (notaMedia/examenesRealizados));        
                // Modificamos el objeto en la BDD
                controlMatriculas.modificarMatricula(matricula);
                // Actualizamos las tablas implicadas
                rellenarTablaMatriculasAlumno();
                rellenarTablaExamenes();
            }            
        }else{
            JOptionPane.showMessageDialog(this, "Elija una fila de la tabla "
                    + "examenes");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnJSONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJSONActionPerformed
        // Declaracion e inicialiazacion de variables
        Object[] datos;
        BufferedWriter bw = null;
        File ficheroJSON = new File(nombreAlumno+".txt");
        // Comprobamos que hay una matricula seleccionada
        if(tablaMatriculas.getSelectedRow() != -1){
            // Obtenemos la lista de los examenes
            ArrayList<Object[]> examenes = controlExamen.consultarExamenes(codAlumno, codCurso);
              
            try {
                // Instanciamos el escritor
                bw = new BufferedWriter(new FileWriter(ficheroJSON));
                // Escribimos toda la informacion correspondiente a cada examen
                bw.write("{\n\"examenes\":[\n\t{\n");
                for(int i = 0; i < examenes.size(); i++){
                    datos = examenes.get(i);
                    bw.write("\t\"ccodalu\":" + "\"" + datos[0] + "\",\n" +
                            "\t\"ccodcurso\":" + "\"" + datos[1]+ "\",\n" +
                            "\t\"nnumexam\":" + datos[2] + ",\n" +
                            "\t\"defecexam\":" + "\"" + datos[3]+ "\",\n" +
                            "\t\"nnotaexam\":" + datos[4]+ "\n"
                    );               
                    if(i + 1 == examenes.size()){
                        bw.write("\t}");
                    }else{
                        bw.write("\t},\n");
                    }
                }
                bw.write("\n]\n}");            
            }catch(IOException ex) {
                JOptionPane.showMessageDialog(null,"Error de entrada/salida");
            }finally{
                try {
                    bw.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,"Error de entrada/salida");
                }
            }
        }else{
            JOptionPane.showMessageDialog(this, "Elija una matricula de la tabla");
        }        
    }//GEN-LAST:event_btnJSONActionPerformed

    private void btnXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXMLActionPerformed
        // Declaracion e inicializacion de variables
        DocumentBuilder documentBuilder = null;
        Transformer xformer = null;
        Source source;
        Result result;
                
        try {
            // Obtenemos una instancia de un documentbuilder para poder parsear 
            // el documento xml
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();                    
        } catch (ParserConfigurationException ex) {
            JOptionPane.showMessageDialog(this,"Error de configuracion de parseo");            
        } 
        // Nos creamos un arbol DOM
        Document doc = documentBuilder.newDocument();
        // Nos creamos el nodo raiz alumnos
        Element alumnos = doc.createElement("alumnos");
        // Anadimos el nodo raiz alumnos a la estructura del DOM
        doc.appendChild(alumnos);
        // Obtenemos todos los objetos de la clase Alumnos
        ArrayList<Alumnos> listaAlumnos = (ArrayList) controlAlumno.consultarAlumnos();
        // Por cada alumno dentro de la lista 
        for(Alumnos a : listaAlumnos){
            // Nos creamos un elemento para cada alumno y lo anadimos a la 
            // estructura del elemento alumnos. Posteriormente le anadimos a 
            // cada alumno sus cursos.             
            Element elementoAlumno = doc.createElement("alumno");
            elementoAlumno.setAttribute("codigo", a.getCcodalu());
            elementoAlumno.setAttribute("nombre", a.getCnomalu());
            alumnos.appendChild(elementoAlumno);
            
            Element elementoCursos = doc.createElement("cursos");
            elementoAlumno.appendChild(elementoCursos);
            for(Matriculas m : a.getListMatriculas()){             
                // Escribimos la informacion de las matriculas en los
                // atributos y el texto
                Element elementoCurso = doc.createElement("curso");
                elementoCurso.setAttribute("codigo", m.getId().getCcodcurso());
                elementoCurso.setAttribute("numero_examenes", m.getCursos().getNnumexa()+"");
                elementoCurso.setAttribute("nota_media", m.getNnotamedia()+"");
                elementoCurso.setTextContent(m.getCursos().getCnomcurso());
                elementoCursos.appendChild(elementoCurso);
            }
        }
        
        try {
            // Obtenemos una instancia de una transformador para poder convertir
            // el documento en un fichero xml
            xformer = TransformerFactory.newInstance().newTransformer();
        } catch (TransformerConfigurationException ex) {
            JOptionPane.showMessageDialog(this,"Error de configuracion de transformacion");            
        }
        // Propiedades del fichero XML de salida
        xformer.setOutputProperty(OutputKeys.METHOD, "xml");
        xformer.setOutputProperty(OutputKeys.INDENT, "yes"); 
        // Definimos la Entrada y Salida de la Transformacion
        source = new DOMSource(doc);
        result = new StreamResult(new File("alumnos.xml"));            
        try {
            // Realizamos la transformacion mediante el metodo transform()
            xformer.transform(source,result);
        } catch (TransformerException ex) {
            JOptionPane.showMessageDialog(this,"Error de transformacion");
        }
    }//GEN-LAST:event_btnXMLActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAlumnos;
    private javax.swing.JButton btnCursos;
    private javax.swing.JButton btnJSON;
    private javax.swing.JButton btnMatricula;
    private javax.swing.JButton btnXML;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblFechaExamen;
    private javax.swing.JLabel lblNota;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTable tablaExamenes;
    private javax.swing.JTable tablaMatriculas;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables

    /**
     * Metodo encargado de rellenar la tabla de Alumnos con los datos de la BDD
     * @param dtm Modelo de tabla correspondiente a la tabla que se quiere 
     * rellenar
     */
    protected void rellenarTablaAlumnos(DefaultTableModel dtm) {
        dtm.setRowCount(0);
        for(Alumnos a : controlAlumno.consultarAlumnos()){
            dtm.addRow(new Object[]{a.getCcodalu(), a.getCnomalu()});
        }
    }

    /**
     * Metodo encargado de rellenar la tabla de Cursos con los datos de la BDD
     * @param dtm Modelo de tabla correspondiente a la tabla que se quiere 
     * rellenar
     */
    protected void rellenarTablaCursos(DefaultTableModel dtm) {
        dtm.setRowCount(0);
        for(Cursos c : controlCurso.consultarCursos()){
            dtm.addRow(new Object[]{c.getCcodcurso(), c.getCnomcurso(), c.getNnumexa()});
        }
    }
    
    /**
     * Metodo encargado de rellenar la tabla de Matriculas con los datos de la 
     * BDD a partir del Alumno elegido
     */
    private void rellenarTablaMatriculasAlumno() {
        dtmMatriculas.setRowCount(0);
        for(Object[] datos : controlDatos.consultarMatriculasAlumnos(codAlumno)){            
            dtmMatriculas.addRow(datos);
        }        
    }
    
    /**
     * Metodo encargado de rellenar la tabla de Matriculas con los datos de la 
     * BDD a partir del Curso elegido 
     */
    private void rellenarTablaMatriculasCurso() {
        dtmMatriculas.setRowCount(0);
        for(Object[] datos : controlDatos.consultarMatriculasCursos(codCurso)){            
            dtmMatriculas.addRow(datos);
        }        
    }
    
    /**
     * Metodo encargado de rellenar la tabla de Examenes con los datos de la 
     * BDD a partir de la Matricula elegida 
     */
    public void rellenarTablaExamenes(){
        dtmExamenes.setRowCount(0);        
        for(Object[] datos : controlExamen.consultarExamenes(codAlumno, codCurso)){
            Object[] aux = new Object[]{datos[2], datos[3], datos[4]};
            dtmExamenes.addRow(aux);            
        }
    }
    
    /* Metodos auxiliares
    Metodos de clase necesarios en diferentes vistas para comprobar la validez 
    de los datos introducidos
    */
    /**
     * Metodo que comprueba que se trata de un numero
     * @param campoTexto String que se comprueba
     * @return Devuelve un numero, -1 por defecto
     * @throws NullPointerException Se lanza si no se introducen datos
     * @throws NumberFormatException Se lanza si el formato es incorrecto
     */
    public short comprobarNota(String campoTexto) throws 
            NullPointerException,NumberFormatException{
        short numero = -1;        
        numero = Short.parseShort(campoTexto);        
        return numero;
    }       
    
    /**
     * Metodo que comprueba que se trata de una fecha con el formato dd/MM/yyyy
     * @param stringFecha String que se comprueba
     * @return Devuelve un String
     * @throws ParseException Se lanza si el formato es incorrecto
     */
    public Date comprobarFecha(String stringFecha) throws ParseException{
        Date fecha = null;
        // Formato correspondiente a mis SQLDeveloper
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        // Para que fuera como en el ejemplo
        //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        
        fecha = formato.parse(stringFecha);      
        
        return fecha;
    }
}

