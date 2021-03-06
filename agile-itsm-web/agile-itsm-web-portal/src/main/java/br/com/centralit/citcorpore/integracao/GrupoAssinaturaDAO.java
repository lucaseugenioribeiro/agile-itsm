package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GrupoAssinaturaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class GrupoAssinaturaDAO extends CrudDaoDefaultImpl {

    public GrupoAssinaturaDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List<Order> order = new ArrayList<>();
        order.add(new Order("idGrupoAssinatura", "ASC"));
        return super.find(obj, order);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idgrupoassinatura", "idGrupoAssinatura", true, true, false, false));
        listFields.add(new Field("titulo", "titulo", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "grupoassinatura";
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("titulo", "ASC"));
        condicao.add(new Condition("dataFim", "is", null));
        return super.findByCondition(condicao, ordenacao);
    }

    @Override
    public Class getBean() {
        return GrupoAssinaturaDTO.class;
    }

    public boolean violaIndiceUnico(final GrupoAssinaturaDTO grupoAssinaturaDTO) {
        boolean encontrou = false;

        List result;
        try {
            List resp = new ArrayList<>();

            final List parametro = new ArrayList<>();

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT idgrupoassinatura FROM " + this.getTableName() + " ");
            sql.append("WHERE (datafim is null) AND ");

            if (grupoAssinaturaDTO.getIdGrupoAssinatura() != null) {
                sql.append("idgrupoassinatura <> ? AND ");
                parametro.add(grupoAssinaturaDTO.getIdGrupoAssinatura());
            }

            sql.append("titulo = ?");
            parametro.add(grupoAssinaturaDTO.getTitulo());

            resp = this.execSQL(sql.toString(), parametro.toArray());

            final List listRetorno = new ArrayList<>();
            listRetorno.add("idgrupoassinatura");

            result = engine.listConvertion(this.getBean(), resp, listRetorno);
        } catch (final PersistenceException e) {
            e.printStackTrace();
            result = null;
        } catch (final Exception e) {
            e.printStackTrace();
            result = null;
        }
        encontrou = result != null && result.size() > 0;
        return encontrou;
    }

    public boolean naoEstaSendoUtilizado(final Integer idGrupoAssinatura) {
        boolean encontrou = false;

        List resp = new ArrayList<>();
        try {

            final List parametro = new ArrayList<>();

            final StringBuilder sql = new StringBuilder();

            sql.append("SELECT idos FROM os ");

            if (idGrupoAssinatura != null) {
                sql.append("WHERE idgrupoassinatura = ?");
                parametro.add(idGrupoAssinatura);
                // (datafim is null) Toda OS tem data fim!
            }

            resp = this.execSQL(sql.toString(), parametro.toArray());

        } catch (final PersistenceException e) {
            e.printStackTrace();
            resp = null;
        } catch (final Exception e) {
            e.printStackTrace();
            resp = null;
        }
        encontrou = resp != null && resp.size() > 0;
        return !encontrou;
    }

}
