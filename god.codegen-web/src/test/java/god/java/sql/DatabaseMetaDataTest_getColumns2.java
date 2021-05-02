package god.java.sql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import god.test.GodTestV1;
import lombok.extern.slf4j.Slf4j;
import model.NameCasing;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseMetaDataTest_getColumns2.class })
//@ActiveProfiles({ "oracle", "dummy" })
@ActiveProfiles({ "mysql", "dummy" })

@Configuration

@ImportResource({

//		"classpath*:egovframework/spring/com/**/context-*.xml",

		"classpath*:/egovframework/spring/com/context-crypto.xml",
		"classpath*:/egovframework/spring/com/context-datasource.xml",

		"classpath*:egovframework/spring/com/test-context-common.xml",

})

@ComponentScan(useDefaultFilters = false, basePackages = { "god" }, includeFilters = {
		@Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {}) })

public class DatabaseMetaDataTest_getColumns2 extends GodTestV1 {

	@Autowired
	private DataSource dataSource;

	@Override
	@Test
	public void test() {
		log.debug("test\n");

		try {
			log.debug("dataSource={}\n", dataSource);

			Connection connection = dataSource.getConnection();
			log.debug("connection={}\n", connection);

			DatabaseMetaData dmd = connection.getMetaData();
			log.debug("dmd={}\n", dmd);

			ResultSet columns = getColumns(dmd);
			log.debug("columns={}\n", columns);

			ResultSetMetaData rsmd = columns.getMetaData();
			log.debug("rsmd={}\n", rsmd);

//			debug(rsmd);
//			debug2(rsmd);
//			debug3(rsmd);

			get(rsmd);

//			debug(columns);
//			debug(columns, rsmd);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	ResultSet getColumns(DatabaseMetaData dmd) {
		try {
			String catalog = "";
			String schemaPattern = "COM";
			String tableNamePattern = "COMTCADMINISTCODE";
			String columnNamePattern = "";
			return dmd.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	void debug(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);
				String columnClassName = rsmd.getColumnClassName(column);
				String columnClassName2 = columnClassName.substring(columnClassName.lastIndexOf(".") + 1);

				sb.append(column);
				sb.append(". ");
				sb.append(columnLabel);
				sb.append(" ");
				sb.append(columnClassName2);
				sb.append("\n");
			}

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	void debug2(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);
				String columnClassName = rsmd.getColumnClassName(column);
				String columnClassName2 = columnClassName.substring(columnClassName.lastIndexOf(".") + 1);

				sb.append(column);
				sb.append("\t");
				sb.append(columnLabel);
				sb.append("\t");
				sb.append(columnClassName2);
				sb.append("\n");
			}

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	void debug3(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);

				NameCasing nameCasing = new NameCasing(columnLabel);

				sb.append("<result property=\"" + nameCasing.getCcName() + "\" column=\"" + columnLabel + "\" />");
				sb.append("\n");
			}

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	void get(ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			for (int column = 1; column < columnCount; column++) {
				String columnLabel = rsmd.getColumnLabel(column);

				NameCasing nameCasing = new NameCasing(columnLabel);

				sb.append("log.debug(\"" + nameCasing.getCcName() + "={}\", allTabCol.get" + nameCasing.getPcName()
						+ "());");
				sb.append("\n");
			}

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	void debug(ResultSet columns) {
		try {
			while (columns.next()) {
//				log.debug("getString DATA_TYPE={}", columns.getString("DATA_TYPE"));
//				log.debug("getInt DATA_TYPE={}", columns.getInt("DATA_TYPE"));
//				log.debug("getTypeName COLUMN_NAME={}", columns.getObject("COLUMN_NAME").getClass().getTypeName());
//				log.debug("getTypeName DATA_TYPE={}", columns.getObject("DATA_TYPE").getClass().getTypeName());

				Object SOURCE_DATA_TYPE = columns.getObject("SOURCE_DATA_TYPE");
				log.debug("SOURCE_DATA_TYPE={}", SOURCE_DATA_TYPE);
				if (SOURCE_DATA_TYPE != null) {
					log.debug("getTypeName SOURCE_DATA_TYPE={}", SOURCE_DATA_TYPE.getClass().getTypeName());
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	void debug(ResultSet columns, ResultSetMetaData rsmd) {
		try {
			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			StringBuffer sb = new StringBuffer("\n");

			while (columns.next()) {
				for (int column = 1; column < columnCount; column++) {
					String columnLabel = rsmd.getColumnLabel(column);

					String s = columns.getString(columnLabel);

					log.debug("{}={}", columnLabel, s);

					sb.append(columnLabel);
					sb.append("\t");
					sb.append(s);
					sb.append("\n");
				}

				log.debug("");

				sb.append("\n");
			}

			log.debug("{}", sb);
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}
