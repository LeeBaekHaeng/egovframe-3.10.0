package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAF_getTypes {

	@Test
	public void test() {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		compilationUnit.getTypes().forEach(type -> {
//			log.debug("type={}", type);
			type.getMembers().forEach(member -> {
//				log.debug("member={}", member);
				member.getAnnotations().forEach(annotation -> {
					log.debug("annotation={}", annotation);
				});

//				log.debug("member.getMetaModel().getQualifiedClassName()={}",
//						member.getMetaModel().getQualifiedClassName());
//				log.debug("member.getMetaModel().getPackageName()={}", member.getMetaModel().getPackageName());

				log.debug("member.isAnnotationDeclaration()={}", member.isAnnotationDeclaration());
			});
		});
	}

}