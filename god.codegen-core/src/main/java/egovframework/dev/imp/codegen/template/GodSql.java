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

			sb2.append("\t");
			sb2.append(getValue(attr, today));

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

	public static String select(DataModelContext dataModel) {
		String today = EgovDateUtil.toString(new Date(), null, null);

		StringBuffer sb = new StringBuffer();
		StringBuffer select = new StringBuffer();
		StringBuffer where = new StringBuffer();

		sb.append("select\n");

		for (Attribute attr : dataModel.getAttributes()) {
			if (attr.getIsPrimaryKey()) {
				where.append("\tand ");
				where.append(attr.getName());
				where.append(" = ");
				where.append(getValue(attr, today));
				where.append("\n");
			} else {
				select.append("\t");
				select.append(attr.getName());
				select.append(",\n");
			}
		}

		String selectString = select.toString();
		sb.append(selectString.substring(0, selectString.length() - 2));
		sb.append("\nfrom\n\t");
		sb.append(dataModel.getEntity().getTableName());
		sb.append("\nwhere\n\t1 = 1\n");
		sb.append(where);

		return sb.toString();
	}

	public static String selectList(DataModelContext dataModel) {
		String today = EgovDateUtil.toString(new Date(), null, null);

		StringBuffer sb = new StringBuffer();
		StringBuffer select = new StringBuffer();
		StringBuffer where = new StringBuffer();

		sb.append("select\n");

		for (Attribute attr : dataModel.getAttributes()) {
			where.append("\tand ");
			where.append(attr.getName());
			where.append(" = ");
			where.append(getValue(attr, today));
			where.append("\n");

			select.append("\t");
			select.append(attr.getName());
			select.append(",\n");
		}

		String selectString = select.toString();
		sb.append(selectString.substring(0, selectString.length() - 2));
		sb.append("\nfrom\n\t");
		sb.append(dataModel.getEntity().getTableName());
		sb.append("\nwhere\n\t1 = 1\n");
		sb.append(where);

		return sb.toString();
	}

	public static String update(DataModelContext dataModel) {
		String today = EgovDateUtil.toString(new Date(), null, null);

		StringBuffer sb = new StringBuffer();
		StringBuffer set = new StringBuffer();
		StringBuffer where = new StringBuffer();

		sb.append("update ");
		sb.append(dataModel.getEntity().getTableName());
		sb.append(" set\n");

		for (Attribute attr : dataModel.getAttributes()) {
			if (attr.getIsPrimaryKey()) {
				where.append("\tand ");
				where.append(attr.getName());
				where.append(" = ");
				where.append(getValue(attr, today));
				where.append("\n");
			} else {
				set.append("\t");
				set.append(attr.getName());
				set.append(" = ");
				set.append(getValue(attr, today));
				set.append(",\n");
			}
		}

		String setString = set.toString();
		sb.append(setString.substring(0, setString.length() - 2));
		sb.append("\nwhere 1 = 1\n");
		sb.append(where);

		return sb.toString();
	}

	public static String delete(DataModelContext dataModel) {
		String today = EgovDateUtil.toString(new Date(), null, null);

		StringBuffer sb = new StringBuffer();
		StringBuffer where = new StringBuffer();

		sb.append("delete\nfrom\n\t");
		sb.append(dataModel.getEntity().getTableName());

		for (Attribute attr : dataModel.getAttributes()) {
			if (attr.getIsPrimaryKey()) {
				where.append("\tand ");
				where.append(attr.getName());
				where.append(" = ");
				where.append(getValue(attr, today));
				where.append("\n");
			}
		}

		sb.append("\nwhere\n\t1 = 1\n");
		sb.append(where);

		return sb.toString();
	}

	private static String getValue(Attribute attr, String today) {
		StringBuffer sb = new StringBuffer();

		if ("VARCHAR".equals(attr.getType())) {
			sb.append("'");
			sb.append(attr.getName());
			sb.append("'");
		} else if ("DATETIME".equals(attr.getType())) {
			sb.append("'");
			sb.append(today);
			sb.append("'");
		} else {
			sb.append("0");
		}

		return sb.toString();
	}

}
