package god.java.sql.v1;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class GodDriverManagerTest_JavaParser {

	@Test
	public void test() throws IOException {
		CompilationUnit cu = StaticJavaParser.parse(Paths.get(
				"C:\\EGOVFRAME-3.10.0\\eGovCI-3.10.0_64bit\\bin\\jdk8u242-b08\\src\\java\\sql", "DriverManager.java"));

		StringBuffer sb = new StringBuffer();

//		cu.getPrimaryTypeName().ifPresent(consumer -> {
//			sb.append(consumer);
//			sb.append("\n");
//		});

		cu.getClassByName("DriverManager").ifPresent(coid -> {
			coid.getMethods().forEach(method -> {
				String methodName = method.getNameAsString();

				if (method.isPublic()) {
					sb.append(methodName);
					sb.append("\n");

					method.getParameters().forEach(action -> {
						sb.append(action);
						sb.append("\n");
					});

					sb.append(method.getTypeAsString());
					sb.append("\n");

					sb.append("\n");
				}
			});
		});

		System.out.println(sb);
	}

}
