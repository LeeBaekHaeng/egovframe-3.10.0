package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAB_getAllComments {

	@Test
	public void test() throws IOException {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit cu = StaticJavaParser.parse(Paths.get(first));

		cu.getAllComments().forEach(comment -> {
//			log.debug("comment={}", comment);

			log.debug("getContent={}", comment.getContent());

			log.debug("isLineComment={}", comment.isLineComment());

//			log.debug("getCommentedNode={}", comment.getCommentedNode());

			comment.getCommentedNode().ifPresent(commentedNode -> {
				log.debug("commentedNode={}", commentedNode);
			});

			comment.toBlockComment().ifPresent(consumer -> {
				log.debug("toBlockComment={}", consumer);
			});

			comment.toJavadocComment().ifPresent(consumer -> {
//				log.debug("toJavadocComment={}", consumer);
//				log.debug("toText={}", consumer.parse().toText());
				log.debug("getDescription={}", consumer.parse().getDescription().toText());
			});
		});
	}

}