package god.java.sql;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import model.NameCasing;

@Slf4j
public class GodDatabaseMetaDataTest_D3_getColumns_mapper2 {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		GodDatabaseMetaData gdmd = new GodDatabaseMetaDataImpl();

		String catalog = null;
		String schemaPattern = "COM";

//		String tableNamePattern = null;
//		String tableNamePattern = "COMTCADMINISTCODE";
//		String tableNamePattern = "COMTCADMINISTCODERECPTNLOG";
		String tableNamePattern = "COMTCCMMNCLCODE";

		String columnNamePattern = null;

		List<ColumnVO> columns = gdmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);

		resultMap(columns);
		select(columns);
		insert(columns);
		update(columns);
		delete(columns);
	}

	void resultMap(List<ColumnVO> columns) {
		StringBuffer sb = new StringBuffer("\n");

		int i = 1;

		NameCasing tnc = null;

		for (ColumnVO column : columns) {
			NameCasing cnc = new NameCasing(column.getColumnName());

			if (i == 1) {
				tnc = new NameCasing(column.getTableName());
				resultMap(sb, tnc);
			}

			result(sb, cnc);

			i++;
		}

		sb.append("</resultMap>\n");

		log.debug("sb={}", sb);
	}

	void resultMap(StringBuffer sb, NameCasing tnc) {
		sb.append("<resultMap");

		sb.append(" id=\"");
		sb.append(tnc.getCcName());
		sb.append("ResultMap");
		sb.append("\"");

		sb.append(" type=\"");
		sb.append(tnc.getPcName());
		sb.append("VO");
		sb.append("\"");

		sb.append(">\n");
	}

	void result(StringBuffer sb, NameCasing cnc) {
		sb.append("    <result");

		sb.append(" property=\"");
		sb.append(cnc.getCcName());
		sb.append("\"");

		sb.append(" column=\"");
		sb.append(cnc.getName());
		sb.append("\"");

		sb.append(" />\n");
	}

	void select(List<ColumnVO> columns) {
		StringBuffer sb = new StringBuffer("\n");

		int i = 1;
		int size = columns.size();

		NameCasing tnc = null;

		for (ColumnVO column : columns) {
			NameCasing cnc = new NameCasing(column.getColumnName());

			if (i == 1) {
				tnc = new NameCasing(column.getTableName());

				sb.append("<select");

				sb.append(" id=\"select");
				sb.append(tnc.getPcName());
				sb.append("\"");

				sb.append(" parameterType=\"");
				sb.append(tnc.getPcName());
				sb.append("VO\"");

				sb.append(" resultMap=\"");
				sb.append(tnc.getCcName());
				sb.append("ResultMap\"");

				sb.append(">\n");
				sb.append("<![CDATA[\n");

				sb.append("/* select");
				sb.append(tnc.getPcName());
				sb.append(" */\n");

				sb.append("select\n");
				sb.append("    ");
				sb.append(cnc.getName());
				sb.append(",");
			} else if (i == size) {
				sb.append("    ");
				sb.append(cnc.getName());
			} else {
				sb.append("    ");
				sb.append(cnc.getName());
				sb.append(",");
			}

			sb.append("\n");

			i++;
		}

		sb.append("from\n");
		sb.append("    ");
		sb.append(tnc.getName());
		sb.append("\n");

		sb.append("where 1 = 1\n");

		for (ColumnVO column : columns) {
			NameCasing cnc = new NameCasing(column.getColumnName());

			sb.append("    and ");
			sb.append(cnc.getName());
			sb.append(" = ${");
			sb.append(cnc.getCcName());
			sb.append("}");
			sb.append("\n");

			i++;
		}

		sb.append("]]>\n");
		sb.append("</select>\n");

		log.debug("sb={}", sb);
	}

	void insert(List<ColumnVO> columns) {
		StringBuffer sb = new StringBuffer("\n");
		StringBuffer sb2 = new StringBuffer();

		int i = 1;
		int size = columns.size();

		NameCasing tnc = null;

		for (ColumnVO column : columns) {
			NameCasing cnc = new NameCasing(column.getColumnName());

			if (i == 1) {
				tnc = new NameCasing(column.getTableName());

				String insertId = "insert" + tnc.getPcName();

				sb.append("<insert");

				sb.append(" id=\"");
				sb.append(insertId);
				sb.append("\"");

				sb.append(" parameterType=\"");
				sb.append(tnc.getPcName());
				sb.append("VO\"");

				sb.append(">\n");
				sb.append("<![CDATA[\n");

				sb.append("/* ");
				sb.append(insertId);
				sb.append(" */\n");

				sb.append("insert into ");
				sb.append(tnc.getName());
				sb.append(" (\n");

				sb.append("    ");
				sb.append(cnc.getName());
				sb.append(",");

				sb2.append("    #{");
				sb2.append(cnc.getCcName());
				sb2.append("},");
			} else if (i == size) {
				sb.append("    ");
				sb.append(cnc.getName());

				sb2.append("    #{");
				sb2.append(cnc.getCcName());
				sb2.append("}");
			} else {
				sb.append("    ");
				sb.append(cnc.getName());
				sb.append(",");

				sb2.append("    #{");
				sb2.append(cnc.getCcName());
				sb2.append("},");
			}

			sb.append("\n");
			sb2.append("\n");

			i++;
		}

		sb.append(") values (\n");

		sb.append(sb2);

		sb.append(");\n");

		sb.append("]]>\n");
		sb.append("</insert>\n");

		log.debug("sb={}", sb);
	}

	void update(List<ColumnVO> columns) {
		StringBuffer sb = new StringBuffer("\n");
		StringBuffer sb2 = new StringBuffer();

		int i = 1;
		int size = columns.size();

		NameCasing tnc = null;

		for (ColumnVO column : columns) {
			NameCasing cnc = new NameCasing(column.getColumnName());

			if (i == 1) {
				tnc = new NameCasing(column.getTableName());

				String updateId = "update" + tnc.getPcName();

				sb.append("<update");

				sb.append(" id=\"");
				sb.append(updateId);
				sb.append("\"");

				sb.append(" parameterType=\"");
				sb.append(tnc.getPcName());
				sb.append("VO\"");

				sb.append(">\n");
				sb.append("<![CDATA[\n");

				sb.append("/* ");
				sb.append(updateId);
				sb.append(" */\n");

				sb.append("update ");
				sb.append(tnc.getName());
				sb.append(" set\n");

				sb.append("    ");
				sb.append(cnc.getName());
				sb.append(" = #{");
				sb.append(cnc.getCcName());
				sb.append("},");

				sb2.append("    and ");
				sb2.append(cnc.getName());
				sb2.append(" = #{");
				sb2.append(cnc.getCcName());
				sb2.append("}");
			} else if (i == size) {
				sb.append("    ");
				sb.append(cnc.getName());
				sb.append(" = #{");
				sb.append(cnc.getCcName());
				sb.append("}");

				sb2.append("    and ");
				sb2.append(cnc.getName());
				sb2.append(" = #{");
				sb2.append(cnc.getCcName());
				sb2.append("}");
			} else {
				sb.append("    ");
				sb.append(cnc.getName());
				sb.append(" = #{");
				sb.append(cnc.getCcName());
				sb.append("},");

				sb2.append("    and ");
				sb2.append(cnc.getName());
				sb2.append(" = #{");
				sb2.append(cnc.getCcName());
				sb2.append("}");
			}

			sb.append("\n");
			sb2.append("\n");

			i++;
		}

		sb.append("where 1 = 1\n");

		sb.append(sb2);

		sb.append("]]>\n");
		sb.append("</update>\n");

		log.debug("sb={}", sb);
	}

	void delete(List<ColumnVO> columns) {
		StringBuffer sb = new StringBuffer("\n");

		int i = 1;
		int size = columns.size();

		NameCasing tnc = null;

		for (ColumnVO column : columns) {
			NameCasing cnc = new NameCasing(column.getColumnName());

			if (i == 1) {
				tnc = new NameCasing(column.getTableName());

				String deleteId = "delete" + tnc.getPcName();

				sb.append("<delete");

				sb.append(" id=\"");
				sb.append(deleteId);
				sb.append("\"");

				sb.append(" parameterType=\"");
				sb.append(tnc.getPcName());
				sb.append("VO\"");

				sb.append(">\n");
				sb.append("<![CDATA[\n");

				sb.append("/* ");
				sb.append(deleteId);
				sb.append(" */\n");

				sb.append("delete from ");
				sb.append(tnc.getName());
				sb.append(" where 1 = 1\n");

				sb.append("    and ");
				sb.append(cnc.getName());
				sb.append(" = #{");
				sb.append(cnc.getCcName());
				sb.append("}");
			} else if (i == size) {
				sb.append("    and ");
				sb.append(cnc.getName());
				sb.append(" = #{");
				sb.append(cnc.getCcName());
				sb.append("}");
			} else {
				sb.append("    and ");
				sb.append(cnc.getName());
				sb.append(" = #{");
				sb.append(cnc.getCcName());
				sb.append("}");
			}

			sb.append("\n");

			i++;
		}

		sb.append("]]>\n");
		sb.append("</delete>\n");

		log.debug("sb={}", sb);
	}

}
