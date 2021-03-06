package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.TipoUnidadeDTO;
import br.com.centralit.citcorpore.integracao.TipoUnidadeDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.service.CrudServiceImpl;
import br.com.citframework.util.UtilDatas;

public class TipoUnidadeServiceEjb extends CrudServiceImpl implements TipoUnidadeService {

    private TipoUnidadeDao dao;

    @Override
    protected TipoUnidadeDao getDao() {
        if (dao == null) {
            dao = new TipoUnidadeDao();
        }

        return dao;
    }

    @Override
    public BaseEntity create(final BaseEntity model) throws ServiceException, LogicException {
        final TipoUnidadeDTO tipoUnidade = (TipoUnidadeDTO) model;

        tipoUnidade.setDataInicio(UtilDatas.getDataAtual());

        return super.create(tipoUnidade);
    }

    @Override
    public boolean jaExisteUnidadeComMesmoNome(final String nome) {
        final List<Condition> condicoes = new ArrayList<Condition>();
        condicoes.add(new Condition("nomeTipoUnidade", "=", nome));
        condicoes.add(new Condition("dataFim", "is", null));
        Collection retorno = null;
        try {
            retorno = this.getDao().findByCondition(condicoes, null);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return retorno == null ? false : true;
    }

}
