/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Data.UsuarioData;
import Model.Entities.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class Principal {
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Instanciar objeto de la clase Usuario
        Usuario usuario = new Usuario();        
        // Instanciar objeto de la clase UsuarioData
        UsuarioData usuarioData = new UsuarioData();
        
        
        //Definir una lista de tipo ArrayList de tipo Usuario
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        // Obtener la lista de usuario de acuerdo al parámetro pasado al método
        String areaDeTrabajo = "Deporte";
        listaUsuarios = usuarioData.obetenerUsuariosPorArea(areaDeTrabajo);
        
        // Mostrar lista de Usuarios
        System.out.println("LISTA DE USUARIOS DEL AREA DE TRABAJO:" );
        System.out.println("======================================");
        if ( !listaUsuarios.isEmpty() ) {
            for ( Usuario us : listaUsuarios ) {
                System.out.println("Usuario :" + us);
            }
            System.out.println("Cantidad de registros econtrados: " + listaUsuarios.size());
        } else {
            System.out.println("No existen usuario en el area de trabajo: " + areaDeTrabajo);
        }
        
    }
}
