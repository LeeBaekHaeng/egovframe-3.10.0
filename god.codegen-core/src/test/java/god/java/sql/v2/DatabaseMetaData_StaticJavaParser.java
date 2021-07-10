package god.java.sql.v2;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatabaseMetaData_StaticJavaParser {

	@Test
	public void test() {
		CompilationUnit cu = null;

		String interfaceName = "DatabaseMetaData";

		try {
			cu = StaticJavaParser
					.parse(Paths.get("C:\\EGOVFRAME-3.10.0\\eGovCI-3.10.0_64bit\\bin\\jdk8u242-b08\\src\\java\\sql",
							interfaceName + ".java"));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		StringBuffer sb = new StringBuffer();

		cu.getInterfaceByName(interfaceName).ifPresent(consumer -> {
			consumer.getMethods().forEach(action -> {
//				log.debug("action={}", action);
//				log.debug("getNameAsString={}", action.getNameAsString());

				String methodName = action.getNameAsString();

				sb.append("ABA_");
				sb.append(interfaceName);
				sb.append("_");
				sb.append(methodName);
				sb.append("_MySQL");
				sb.append("\n");
			});
		});

		System.out.println(sb);
	}

}
