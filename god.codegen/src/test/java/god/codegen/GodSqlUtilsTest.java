package god.codegen;

import java.time.LocalDate;

public class GodSqlUtilsTest {

	public static String getValue(String columnName, String typeName) {
		StringBuffer sb = new StringBuffer();
		if ("DATETIME".equals(typeName)) {
			sb.append("SYSDATE()");
		} else if ("DECIMAL".equals(typeName)) {
			sb.append("0");
		} else {
			// VARCHAR, CHAR
			if (columnName.endsWith("USE_AT")) {
				sb.append("'Y'");
			} else if (columnName.endsWith("FRST_REGISTER_ID") || columnName.endsWith("LAST_UPDUSR_ID")) {
//				sb.append("'2021'");
				sb.append("'");
				sb.append(LocalDate.now().getYear());
				sb.append("'");
			} else {
				sb.append("''");
			}
		}
		return sb.toString();
	}

}
