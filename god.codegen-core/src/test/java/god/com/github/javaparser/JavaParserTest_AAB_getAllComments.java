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

//			log.debug("getContent={}", comment.getContent());

//			log.debug("isLineComment={}", comment.isLineComment());

//			log.debug("getCommentedNode={}", comment.getCommentedNode());

//			comment.asLineComment();

//			comment.getCommentedNode().ifPresent(commentedNode -> {
//				log.debug("commentedNode={}", commentedNode);
//			});

//			log.debug("isOrphan={}", comment.isOrphan());

//			log.debug("getMetaModel={}", comment.getMetaModel());

//			log.debug("isBlockComment={}", comment.isBlockComment());

//			log.debug("asBlockComment={}", comment.asBlockComment());

//			boolean isJavadocComment = comment.isJavadocComment();
////			log.debug("isJavadocComment={}", isJavadocComment);
//			if (isJavadocComment) {
////				log.debug("asJavadocComment={}", comment.asJavadocComment());
//				Javadoc parse = comment.asJavadocComment().parse();
////				log.debug("parse={}", parse);
////				log.debug("toText={}", parse.toText());
//				log.debug("getDescription={}", parse.getDescription().toText());
//			}

//			comment.toBlockComment().ifPresent(consumer -> {
//				log.debug("toBlockComment={}", consumer);
//			});

			comment.toJavadocComment().ifPresent(consumer -> {
//				log.debug("toJavadocComment={}", consumer);
//				log.debug("toText={}", consumer.parse().toText());
				log.debug("getDescription={}", consumer.parse().getDescription().toText());
			});

//			comment.toLineComment().ifPresent(consumer -> {
//				log.debug("toLineComment={}", consumer);
//			});
		});
	}

}