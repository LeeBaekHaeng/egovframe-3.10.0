package god.com.github.javaparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.CompilationUnit.Storage;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.utils.CodeGenerationUtils;
import com.github.javaparser.utils.SourceRoot;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest {

//	@Test
	public void test() {
		log.debug("test");

		File file = new File(
				"C:\\EGOVFRAME-3.10.0\\git\\egovframe-3.10.0\\god.codegen-core\\src\\main\\java\\egovframework\\com\\cmm\\service\\impl\\CmmUseDAO.java");

		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = StaticJavaParser.parse(file);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());
		}

		Optional<ClassOrInterfaceDeclaration> classCmmUseDAO = compilationUnit.getClassByName("CmmUseDAO");

		classCmmUseDAO.get().getMethods().forEach(method -> {
			log.debug("method={}", method);

			log.debug("getNameAsString={}", method.getNameAsString());

			method.getParameters().forEach(parameter -> {
				log.debug("parameter={}", parameter);
			});

			log.debug("getType={}", method.getType());

			log.debug("getDescription={}", method.getJavadoc().get().getDescription().toText());
		});

//		log.debug("getMetaModel={}", compilationUnit.getMetaModel());
//		log.debug("getMetaModelFieldName={}", compilationUnit.getMetaModel().getMetaModelFieldName());

		log.debug("getStorage={}", compilationUnit.getStorage().get());
		log.debug("getDirectory={}", compilationUnit.getStorage().get().getDirectory());
		log.debug("getFileName={}", compilationUnit.getStorage().get().getFileName());
		log.debug("getPath={}", compilationUnit.getStorage().get().getPath());
		log.debug("getSourceRoot={}", compilationUnit.getStorage().get().getSourceRoot());
	}

	@Test
	public void test2() throws Exception {
		log.debug("test2");

		SourceRoot sourceRoot = new SourceRoot(
				CodeGenerationUtils.mavenModuleRoot(JavaParserTest.class).resolve("src/main/java"));

		sourceRoot.tryToParse().forEach(action -> {
//			log.debug("action={}", action);
//			log.debug("action={}", action.getResult());
//			log.debug("action={}", action.getResult().get());
//			log.debug("action={}", action.getResult().get().getPackageDeclaration().get().getNameAsString());

			CompilationUnit cu = action.getResult().get();
			Storage storage = cu.getStorage().get();
			log.debug("getDirectory={}", storage.getDirectory());
			log.debug("getFileName={}", storage.getFileName());

			try {
				Optional<ClassOrInterfaceDeclaration> classOrInterfaceDeclaration = cu
						.getClassByName(storage.getFileName().replaceAll(".java", ""));

				if (classOrInterfaceDeclaration.isPresent()) {

					classOrInterfaceDeclaration.get().getMethods().forEach(method -> {
						log.debug("method={}", method);

						log.debug("getNameAsString={}", method.getNameAsString());

						method.getParameters().forEach(parameter -> {
							log.debug("parameter={}", parameter);
						});

						log.debug("getType={}", method.getType());

						log.debug("getDescription={}", method.getJavadoc().get().getDescription().toText());
					});
				}

			} catch (Exception e) {
				System.err.println("god");
				System.err.println(e.getMessage());
				System.err.println("god2");
			}

		});
	}

}
