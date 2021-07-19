package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAG_getClassByName {

	@Test
	public void test() {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(Paths.get(first));
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		compilationUnit.getClassByName("ComDefaultCodeVO").ifPresent(classOrInterfaceDeclaration -> {
//			log.debug("classOrInterfaceDeclaration={}", classOrInterfaceDeclaration);
			classOrInterfaceDeclaration.getExtendedTypes().forEach(action -> log.debug("action={}", action));
			classOrInterfaceDeclaration.getImplementedTypes().forEach(action -> log.debug("action={}", action));
			classOrInterfaceDeclaration.getTypeParameters().forEach(action -> log.debug("action={}", action));
			log.debug("isInterface={}", classOrInterfaceDeclaration.isInterface());
			log.debug("isLocalClassDeclaration={}", classOrInterfaceDeclaration.isLocalClassDeclaration());

			log.debug("getFullyQualifiedName={}", classOrInterfaceDeclaration.getFullyQualifiedName());
			classOrInterfaceDeclaration.getFullyQualifiedName()
					.ifPresent(consumer -> log.debug("getFullyQualifiedName={}", consumer));

			log.debug("isInnerClass={}", classOrInterfaceDeclaration.isInnerClass());

			log.debug("getMetaModel={}", classOrInterfaceDeclaration.getMetaModel());
			log.debug("getMetaModelFieldName={}", classOrInterfaceDeclaration.getMetaModel().getMetaModelFieldName());
			log.debug("getPackageName={}", classOrInterfaceDeclaration.getMetaModel().getPackageName());
			log.debug("getQualifiedClassName={}", classOrInterfaceDeclaration.getMetaModel().getQualifiedClassName());
			log.debug("getTypeName={}", classOrInterfaceDeclaration.getMetaModel().getTypeName());
			log.debug("getTypeNameGenerified={}", classOrInterfaceDeclaration.getMetaModel().getTypeNameGenerified());

			log.debug("isClassOrInterfaceDeclaration={}", classOrInterfaceDeclaration.isClassOrInterfaceDeclaration());
//			log.debug("asClassOrInterfaceDeclaration={}", classOrInterfaceDeclaration.asClassOrInterfaceDeclaration());
		});
	}

}