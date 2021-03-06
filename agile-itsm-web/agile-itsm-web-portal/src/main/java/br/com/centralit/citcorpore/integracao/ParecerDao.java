package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ParecerDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

public class ParecerDao extends CrudDaoDefaultImpl {

    public ParecerDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();
        listFields.add(new Field("idParecer", "idParecer", true, true, false, false));
        listFields.add(new Field("idAlcada", "idAlcada", false, false, false, false));
        listFields.add(new Field("idJustificativa", "idJustificativa", false, false, false, false));
        listFields.add(new Field("idResponsavel", "idResponsavel", false, false, false, false));
        listFields.add(new Field("dataHoraParecer", "dataHoraParecer", false, false, false, false));
        listFields.add(new Field("complementoJustificativa", "complementoJustificativa", false, false, false, false));
        listFields.add(new Field("aprovado", "aprovado", false, false, false, false));
        listFields.add(new Field("observacoes", "observacoes", false, false, false, false));
        return listFields;
    }

    @Override
    public String getTableName() {
        return this.getOwner() + "Parecer";
    }

    @Override
    public Collection list() throws PersistenceException {
        return null;
    }

    @Override
    public Class getBean() {
        return ParecerDTO.class;
    }

    @Override
    public Collection find(final BaseEntity arg0) throws PersistenceException {
        return null;
    }

    /**
     * Retorna verdadeiro caso exista alguma associação com a tabela JustificativaParecer ou falso caso não exista associação com a tabela JustificativaParecer
     *
     * @param obj
     * @return boolean
     * @throws Exception
     * @author thays.araujo
     */
    public boolean verificarSeExisteJustificativaParecer(final ParecerDTO obj) throws PersistenceException {
        final List parametro = new ArrayList<>();
        List list = new ArrayList<>();
        final String sql = "select idjustificativa From " + this.getTableName() + "  where  idjustificativa = ?    ";

        parametro.add(obj.getIdJustificativa());
        list = this.execSQL(sql, parametro.toArray());
        if (list != null && !list.isEmpty()) {
            return true;
        }
        return false;
    }

}
