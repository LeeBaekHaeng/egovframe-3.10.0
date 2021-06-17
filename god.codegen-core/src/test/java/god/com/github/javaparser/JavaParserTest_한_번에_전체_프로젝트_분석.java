package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_한_번에_전체_프로젝트_분석 {

	@Test
	public void test() throws IOException {
		Path projectRoot = Paths.get("C:\\EGOVFRAME-3.10.0\\git\\egovframe-3.10.0");
		String[] roots = new String[] {

				"god.codegen-core/src/main/java",

//				"god.codegen-web/src/main/java",

		};
		for (String root : roots) {
			SourceRoot sourceRoot = new SourceRoot(projectRoot.resolve(root));
			List<ParseResult<CompilationUnit>> parseResults = sourceRoot.tryToParse();

//			log.debug("parseResults={}", parseResults);
			log.debug("size={}", parseResults.size());

			for (ParseResult<CompilationUnit> parseResult : parseResults) {
//				log.debug("parseResult={}", parseResult);

				parseResult.getResult().ifPresent(cu -> {
					log.debug("cu={}", cu);
				});

				parseResult.getProblems().forEach(problem -> {
					log.debug("problem={}", problem);
				});

				parseResult.getCommentsCollection().ifPresent(cc -> {
//					log.debug("cc={}", cc);
//					cc.getLineComments().forEach(comment -> {
//						log.debug("comment={}", comment);
//					});
//					cc.getBlockComments().forEach(comment -> {
//						log.debug("comment={}", comment);
//					});
					cc.getJavadocComments().forEach(comment -> {
						log.debug("comment={}", comment);
					});
					;
				});
			}

			log.debug("");
		}
	}

}
