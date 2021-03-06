package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoDTO;
import br.com.centralit.citcorpore.bean.SolicitacaoServicoProblemaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

public class SolicitacaoServicoProblemaDao extends CrudDaoDefaultImpl {

    public SolicitacaoServicoProblemaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);

    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDPROBLEMA", "idProblema", false, false, false, false));
        listFields.add(new Field("IDSOLICITACAOSERVICO", "idSolicitacaoServico", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "solicitacaoservicoproblema";
    }

    @Override
    public Class getBean() {
        return SolicitacaoServicoProblemaDTO.class;
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    public void deleteByIdProblema(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idProblema", "=", parm));
        super.deleteByCondition(condicao);
    }

    public void deleteByIdSolictacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", parm));
        super.deleteByCondition(condicao);
    }

    public Collection findByIdSolictacaoServico(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idSolicitacaoServico", "=", parm));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection findByIdProblema(final Integer parm) throws PersistenceException {
        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();
        condicao.add(new Condition("idProblema", "=", parm));
        return super.findByCondition(condicao, ordenacao);
    }

    public Collection<SolicitacaoServicoDTO> listSolicitacaoServicoByProblema(final Integer id) throws PersistenceException {
        final List listRetorno = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        List list = new ArrayList<>();
        final List parametro = new ArrayList<>();

        sql.append("select ss.idsolicitacaoservico, s.nomeservico, emp.nome , us.nome , ssp.idproblema, pr.titulo, pr.descricao ");
        sql.append("from solicitacaoservicoproblema ssp ");
        sql.append("inner join solicitacaoservico ss on ssp.idsolicitacaoservico = ss.idsolicitacaoservico ");
        sql.append("inner join empregados emp on emp.idempregado = ss.idsolicitante ");
        sql.append("inner join usuario us on us.idusuario = ss.idresponsavel ");
        sql.append("inner join servicocontrato sc on ss.idservicocontrato = sc.idservicocontrato ");
        sql.append("inner join servico s on sc.idservico = s.idservico ");
        sql.append("inner join problema pr on pr.idproblema = ssp.idproblema ");

        sql.append("where ssp.idproblema = ? ");
        parametro.add(id);
        sql.append("order by ssp.idproblema asc ");

        list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("idSolicitacaoServico");
        listRetorno.add("nomeServico");
        listRetorno.add("solicitante");
        listRetorno.add("responsavel");
        listRetorno.add("idProblema");
        listRetorno.add("tituloProblema");
        listRetorno.add("descricaoProblema");

        if (list != null && !list.isEmpty()) {

            final Collection<SolicitacaoServicoDTO> listSolicitacaoProblemaByCriterios = this.listConvertion(SolicitacaoServicoDTO.class, list, listRetorno);

            return listSolicitacaoProblemaByCriterios;

        }

        return null;
    }

    public List<SolicitacaoServicoDTO> listSolicitacaoServicoByIdProblema(final Integer idProblema) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final List fields = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select ss.idsolicitacaoservico  ");
        sql.append("  from solicitacaoservico ss   ");
        sql.append(" inner join solicitacaoservicoproblema pm ");
        sql.append(" on ss.idsolicitacaoservico = pm.idsolicitacaoservico ");
        sql.append(" where pm.idProblema =  ? ");

        parametro.add(idProblema);

        fields.add("idSolicitacaoServico");

        final List dados = this.execSQL(sql.toString(), parametro.toArray());

        return this.listConvertion(SolicitacaoServicoDTO.class, dados, fields);
    }

    public SolicitacaoServicoProblemaDTO restoreByIdProblema(final Integer idProblema) throws PersistenceException {
        final List parametro = new ArrayList<>();
        parametro.add(idProblema);

        final List<String> listRetorno = new ArrayList<String>();
        listRetorno.add("idProblema");
        listRetorno.add("idSolicitacaoServico");

        final String sql = " select * from solicitacaoservicoproblema where idproblema = ? ";

        final List lista = this.execSQL(sql.toString(), parametro.toArray());

        if (lista != null && !lista.isEmpty()) {
            final List listaResult = engine.listConvertion(SolicitacaoServicoProblemaDTO.class, lista, listRetorno);
            return (SolicitacaoServicoProblemaDTO) listaResult.get(0);
        }
        return null;
    }

}
