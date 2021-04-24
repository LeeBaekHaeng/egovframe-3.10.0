package model;

public class Attribute extends NameCasing {
	public Attribute(String name) {
		super(name);
	}

	private String type;
	private String javaType;

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		if ("DATE".equals(type)) {
			this.javaType = "LocalDateTime";
		} else if ("NUMBER".equals(type)) {
			this.javaType = "Long";
		} else {
			this.javaType = "String";
		}
		this.type = type;
	}

	private String nullable;
	private int dataLength;
	private String tableComments;
	private String columnComments;

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}

	public String getColumnComments() {
		return columnComments;
	}

	public void setColumnComments(String columnComments) {
		this.columnComments = columnComments;
	}

	public String getValues() {
		if ("NUMBER".equals(type)) {
			return "0";
		} else if ("DATE".equals(type)) {
			return "SYSDATE";
		} else {
			if ("Y".equals(nullable)) {
				return "''";
			} else {
				return "'0'";
			}
		}
	}
}
