package br.com.centralit.citgerencial.bean;

import java.util.Collection;
import java.util.HashMap;

public abstract class GerencialProcessParameters {

    public abstract String processParameters(final HashMap hsmParms, final Collection colParmsUtilizadosNoSQL, final Collection colDefinicaoParametros);

}
