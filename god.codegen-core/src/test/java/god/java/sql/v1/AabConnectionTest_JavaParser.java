package god.java.sql.v1;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

//@Slf4j
public class AabConnectionTest_JavaParser {

	@Test
	public void test() throws IOException {
		CompilationUnit cu = StaticJavaParser.parse(Paths.get(
				"C:\\EGOVFRAME-3.10.0\\eGovCI-3.10.0_64bit\\bin\\jdk8u242-b08\\src\\java\\sql", "Connection.java"));

//		log.debug("cu={}", cu);

		cu.getInterfaceByName("Connection").ifPresent(coid -> {
//			log.debug("coid={}", coid);

			StringBuffer sb = new StringBuffer();

			coid.getMethods().forEach(method -> {
//				log.debug("getNameAsString={}", method.getNameAsString());

				String methodName = method.getNameAsString();

				if (methodName.startsWith("get")) {
//					log.debug("methodName={}", methodName);

					sb.append("log.debug(\"");
					sb.append(methodName);
					sb.append("={}\", con.");
					sb.append(methodName);
					sb.append("());\n");
				}
			});

			System.out.println(sb);
		});
	}

}
