package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

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

//		aaa(con);
//		aab(con);
		aac(con);
	}

	void aaa(Connection con) throws SQLException {
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
	}

	void aab(Connection con) throws SQLException {
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

	void aac(Connection con) throws SQLException {
		boolean autoCommit = con.getAutoCommit();
		boolean isClosed = con.isClosed();
		DatabaseMetaData metaData = con.getMetaData();
		boolean isReadOnly = con.isReadOnly();
		String catalog = con.getCatalog();
		int transactionIsolation = con.getTransactionIsolation();
		SQLWarning warnings = con.getWarnings();
		java.util.Map<String, Class<?>> typeMap = con.getTypeMap();
		int holdability = con.getHoldability();
//		boolean isValid = con.isValid();
//		String clientInfo = con.getClientInfo();
//		Properties clientInfo = con.getClientInfo();
//		String schema = con.getSchema();
//		int networkTimeout = con.getNetworkTimeout();

		log.debug("autoCommit={}", autoCommit);
		log.debug("isClosed={}", isClosed);
		log.debug("metaData={}", metaData);
		log.debug("isReadOnly={}", isReadOnly);
		log.debug("catalog={}", catalog);
		log.debug("transactionIsolation={}", transactionIsolation);
		log.debug("warnings={}", warnings);
		log.debug("typeMap={}", typeMap);
		log.debug("holdability={}", holdability);
//		log.debug("isValid={}", isValid);
//		log.debug("clientInfo={}", clientInfo);
//		log.debug("clientInfo={}", clientInfo);
//		log.debug("schema={}", schema);
//		log.debug("networkTimeout={}", networkTimeout);
	}

}
