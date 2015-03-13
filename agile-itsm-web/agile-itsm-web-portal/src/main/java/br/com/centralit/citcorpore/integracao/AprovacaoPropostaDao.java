package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AprovacaoPropostaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class AprovacaoPropostaDao extends CrudDaoDefaultImpl {

    public AprovacaoPropostaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idAprovacaoProposta", "idAprovacaoProposta", true, true, false, false));
        listFields.add(new Field("idRequisicaoMudanca", "idRequisicaoMudanca", false, false, false, false));
        listFields.add(new Field("idEmpregado", "idEmpregado", false, false, false, false));
        listFields.add(new Field("nomeEmpregado", "nomeEmpregado", false, false, false, false));
        listFields.add(new Field("voto", "voto", false, false, false, false));
        listFields.add(new Field("comentario", "comentario", false, false, false, false));
        listFields.add(new Field("datahorainicio", "dataHoraInicio", false, false, false, false));
        listFields.add(new Field("datahoravotacao", "datahoravotacao", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return "APROVACAOPROPOSTA";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return AprovacaoPropostaDTO.class;
    }

    public Collection<AprovacaoPropostaDTO> listaAprovacaoPropostaPorIdRequisicaoMudanca(final Integer idRequisicaoMudanca, final Integer idGrupo,
            final Integer idEmpregado) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT distinct (aprovacaoproposta.idempregado), aprovacaoproposta.comentario, aprovacaoproposta.voto, aprovacaoproposta.idrequisicaomudanca,aprovacaoproposta.idaprovacaoproposta,empregados.nome,aprovacaoproposta.datahorainicio, aprovacaoproposta.datahoravotacao  ");
        sql.append("FROM " + this.getTableName() + " ");
        sql.append("inner join gruposempregados on gruposempregados.idempregado = aprovacaoproposta.idempregado ");
        sql.append("inner join empregados on empregados.idempregado = aprovacaoproposta.idempregado ");
        sql.append(" WHERE  aprovacaoproposta.idrequisicaomudanca = ?  ");
        parametro.add(idRequisicaoMudanca);
        /*
         * if(idGrupo!=null){
         * sql.append("and gruposempregados.idgrupo = ?");
         * parametro.add(idGrupo);
         * }
         */

        // parametro.add(idEmpregado);

        final List lista = this.execSQL(sql.toString(), parametro.toArray());

        final List listRetorno = new ArrayList<>();
        listRetorno.add("idEmpregado");
        listRetorno.add("comentario");
        listRetorno.add("voto");
        listRetorno.add("idRequisicaoMudanca");
        listRetorno.add("idAprovacaoProposta");
        listRetorno.add("nomeEmpregado");
        listRetorno.add("dataHoraInicio");
        listRetorno.add("dataHoraVotacao");
        if (lista != null && !lista.isEmpty()) {
            return engine.listConvertion(this.getBean(), lista, listRetorno);
        } else {
            return null;
        }
    }

    public void deleteByIdRequisicaoMudanca(final Integer idRequisicaoMudanca) throws PersistenceException {
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idRequisicaoMudanca", "=", idRequisicaoMudanca));
        super.deleteByCondition(lstCondicao);
    }

    public void updateByIdRequisicaoMudanca(final AprovacaoPropostaDTO aprovacaopropostaDto) throws PersistenceException {
        super.updateNotNull(aprovacaopropostaDto);
    }

    public void deleteLinha(final Integer idRequisicaoMudanca, final Integer idEmpregado) throws PersistenceException {
        // List parametro = new ArrayList<>();
        // parametro.add(idRequisicaoMudanca);
        // String sql = "DELETE FROM aprovacaoproposta where (datahoravotacao = 'Ainda não votou.' and idrequisicaomudanca = ?)";
        // this.execSQL(sql.toString(), parametro.toArray());
        final List lstCondicao = new ArrayList<>();
        lstCondicao.add(new Condition("idRequisicaoMudanca", "=", idRequisicaoMudanca));
        lstCondicao.add(new Condition("idEmpregado", "=", idEmpregado));
        super.deleteByCondition(lstCondicao);
    }

    public Integer quantidadeAprovacaoPropostaPorVotoAprovada(final AprovacaoPropostaDTO aprovacao, final Integer idGrupo) throws PersistenceException {

        final List listRetorno = new ArrayList<>();
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select count(voto) from  aprovacaoproposta ");
        /*
         * if(idGrupo !=null){
         * sql.append("inner join gruposempregados on gruposempregados.idempregado = aprovacaoproposta.idempregado ");
         * }
         */
        sql.append(" where idrequisicaomudanca = ?  and voto = ? ");
        parametro.add(aprovacao.getIdRequisicaoMudanca());
        parametro.add(aprovacao.getVoto());
        /*
         * if(idGrupo !=null){
         * sql.append("and gruposempregados.idgrupo = ?");
         * parametro.add(idGrupo);
         * }
         */

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("quantidadeVotoAprovada");

        if (list != null && !list.isEmpty()) {

            final AprovacaoPropostaDTO aprovacaopropostaDto = (AprovacaoPropostaDTO) engine.listConvertion(AprovacaoPropostaDTO.class, list, listRetorno)
                    .get(0);

            return aprovacaopropostaDto.getQuantidadeVotoAprovada();

        } else {
            return new Integer(0);
        }

    }

    public Integer quantidadeAprovacaoPropostaPorVotoRejeitada(final AprovacaoPropostaDTO aprovacao, final Integer idGrupo) throws PersistenceException {

        final List listRetorno = new ArrayList<>();
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select count(voto) from  aprovacaoproposta  ");
        /*
         * if(idGrupo !=null){
         * sql.append("inner join gruposempregados on gruposempregados.idempregado = aprovacaoproposta.idempregado ");
         * }
         */
        sql.append(" where idrequisicaomudanca = ?  and voto = ? ");
        parametro.add(aprovacao.getIdRequisicaoMudanca());
        parametro.add(aprovacao.getVoto());
        /*
         * if(idGrupo !=null){
         * sql.append("and gruposempregados.idgrupo = ?");
         * parametro.add(idGrupo);
         * }
         */

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("quantidadeVotoRejeitada");

        if (list != null && !list.isEmpty()) {

            final AprovacaoPropostaDTO aprovacaopropostaDto = (AprovacaoPropostaDTO) engine.listConvertion(AprovacaoPropostaDTO.class, list, listRetorno)
                    .get(0);

            return aprovacaopropostaDto.getQuantidadeVotoRejeitada();

        } else {
            return new Integer(0);
        }

    }

    @Deprecated
    public Integer quantidadeAprovacaoProposta(final AprovacaoPropostaDTO aprovacao, final Integer idGrupo) throws PersistenceException {

        final List listRetorno = new ArrayList<>();
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select count(aprovacaoproposta.idempregado) from  aprovacaoproposta  ");
        /*
         * if(idGrupo !=null){
         * sql.append("inner join gruposempregados on gruposempregados.idempregado = aprovacaoproposta.idempregado ");
         * }
         */
        sql.append(" where idrequisicaomudanca = ? ");
        parametro.add(aprovacao.getIdRequisicaoMudanca());
        /*
         * if(idGrupo !=null){
         * sql.append("and gruposempregados.idgrupo = ?");
         * parametro.add(idGrupo);
         * }
         */

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("quantidadeAprovacaoProposta");

        if (list != null && !list.isEmpty()) {

            final AprovacaoPropostaDTO aprovacaopropostaDto = (AprovacaoPropostaDTO) engine.listConvertion(AprovacaoPropostaDTO.class, list, listRetorno)
                    .get(0);

            return aprovacaopropostaDto.getQuantidadeAprovacaoProposta();

        } else {
            return new Integer(0);
        }

    }

    public Boolean validacaoAprovacaoProposta(final Integer idRequisicaoMudanca) throws PersistenceException {

        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select idrequisicaomudanca from aprovacaoproposta where idrequisicaomudanca = ?");

        parametro.add(idRequisicaoMudanca);

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {

            return true;

        } else {
            return false;
        }

    }

    public Integer quantidadeDeEmpregdosPorGrupo(final Integer idGrupo) throws PersistenceException {
        final List listRetorno = new ArrayList<>();
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();

        sql.append("select count(idgrupo) from  gruposempregados  ");
        sql.append("where gruposempregados.idgrupo = ?");
        parametro.add(idGrupo);

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        listRetorno.add("quantidadeAprovacaoProposta");

        if (list != null && !list.isEmpty()) {
            final AprovacaoPropostaDTO aprovacaopropostaDto = (AprovacaoPropostaDTO) engine.listConvertion(AprovacaoPropostaDTO.class, list, listRetorno)
                    .get(0);
            return aprovacaopropostaDto.getQuantidadeAprovacaoProposta();
        } else {
            return new Integer(0);
        }
    }

}
