package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.LocalidadeItemConfiguracaoDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class LocalidadeItemConfiguracaoDao extends CrudDaoDefaultImpl {

    public LocalidadeItemConfiguracaoDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);

    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {

        return null;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idlocalidade", "idLocalidade", true, true, false, false));
        listFields.add(new Field("iditemconfiguracao", "idItemConfiguracao", false, false, false, false));
        listFields.add(new Field("idunidade", "idUnidade", false, false, false, false));
        listFields.add(new Field("idregioes", "idRegioes", false, false, false, false));
        listFields.add(new Field("itemconfiguracao", "itemConfiguracao", false, false, false, false));
        listFields.add(new Field("iduf", "idUf", false, false, false, false));
        listFields.add(new Field("cidade", "cidade", false, false, false, false));
        listFields.add(new Field("endereco", "endereco", false, false, false, false));
        listFields.add(new Field("complemento", "complemento", false, false, false, false));
        listFields.add(new Field("bairro", "bairro", false, false, false, false));
        listFields.add(new Field("numero", "numero", false, false, false, false));
        listFields.add(new Field("edificio", "edificio", false, false, false, false));
        listFields.add(new Field("sala", "sala", false, false, false, false));
        listFields.add(new Field("departamento", "departamento", false, false, false, false));
        listFields.add(new Field("divisao", "divisao", false, false, false, false));
        listFields.add(new Field("subdivisao", "subdivisao", false, false, false, false));
        listFields.add(new Field("secao", "secao", false, false, false, false));
        listFields.add(new Field("datainicio", "dataInicio", false, false, false, false));
        listFields.add(new Field("datafim", "dataFim", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "localidadeitemconfiguracao";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return LocalidadeItemConfiguracaoDTO.class;
    }

    public LocalidadeItemConfiguracaoDTO listByIdRegiao(final LocalidadeItemConfiguracaoDTO obj) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final String sql = "select idregioes from " + this.getTableName() + " where idregioes = ? ";
        fields.add("idRegioes");
        list = this.execSQL(sql, new Object[] {obj.getIdRegioes()});
        return (LocalidadeItemConfiguracaoDTO) this.listConvertion(this.getBean(), list, fields).get(0);
    }

    public LocalidadeItemConfiguracaoDTO listByIdUf(final LocalidadeItemConfiguracaoDTO obj) throws PersistenceException {
        List list = new ArrayList<>();
        final List fields = new ArrayList<>();
        final String sql = "select iduf from " + this.getTableName() + " where idlocalidade = ? ";
        fields.add("idUf");
        list = this.execSQL(sql, new Object[] {obj.getIdRegioes()});
        return (LocalidadeItemConfiguracaoDTO) this.listConvertion(this.getBean(), list, fields).get(0);
    }

}
