package operation;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

import egovframework.dev.imp.codegen.template.model.DataModelContext;
import lombok.Data;

public class CrudCodeGen {
	public String generate(DataModelContext dataModel, String templateFile, WizardModel wizardModel) throws Exception {
		StringWriter sw = new StringWriter();
		generate(dataModel, templateFile, sw, wizardModel);

//		System.out.println(sw.toString());

		return sw.toString();
	}

	private void generate(DataModelContext dataModel, String templateFile, Writer writer, WizardModel wizardModel)
			throws Exception {
		String templateEncoding = "UTF-8";

		Properties p = new Properties();
		p.setProperty("resource.loader", "class");
		p.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		p.setProperty("file.resource.loader.cache", "false");
		p.setProperty("file.resource.loader.modificationCheckInterval", "0");

		Velocity.init(p);

		VelocityContext context = new VelocityContext();

		context.put("model", dataModel);

		context.put("author", wizardModel.getAuthor());
		context.put("createDate", wizardModel.getCreateDate());

		// DataAccess
		context.put("checkDataAccess", wizardModel.getCheckDataAccess());
		context.put("sqlMapFolder", wizardModel.getSqlMapFolder());
		context.put("mapperFolder", wizardModel.getMapperFolder());
		context.put("daoPackage", wizardModel.getDaoPackage());
		context.put("mapperPackage", wizardModel.getMapperPackage());
		context.put("voPackage", wizardModel.getVoPackage());

		// Service
		context.put("checkService", wizardModel.getCheckService());
		context.put("servicePackage", wizardModel.getServicePackage());
		context.put("implPackage", wizardModel.getImplPackage());

		// Web
		context.put("checkWeb", wizardModel.getCheckWeb());
		context.put("controllerPackage", wizardModel.getControllerPackage());
		context.put("jspFolder", wizardModel.getJspFolder());

		Template template = null;

		try {
			template = Velocity.getTemplate(templateFile, templateEncoding);
		} catch (ResourceNotFoundException rnfe) {
			rnfe.printStackTrace();
		} catch (ParseErrorException pee) {
			// syntax error: problem parsing the template
		} catch (MethodInvocationException mie) {
			// something invoked in the template
			// threw an exception
		} catch (Exception e) {
		}

		template.merge(context, writer);

	}

	@Data
	public static class WizardModel {

		private String author;
		private String createDate;

		// DataAccess
		private String checkDataAccess;

		private String sqlMapFolder;
		private String mapperFolder;
		private String daoPackage;
		private String mapperPackage;
		private String voPackage;

		// Service
		private String checkService;

		private String servicePackage;
		private String implPackage;

		// Web
		private String checkWeb;

		private String controllerPackage;
		private String jspFolder;

	}

}
