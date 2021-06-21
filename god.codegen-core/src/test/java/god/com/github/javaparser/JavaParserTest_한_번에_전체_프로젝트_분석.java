package god.com.github.javaparser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
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
//					log.debug("cu={}", cu);
					cu.getPrimaryTypeName().ifPresent(primaryTypeName -> {
						log.debug("primaryTypeName={}", primaryTypeName);

						StringBuffer sb = new StringBuffer();

						Optional<ClassOrInterfaceDeclaration> coidOptional = cu.getClassByName(primaryTypeName);
						coidOptional.ifPresent(coid -> {
							coid.getMethods().forEach(method -> {
//								log.debug("method={}", method);

//								log.debug("getNameAsString={}", method.getNameAsString());

								String methodName = method.getNameAsString();

								sb.append(primaryTypeName);
								sb.append("Test_");
								sb.append(methodName);
								sb.append("\n");

								method.getParameters().forEach(parameter -> {
									log.debug("parameter={}", parameter);
								});

								log.debug("getType={}", method.getType());

								method.getJavadoc().ifPresent(javadoc -> {
									log.debug("getDescription={}", javadoc.getDescription().toText());
								});
							});
						});

						try {
							FileUtils.writeStringToFile(
									new File(SystemUtils.USER_HOME + "/Desktop/test/" + primaryTypeName + ".txt"),
									sb.toString(), StandardCharsets.UTF_8);
						} catch (IOException e) {
							log.error(e.getMessage());
						}
					});
				});

				parseResult.getProblems().forEach(problem -> {
//					log.debug("problem={}", problem);
				});

				parseResult.getCommentsCollection().ifPresent(cc -> {
//					log.debug("cc={}", cc);
//					cc.getLineComments().forEach(comment -> {
//						log.debug("comment={}", comment);
//					});
//					cc.getBlockComments().forEach(comment -> {
//						log.debug("comment={}", comment);
//					});
//					cc.getJavadocComments().forEach(comment -> {
//						log.debug("comment={}", comment);
//					});
				});
			}

			log.debug("");
		}
	}

}
