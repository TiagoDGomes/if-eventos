
package br.edu.ifsp.eventos.classes.web.sistema;

public final class Pagina {
    private String nome;
    private String nomeSimples;
    private String jspf;
    private static final String path = "WEB-INF/jsp/conteudo/";
    public static final String argName = "pagina";
    public static Pagina getPaginaPorNomeSimples(String nome){
        for (Pagina p: Sistema.getListaPagina()){
            if (nome.equals(p.getNomeSimples())){
                return p;
            }
        }
        return null;
    }
    private boolean isMenu;
    
    public String getJspf() {
        return jspf;
    }
    public String getJspfEx() {
        return path + jspf;
    }

    public void setJspf(String jspf) {
        this.jspf = jspf;
    }
    
    public Pagina(String nome, String nomeSimples, String jspf, boolean isMenu) {
        this.nome = nome;
        this.nomeSimples = nomeSimples;
        this.jspf = jspf;
        this.isMenu = isMenu;
    }    
    public Pagina(String nome, String nomeSimples, String jspf) {
        this.nome = nome;
        this.nomeSimples = nomeSimples;
        this.jspf = jspf;
        this.isMenu = true;
    }

    
    public String getNomeSimples() {
        return nomeSimples;
    }

    public void setNomeSimples(String nomeSimples) {
        this.nomeSimples = nomeSimples;
    }

    public String getLink() {
        return getLink(this.getNome());
    }
    public String getLink(String alias) {
        String classSelect = "";
        if (Sistema.getPaginaAtual()==this){
            classSelect = " class=\"selecionado\"";
        }
        return "<a href=\"?"+argName + "=" + this.getNomeSimples() + "\" " + classSelect + ">" + alias + "</a>";
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    
}
