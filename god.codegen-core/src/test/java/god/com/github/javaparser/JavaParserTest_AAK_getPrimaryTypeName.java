package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAK_getPrimaryTypeName {

	@Test
	public void test() {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

//		log.debug("compilationUnit={}", compilationUnit);

		compilationUnit.getPrimaryTypeName()
				.ifPresent(primaryTypeName -> log.debug("primaryTypeName={}", primaryTypeName));

//		compilationUnit.getStorage().ifPresent(storage -> log.debug("storage={}", storage));

		compilationUnit.getStorage().ifPresent(storage -> log.debug("storage={}", storage.getFileName()));
	}

}