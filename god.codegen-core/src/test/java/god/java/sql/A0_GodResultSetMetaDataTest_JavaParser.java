package god.java.sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A0_GodResultSetMetaDataTest_JavaParser {

	@Test
	public void test() throws FileNotFoundException {
		File file = new File(
				"C:\\EGOVFRAME-3.10.0\\eGovCI-3.10.0_64bit\\bin\\jdk8u242-b08\\src\\java\\sql\\ResultSetMetaData.java");

		CompilationUnit cu = StaticJavaParser.parse(file);

		Optional<ClassOrInterfaceDeclaration> classResultSetMetaData = cu.getInterfaceByName("ResultSetMetaData");

		if (classResultSetMetaData.isPresent() == false) {
			return;
		}

		StringBuffer sb = new StringBuffer("\n");

		classResultSetMetaData.get().getMethods().forEach(method -> {
//			log.debug("method={}", method);

//			log.debug("getNameAsString={}", method.getNameAsString());

//			method.getParameters().forEach(parameter -> {
//				log.debug("parameter={}", parameter);
//			});
//
//			log.debug("getType={}", method.getType());
//
//			log.debug("getDescription={}", method.getJavadoc().get().getDescription().toText());

			sb.append("- ");
			sb.append(method.getNameAsString());
			sb.append("\n");
		});

		log.debug("sb={}", sb);
	}

}
