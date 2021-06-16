package egovframework.dev.imp.codegen.template;

import java.util.Date;

import egovframework.dev.imp.codegen.template.model.Attribute;
import egovframework.dev.imp.codegen.template.model.DataModelContext;
import egovframework.rte.fdl.string.EgovDateUtil;

public class GodSql {

	public static String insert(DataModelContext dataModel) {
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();

		sb.append("insert into ");
		sb.append(dataModel.getEntity().getName());

		sb.append(" (\n");

		String today = EgovDateUtil.toString(new Date(), null, null);

		int i = 0;
		int size = dataModel.getAttributes().size() - 1;
		for (Attribute attr : dataModel.getAttributes()) {
			sb.append("\t");
			sb.append(attr.getName());

			if ("VARCHAR".equals(attr.getType())) {
				sb2.append("\t'");
				sb2.append(attr.getName());
				sb2.append("'");
			} else if ("DATETIME".equals(attr.getType())) {
				sb2.append("\t'");
				sb2.append(today);
				sb2.append("'");
			} else {
				sb2.append("\t0");
			}

			if (i == size) {
				sb.append("\n");
				sb2.append("\n");
			} else {
				sb.append(",\n");
				sb2.append(",\n");
			}

			i++;
		}

		sb.append(") values (\n");

		sb.append(sb2);

		sb.append(")");

		return sb.toString();
	}

}
