package br.com.centralit.citcorpore.negocio;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ContatoRequisicaoLiberacaoDTO;
import br.com.centralit.citcorpore.integracao.ContatoRequisicaoLiberacaoDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.CrudServiceImpl;

public class ContatoRequisicaoLiberacaoServiceEjb extends CrudServiceImpl implements ContatoRequisicaoLiberacaoService {

    private ContatoRequisicaoLiberacaoDao dao;

    @Override
    protected ContatoRequisicaoLiberacaoDao getDao() {
        if (dao == null) {
            dao = new ContatoRequisicaoLiberacaoDao();
        }
        return dao;
    }

    @Override
    public ContatoRequisicaoLiberacaoDTO restoreContatosById(final Integer idContatoRequisicaoLiberacao) {
        ContatoRequisicaoLiberacaoDTO contatoRequisicaoLiberacaoDTO = new ContatoRequisicaoLiberacaoDTO();
        contatoRequisicaoLiberacaoDTO.setIdContatoRequisicaoLiberacao(idContatoRequisicaoLiberacao);
        try {
            contatoRequisicaoLiberacaoDTO = (ContatoRequisicaoLiberacaoDTO) this.getDao().restore(contatoRequisicaoLiberacaoDTO);
        } catch (final Exception e) {
            
            e.printStackTrace();
            System.out.println("Contato Requisicao Liberacão não foi encontrado com esse ID");
        }
        return contatoRequisicaoLiberacaoDTO;
    }

    @Override
    public synchronized BaseEntity create(final BaseEntity model) throws ServiceException, LogicException {
        return super.create(model);
    }

}
