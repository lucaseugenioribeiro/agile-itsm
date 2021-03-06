package br.com.centralit.bpm.servico;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.bpm.dto.FluxoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.service.CrudService;

public interface FluxoService extends CrudService {

    Collection listAll() throws Exception;

    FluxoDTO findByTipoFluxo(final Integer idTipoFluxo) throws Exception;

    FluxoDTO criaEstrutura(final FluxoDTO fluxoDto) throws Exception;

    FluxoDTO criaFluxoEEstrutura(final FluxoDTO fluxoDto) throws Exception;
    
    public BaseEntity restoreComEstrutura(final BaseEntity obj) throws PersistenceException;

}
