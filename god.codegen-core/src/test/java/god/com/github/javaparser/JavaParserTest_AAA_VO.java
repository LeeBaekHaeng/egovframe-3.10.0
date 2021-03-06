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
	public void test6() throws IOException {
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
					sb.append(", ");
				});

			});
		});

		System.out.println(sb);
	}

	@Test
	public void testa() {
		String codeId = ""; // ?????? ID
		String code = ""; // ????????????
		String codeNm = ""; // ?????????
		String codeDc = ""; // ????????????
		String tableNm = "";
		String haveDetailCondition = ""; // ?????? ?????? ??????
		String detailCondition = ""; // ?????? ??????

		debug(codeId, code, codeNm, codeDc, tableNm, haveDetailCondition, detailCondition);

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId(codeId); // ?????? ID
		vo.setCode(code); // ????????????
		vo.setCodeNm(codeNm); // ?????????
		vo.setCodeDc(codeDc); // ????????????
		vo.setTableNm(tableNm);
		vo.setHaveDetailCondition(haveDetailCondition); // ?????? ?????? ??????
		vo.setDetailCondition(detailCondition); // ?????? ??????

		debug(vo);
	}

	@Test
	public void testb() {
		String codeId = "?????? ID"; // ?????? ID
		String code = "????????????"; // ????????????
		String codeNm = "?????????"; // ?????????
		String codeDc = "????????????"; // ????????????
		String tableNm = "";
		String haveDetailCondition = "?????? ?????? ??????"; // ?????? ?????? ??????
		String detailCondition = "?????? ??????"; // ?????? ??????

		debug(codeId, code, codeNm, codeDc, tableNm, haveDetailCondition, detailCondition);

		ComDefaultCodeVO vo = new ComDefaultCodeVO();
		vo.setCodeId(codeId); // ?????? ID
		vo.setCode(code); // ????????????
		vo.setCodeNm(codeNm); // ?????????
		vo.setCodeDc(codeDc); // ????????????
		vo.setTableNm(tableNm);
		vo.setHaveDetailCondition(haveDetailCondition); // ?????? ?????? ??????
		vo.setDetailCondition(detailCondition); // ?????? ??????

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

	private void debug(String codeId, String code, String codeNm, String codeDc, String tableNm,
			String haveDetailCondition, String detailCondition) {
		log.debug("codeId={}", codeId);
		log.debug("code={}", code);
		log.debug("codeNm={}", codeNm);
		log.debug("codeDc={}", codeDc);
		log.debug("tableNm={}", tableNm);
		log.debug("haveDetailCondition={}", haveDetailCondition);
		log.debug("detailCondition={}", detailCondition);
	}

}
