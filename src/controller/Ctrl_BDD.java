
package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import javax.persistence.ParameterMode;
import javax.swing.JOptionPane;
import model.Alumnos;
import model.Cursos;
import model.Examenes;
import model.Matriculas;
import model.MatriculasId;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import static view.Principal.controlAlumno;
import static view.Principal.controlCurso;

/**
 *
 * @author migup
 */
public class Ctrl_BDD {
    //Declaracion de variables
    private Session sesion;
    private Transaction transaccion;
 
    /**
     * Metodo encargado de guardar un objeto de la clase Matriculas en la BDD a 
     * traves de una procedimiento almacenado en la BDD
     * @param codAlumno Codigo del alumno, componente de la clave primaria
     * @param codCurso Codigo del curso, componente de la clave primaria
     */
    public void altaMatricula(String codAlumno, String codCurso) {
        int error = -1;
        
        iniciarConexion();
        // Nos creamos una instancia de una llamada al procedimiento almacenado
        ProcedureCall call = sesion.createStoredProcedureCall("sp_AltaMatricula");
        // Registramos los parametros necesarios para el procedimiento y le 
        // asignamos el valor correspondiente 
        call.registerParameter(1, String.class, ParameterMode.IN).bindValue(codAlumno);
        call.registerParameter(2, String.class, ParameterMode.IN).bindValue(codCurso);
        call.registerParameter(3, Integer.class, ParameterMode.OUT);
        // Obtenemos el error 
        error = (int) call.getOutputs().getOutputParameterValue(3);
        // Si ha producido un error se lo mostramos al usuario
        if(error == -1){
            JOptionPane.showMessageDialog(null, "Se ha producido un error a la"
                    + " hora de matricular el alumno " + codAlumno + " en el "
                            + "curso " + codCurso + ". Probablemente ya esté"
                                    + "dado de alta esa matricula.");
        }
        
        sesion.getTransaction().commit();
        sesion.close();
    }
    
    /**
     * Metodo encargado de consultar todas las Matriculas de un objeto de la
     * clase Alumnos 
     * @param codAlumno Codigo del alumno cuyas Matriculas se quieren consultar
     * @return Devuelve una lista de objetos con la informacion del codigo y 
     * codigo del alumno y del curso y la nota media respectiva
     */
    public ArrayList<Object[]> consultarMatriculasAlumnos(String codAlumno){
        ArrayList<Object[]> listaDatos = new ArrayList<>();
        iniciarConexion();
        // Obtenemos el alumno
        Alumnos alumno = controlAlumno.consultarAlumno(codAlumno); 
        // Obtenemos sus matriculas
        ArrayList<Matriculas> matriculas = alumno.getListMatriculas();        
        for (Matriculas matricula : matriculas) {
            // anadimos sus datos a la lista
            listaDatos.add(new Object[]{alumno.getCcodalu(),
                alumno.getCnomalu(),
                matricula.getCursos().getCcodcurso(),
                matricula.getCursos().getCnomcurso(),
                matricula.getNnotamedia()
            });
        }
        
        sesion.close();        
        return listaDatos;        
    } 
    
    /**
     * Metodo encargado de consultar todas las Matriculas de un objeto de la
     * clase Matriculas  
     * @param codCurso Codigo del curso cuyos Matriculas se quieren consultar
     * @return Devuelve una lista de objetos con la informacion del codigo y 
     * codigo del alumno y del curso y la nota media respectiva
     */
    public ArrayList<Object[]> consultarMatriculasCursos(String codCurso){
        ArrayList<Object[]> listaDatos = new ArrayList<>();
        iniciarConexion();
        // Obtenemos el curso       
        Cursos curso = controlCurso.consultarCurso(codCurso);
        // Obtenemos sus matriculas
        ArrayList<Matriculas> matriculas = curso.getListMatriculas();        
        for (Matriculas matricula : matriculas) {
            // anadimos sus datos a la lista
            listaDatos.add(new Object[]{matricula.getAlumnos().getCcodalu(),
                matricula.getAlumnos().getCnomalu(),
                matricula.getCursos().getCcodcurso(),
                matricula.getCursos().getCnomcurso(),
                matricula.getNnotamedia()
            });
        }
        
        return listaDatos;
    }
    
    /**
     * /**
     * Metodo encargado de consultar los datos de un objeto de la clase 
     * Examenes correspondiente a la tabla Examenes
     * @param codigoAlumno Codigo del alumno, componente de la clave primaria
     * @param codigoCurso Codigo del curso, componente de la clave primaria
     * @return Devuelve un lista de objetos con informacion parcial de la clase 
     * Examenes con informacion limitada
     * @deprecated Se trata de un metodo redudante 
     */
    @Deprecated
    public ArrayList<Object[]> consultarExamenes(String codigoAlumno, String codigoCurso){
        ArrayList<Object[]> listaDatos = new ArrayList<>();
        iniciarConexion();
        // Obtenemos la matricula
        Matriculas matricula = (Matriculas) sesion.get(Matriculas.class, 
                new MatriculasId(codigoAlumno, codigoCurso));
        // Obtenemos sus examenes
        ArrayList<Examenes> examenes = matricula.getListExamenes();        
        Collections.sort(examenes);
        for (Examenes examen : examenes) {
            // anadimos sus datos a la lista
            listaDatos.add(new Object[]{examen.getId().getNnumexam(),
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
