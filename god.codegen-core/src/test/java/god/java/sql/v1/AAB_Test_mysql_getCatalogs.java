package god.java.sql.v1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAB_Test_mysql_getCatalogs {

	@Test
	public void test() throws SQLException {
		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306?useInformationSchema=true", "root",
				"");
//		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/com?useInformationSchema=true",
//				"root", "");

//		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306?useInformationSchema=true", "com",
//				"com01");
//		Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/com?useInformationSchema=true", "com",
//				"com01");

		DatabaseMetaData dmd = con.getMetaData();

		ResultSet catalogs = dmd.getCatalogs();

		while (catalogs.next()) {
			String tableCat = catalogs.getString("TABLE_CAT");

			log.debug("tableCat={}", tableCat);
		}
	}

}
