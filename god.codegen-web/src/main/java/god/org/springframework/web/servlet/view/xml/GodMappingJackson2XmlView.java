package god.org.springframework.web.servlet.view.xml;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import com.fasterxml.jackson.annotation.JsonView;

public class GodMappingJackson2XmlView extends MappingJackson2XmlView {

	@Override
	protected Object filterModel(Map<String, Object> model) {
		Map<String, Object> value = new HashMap<>();
		for (Map.Entry<String, Object> entry : model.entrySet()) {
			if (!(entry.getValue() instanceof BindingResult) && !entry.getKey().equals(JsonView.class.getName())) {
				value.put(entry.getKey(), entry.getValue());
			}
		}
		return value;
	}

}
