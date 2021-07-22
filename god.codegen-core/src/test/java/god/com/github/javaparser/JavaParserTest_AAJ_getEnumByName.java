package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAJ_getEnumByName {

	@Test
	public void test() {
		String first = "src/main/java/egovframework/com/utl/wed/filter/DirectoryPathManager.java";

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

//		log.debug("compilationUnit={}", compilationUnit);

		compilationUnit.getTypes().forEach(type -> log.debug("type={}", type.getNameAsString()));

		compilationUnit.getEnumByName("DirectoryPathManager").ifPresent(enumDeclaration -> {
			log.debug("enumDeclaration={}", enumDeclaration);
		});

		compilationUnit.getEnumByName("DIR_DATE_TYPE").ifPresent(enumDeclaration -> {
			log.debug("enumDeclaration={}", enumDeclaration);
		});
	}

}