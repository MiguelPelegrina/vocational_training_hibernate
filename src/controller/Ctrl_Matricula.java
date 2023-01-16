
package controller;

import javax.swing.JOptionPane;
import model.Matriculas;
import model.MatriculasId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase que gestiona la informacion de la clase Matriculas, obteniendola de la BDD, 
 * modificandola en la BDD o devolviendosela a la vista encargada de mostrar
 * dicha informacion
 * @author migup
 */
public class Ctrl_Matricula {
    //Declaracion de variables
    private Session sesion;
    private Transaction transaccion;
    
    /**
     * Metodo encargado de modificar un objeto de la clase Matriculas
     * @param matricula Matriculas cuyos datos se quieren modificar
     */
    public void modificarMatricula(Matriculas matricula){
        try{
            iniciarConexion();
            sesion.update(matricula);
            transaccion.commit();
        }catch(HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se ha podido modificar "
                     + "la matricula.");
        }finally{
            sesion.close();
        }
    }
    
    /**
     * Metodo encargado de consultar los datos de un objeto de la clase 
     * Matriculas 
     * @param codAlu Codigo del alumno, componente de la clave primaria
     * @param codCurso Codigo del curso, componente de la clave primaria
     * @return Devuelve el objeto de la clase Matriculas identificado a traves 
     * de los codigos del alumno y del curso
     */
    public Matriculas consultarMatricula(String codAlu, String codCurso){
        Matriculas matricula = null;
        
        try{ 
            iniciarConexion(); 
            matricula = (Matriculas) sesion.get(Matriculas.class, 
                    new MatriculasId(codAlu, codCurso));            
        }catch(HibernateException he){ 
            manejarExcepcion(he); 
            throw he; 
        }finally { 
            sesion.close(); 
        }
        
        return matricula;
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
