
package controller;

import java.util.List;
import javax.swing.JOptionPane;
import model.Alumnos;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase que gestiona la informacion de la clase Alumnos, obteniendola de la BDD, 
 * modificandola en la BDD o devolviendosela a la vista encargada de mostrar
 * dicha informacion
 * @author migup
 */
public class Ctrl_Alumno {
    //Declaracion de variables
    private Session sesion;
    private Transaction transaccion;
 
    /**
     * Metodo encargado de guardar un objeto de la clase Alumnos en la BDD
     * @param alumno Alumno que se desea guardar
     */
    public void altaAlumno(Alumnos alumno){
        try{
            iniciarConexion();
            // Corresponde al Create de CRUD
            sesion.save(alumno);
            transaccion.commit();
        } catch (HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se ha podido dar de alta "
                     + "el alumno. Compruebe que el alumno no esté dado de alta ya");
        }finally{
            sesion.close();
        }
    }
    
    /**
     * Metodo encargado de modificar los datos de un objeto de la clase Alumnos
     * en la BDD
     * @param alumno Alumno cuyos datos se quieren modificar
     */
    public void modificarAlumno(Alumnos alumno){
        try{
            iniciarConexion();
            // Corresponde al Update de CRUD
            sesion.update(alumno);
            transaccion.commit();
        }catch(HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se ha podido modificar "
                     + "el alumno. Compruebe que el alumno exista");
        }finally{
            sesion.close();
        }
    }
    
    /**
     * Metodo encargado de dar de baja un objeto de la clase Alumnos en la BDD
     * @param alumno Alumno que se desea dar de baja
     */
    public void bajaAlumno(Alumnos alumno){
        try { 
            iniciarConexion(); 
            // Corresponde al Delete de CRUD
            sesion.delete(alumno);
            transaccion.commit();
        }catch(HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se ha podido dar de baja "
                     + "el alumno. Compruebe que el alumno existe o que no esté "
                    + "matriculado en un curso a través de una consulta.");
        }finally{ 
            sesion.close(); 
        }
    }
    
    /**
     * Metodo encargado de consultar los datos de un objeto de la clase Alumnos.     * 
     * @param codigo Codigo que funge como clave primaria del objeto
     * @return Devuelve el objeto de la clase Alumnos identificado a traves del
     * codigo
     */
    public Alumnos consultarAlumno(String codigo){
        Alumnos alumno = null;
        
        try{ 
            iniciarConexion(); 
            // Corresponde al Read de CRUD
            alumno = (Alumnos) sesion.get(Alumnos.class, codigo);            
        }catch(HibernateException he){ 
            manejarExcepcion(he); 
            throw he; 
        }finally { 
            sesion.close(); 
        }
        
        return alumno;
    }
    
    /**
     * Metodo encargado de consultar todos los objeto de la clase Alumnos
     * guardados en la BDD
     * @return Devuelve un lista de objetos de la clase Alumnos
     */
    public List<Alumnos> consultarAlumnos(){
        List<Alumnos> listaAlumnos = null;
        
        try { 
            iniciarConexion(); 
            listaAlumnos = sesion.createQuery("from Alumnos").list(); 
        }catch (HibernateException he) { 
            manejarExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        }
        
        return listaAlumnos;        
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
        throw new HibernateException("Ocurrió un error en la capa de acceso a "
                + "datos:" + he.getMessage(), he); 
    }

    /**
     * Metodo encargado de guardar el objeto si no existia o modificarlo en el 
     * caso de que ya existiese 
     * @param alumno Alumno que se desea insertar o modificar en la BDD
     * @deprecated Si el usuario no es consciente del funcionamiento puede ser
     * confuso
     */
    @Deprecated
    private void guardarActualizar(Alumnos alumno) {
        try{
            iniciarConexion();
            // Corresponde al Create y Update de CRUD
            sesion.saveOrUpdate(alumno);
            transaccion.commit();
        }finally{
            sesion.close();
        }
    }
}
