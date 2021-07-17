package god.codegen.test.aaa.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TestAAAController {

	private final RequestMappingHandlerMapping handlerMapping;

//	@GetMapping(value = { "/test/aaa/insertTestAAA.do", "/test/aaa/insertTestAAA.god" })
	@RequestMapping(value = { "/test/aaa/insertTestAAA.do", "/test/aaa/insertTestAAA.god", }, method = {
			RequestMethod.GET, RequestMethod.POST, })
	public String insertTestAAA(@RequestParam Map<String, Object> params, Model model) {
		model.addAttribute("params", params);
		model.addAttribute("god", "갓");
//		debug();
		debug2();
		return "god/codegen/test/aaa/insertTestAAA";
	}

	void debug() {
		if (!log.isDebugEnabled()) {
			return;
		}

		handlerMapping.getHandlerMethods().forEach((key, value) -> {
			log.debug("key={}", key);

			log.debug("getPatternsCondition={}", key.getPatternsCondition());
			key.getPatternsCondition().getPatterns().forEach(pattern -> log.debug("pattern={}", pattern));

			log.debug("getMethodsCondition={}", key.getMethodsCondition());
			key.getMethodsCondition().getMethods().forEach(method -> {
				log.debug("methodName={}", method.name());
			});

			log.debug("");

			log.debug("value={}", value);
			log.debug("getBean={}", value.getBean());
			log.debug("getMethod={}", value.getMethod());
			log.debug("getMethod.getName={}", value.getMethod().getName());
			log.debug("getBeanType={}", value.getBeanType());
			log.debug("getBeanType.getName={}", value.getBeanType().getName());
			log.debug("getMethodParameters={}", value.getMethodParameters().toString());
			log.debug("getReturnType={}", value.getReturnType());
			log.debug("isVoid={}", value.isVoid());
			log.debug("getResolvedFromHandlerMethod={}", value.getResolvedFromHandlerMethod());
			log.debug("getShortLogMessage={}", value.getShortLogMessage());

			log.debug("");
		});
	}

	void debug2() {
		handlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
			StringBuffer sb = new StringBuffer();

			String methodName = handlerMethod.getMethod().getName();
			String beanTypeName = handlerMethod.getBeanType().getName();

			requestMappingInfo.getMethodsCondition().getMethods().forEach(method -> {
				String requestMethodName = method.name();

				requestMappingInfo.getPatternsCondition().getPatterns().forEach(pattern -> {
					sb.append(requestMethodName);
					sb.append("\t");

					sb.append(pattern);
					sb.append("\t");

					sb.append(methodName);
					sb.append("\t");

					sb.append(beanTypeName);
					sb.append("\t");
					sb.append("\n");
				});
			});

			System.out.println(sb);
		});
	}

}
