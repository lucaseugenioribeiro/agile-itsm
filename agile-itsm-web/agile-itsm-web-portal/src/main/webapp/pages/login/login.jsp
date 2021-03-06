<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.com.centralit.citcorpore.util.ParametroUtil" %>
<%@ page import="br.com.centralit.citcorpore.versao.Versao" %>
<%@ page import="br.com.centralit.citcorpore.util.WebUtil" %>
<%@ page import="br.com.centralit.citcorpore.bean.UsuarioDTO" %>
<%@ page import="br.com.centralit.citcorpore.util.CitCorporeConstantes" %>
<%@ page import="br.com.citframework.service.ServiceLocator" %>
<%@ page import="br.com.centralit.citcorpore.negocio.ParametroCorporeService" %>
<%@ page import="br.com.centralit.citcorpore.bean.ParametroCorporeDTO" %>
<%@ page import="br.com.centralit.citcorpore.util.Enumerados.ParametroSistema" %>
<%@ page import="br.com.centralit.citcorpore.negocio.UsuarioService" %>
<%@ page import="br.com.centralit.citcorpore.util.Enumerados" %>
<%@ page import="br.com.citframework.util.UtilStrings" %>

<%@ taglib prefix="compress" uri="http://htmlcompressor.googlecode.com/taglib/compressor"%>

<!DOCTYPE html>
<compress:html
    enabled="true"
    jsCompressor="closure"
    compressCss="true"
    compressJavaScript="true"
    removeComments="true"
    removeMultiSpaces="true">
<html>
<head>

<%@include file="/novoLayout/common/include/libCabecalho.jsp" %>
<link type="text/css" rel="stylesheet" href="${ctx}/pages/login/css/login.css"/>

<%@include file="/novoLayout/common/include/titulo.jsp" %>
</head>

<body class="login">

<cit:janelaAguarde id="JANELA_AGUARDE_MENU" title="" style="display:none;top:100px;width:300px;left:200px;height:50px;position:absolute;"></cit:janelaAguarde>

