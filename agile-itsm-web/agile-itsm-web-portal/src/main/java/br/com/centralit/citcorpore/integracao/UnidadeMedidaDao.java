package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.UnidadeMedidaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class UnidadeMedidaDao extends CrudDaoDefaultImpl {

    public UnidadeMedidaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idUnidadeMedida", "idUnidadeMedida", true, true, false, false));
        listFields.add(new Field("nomeUnidadeMedida", "nomeUnidadeMedida", false, false, false, false));
        listFields.add(new Field("siglaUnidadeMedida", "siglaUnidadeMedida", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "UnidadeMedida";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeUnidadeMedida"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return UnidadeMedidaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Retorna true ou falso caso nomeUnidadeMedida ja exista no banco de dados
     * 
     * @param marcaDto
     * @return boolean
     * @throws Exception
     * @author thays.araujo
     */
    public boolean consultarUnidadesMedidas(final UnidadeMedidaDTO unidadeMedidaDTO) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("select idunidademedida from " + this.getTableName() + "  where  nomeUnidadeMedida = ?");

        parametro.add(unidadeMedidaDTO.getNomeUnidadeMedida());

        if (unidadeMedidaDTO.getIdUnidadeMedida() != null) {
            sql.append("and idunidademedida <> ?");
            parametro.add(unidadeMedidaDTO.getIdUnidadeMedida());
        }

        list = this.execSQL(sql.toString(), parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

}
