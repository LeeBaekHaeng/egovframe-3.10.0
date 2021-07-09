package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAC_getImports {

	@Test
	public void test() {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit cu = null;

		try {
			cu = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		cu.getImports().forEach(action -> {
//			log.debug("action={}", action);
//			log.debug("getName={}", action.getName());
//			log.debug("getName.getIdentifier={}", action.getName().getIdentifier());
			log.debug("getName={}", action.getName().asString());
		});
	}

}