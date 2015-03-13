/**
 * CentralIT - CITSmart
 */
package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.GrupoDTO;
import br.com.centralit.citcorpore.bean.PastaDTO;
import br.com.centralit.citcorpore.bean.PerfilAcessoGrupoDTO;
import br.com.centralit.citcorpore.bean.UsuarioDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.Condition;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.integracao.Order;
import br.com.citframework.util.Constantes;

/**
 * DAO de Pasta.
 *
 * @author valdoilo.damasceno
 */
public class PastaDAO extends CrudDaoDefaultImpl {

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("IDPASTA", "id", true, true, false, false));
        listFields.add(new Field("NOME", "nome", false, false, false, false));
        listFields.add(new Field("DATAINICIO", "dataInicio", false, false, false, false));
        listFields.add(new Field("DATAFIM", "dataFim", false, false, false, false));
        listFields.add(new Field("IDPASTAPAI", "idPastaPai", false, false, false, false));
        listFields.add(new Field("HERDAPERMISSOES", "herdaPermissoes", false, false, false, false));
        listFields.add(new Field("IDNOTIFICACAO", "idNotificacao", false, false, false, false));

        return listFields;
    }

    @Override
    public Collection list() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        ordenacao.add(new Order("nome"));
        return super.list(ordenacao);
    }

    /**
     * Consulta Pastas Ativas.
     *
     * @return pastasAtivas
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public Collection<PastaDTO> consultarPastasAtivas() throws PersistenceException {
        final List<Order> ordenacao = new ArrayList<>();
        final List<Condition> condicao = new ArrayList<>();

        ordenacao.add(new Order("nome"));
        condicao.add(new Condition("dataFim", "is", null));

        return super.findByCondition(condicao, ordenacao);
    }

    /**
     * Consulta Pastas que não possuem Pasta Pai. Ou seja, retorna Primeiras Pastas da Arvore de Pastas.
     *
     * @return listaDePastas
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public Collection<PastaDTO> listPastaSuperiorSemPai() throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();
        final List retorno = new ArrayList<>();

        sql.append("SELECT * FROM pasta WHERE  datafim IS NULL  AND idpastapai IS NULL ORDER BY pasta.nome");

        retorno.add("id");
        retorno.add("nome");
        retorno.add("dataInicio");
        retorno.add("dataFim");
        retorno.add("idPastaPai");

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        return engine.listConvertion(PastaDTO.class, list, retorno);
    }

    /**
     * Verifica se Pasta está sendo utilizada.
     *
     * @param pastaBean
     * @return boolean
     * @throws Exception
     * @author valdoilo.damasceno
     */
    public boolean isEmUso(final PastaDTO pastaBean) throws PersistenceException {
        if (this.possuiSubPasta(pastaBean) || this.possuiBaseConhecimento(pastaBean)) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se pasta possui Base de Conhecimento.
     *
     * @param pastaBean
     * @return true - se possui; false - se não possui.
     * @throws PersistenceException
     * @author valdoilo.damasceno
     */
    public boolean possuiBaseConhecimento(final PastaDTO pastaBean) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();

        sql.append("SELECT distinct pasta.nome FROM pasta pasta ");
        sql.append("INNER JOIN baseconhecimento on pasta.idpasta = baseconhecimento.idpasta AND baseconhecimento.datafim IS NULL ");
        sql.append("WHERE pasta.idPasta = ?");
        parametro.add(pastaBean.getId());
        final List list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Verifica se pasta possui subPasta.
     *
     * @param pastaBean
     * @return true - se possui; false - se não possui.
     * @throws PersistenceException
     * @author valdoilo.damasceno
     */
    public boolean possuiSubPasta(final PastaDTO pastaBean) throws PersistenceException {
        final StringBuilder sql = new StringBuilder();
        final List parametro = new ArrayList<>();

        sql.append("SELECT distinct pasta.nome FROM pasta pasta ");
        sql.append("INNER JOIN pasta subpasta ON pasta.idpasta = subpasta.idpastapai ");
        sql.append("WHERE pasta.idPasta = ? AND subpasta.datafim is null");

        parametro.add(pastaBean.getId());

        final List list = this.execSQL(sql.toString(), parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Busca somente pastas com perfil de acesso onde usuario logado tenha acesso.
     *
     * @param usuario
     * @param idgrupo
     * @return List<PastaDTO>
     * @throws Exception
     */
    public List<PastaDTO> listPastaByPerfilAcessoUsuario(final UsuarioDTO usuario) throws PersistenceException {

        final StringBuilder sql = new StringBuilder();
        final List parametros = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        final GrupoDao grupoDao = new GrupoDao();
        final PerfilAcessoGrupoDao perfilAcessoGrupoDao = new PerfilAcessoGrupoDao();

        final Collection<GrupoDTO> gruposDoEmpregado = grupoDao.getGruposByIdEmpregado(usuario.getIdEmpregado());

        listRetorno.add("id");
        listRetorno.add("nome");
        listRetorno.add("idPastaPai");

        sql.append("SELECT distinct pasta.idpasta , pasta.nome , pasta.idpastapai FROM pasta pasta ");
        sql.append("inner join perfilacessopasta perfilacessopasta ON perfilacessopasta.idpasta = pasta.idpasta  ");
        sql.append("WHERE  pasta.idpastapai is null and pasta.datafim is null ");

        if (usuario.getIdPerfilAcessoUsuario() != null) {

            sql.append("AND (perfilacessopasta.idperfil = ? ");
            parametros.add(usuario.getIdPerfilAcessoUsuario());

            if (gruposDoEmpregado != null && !gruposDoEmpregado.isEmpty()) {
                for (final GrupoDTO grupo : gruposDoEmpregado) {

                    final PerfilAcessoGrupoDTO perfilAcessoGrupo = perfilAcessoGrupoDao.obterPerfilAcessoGrupo(grupo);

                    if (perfilAcessoGrupo != null) {

                        sql.append("OR perfilacessopasta.idperfil = ? ");
                        parametros.add(perfilAcessoGrupo.getIdPerfilAcessoGrupo());

                    }
                }
            }
            sql.append(")");

        } else {

            if (gruposDoEmpregado != null && !gruposDoEmpregado.isEmpty()) {

                boolean aux = true;
                for (final GrupoDTO grupo : gruposDoEmpregado) {
                    final PerfilAcessoGrupoDTO perfilAcessoGrupo = perfilAcessoGrupoDao.obterPerfilAcessoGrupo(grupo);

                    if (perfilAcessoGrupo != null) {

                        if (aux) {

                            sql.append("AND perfilacessopasta.idperfil = ? ");
                            parametros.add(perfilAcessoGrupo.getIdPerfilAcessoGrupo());

                            aux = false;
                        } else {

                            sql.append("OR perfilacessopasta.idperfil = ? ");
                            parametros.add(perfilAcessoGrupo.getIdPerfilAcessoGrupo());

                            aux = false;

                        }
                    }
                }
                sql.append(")");
            }

        }

        sql.append(" ORDER BY pasta.nome");

        return engine.listConvertion(this.getBean(), this.execSQL(sql.toString(), parametros.toArray()), listRetorno);
    }

    /**
     * Lista Subpastas de acordo com o Perfil de Acesso do Usuário informado.
     *
     * @param pastaSuperior
     * @param usuario
     * @return Collection<PastaDTO>
     * @throws Exception
     */
    public Collection<PastaDTO> listSubPastaByPerfilAcessoUsuario(final PastaDTO pastaSuperior, final UsuarioDTO usuario) throws PersistenceException {

        final StringBuilder sql = new StringBuilder();
        final List parametros = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        final GrupoDao grupoDao = new GrupoDao();
        final PerfilAcessoGrupoDao perfilAcessoGrupoDao = new PerfilAcessoGrupoDao();

        final Collection<GrupoDTO> gruposDoEmpregado = grupoDao.getGruposByIdEmpregado(usuario.getIdEmpregado());

        sql.append("SELECT distinct pasta.idPasta, pasta.idPastaPai, nome FROM pasta  pasta ");
        sql.append("JOIN perfilacessopasta perfilacessopasta ON pasta.idpasta = perfilacessopasta.idpasta where pasta.datafim IS NULL AND pasta.idPastaPai = ? ");
        parametros.add(pastaSuperior.getId());

        if (usuario.getIdPerfilAcessoUsuario() != null) {

            sql.append("AND (perfilacessopasta.idperfil = ? ");
            parametros.add(usuario.getIdPerfilAcessoUsuario());

            if (gruposDoEmpregado != null && !gruposDoEmpregado.isEmpty()) {
                for (final GrupoDTO grupo : gruposDoEmpregado) {

                    final PerfilAcessoGrupoDTO perfilAcessoGrupo = perfilAcessoGrupoDao.obterPerfilAcessoGrupo(grupo);

                    if (perfilAcessoGrupo != null) {

                        sql.append("OR perfilacessopasta.idperfil = ? ");
                        parametros.add(perfilAcessoGrupo.getIdPerfilAcessoGrupo());

                    }
                }
            }
            sql.append(")");

        } else {

            if (gruposDoEmpregado != null && !gruposDoEmpregado.isEmpty()) {

                boolean aux = true;
                for (final GrupoDTO grupo : gruposDoEmpregado) {
                    final PerfilAcessoGrupoDTO perfilAcessoGrupo = perfilAcessoGrupoDao.obterPerfilAcessoGrupo(grupo);

                    if (perfilAcessoGrupo != null) {
                        if (aux) {
                            sql.append("AND perfilacessopasta.idperfil = ? ");
                            parametros.add(perfilAcessoGrupo.getIdPerfilAcessoGrupo());

                            aux = false;
                        } else {
                            sql.append("OR perfilacessopasta.idperfil = ? ");
                            parametros.add(perfilAcessoGrupo.getIdPerfilAcessoGrupo());

                            aux = false;
                        }
                    }
                }
            }
            sql.append(")");
        }
        sql.append(" ORDER BY pasta.nome");

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametros.toArray());

        listRetorno.add("id");
        listRetorno.add("idPastaPai");
        listRetorno.add("nome");

        final List result = engine.listConvertion(this.getBean(), lista, listRetorno);

        return result;
    }

    /**
     * Lista Subpastas ATIVIVAS da Pasta informada.
     *
     * @param pastaSuperior
     * @return Collection<PastaDTO>
     * @throws Exception
     */
    public Collection<PastaDTO> listSubPastas(final PastaDTO pastaSuperior) throws PersistenceException {

        final List<Condition> condicao = new ArrayList<>();
        final List<Order> ordenacao = new ArrayList<>();

        condicao.add(new Condition("dataFim", "is", null));
        condicao.add(new Condition("idPastaPai", "=", pastaSuperior.getId()));

        ordenacao.add(new Order("id"));

        return this.findByCondition(condicao, ordenacao);
    }

    @Override
    public String getTableName() {
        return "PASTA";
    }

    @Override
    public Class getBean() {
        return PastaDTO.class;
    }

    public PastaDAO() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Método para verificar se caso exista uma pasta com o mesmo nome
     *
     * @author rodrigo.oliveira
     * @param pastaDTO
     * @return Se caso exista pasta com o mesmo nome retorna true
     */
    public boolean verificaSeExistePasta(final PastaDTO pastaDTO) throws PersistenceException {

        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        String sql = "SELECT idpasta FROM " + this.getTableName() + " WHERE nome = ? AND dataFim IS NULL ";
        parametro.add(pastaDTO.getNome());

        if (pastaDTO.getId() != null) {
            sql += " AND idpasta <> ? ";
            parametro.add(pastaDTO.getId());
        }

        list = this.execSQL(sql, parametro.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean verificarSeUsuarioPossuiAcessoPasta(final PastaDTO pastaDto, final UsuarioDTO usuarioDto) throws PersistenceException {
        final List parametro = new ArrayList<>();
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT distinct pasta.idPasta FROM pasta  pasta ");
        sql.append("JOIN perfilacessopasta perfilacessopasta ON pasta.idpasta = perfilacessopasta.idpasta where pasta.datafim IS NULL AND pasta.idPasta = ? ");
        parametro.add(pastaDto.getId());
        if (usuarioDto.getIdPerfilAcessoUsuario() != null) {
            sql.append(" AND (perfilacessopasta.idperfil = ? ");
            parametro.add(usuarioDto.getIdPerfilAcessoUsuario());

            final GrupoDao grupoDao = new GrupoDao();
            final PerfilAcessoGrupoDao perfilAcessoGrupoDao = new PerfilAcessoGrupoDao();

            final Collection<GrupoDTO> gruposDoEmpregado = grupoDao.getGruposByIdEmpregado(usuarioDto.getIdEmpregado());

            if (gruposDoEmpregado != null && !gruposDoEmpregado.isEmpty()) {
                for (final GrupoDTO grupo : gruposDoEmpregado) {
                    final PerfilAcessoGrupoDTO perfilAcessoGrupo = perfilAcessoGrupoDao.obterPerfilAcessoGrupo(grupo);

                    if (perfilAcessoGrupo != null) {
                        sql.append(" OR perfilacessopasta.idperfil = ? ");
                        parametro.add(perfilAcessoGrupo.getIdPerfilAcessoGrupo());
                    }
                }
            }
            sql.append(")");

        }

        List lista = new ArrayList<>();
        lista = this.execSQL(sql.toString(), parametro.toArray());

        if (lista != null && !lista.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Verifica se Pasta Informada porssui conhecimento do tipo FAQ.
     *
     * @param pastaDto
     * @return true = Pasta possui FAQ; false = Pasta não possui FAQ.
     * @throws Exception
     */
    public boolean verificarSePastaPossuiFaq(final PastaDTO pastaDto) throws PersistenceException {

        final StringBuilder sql = new StringBuilder();
        List list = new ArrayList<>();
        final List parametros = new ArrayList<>();

        sql.append("select * from pasta ");
        sql.append("inner join baseconhecimento on pasta.idpasta = baseconhecimento.idpasta ");
        sql.append("where baseconhecimento.faq = ? and pasta.idpasta = ? and baseconhecimento.datafim is null and (baseconhecimento.arquivado = ? or baseconhecimento.arquivado = ?) and baseconhecimento.status = ?");

        parametros.add("S");
        parametros.add(pastaDto.getId());
        parametros.add("N");
        parametros.add(null);
        parametros.add("S");

        list = this.execSQL(sql.toString(), parametros.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Verifica se Pasta Informada porssui conhecimento do tipo ERRO CONHECIDO.
     *
     * @param pastaDto
     * @return true = Pasta possui FAQ; false = Pasta não possui FAQ.
     * @throws Exception
     */
    public boolean verificarSePastaPossuiErroConhecido(final PastaDTO pastaDto) throws PersistenceException {

        final StringBuilder sql = new StringBuilder();
        List list = new ArrayList<>();
        final List parametros = new ArrayList<>();

        sql.append("select * from pasta ");
        sql.append("inner join baseconhecimento on pasta.idpasta = baseconhecimento.idpasta ");
        sql.append("where baseconhecimento.erroconhecido = ? and pasta.idpasta = ? and baseconhecimento.datafim is null and (baseconhecimento.arquivado = ? or baseconhecimento.arquivado = ?) and baseconhecimento.status = ?");

        parametros.add("S");
        parametros.add(pastaDto.getId());
        parametros.add("N");
        parametros.add(null);
        parametros.add("S");

        list = this.execSQL(sql.toString(), parametros.toArray());

        if (list != null && !list.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Verifca se existe pasta pai e se herda de pasta pai.
     *
     * @param idPastaFilho
     * @return
     * @throws Exception
     * @author mario.haysaki
     */
    public PastaDTO idpastaPaiEHerdaDaPastaPai(final Integer idPastaFilho) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final List listRetorno = new ArrayList<>();

        listRetorno.add("id");
        listRetorno.add("idPastaPai");
        listRetorno.add("herdaPermissoes");
        final String sql = "SELECT idpasta, idpastapai, herdapermissoes from " + this.getTableName() + " WHERE idpasta = ? AND datafim is null ";
        parametro.add(idPastaFilho);

        list = engine.listConvertion(this.getBean(), this.execSQL(sql.toString(), parametro.toArray()), listRetorno);
        if (list != null && !list.isEmpty()) {
            return (PastaDTO) list.get(0);
        }
        return null;
    }

}
