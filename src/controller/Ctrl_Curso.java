
package controller;

import java.util.List;
import javax.swing.JOptionPane;
import model.Cursos;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Clase que gestiona la informacion de la clase Cursos, obteniendola de la BDD, 
 * modificandola en la BDD o devolviendosela a la vista encargada de mostrar
 * dicha informacion
 * @author migup
 */
public class Ctrl_Curso {
    //Declaracion de variables

    private Session sesion;
    private Transaction transaccion;
 
    /**
     * Metodo encargado de guardar un objeto de la clase Cursos en la BDD
     * @param curso Curso que se desea guardar
     */
    public void altaCurso(Cursos curso){
         try { 
            iniciarConexion(); 
            // Corresponde al Create de CRUD
            sesion.save(curso);
            transaccion.commit();
        }catch (HibernateException he) { 
             JOptionPane.showMessageDialog(null, "No se ha podido dar de alta "
                     + "el curso. Compruebe que el curso no esté dado de alta ya");
        } finally { 
            sesion.close(); 
        }
    }
    
    /**
     * Metodo encargado de modificar los datos de un objeto de la clase Alumnos
     * en la BDD
     * @param curso Curso cuyos datos se quieren modificar
     */
    public void modificarCurso(Cursos curso){
        try { 
            iniciarConexion(); 
            // Corresponde al Update de CRUD
            sesion.update(curso);
            transaccion.commit();
        }catch (HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se ha podido modificar "
                     + "el curso. Compruebe que el curso exista");
        } finally { 
            sesion.close(); 
        }
    }
    
    /**
     * Metodo encargado de dar de baja un objeto de la clase Cursos en la BDD
     * @param curso Curso que se desea dar de baja
     */
    public void bajaCurso(Cursos curso){
        try { 
            iniciarConexion(); 
            // Corresponde al Delete de CRUD
            sesion.delete(curso);
            transaccion.commit();
        }catch (HibernateException he) { 
            JOptionPane.showMessageDialog(null, "No se ha podido dar de baja "
                     + "el curso. Compruebe que el curso exista o que no haya "
                    + "alumnos matriculados en este."); 
        } finally { 
            sesion.close(); 
        }
    }
    
    /**
     * Metodo encargado de consultar los datos de un objeto de la clase Cursos.      
     * @param codigo Codigo que funge como clave primaria del objeto
     * @return Devuelve el objeto de la clase Cursos identificado a traves del
     * codigo
     */
    public Cursos consultarCurso(String codigo){
        Cursos curso = null;
        
        try { 
            iniciarConexion(); 
            // Corresponde al Read de CRUD
            curso = (Cursos) sesion.get(Cursos.class, codigo);            
        }catch (HibernateException he) { 
            manejarExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        }
        
        return curso;
    }
    
    /**
     * Metodo encargado de consultar todos los objeto de la clase Cursos
     * guardados en la BDD
     * @return Devuelve un lista de objetos de la clase Cursos
     */
    public List<Cursos> consultarCursos(){
        List<Cursos> listaCursos = null;
        
        try { 
            iniciarConexion(); 
            listaCursos = sesion.createQuery("from Cursos").list(); 
        }catch (HibernateException he) { 
            manejarExcepcion(he); 
            throw he; 
        } finally { 
            sesion.close(); 
        }
        
        return listaCursos;        
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
    
    /**
     * Metodo encargado de guardar el objeto si no existia o modificarlo en el 
     * caso de que ya existiese 
     * @param curso Curso que se desea insertar o modificar en la BDD
     * @deprecated Si el usuario no es consciente del funcionamiento puede ser
     * confuso
     */
    @Deprecated
    private void guardarActualizar(Cursos curso) {
        try{
            iniciarConexion();
            sesion.saveOrUpdate(curso);
            transaccion.commit();
        }finally{
            sesion.close();
        }
    }
}
