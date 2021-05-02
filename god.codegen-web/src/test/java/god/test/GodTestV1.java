package god.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GodTestV1 {

	private static final StopWatch STOP_WATCH = new StopWatch();

	@Autowired
	private ApplicationContext context;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		log.info("setUpBeforeClass\n");

		log.info("start\n");
		STOP_WATCH.start();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		log.info("tearDownAfterClass\n");

		log.info("stop\n");
		STOP_WATCH.stop();

		log.info("getTotalTimeMillis={}\n", STOP_WATCH.getTotalTimeMillis());
		log.info("getTotalTimeSeconds={}\n", STOP_WATCH.getTotalTimeSeconds());
	}

	@Before
	public void setUp() throws Exception {
		log.info("setUp\n");

		String[] beanDefinitionNames = context.getBeanDefinitionNames();

		log.debug("beanDefinitionNames.length={}\n", beanDefinitionNames.length);

		for (String beanDefinitionName : beanDefinitionNames) {
			log.debug("beanDefinitionName={}", beanDefinitionName);
		}

		log.debug("\n");
	}

	@After
	public void tearDown() throws Exception {
		log.info("tearDown\n");
	}

	@Test
	public void test() {
		log.debug("test\n");

		try {

		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

}
