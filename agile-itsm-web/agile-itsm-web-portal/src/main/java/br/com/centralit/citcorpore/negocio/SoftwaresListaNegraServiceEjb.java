package br.com.centralit.citcorpore.negocio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.RelatorioListaNegraDTO;
import br.com.centralit.citcorpore.bean.SoftwaresListaNegraDTO;
import br.com.centralit.citcorpore.integracao.SoftwaresListaNegraDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.service.CrudServiceImpl;

public class SoftwaresListaNegraServiceEjb extends CrudServiceImpl implements SoftwaresListaNegraService {

    private SoftwaresListaNegraDao dao;

    @Override
    protected SoftwaresListaNegraDao getDao() {
        if (dao == null) {
            dao = new SoftwaresListaNegraDao();
        }
        return dao;
    }

    @Override
    public BaseEntity create(final BaseEntity model) throws ServiceException, LogicException {
        final SoftwaresListaNegraDTO softwaresListaNegraDTO = (SoftwaresListaNegraDTO) model;
        return super.create(softwaresListaNegraDTO);
    }

    @Override
    public boolean verficiarSoftwareListaNegraMesmoNome(final String nome) {
        final List<Condition> condicoes = new ArrayList<Condition>();
        condicoes.add(new Condition("nomeSoftwaresListaNegra", "=", nome));
        Collection retorno = null;
        try {
            retorno = this.getDao().findByCondition(condicoes, null);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return retorno == null ? false : true;
    }

    @Override
    public Collection<RelatorioListaNegraDTO> listaRelatorioListaNegra(final RelatorioListaNegraDTO relatorioListaNegraDTO) throws Exception {
        try {
            return this.getDao().listaRelatorioListaNegra(relatorioListaNegraDTO);
        } catch (final Exception e) {
            throw new ServiceException(e);
        }
    }

}
