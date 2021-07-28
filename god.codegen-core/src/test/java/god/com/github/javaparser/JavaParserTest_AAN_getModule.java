package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAN_getModule {

	@Test
	public void test() {
		String first = "src/test/java/god/com/github/javaparser/package-info.java";

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		compilationUnit.getModule().ifPresent(module -> {
			log.debug("module={}", module);
		});
	}

}