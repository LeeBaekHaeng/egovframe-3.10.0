package model;

public class Entity extends NameCasing {

	public Entity(String name) {
		super(name);
	}

	private String owner;
	private String tableName;
	private String tableComments;
	private String pkName;

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

	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

}
