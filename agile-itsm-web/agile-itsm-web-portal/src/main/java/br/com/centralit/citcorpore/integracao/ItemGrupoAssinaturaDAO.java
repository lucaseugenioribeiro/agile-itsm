package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ItemGrupoAssinaturaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class ItemGrupoAssinaturaDAO extends CrudDaoDefaultImpl {

    public ItemGrupoAssinaturaDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List<Order> order = new ArrayList<>();
        order.add(new Order("idItemGrupoAssinatura", "ASC"));
        return super.find(obj, order);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("iditemgrupoassinatura", "idItemGrupoAssinatura", true, true, false, false));
        listFields.add(new Field("idgrupoassinatura", "idGrupoAssinatura", false, false, false, false));
        listFields.add(new Field("idassinatura", "idAssinatura", false, false, false, false));
        listFields.add(new Field("ordem", "ordem", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "itemgrupoassinatura";
    }

    @Override
    public Collection list() throws PersistenceException {
        return super.list("ordem");
    }

    @Override
    public Class getBean() {
        return ItemGrupoAssinaturaDTO.class;
    }

    public Collection findByIdGrupoAssinatura(final Integer idGrupoAssinatura) {
        List result;
        try {
            List resp = new ArrayList<>();
            final List parametro = new ArrayList<>();
            final List listRetorno = new ArrayList<>();

            listRetorno.add("idItemGrupoAssinatura");
            listRetorno.add("idGrupoAssinatura");
            listRetorno.add("idAssinatura");
            listRetorno.add("ordem");
            listRetorno.add("dataInicio");
            listRetorno.add("dataFim");
            listRetorno.add("nomeResponsavel");
            listRetorno.add("papel");
            listRetorno.add("fase");

            final String sql = "SELECT itemgrupoassinatura.iditemgrupoassinatura,itemgrupoassinatura.idgrupoassinatura,itemgrupoassinatura.idassinatura,itemgrupoassinatura.ordem,itemgrupoassinatura.datainicio,itemgrupoassinatura.datafim, empregados.nome as nomeresponsavel, assinatura.papel, assinatura.fase "
                    + "FROM itemgrupoassinatura JOIN assinatura on  (itemgrupoassinatura.datafim is null) and itemgrupoassinatura.idgrupoassinatura = ? and itemgrupoassinatura.idassinatura=assinatura.idassinatura "
                    + " LEFT JOIN empregados on assinatura.idempregado = empregados.idempregado "
                    + "ORDER BY itemgrupoassinatura.idgrupoassinatura,itemgrupoassinatura.ordem,itemgrupoassinatura.idassinatura";
            parametro.add(idGrupoAssinatura);

            resp = this.execSQL(sql, parametro.toArray());

            result = engine.listConvertion(this.getBean(), resp, listRetorno);
        } catch (final PersistenceException e) {
            e.printStackTrace();
            result = null;
        } catch (final Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result == null || result.size() <= 0 ? new ArrayList<ItemGrupoAssinaturaDTO>() : result;
    }

    public Collection findByIdAssinatura(final Integer idAssinatura) {
        List result;
        try {
            List resp = new ArrayList<>();
            final List parametro = new ArrayList<>();
            final List listRetorno = new ArrayList<>();

            listRetorno.add("idItemGrupoAssinatura");
            listRetorno.add("idGrupoAssinatura");
            listRetorno.add("idAssinatura");
            listRetorno.add("ordem");
            listRetorno.add("dataInicio");
            listRetorno.add("dataFim");
            listRetorno.add("nomeResponsavel");
            listRetorno.add("papel");
            listRetorno.add("fase");

            final String sql = "SELECT itemgrupoassinatura.iditemgrupoassinatura,itemgrupoassinatura.idgrupoassinatura,itemgrupoassinatura.idassinatura,itemgrupoassinatura.ordem,itemgrupoassinatura.datainicio,itemgrupoassinatura.datafim, empregados.nome as nomeresponsavel, assinatura.papel, assinatura.fase "
                    + "FROM itemgrupoassinatura JOIN assinatura on  (itemgrupoassinatura.datafim is null) and itemgrupoassinatura.idassinatura = ? and itemgrupoassinatura.idassinatura=assinatura.idassinatura "
                    + " LEFT JOIN empregados on assinatura.idempregado = empregados.idempregado "
                    + "ORDER BY itemgrupoassinatura.idgrupoassinatura,itemgrupoassinatura.ordem,itemgrupoassinatura.idassinatura";
            parametro.add(idAssinatura);

            resp = this.execSQL(sql, parametro.toArray());

            result = engine.listConvertion(this.getBean(), resp, listRetorno);
        } catch (final PersistenceException e) {
            e.printStackTrace();
            result = null;
        } catch (final Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result == null || result.size() <= 0 ? new ArrayList<ItemGrupoAssinaturaDTO>() : result;
    }

}
