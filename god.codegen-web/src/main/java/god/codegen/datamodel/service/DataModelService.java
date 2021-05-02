package god.codegen.datamodel.service;

import java.util.List;

import model.DataModelContext;

public interface DataModelService {

	List<DataModelContext> getDataModel(DataModelVO dataModelVO);

}
