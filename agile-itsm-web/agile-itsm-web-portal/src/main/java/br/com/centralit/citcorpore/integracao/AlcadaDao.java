package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AlcadaDTO;
import br.com.centralit.citcorpore.util.Enumerados.TipoAlcada;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class AlcadaDao extends CrudDaoDefaultImpl {

    public AlcadaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAlcada", "idAlcada", true, true, false, false));
        listFields.add(new Field("nomeAlcada", "nomeAlcada", false, false, false, false));
        listFields.add(new Field("tipoAlcada", "tipoAlcada", false, false, false, false));
        listFields.add(new Field("situacao", "situacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Alcada";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        list.add(new Order("nomeAlcada"));
        return super.list(list);
    }

    @Override
    public Class getBean() {
        return AlcadaDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    public AlcadaDTO findByTipo(final TipoAlcada tipoAlcada) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("tipoAlcada", "=", tipoAlcada.name()));
        condicao.add(new Condition("situacao", "=", "A"));
        ordenacao.add(new Order("nomeAlcada"));
        final List result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return (AlcadaDTO) result.get(0);
        }
        return null;
    }

    /**
     * Se existir igual retorna true.
     */
    public boolean existeIgual(final AlcadaDTO alcada) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("nomeAlcada", "=", alcada.getNomeAlcada()));
        ordenacao.add(new Order("nomeAlcada"));

        final List result = (List) super.findByCondition(condicao, ordenacao);

        if (result != null && !result.isEmpty()) {
            return true;
        }
        return false;
    }

}
