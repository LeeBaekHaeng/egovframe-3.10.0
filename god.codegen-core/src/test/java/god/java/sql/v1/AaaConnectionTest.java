package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleConnection;

@Slf4j
public class AaaConnectionTest {

	@Test
	public void test() throws SQLException {
//		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "root", "");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.254:1521:orcl", "system", "orcl");

		if (con.isWrapperFor(OracleConnection.class)) {
			OracleConnection ocon = con.unwrap(OracleConnection.class);
			ocon.setRemarksReporting(true);
		}

		log.debug("con={}", con);
		log.debug("getCatalog={}", con.getCatalog());
		log.debug("getClass={}", con.getClass());
		log.debug("getClientInfo={}", con.getClientInfo());
		log.debug("getHoldability={}", con.getHoldability());
		log.debug("getMetaData={}", con.getMetaData());
		log.debug("getNetworkTimeout={}", con.getNetworkTimeout());
//		log.debug("getSchema={}", con.getSchema());
		log.debug("getTransactionIsolation={}", con.getTransactionIsolation());
		log.debug("getTypeMap={}", con.getTypeMap());
		log.debug("getWarnings={}", con.getWarnings());
		log.debug("");

		log.debug("getAutoCommit={}", con.getAutoCommit());
		log.debug("getMetaData={}", con.getMetaData());
		log.debug("getCatalog={}", con.getCatalog());
		log.debug("getTransactionIsolation={}", con.getTransactionIsolation());
		log.debug("getWarnings={}", con.getWarnings());
		log.debug("getTypeMap={}", con.getTypeMap());
		log.debug("getHoldability={}", con.getHoldability());
		log.debug("getClientInfo={}", con.getClientInfo());
		log.debug("getClientInfo={}", con.getClientInfo());
//		log.debug("getSchema={}", con.getSchema());
//		log.debug("getNetworkTimeout={}", con.getNetworkTimeout());
	}

}
