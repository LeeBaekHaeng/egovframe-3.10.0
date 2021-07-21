package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAI_getInterfaceByName {

	@Test
	public void test() {
		String first = "src/main/java/egovframework/com/cmm/service/EgovCmmUseService.java";

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		compilationUnit.getInterfaceByName("EgovCmmUseService").ifPresent(classOrInterfaceDeclaration -> {
			classOrInterfaceDeclaration.getMethods().forEach(method -> {
//				log.debug("method={}", method);

				log.debug("getTypeAsString={}", method.getTypeAsString());

				log.debug("getNameAsString={}", method.getNameAsString());

				method.getParameters().forEach(parameter -> {
					log.debug("parameter={}", parameter);
				});
			});
		});
	}

}