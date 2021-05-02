package model;

import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class GodPathname {

	public GodPathname(Entity entity) {
		setSql(entity);
	}

	private String sql;

	public String getSql(String filename) {
		if (StringUtils.hasText(filename)) {
			return this.sql.replaceAll(".sql", "-" + filename + ".sql");
		} else {
			return this.sql + filename;
		}
	}

	private void setSql(Entity entity) {
		boolean hasText = StringUtils.hasText(entity.getTableComments());

		StringBuffer sb = new StringBuffer();

		sb.append("/");
		sb.append(entity.getOwner());

		sb.append("/");
		sb.append(entity.getName());
		if (hasText) {
			sb.append(" ");
			sb.append(entity.getTableComments());
		}

		sb.append("/");
		sb.append(entity.getName());
		if (hasText) {
			sb.append(" ");
			sb.append(entity.getTableComments());
		}

		sb.append(".sql");

		this.sql = sb.toString();
	}

}
