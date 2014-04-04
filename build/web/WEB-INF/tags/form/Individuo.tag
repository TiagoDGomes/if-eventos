
<%@taglib prefix="ev" tagdir="/WEB-INF/tags" %>
<%@tag description="Formulário de Indivíduo" pageEncoding="UTF-8"%>
<%@tag import="br.edu.ifsp.eventos.classes.web.sistema.Pagina" %>
<%@tag import="br.edu.ifsp.eventos.classes.Inscrito" %>
<%@tag import="br.edu.ifsp.eventos.classes.Individuo" %>
<%@tag import="br.edu.ifsp.eventos.classes.Instituicao" %>

<%@attribute name="hash" required="true"%>
<%@attribute name="preffix"%>
<%@attribute name="paramUrl"%>
<%@attribute name="usuario" type="Individuo"%>
<%@attribute name="instituicao" type="br.edu.ifsp.eventos.classes.Instituicao"%>

<script type="text/javascript" src="/eventos/js/cadastro.cep.js"></script>


<script type="text/javascript">
    function ${preffix}cadastroInscrito(){

        $.ajax({
            type: 'post',
            data: {
                'nome'  : $("#${preffix}nome").val() , 
                'cpf'   : $("#${preffix}cpf").val(), 
                'email' : $("#${preffix}email").val(), 
                'senha' : $("#${preffix}senha").val(),
                'cep'   : $("#${preffix}cep").val(),
                'endereco':$("#${preffix}endereco").val(),
                'bairro': $("#${preffix}bairro").val(),
                'uf'    : $("#${preffix}uf").val() ,
                'cidade': $("#${preffix}cidade").val(),
                'tipo'  :  'Individuo',
                'idi'  :  '${instituicao.getId()}',
                'h':  '${hash}'  ,
                'ii' : '${usuario.getId()}'
            }               
            ,
            url:'/eventos/cadastro?json=1&${paramUrl}',
            success: function(retorno){
                
                if (retorno.confirma == 1){
                    $("#mensagemErroCadastro").fadeIn();
                    msg = "<span>Você não preencheu todos os campos do cadastro.</span><br /><ul>" 
                    if (retorno.nome) msg+= "<li>Campo nome vazio</li>";
                    if (retorno.cpf) msg+= "<li>Campo CPF vazio</li>";
                    if (retorno.email) msg+= "<li>Campo e-mail vazio</li>";
                    if (retorno.senha) msg+= "<li>Campo senha vazio</li>";
                    if (retorno.cep) msg+= "<li>Campo CEP vazio</li>";
                    if (retorno.endereco) msg+= "<li>Campo endereço vazio</li>";
                    if (retorno.numero) msg+= "<li>Campo número vazio</li>";
                    if (retorno.bairro) msg+= "<li>Campo bairro vazio</li>";
                    if (retorno.estado) msg+= "<li>Campo estado vazio</li>";
                    if (retorno.cidade) msg+= "<li>Campo cidade vazio</li>";
                    msg += "</ul>"
                    $("#mensagemErroCadastro .mensagem").html(msg);
                } else {
                    location.href = "?${paramUrl}";
                }
            }
        });
        
        return false;
    }   
    
</script>
<ev:mensagemErro id="${preffix}mensagemErroCadastro"
                 titulo="Erro no cadastro"
                 mensagem="Você não preencheu todos os campos do cadastro." 
                 classCss="mensagemErro" 
                 visible="false"
                 />
