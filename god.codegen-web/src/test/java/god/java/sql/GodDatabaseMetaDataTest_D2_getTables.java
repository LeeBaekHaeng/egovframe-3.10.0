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
public class GodDatabaseMetaDataTest_D2_getTables {

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

		List<TableVO> tables = gdmd.getTables(null, "COM", null, null);

		StringBuffer sb = new StringBuffer("\n");

		for (TableVO table : tables) {
			log.debug("table={}", table);
			log.debug("tableCat={}", table.getTableCat());
			log.debug("tableSchem={}", table.getTableSchem());
			log.debug("tableName={}", table.getTableName());
			log.debug("tableType={}", table.getTableType());
			log.debug("remarks={}", table.getRemarks());
			log.debug("");

			NameCasing tableNamePatternNameCasing = new NameCasing(table.getTableName());

			sb.append(tableNamePatternNameCasing.getPcName());
			sb.append("\n");
			sb.append(tableNamePatternNameCasing.getPcName());
			sb.append("VO\n");
			sb.append(tableNamePatternNameCasing.getPcName());
			sb.append("Dto\n");

			sb.append(tableNamePatternNameCasing.getPcName());
			sb.append("DAO\n");

			sb.append(tableNamePatternNameCasing.getPcName());
			sb.append("Service\n");

			sb.append(tableNamePatternNameCasing.getPcName());
			sb.append("ServiceImpl\n");

			sb.append(tableNamePatternNameCasing.getPcName());
			sb.append("Controller\n");

			sb.append(tableNamePatternNameCasing.getPcName());
			sb.append("RestController\n");

			sb.append("\n\n");
		}

		log.debug("sb={}", sb);
	}

}