<div id="login">
    <div class="container">
        <div class="wrapper">
            <h1 class="glyphicons lock"><img alt="CITSMart" id="logo" src="/citsmart/imagens/logo/logo.png"/><i></i></h1>

            <!-- Box -->
            <div class="widget">

                <div class="widget-head">
                    <h3 class="heading"><fmt:message key="login.area"/></h3>
                </div>
                <div class="widget-body">

                    <%
                        String telefoneSuporteTelaLogin = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.CONFIGURACAO_TELEFONE_SUPORTE_TELA_LOGIN, ";" );
                        String arrayTelefoneSuporteTelaLogin[] = telefoneSuporteTelaLogin.split(";");

                        String emailSuporteTelaLogin = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.CONFIGURACAO_EMAIL_SUPORTE_TELA_LOGIN, ";" );
                        String arrayEmailSuporteTelaLogin[] = emailSuporteTelaLogin.split(";");
                    %>

                    <form name='formInternacionaliza' id='formInternacionaliza' class="marginless" action="${ctx}/pages/start/start">
                        <input type="hidden" name="locale" id="locale"/>
                            <div class="navbar main hidden-print">
                            <ul class="topnav pull-right">
                                <li class="hidden-phone dropdown dd-1 dd-flags" id="lang_nav">
                                    <c:choose>
                                        <c:when test="${locale eq en}">
                                            <a href="#" data-toggle="dropdown"><img id='linguagemAtiva' src="${ctx}/novoLayout/common/theme/images/lang/es.png" alt="us"></a>
                                        </c:when>
                                        <c:when test="${locale eq es}">
                                            <a href="#" data-toggle="dropdown"><img id='linguagemAtiva' src="${ctx}/novoLayout/common/theme/images/lang/es.png" alt="br"></a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="#" data-toggle="dropdown"><img id='linguagemAtiva' src="${ctx}/novoLayout/common/theme/images/lang/br.png" alt="br"></a>
                                        </c:otherwise>
                                    </c:choose>
                                    <ul class="dropdown-menu pull-left">
                                        <li class="active"><a href="javascript:;" onclick="internacionalizar('pt')" title="Portugues" ><img src="${ctx}/novoLayout/common/theme/images/lang/br.png" alt="Portugues"> Português BR</a></li>
                                        <li><a href="javascript:;" onclick="internacionalizar('en')" title="English"><img src="${ctx}/novoLayout/common/theme/images/lang/us.png" alt="English"> English</a></li>
                                        <li><a href="javascript:;" onclick="internacionalizar('es')" title="Español"><img src="${ctx}/novoLayout/common/theme/images/lang/es.png" alt="Espanhol"> Español</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </form>

                    <form name="form" onkeydown="if ( event.keyCode == 13 ) validar();" id="formlogin" action="${ctx}/pages/login/login">
                        <label><fmt:message key="login.nomeusuario"/></label>
                        <input type="text" class="input-block-level" id="user" name="user" maxlength="256" placeholder="<fmt:message key='login.placeholderUsuario'/>"/>
                        <label><fmt:message key="login.senha"/></label>
                        <input type="password" class="input-block-level margin-none" id="senha" name="senha" maxlength="300" placeholder="<fmt:message key='login.placeholderSenha'/>" />
                        <div class="separator bottom"></div>
                        <div class="row-fluid">
                            <div class="span8">
                            <% UsuarioService usuarioService = (UsuarioService) ServiceLocator.getInstance().getService(UsuarioService.class, null);
                               boolean usuarioIsAd = usuarioService.usuarioIsAD(WebUtil.getUsuario(request) );
                               String metodoAutenticacaoProprio = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.METODO_AUTENTICACAO_Pasta, "1");
                               if (metodoAutenticacaoProprio != null && metodoAutenticacaoProprio.trim().equalsIgnoreCase("1") ) {
                                   if (!usuarioIsAd) {
                            %>
                                <a class="" href="#modal_alteraSenha" data-toggle="modal" id='modals-bootbox-confirm'><fmt:message key="recuperacaoSenha.esqueceuSuaSenha" /></a>
                            <%
                                }
                            }
                            %>

                            </div>
                            <div class="span4 center">
                                <button class="btn btn-block btn-primary" onclick='validar();' type="button"><fmt:message key="login.entrar"/></button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="widget-footer" style="height:100%">
                    <%
                        String mensagemTelaLogin = ParametroUtil.getValorParametroCitSmartHashMap(ParametroSistema.MENSAGEM_TELA_LOGIN, "" );
                        if(mensagemTelaLogin == null || mensagemTelaLogin.isEmpty()) {
                    %>
                        <p class="glyphicons restart"><i></i><fmt:message key="login.usuarioSenha"/></p>
                    <% } else { %>
                        <p class="glyphicons restart" style="height:100%"><i></i><%= mensagemTelaLogin %></p>
                    <% } %>
                </div>
            </div>
            <!-- // Box END -->

            <!-- INICIO MODAL REDEFINIR SENHA -->
            <div class="modal hide fade in" id="modal_alteraSenha"  aria-hidden="false">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3><fmt:message key="login.esqueceu" /></h3>
                </div>
                <div class="modal-body">
                    <form name="form1" action="${ctx}/pages/login/login" method="post">
                        <label for="login"><fmt:message key="recuperacaoSenha.loginOuEmail" /></label>
                        <div class='row-fluid'>
                            <div class='span12'>
                                <input type="text" id="login" name="login" placeholder="<fmt:message key="recuperacaoSenha.dica.loginOuEmail" />" />
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-default" data-dismiss="modal"><fmt:message key="citcorpore.comum.fechar" /></a>
                    <a href="#" data-dismiss="modal" class="btn btn-primary" onclick="document.form1.fireEvent('redefinirSenha');"><fmt:message key="citcorpore.comum.gravar" /></a>
                </div>
            </div>
        </div>
        <div class="innerAll center">
            <p><i></i><fmt:message key="login.problema"/></p>
            <table align="center">
                <td align="left">
                <%
                    int i = 0;
                    while(i < arrayTelefoneSuporteTelaLogin.length) {
                %>
                      <span class="glyphicons phone" data-toggle="notyfy" data-layout="topRight" data-type="primary"><i></i><b><fmt:message key="citcorpore.comum.ligue_nos"/></b><span class="">&nbsp;<%= arrayTelefoneSuporteTelaLogin[i] %></span></span><br>
                <%
                        i++;
                    }
                %>
                    </td><td align="left">
                <%
                      int j = 0;
                      while(j<arrayEmailSuporteTelaLogin.length) { %>
                          &nbsp;&nbsp;<a href="mailto:<%= arrayEmailSuporteTelaLogin[j] %>?Subject=[<fmt:message key="citcorpore.comum.suporte"/>]" target="top" data-toggle="" class="glyphicons envelope"><i></i><%= arrayEmailSuporteTelaLogin[j] %><span class=""></span></a><br>
                <%
                          j++;
                      }
                %>
                </td>
            </table>
        </div>
    </div>
</div>
<div class="modal hide fade in" id="mensagem_insercao" aria-hidden="false" data-backdrop="static" data-keyboard="false">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <fmt:message key="instalacao.mensagemInsucessoAvisoImportante"/>
    </div>
    <div class="modal-body" >
        <div id="divInsercao">
        </div>
    </div>
    <div class="modal-footer">
        <a id="btFechar" href="#" class="btn " data-dismiss="modal"><fmt:message key="citcorpore.comum.fechar" /></a>
    </div>
</div>

<%@include file="/novoLayout/common/include/libRodape.jsp" %>
<script type="text/javascript" src="${ctx}/pages/login/js/login.js"></script>
</body>
</html>
</compress:html>
