package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAD_getImport {

	@Test
	public void test() {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		int size = compilationUnit.getImports().size();

		log.debug("size={}", size);

		for (int i = 0; i < size; i++) {
			ImportDeclaration importDeclaration = compilationUnit.getImport(i);

//			log.debug("importDeclaration={}", importDeclaration);
			log.debug("importDeclaration.getName().asString()={}", importDeclaration.getName().asString());
//			log.debug("importDeclaration.getNameAsString()={}", importDeclaration.getNameAsString());
		}
	}

}