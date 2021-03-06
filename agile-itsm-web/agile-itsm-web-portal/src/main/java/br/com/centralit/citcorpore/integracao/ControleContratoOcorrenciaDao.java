package br.com.centralit.citcorpore.integracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ControleContratoDTO;
import br.com.centralit.citcorpore.bean.ControleContratoOcorrenciaDTO;
import br.com.citframework.excecao.PersistenceException;
import br.com.citframework.integracao.CrudDaoDefaultImpl;
import br.com.citframework.integracao.Field;
import br.com.citframework.util.Constantes;

/**
 * @author Pedro
 *
 */
public class ControleContratoOcorrenciaDao extends CrudDaoDefaultImpl {

    public ControleContratoOcorrenciaDao() {
        super(Constantes.getValue("DATABASE_ALIAS"), null);
    }

    @Override
    public Class getBean() {
        return ControleContratoOcorrenciaDTO.class;
    }

    @Override
    public Collection<Field> getFields() {
        final Collection<Field> listFields = new ArrayList<>();

        listFields.add(new Field("idccocorrencia", "idCcOcorrencia", true, true, false, false));
        listFields.add(new Field("assuntoccocorrencia", "assuntoCcOcorrencia", false, false, false, false));
        listFields.add(new Field("idempregadoocorrencia", "idEmpregadoOcorrencia", false, false, false, false));
        listFields.add(new Field("idcontrolecontrato", "idControleContrato", false, false, false, false));
        listFields.add(new Field("dataccocorrencia", "dataCcOcorrencia", false, false, false, false));

        return listFields;
    }

    @Override
    public String getTableName() {
        return "CONTROLECONTRATOOCORRENCIA";
    }

    @Override
    public Collection find(final BaseEntity obj) throws PersistenceException {
        final List ordem = new ArrayList<>();
        return super.find(obj, ordem);
    }

    @Override
    public Collection list() throws PersistenceException {
        final List list = new ArrayList<>();
        return super.list(list);
    }

    private static final String SQL_DELETE = "DELETE FROM CONTROLECONTRATOOCORRENCIA WHERE idcontrolecontrato = ? ";

    public void deleteByIdControleContrato(final ControleContratoDTO controleContrato) throws PersistenceException {
        super.execUpdate(SQL_DELETE, new Object[] {controleContrato.getIdControleContrato()});
    }

    private static final String SQL_FIND = "SELECT * FROM CONTROLECONTRATOOCORRENCIA WHERE idcontrolecontrato = ? ";

    public Collection findByIdControleContrato(final ControleContratoOcorrenciaDTO dto) throws PersistenceException {
        return super.listConvertion(this.getBean(), super.execSQL(SQL_FIND, new Object[] {dto.getIdControleContrato()}), new ArrayList(this.getFields()));
    }

}
