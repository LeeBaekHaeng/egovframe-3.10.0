package god.java.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultSetTest {

	public static void debug(ResultSet rs) {
		try {
			ResultSetMetaData rsmd = rs.getMetaData();

			int columnCount = rsmd.getColumnCount();

			log.debug("columnCount={}\n", columnCount);

			while (rs.next()) {
				for (int column = 1; column < columnCount; column++) {
					String columnLabel = rsmd.getColumnLabel(column);
//					String columnClassName = rsmd.getColumnClassName(column);

					log.debug("{}/{} {}={}", column, columnCount, columnLabel, rs.getString(columnLabel));
				}
				log.debug("");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

}
