package god.java.sql;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.javadoc.Javadoc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class A0_GodCreateGetterAndCreateSetterTest_JavaParser {

	@Test
	public void test() throws FileNotFoundException {
		File file = new File(
				"C:\\EGOVFRAME-3.10.0\\git\\egovframe-3.10.0\\god.codegen-core\\src\\main\\java\\egovframework\\com\\cmm\\ComDefaultVO.java");

		CompilationUnit cu = StaticJavaParser.parse(file);

		Optional<ClassOrInterfaceDeclaration> ocoid = cu.getClassByName("ComDefaultVO");

		if (ocoid.isPresent() == false) {
			return;
		}

		ClassOrInterfaceDeclaration coid = ocoid.get();

		StringBuffer sb = new StringBuffer("\n");
		StringBuffer sb2 = new StringBuffer("\n");
		StringBuffer sb3 = new StringBuffer("\n");

		coid.getFields().forEach(field -> {
			sb.append("field\n");
			sb.append(field);
			sb.append("\n");

			sb.append("createGetter\n");
			sb.append(field.createGetter());
			sb.append("\n");

			sb.append("createSetter\n");
			sb.append(field.createSetter());
			sb.append("\n");

			VariableDeclarator variable = field.getVariable(0);
			String fieldName = variable.getNameAsString();
			String fieldNameUpper = fieldName.toUpperCase().substring(0, 1)
					+ fieldName.substring(1, fieldName.length());

			sb.append("getVariable.fieldName\n");
			sb.append(fieldName);
			sb.append("\n");

			String fieldType = variable.getTypeAsString();

			sb.append("getVariable.getTypeAsString\n");
			sb.append(fieldType);
			sb.append("\n");

			String comment = null;
			Optional<Javadoc> oJavadoc = field.getJavadoc();
			if (oJavadoc.isPresent()) {
				comment = field.getJavadoc().get().toText().trim();
			} else {
				comment = fieldName;
			}

			sb.append("getJavadocComment\n");
			sb.append(comment);
			sb.append("\n");

			sb2.append("vo.get");
			sb2.append(fieldNameUpper);
			sb2.append("(); // ");
			sb2.append(comment);
			sb2.append("\n");

			sb3.append("vo.set");
			sb3.append(fieldNameUpper);

			if ("int".equals(fieldType)) {
				sb3.append("(0); // ");
				sb3.append(comment);
				sb3.append("\n");
			} else {
				sb3.append("(\"");
				sb3.append(comment);
				sb3.append("\"); // ");
				sb3.append(comment);
				sb3.append("\n");
			}
		});

		log.debug("sb={}", sb);
		log.debug("sb2={}", sb2);
		log.debug("sb3={}", sb3);
	}

}
