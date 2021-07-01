package god.com.github.javaparser;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;

import egovframework.com.cmm.ComDefaultCodeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaParserTest_AAA_VO {

	@Test
	public void test() throws IOException {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit cu = StaticJavaParser.parse(Paths.get(first));

		StringBuffer sb = new StringBuffer();

		cu.getPrimaryTypeName().ifPresent(className -> {
			cu.getClassByName(className).ifPresent(coid -> {

				coid.getFields().forEach(field -> {
					VariableDeclarator variable = field.getVariable(0);
					String fieldType = variable.getTypeAsString();
					String fieldName = variable.getNameAsString();

					sb.append(fieldType);
					sb.append(" ");
					sb.append(fieldName);
					sb.append(" = ");
					if ("byte".equals(fieldType)) {
						sb.append("0");
					} else if ("short".equals(fieldType)) {
						sb.append("0");
					} else if ("int".equals(fieldType)) {
						sb.append("0");
					} else if ("long".equals(fieldType)) {
						sb.append("0L");
					} else if ("float".equals(fieldType)) {
						sb.append("0.0f");
					} else if ("double".equals(fieldType)) {
						sb.append("0.0d");
					} else if ("char".equals(fieldType)) {
						sb.append("'\u0000'");
					} else if ("String".equals(fieldType)) {
//						sb.append("null");
						sb.append("\"\"");
					} else if ("boolean".equals(fieldType)) {
						sb.append("false");
					} else {
						sb.append("\"\"");
					}

					sb.append(";");

					field.getJavadoc().ifPresent(consumer -> {
						sb.append(" // ");
						sb.append(consumer.getDescription().toText());
					});

					sb.append("\n");
				});

			});
		});

		System.out.println(sb);
	}

	@Test
	public void test3() throws IOException {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit cu = StaticJavaParser.parse(Paths.get(first));

		StringBuffer sb = new StringBuffer();

		cu.getPrimaryTypeName().ifPresent(className -> {
			cu.getClassByName(className).ifPresent(coid -> {

				coid.getFields().forEach(field -> {
					VariableDeclarator variable = field.getVariable(0);
					String fieldType = variable.getTypeAsString();
					String fieldName = variable.getNameAsString();

					sb.append(fieldType);
					sb.append(" ");
					sb.append(fieldName);
					sb.append(" = ");
					if ("byte".equals(fieldType)) {
						sb.append("0");
					} else if ("short".equals(fieldType)) {
						sb.append("0");
					} else if ("int".equals(fieldType)) {
						sb.append("0");
					} else if ("long".equals(fieldType)) {
						sb.append("0L");
					} else if ("float".equals(fieldType)) {
						sb.append("0.0f");
					} else if ("double".equals(fieldType)) {
						sb.append("0.0d");
					} else if ("char".equals(fieldType)) {
						sb.append("'\u0000'");
					} else if ("String".equals(fieldType)) {
//						sb.append("null");
//						sb.append("\"\"");
						if (field.getJavadoc().isPresent()) {
							sb.append("\"");
							sb.append(field.getJavadoc().get().getDescription().toText());
							sb.append("\"");

						} else {
							sb.append("\"\"");
						}
					} else if ("boolean".equals(fieldType)) {
						sb.append("false");
					} else {
						sb.append("\"\"");
					}

					sb.append(";");

					field.getJavadoc().ifPresent(consumer -> {
						sb.append(" // ");
						sb.append(consumer.getDescription().toText());
					});

					sb.append("\n");
				});

			});
		});

		System.out.println(sb);
	}

	@Test
	public void test2() throws IOException {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit cu = StaticJavaParser.parse(Paths.get(first));

		StringBuffer sb = new StringBuffer();

		cu.getPrimaryTypeName().ifPresent(className -> {

			sb.append(className);
			sb.append(" vo = new ");
			sb.append(className);
			sb.append("();");
			sb.append("\n");

			cu.getClassByName(className).ifPresent(coid -> {

				coid.getFields().forEach(field -> {
					VariableDeclarator variable = field.getVariable(0);
					String fieldName = variable.getNameAsString();
					String fieldNameUpper = fieldName.toUpperCase().substring(0, 1)
							+ fieldName.substring(1, fieldName.length());

					sb.append("vo.set");
					sb.append(fieldNameUpper);
					sb.append("(");
					sb.append(fieldName);
					sb.append(");");

					field.getJavadoc().ifPresent(consumer -> {
						sb.append(" // ");
						sb.append(consumer.getDescription().toText());
					});

					sb.append("\n");
				});

			});
		});

		System.out.println(sb);
	}

	@Test
	public void test4() throws IOException {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit cu = StaticJavaParser.parse(Paths.get(first));

		StringBuffer sb = new StringBuffer();

		cu.getPrimaryTypeName().ifPresent(className -> {

			cu.getClassByName(className).ifPresent(coid -> {

				coid.getFields().forEach(field -> {
					VariableDeclarator variable = field.getVariable(0);
					String fieldName = variable.getNameAsString();

					sb.append("log.debug(\"");
					sb.append(fieldName);
					sb.append("={}\", ");
					sb.append(fieldName);
					sb.append(");");

					sb.append("\n");
				});

			});
		});

		System.out.println(sb);
	}

	@Test
	public void test5() throws IOException {
		String first = "src/main/java/egovframework/com/cmm/ComDefaultCodeVO.java";

		CompilationUnit cu = StaticJavaParser.parse(Paths.get(first));

		StringBuffer sb = new StringBuffer();

		cu.getPrimaryTypeName().ifPresent(className -> {

			cu.getClassByName(className).ifPresent(coid -> {

				sb.append("log.debug(\"");
				sb.append(className);
				sb.append("={}\", vo);");
				sb.append("\n");

				coid.getFields().forEach(field -> {
					VariableDeclarator variable = field.getVariable(0);
					String fieldName = variable.getNameAsString();
					String fieldNameUpper = fieldName.toUpperCase().substring(0, 1)
							+ fieldName.substring(1, fieldName.length());

					sb.append("log.debug(\"");
					sb.append(fieldName);
					sb.append("={}\", vo.get");
					sb.append(fieldNameUpper);
					sb.append("());");

					sb.append("\n");
				});

			});
		});

		System.out.println(sb);
	}

	@Test
	public void testa() {
		String codeId = ""; // 코드 ID
		String code = ""; // 상세코드
		String codeNm = ""; // 코드명
		String codeDc = ""; // 코드설명
		String tableNm = "";
		String haveDetailCondition = ""; // 상세 조건 여부
		String detailCondition = ""; // 상세 조건

		log.debug("codeId={}", codeId);
		log.debug("code={}", code);
		log.debug("codeNm={}", codeNm);
		log.debug("codeDc={}", codeDc);
		log.debug("tableNm={}", tableNm);
		log.debug("haveDetailCondition={}", haveDetailCondition);
		log.debug("detailCondition={}", detailCondition);

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId(codeId); // 코드 ID
		vo.setCode(code); // 상세코드
		vo.setCodeNm(codeNm); // 코드명
		vo.setCodeDc(codeDc); // 코드설명
		vo.setTableNm(tableNm);
		vo.setHaveDetailCondition(haveDetailCondition); // 상세 조건 여부
		vo.setDetailCondition(detailCondition); // 상세 조건

		debug(vo);
	}

	@Test
	public void testb() {
		String codeId = "코드 ID"; // 코드 ID
		String code = "상세코드"; // 상세코드
		String codeNm = "코드명"; // 코드명
		String codeDc = "코드설명"; // 코드설명
		String tableNm = "";
		String haveDetailCondition = "상세 조건 여부"; // 상세 조건 여부
		String detailCondition = "상세 조건"; // 상세 조건

		log.debug("codeId={}", codeId);
		log.debug("code={}", code);
		log.debug("codeNm={}", codeNm);
		log.debug("codeDc={}", codeDc);
		log.debug("tableNm={}", tableNm);
		log.debug("haveDetailCondition={}", haveDetailCondition);
		log.debug("detailCondition={}", detailCondition);

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId(codeId); // 코드 ID
		vo.setCode(code); // 상세코드
		vo.setCodeNm(codeNm); // 코드명
		vo.setCodeDc(codeDc); // 코드설명
		vo.setTableNm(tableNm);
		vo.setHaveDetailCondition(haveDetailCondition); // 상세 조건 여부
		vo.setDetailCondition(detailCondition); // 상세 조건

		debug(vo);
	}

	private void debug(ComDefaultCodeVO vo) {
		log.debug("ComDefaultCodeVO={}", vo);
		log.debug("codeId={}", vo.getCodeId());
		log.debug("code={}", vo.getCode());
		log.debug("codeNm={}", vo.getCodeNm());
		log.debug("codeDc={}", vo.getCodeDc());
		log.debug("tableNm={}", vo.getTableNm());
		log.debug("haveDetailCondition={}", vo.getHaveDetailCondition());
		log.debug("detailCondition={}", vo.getDetailCondition());
	}

}
