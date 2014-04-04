
package br.edu.ifsp.eventos.classes.web.sistema;


import br.edu.ifsp.eventos.classes.dao.connection.ConnectionFactory;
import java.util.ArrayList;


public abstract class Sistema {
    private static ArrayList<Pagina> listaPaginas = new ArrayList();
    private static Pagina paginaAtual = null;
    public static ArrayList<Pagina> getListaPagina() {
        return listaPaginas;
    }

    public static void novaPagina(Pagina m){
        listaPaginas.add(m);
    }

    public static Pagina getPaginaAtual() {
        return paginaAtual;
    }

    public static void setPaginaAtual(Pagina secaoAtual) {
        Sistema.paginaAtual = secaoAtual;
    }
    
    public static String estilo = "greenbook";

    public static String getEstilo() {
        return estilo;
    }

    public static void setEstilo(String estilo) {
        Sistema.estilo = estilo;
    }
    public static boolean isBancoDeDadosOK(){
        try {
            ConnectionFactory.getConnection();
            return true;
        } catch(Exception ex){
            return false;
        }
        
    }

}
