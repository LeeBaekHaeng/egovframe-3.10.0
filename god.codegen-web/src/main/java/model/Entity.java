package model;

public class Entity extends NameCasing {

	public Entity(String name) {
		super(name);
	}

	private String owner;
	private String tableComments;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTableComments() {
		return tableComments;
	}

	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}

}
