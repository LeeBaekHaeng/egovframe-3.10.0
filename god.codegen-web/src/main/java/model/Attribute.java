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

	private String owner;
	private String tableName;
	private String nullable;
	private int dataLength;
	private String tableComments;
	private String columnComments;
	private String pk;
	private String fk;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

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

	public String getPk() {
		return pk;
	}

	public void setPk(String pk) {
		this.pk = pk;
	}

	public String getFk() {
		return fk;
	}

	public void setFk(String fk) {
		this.fk = fk;
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
