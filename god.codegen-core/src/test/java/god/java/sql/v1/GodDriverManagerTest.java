package god.java.sql.v1;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GodDriverManagerTest {

	@Test
	public void test() {
		Enumeration<Driver> drivers = DriverManager.getDrivers();

		log.debug("drivers={}", drivers);

		StringBuffer sb = new StringBuffer("\n");

		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();

			log.debug("driver={}", driver);
			log.debug("driverClassName={}", driver.getClass().getName());
			log.debug("getMajorVersion={}", driver.getMajorVersion());
			log.debug("getMinorVersion={}", driver.getMinorVersion());

			sb.append(driver);
			sb.append("\n");
		}

		log.debug("sb={}", sb);
	}

}
