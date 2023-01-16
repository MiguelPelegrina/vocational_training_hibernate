
package controller;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;
import model.Examenes;
import model.ExamenesId;
import model.Matriculas;
import model.MatriculasId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase que gestiona la informacion de la clase Examenes, obteniendola de la BDD, 
 * modificandola en la BDD o devolviendosela a la vista encargada de mostrar
 * dicha informacion
 * @author migup
 */
public class Ctrl_Examen {
    //Declaracion de variables
    private Session sesion;
    private Transaction transaccion;
    
    /**
     * Metodo encargado de consultar los datos de un objeto de la clase Examenes
     * @param codAlu Codigo del alumno, componente de la clave primaria 
     * @param codCurso Codigo del curso, componente de la clave primaria 
     * @param numExamen Numero del examen, componente de la clave primaria 
     * @return Devuelve el objeto de la clase Examenes identificado a traves del
     * codigo
     */
    public Examenes consultarExamen(String codAlu, String codCurso, short numExamen){
        Examenes examen = null;
        
        try{ 
            iniciarConexion(); 
            examen = (Examenes) sesion.get(Examenes.class, new ExamenesId(
                    codAlu, codCurso, numExamen));            
        }catch(HibernateException he){ 
            manejarExcepcion(he); 
            throw he; 
        }finally { 
            sesion.close(); 
        }
        
        return examen;
    }
    
    /**
     * Metodo encargado de modificar los datos de un objeto de la clase Examenes
     * en la BDD
     * @param examen Examen cuyos valores se quieren modificar
     */
    public void modificarExamen(Examenes examen){
        try{
            iniciarConexion();
            sesion.update(examen);
            transaccion.commit();
        }catch(HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se ha podido modificar "
                     + "el examen.");
        }finally{
            sesion.close();
        }
    }
    
    /**
     * Metodo encargado de consultar los datos de un objeto de la clase 
     * Examenes 
     * @param codigoAlumno Codigo del alumno, componente de la clave primaria
     * @param codigoCurso Codigo del curso, componente de la clave primaria
     * @return Devuelve un lista de objetos de la clase Examenes
     */
    public ArrayList<Object[]> consultarExamenes(String codigoAlumno, String codigoCurso){
        ArrayList<Object[]> listaDatos = new ArrayList<>();
        iniciarConexion();
        // Obtenemos la Matricula a traves de codigo del alumno y del curso
        Matriculas matricula = (Matriculas) sesion.get(Matriculas.class, 
                new MatriculasId(codigoAlumno, codigoCurso));
        // Obtenemos todos los examenes de dicha matricula        
        ArrayList<Examenes> examenes = matricula.getListExamenes();           
        Collections.sort(examenes);
        // Rellenamos la lista con los datos de los examenes
        for (Examenes examen : examenes) {
            listaDatos.add(new Object[]{
                examen.getId().getCcodalu(),
                examen.getId().getCcodcurso(),
                examen.getId().getNnumexam(), 
                examen.getDfecexam(), 
                examen.getNnotaexam()});
        }
        
        return listaDatos;        
    }
    
    // Metodos auxiliares
    /**
     * Metodo encargado de establecer la conexion
     * @throws HibernateException 
     */
    private void iniciarConexion() throws HibernateException { 
        sesion = NewHibernateUtil.getSessionFactory().openSession(); 
        transaccion = sesion.beginTransaction(); 
    } 
    
    /**
     * Metodo encargado de cancelar la transaccion cuando se produce una 
     * excepcion y relanzar la excepcion para que puede ser trata de forma mas 
     * especifica en el codigo que la produce. 
     * @param he HibernateException que produce la excepcion
     * @throws HibernateException 
     */
    private void manejarExcepcion(HibernateException he) throws HibernateException { 
        transaccion.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    }
}
