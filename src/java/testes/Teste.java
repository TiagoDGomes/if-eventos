/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import br.edu.ifsp.eventos.classes.web.servlets.ParticiparEvento;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Tiago
 */
public class Teste {

    public static void main(String[] args) {
        JSONObject j1 = new JSONObject();
        JSONObject j2 = new JSONObject();
        ArrayList <String> a = new ArrayList<String>();
        a.add("IF");
        a.add("FAE");
        try {
            j2.put("joao", "25");
            j1.put("pessoa", a);
        } catch (JSONException ex) {
            Logger.getLogger(ParticiparEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, j1.toString());
    }
}
