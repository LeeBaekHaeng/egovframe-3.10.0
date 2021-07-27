package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAM_getAnnotationDeclarationByName {

	@Test
	public void test() {
		String first = "src/main/java/egovframework/com/cmm/service/impl/CmmUseDAO.java";

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		compilationUnit.getAnnotationDeclarationByName("CmmUseDAO").ifPresent(annotationDeclaration -> {
			log.debug("annotationDeclaration={}", annotationDeclaration);
		});

		compilationUnit.getTypes().forEach(type -> {
//			log.debug("type={}", type);
			log.debug("type.getNameAsString={}", type.getNameAsString());
		});
	}

}