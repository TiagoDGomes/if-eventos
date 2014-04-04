/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsp.eventos.classes;

import br.edu.ifsp.eventos.classes.dao.EventoDAO;
import br.edu.ifsp.eventos.classes.dao.InscritoDAO;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago
 */
public class Evento {

    public static void remover(Evento e) {
        try {
            new EventoDAO().excluir(e);
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private long id = -1;
    private String nome;
    private String descricao;
    private int vagas;
    private Time horarioEvento;
    private Date dataEvento;
    private Palestrante palestrante;
    private BufferedImage imagem;
    //private ArrayList<Inscrito> listaInscrito = new ArrayList<Inscrito>();

    public static ArrayList<Evento> getListaEventos(boolean inclusiveJaRealizados) {
        try {
            return new EventoDAO().listarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<Evento>();
    }
    public static ArrayList<Evento> getListaEventos() {
        return getListaEventos(true);
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Palestrante getPalestrante() {
        return palestrante;
    }

    public void setPalestrante(Palestrante palestrante) {
        this.palestrante = palestrante;
    }

    public Date getData() {
        return dataEvento;
    }

    public void setData(Date data) {
        this.dataEvento = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Time getHorario() {
        return horarioEvento;
    }
    
    
    public String getHorarioSdf(){
        //return new SimpleDateFormat("HH:mm").format(horarioEvento);

        //return "00:00 bug";
        return new SimpleDateFormat("HH:mm").format(new java.util.Date(horarioEvento.getTime()));        
    }  
    public String getDataSdf(){
        //return new SimpleDateFormat("dd/MM/yyyy").format(dataEvento);
        //return "00/00/0000 bug";
        return new SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(dataEvento.getTime())); 
        //return dataEvento.toString();
    }

    public void setHorario(Time horario) {
        this.horarioEvento = horario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTotalParticipantes() {
        /*int total = 0;
        for (int i = 0; i < listaInscrito.size(); i++){
            if (listaInscrito.get(i) instanceof Instituicao){
                Instituicao x = (Instituicao) listaInscrito.get(i);
                total += x.getMembros().size();
            } else {
                total++;
            }
        }
        return total;*/
        return 0;
    }
    
    
    public int getTotalPessoas(){
        int i = 0;
        for (Inscrito ic: getListaInscritos()){
            if (ic instanceof Individuo){
                i++;
            }else {
                Instituicao ii = (Instituicao) ic;
                i += ii.getMembros().size();
            }
        }
        return i;
    }
    public int getTotalInstituicoes(){
        int i = 0;
        for (Inscrito ic: getListaInscritos()){
            if (ic instanceof Individuo){
                //i++;
            }else {
                Instituicao ii = (Instituicao) ic;
                i ++;
            }
        }
        return i;
    }
    public ArrayList<Inscrito> getListaInscritos() {
        ArrayList<Inscrito> a = new ArrayList<Inscrito>();
        try {
            a = new EventoDAO().listarTodosParticipantes(this);
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         return a;
    }
    public int getVagasDisponiveis(){
        return 0;
    }
    public int getVagas() {
        return vagas;
    }

    public void setVagas(int vagas) {
        this.vagas = vagas;
    }

    public void adicionarInscrito(Inscrito inscrito) {
        //listaInscrito.add(inscrito);
        try {            
            new EventoDAO().inserirInscrito(this, inscrito);
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void removerInscrito(Inscrito inscrito) {
        //listaInscrito.remove(inscrito);
        try {            
            new EventoDAO().removerInscrito(this, inscrito);
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void removerInscrito(int index) {
        //listaInscrito.remove(index);
    }

    public static void adicionar(Evento e) {
        //e.setId(idCount++);
        //listaEventos.add(e);
        try {
            new EventoDAO().salvar(e);
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
/*
    public static Evento getEvento(int index) {
        return listaEventos.get(index);
    }*/
    public static Evento getEventoPorID(int index) {
        try {
            //return listaEventos.get(index);
            return new EventoDAO().obterPorId(index);
        } catch (SQLException ex) {
            Logger.getLogger(Evento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Iterator<Evento> getEventos()  {        
        return getListaEventos().iterator();
    }


    public void setImagem(BufferedImage imageBuffer) {
        imagem = imageBuffer;
    }

    public BufferedImage getImagem() {
        return imagem;
    }
    
    
}