<div class="formulario">
    <form action="cadastro?${paramUrl}" id="${preffix}cadastro" method="post" onsubmit="return ${preffix}cadastroInscrito(); return false">
        <div id="status">

        </div>
        <div class="info">
            <span><label id="fnome" for="${preffix}nome">Nome: </label></span>
            <span>
                <input size="30" type="text" id="${preffix}nome" name="nome" value="${usuario.getNome()}" />
            </span>
        </div>
        <div class="info">
            <span><label for="${preffix}cpf">CPF: </label></span>
            <span><input size="18" type="text" id="${preffix}cpf" name="cpf" value="${usuario.getCpf()}" /></span>
        </div>
        <div class="info">
            <span><label for="${preffix}email">E-mail: </label></span>
            <span><input size="30" type="text" id="${preffix}email" name="email" value="${usuario.getEmail()}" /></span>
        </div>
        <div class="info">
            <span><label for="${preffix}senha">Senha: </label></span>
            <span><input size="20" type="password" id="${preffix}senha" name="senha"  /></span>
        </div>
        <div class="info">
            <span><label for="cep">CEP: </label></span>
            <span><input onkeyup="checkCEP('${preffix}')" onblur="checkCEP('${preffix}');" size="8" maxlength="8" type="text" id="${preffix}cep" name="cep" value="${usuario.getEndereco().getCep()}" /></span>
            <span class="linkajuda"><a href="http://m.correios.com.br/movel/buscaCep.do">Não sei meu CEP</a></span>
            <span id="${preffix}cepLoading" style="display: none"><img src="/eventos/imagens/loading.gif" alt="Carregando..." /> Aguarde...</span>
        </div>
        <div id="enderecamento">
            <div class="info">
                <span><label for="${preffix}endereco">Endereço: </label></span>
                <span><input size="40" type="text" id="${preffix}endereco" name="endereco"  value="${usuario.getEndereco().getLogradouro()}"/></span>
            </div>
            <div class="info">
                <span><label for="${preffix}numeroEndereco">Número: </label></span>
                <span><input size="5" type="text" id="${preffix}numeroEndereco" name="numeroEndereco" value="${usuario.getEndereco().getNumero()}" /></span>
            </div>
            <div class="info">
                <span></span><label for="${preffix}bairro">Bairro: </label>
                <span><input size="20" type="text" id="${preffix}bairro" name="bairro" value="${usuario.getEndereco().getBairro()}" /></span>
            </div>
            <div class="info">
                <span><label for="${preffix}uf">UF:</label></span>
                <span><select name="uf" id="${preffix}uf" size="1">
                        <option value=""></option>
                        <option value="AC">AC</option>
                        <option value="AL">AL</option>
                        <option value="AP">AP</option>
                        <option value="AM">AM</option>
                        <option value="BA">BA</option>
                        <option value="CE">CE</option>
                        <option value="DF">DF</option>
                        <option value="ES">ES</option>
                        <option value="GO">GO</option>
                        <option value="MA">MA</option>
                        <option value="MG">MG</option>
                        <option value="MS">MS</option>
                        <option value="MT">MT</option>
                        <option value="PA">PA</option>
                        <option value="PB">PB</option>
                        <option value="PE">PE</option>
                        <option value="PI">PI</option>
                        <option value="PR">PR</option>
                        <option value="RJ">RJ</option>
                        <option value="RN">RN</option>
                        <option value="RO">RO</option>
                        <option value="RR">RR</option>
                        <option value="RS">RS</option>
                        <option value="SP">SP</option>
                        <option value="SC">SC</option>
                        <option value="SE">SE</option>
                        <option value="TO">TO</option>
                    </select></span>
            </div> 
           <script>
           $('#${preffix}uf option').each(function(){
               if($(this).val() == '${usuario.getEndereco().getEstado()}'){
                    $(this).attr('selected',true);
                }
            });
             </script>
            <div class="info">
                <span>
                    <label for="${preffix}cidade">Cidade: </label>
                    <input  size="20" type="text" id="${preffix}cidade" name="cidade" value="${usuario.getEndereco().getCidade()}" />
                </span>
            </div>

        </div>           

        <div class="info">
            <span>
                <label>&nbsp;</label>
                <input type="hidden" name="tipo" value="Individuo" />
                <input type="hidden" name="idi" value="${instituicao.getId()}" />
                <input type="hidden" name="h" value="${hash}" />
                <input type="hidden" name="ii" value="${usuario.getId()}" />
                
            </span>
            <span>
                <input class="button" type="submit" value="Confirmar" />
            </span>
        </div>

    </form>
</div>
