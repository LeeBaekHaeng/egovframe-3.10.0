package god.java.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;

@Slf4j
public class GodDatabaseMetaDataTest_B3_getColumns {

	private static Connection con;
	private static java.sql.DatabaseMetaData dmd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		con = GodDriverManagerTest.getConnection();
		con = GodDriverManagerTest.getConnectionMariadb();

		log.debug("con={}", con);
		log.debug("");

		if (con.isWrapperFor(OracleConnection.class)) {
			OracleConnection ocon = con.unwrap(OracleConnection.class);
			ocon.setRemarksReporting(true);
		}

		dmd = con.getMetaData();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if (con != null) {
			con.close();
		}
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SQLException {
		ResultSet rs = null;

		try {
			rs = dmd.getColumns(null, "COM", "COMTCADMINISTCODE", null);
			log.debug("rs={}", rs);

			ResultSetMetaData rsmd = rs.getMetaData();
			log.debug("rsmd={}", rsmd);

			int columnCount = rsmd.getColumnCount();
			log.debug("columnCount={}", columnCount);

			testA1(rsmd, columnCount);
//			testA2(rsmd, columnCount);
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}

	void testA1(ResultSetMetaData rsmd, int columnCount) throws SQLException {
		for (int column = 1; column <= columnCount; column++) {
			log.debug("column={}", column);
			log.debug("getColumnName={}", rsmd.getColumnName(column));
			log.debug("getColumnClassName={}", rsmd.getColumnClassName(column));
			log.debug("");
		}
	}

}
