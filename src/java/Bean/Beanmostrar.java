/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Estudiante.Notas;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Erick
 */
@ManagedBean
@RequestScoped
public class Beanmostrar {
    private String cedula;
    private String lectivo;
    private String alum;
    private String carrera;
    private String materia;
    private ArrayList<Notas> notas;

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
    
    

    public String getAlum() {
        return alum;
    }

    public void setAlum(String alum) {
        this.alum = alum;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    /**
     * Creates a new instance of Beanmostrar
     */
    public Beanmostrar() {
    }
    public void Mostrar()
    {
        Client client=ClientBuilder.newBuilder()
				.property("connection.timeout", 100)
				.register(JacksonJsonProvider.class)
				.build();
		
		 notas=(ArrayList<Notas>)client.target("http://lisrestful.azurewebsites.net/api/notas?cod_estudiante="+this.cedula+"&cod_lectivo="+this.lectivo+"")
		.request().accept("application/json")
		.get(new GenericType<List<Notas>>(){});
                
                alum=notas.get(1).getApe_estudiante()+" "+notas.get(1).getNom_estudiante();
                carrera=notas.get(1).getNom_carrera();
                
               
                for(int i=0; i<notas.size();i++)
                {
                   notas.get(i).getNom_materia();
                   notas.get(i).getPrimer_academica();
                   notas.get(i).getPrimer_promedio();
                   notas.get(i).getSegundo_academica();
                   notas.get(i).getSegundo_evaluacion();
                   notas.get(i).getSegundo_promedio();
                   notas.get(i).getPromedio();
                }
               
                
      
    }
    
         public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getLectivo() {
        return lectivo;
    }

    public void setLectivo(String lectivo) {
        this.lectivo = lectivo;
    }

    public ArrayList<Notas> getNotas() {
        return notas;
    }

    public void setNotas(ArrayList<Notas> notas) {
        this.notas = notas;
    }

    private static class JacksonJsonProvider {

        public JacksonJsonProvider() {
        }
    }
    
    
}
