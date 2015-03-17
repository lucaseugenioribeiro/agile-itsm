<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="br.com.centralit.citcorpore.util.WebUtil"%>
<%@page import="br.com.centralit.citcorpore.bean.UsuarioDTO"%>
<%@page import="br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO"%>

<%@ include file="/WEB-INF/templates/taglibs.jsp"%>

<!doctype html public "">
<html>
<head>
<%@include file="/include/header.jsp"%>

<%@include file="/novoLayout/common/include/titulo.jsp" %>

<%@include file="/include/javaScriptsComuns/javaScriptsComuns.jsp"%>
<link rel="stylesheet" type="text/css" href="./css/relatorioValorServicoContrato.css" />

<script type="text/javascript" src="./js/relatorioValorServicoContrato.js"></script>
</head>
<!-- Definicoes Comuns -->
<cit:janelaAguarde id="JANELA_AGUARDE_MENU"
    title=""
    style="display:none;top:100px;width:300px;left:200px;height:50px;position:absolute;">
</cit:janelaAguarde>
<body>

    <div id="wrapper">
        <%@include file="/include/menu_vertical.jsp"%>
        <!-- Conteudo -->
        <div id="main_container" class="main_container container_16 clearfix">
            <%@include file="/include/menu_horizontal.jsp"%>
            <div class="box grid_16 tabs">
                <ul class="tab_header clearfix">
                    <li><a href="#tabs-1"><fmt:message key="relatorioValorServicoContrato.titulo" /></a></li>
                </ul>
                <a href="#" class="toggle">&nbsp;</a>
                <div class="toggle_container">
                    <div class="block" >
                        <div id="parametros">
                            <form name='form' action='${ctx}/pages/relatorioValorServicoContrato/relatorioValorServicoContrato'>
                                <div class="columns clearfix">
                                    <div class="col_25">
                                        <fieldset>
                                            <label class="campoObrigatorio"><fmt:message key="citcorpore.comum.periodo" /></label>
                                            <div>
                                                <table>
                                                    <tr>
                                                        <td><input type='text' name='dataInicio'
                                                            id='dataInicio' size='10' maxlength="10"
                                                            class='Format[Date] Valid[Date] datepicker' /></td>
                                                        <td><fmt:message key="citcorpore.comum.a" /></td>
                                                        <td><input type='text' name='dataFim' id='dataFim'
                                                            size='10' maxlength="10"
                                                            class='Format[Date] Valid[Date] datepicker' /></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </fieldset>
                                    </div>
                                    <div class="col_25">
                                        <fieldset style="height: 64px">
                                            <label><fmt:message key="contrato.contrato" /></label>
                                            <div>
                                                <select id="idContrato" name="idContrato"></select>
                                            </div>
                                        </fieldset>
                                    </div>
                                    <div class="col_25">
                                        <fieldset style="height: 64px">
                                            <label><fmt:message key="servico.servico" /></label>
                                            <div>
                                                <select id="idServico" name="idServico"></select>
                                            </div>
                                        </fieldset>
                                    </div>
                                </div>
                                <div class="col_100">
                                    <span class="campoObrigatorio" style="margin: 20px !important;"><fmt:message key="relatorioValorServicoContrato.info" />.</span>
                                </div>
                                <div class="col_100">
                                    <fieldset>
                                    <button type='button' name='btnRelatorio' class="light" title='Download documento PDF'
                                            onclick='imprimirRelatorioValorServicoContrato();'
                                            style="margin: 20px !important;">
                                            <img src="${ctx}/template_new/images/icons/small/util/file_pdf.png" style="padding-left: 8px;">
                                            <span><fmt:message key="citcorpore.comum.gerarRelatorio" /></span>
                                        </button>
                                        <button type='button' name='btnRelatorio' class="light" title='Download documento XLS'
                                            onclick='imprimirRelatorioValorServicoContratoXls();'
                                            style="margin: 20px !important;">
                                            <img src="${ctx}/template_new/images/icons/small/util/excel.png"  style="padding-left: 8px;">
                                            <span><fmt:message key="citcorpore.comum.gerarRelatorio" /></span>
                                        </button>
                                        <button type='button' name='btnLimpar' class="light"
                                            onclick='meuLimpar()' style="margin: 20px !important;">
                                            <img
                                                src="${ctx}/template_new/images/icons/small/grey/clear.png">
                                            <span><fmt:message key="citcorpore.comum.limpar" /></span>
                                        </button>

                                    </fieldset>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- ## FIM - AREA DA APLICACAO ## -->
                </div>
            </div>
        </div>
        <!-- Fim da Pagina de Conteudo -->
    </div>
    <%@include file="/include/footer.jsp"%>
</body>
</html>

