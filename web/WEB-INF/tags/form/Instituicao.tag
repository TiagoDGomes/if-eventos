<%-- 
    Document   : Instituicao
    Created on : 02/06/2012, 15:46:27
    Author     : Tiago
--%>

<%@tag description="Formulário de Instituição" pageEncoding="UTF-8"%>


<%@attribute name="hash" required="true"%>
<%@attribute name="instituicao" type="br.edu.ifsp.eventos.classes.Instituicao"  %>


<script type="text/javascript" src="/eventos/js/cadastro.cep.js"></script>
<form class="formulario" action="cadastro" method="post">

    <div class="info">
        <span><label for="nome">Nome: </label></span>
        <span><input size="30" type="text" id="nome" name="nome" value="${instituicao.getNome()}" />*</span>
    </div>
    <div class="info">
        <span><label for="cnpj">CNPJ: </label></span>
        <span><input size="18" type="text" id="cnpj" name="cnpj"  value="${instituicao.getCnpj()}" /></span>
    </div>
    <div class="info">
        <span><label for="email">E-mail: </label></span>
        <span><input size="30" type="text" id="email" name="email"  value="${instituicao.getEmail()}" />*</span>
    </div>
    <div class="info">
        <span><label for="senha">Senha: </label></span>
        <span><input size="20" type="password" id="senha" name="senha" />*</span>
    </div>
    <div class="info">
        <span><label for="cep">CEP: </label></span>
        <span><input onkeyup="checkCEP()" onblur="checkCEP();" size="8" maxlength="8" type="text" id="cep" name="cep"  value="${instituicao.getEndereco().getCep()}" /></span>
        <span id="cepLoading" style="display: none"><img src="/eventos/imagens/loading.gif" alt="Carregando..." /> Aguarde...</span>
    </div>
    <div id="enderecamento">
        <div class="info">
            <span><label for="endereco">Endereço: </label></span>
            <span><input size="40" type="text" id="endereco" name="endereco" value="${instituicao.getEndereco().getLogradouro()}" /></span>
        </div>
        <div class="info">
            <span><label for="numeroEndereco">Número: </label></span>
            <span><input size="5" type="text" id="numeroEndereco" name="numeroEndereco" value="${instituicao.getEndereco().getNumero()}" /></span>
        </div>
        <div class="info">
            <span></span><label for="bairro">Bairro: </label>
            <span><input  size="20" type="text" id="bairro" name="bairro" value="${instituicao.getEndereco().getBairro()}" /></span>
        </div>
        <div class="info">
            <span><label for="uf">UF:</label></span>
            <span>
                <select name="uf" id="uf" size="1">
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
           $('#uf option').each(function(){
           if($(this).val() == '${instituicao.getEndereco().getEstado()}'){
               $(this).attr('selected',true);
           }
       });
        </script>
        <div class="info">
            <span><label for="cidade">Cidade: </label>
                <input  size="20" type="text" id="cidade" name="cidade" value="${instituicao.getEndereco().getCidade()}" /></span>
        </div>   
        <div>
        </div>
    </div>           

    <div class="info">
        <span>
            <label>&nbsp;</label>
            <input type="hidden" name="tipo" value="Instituicao" />
            <input type="hidden" name="h" value="${hash}" />
            <input type="hidden" name="i" value="${instituicao.getId()}" />
        </span>
        <span>
            <input class="button" type="submit" value="Salvar" />
            <input class="button" type="button" value="Cancelar" onclick="ss('#admInicio')" />
        </span>
    </div>
</form>