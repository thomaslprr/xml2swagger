package definitions;

import javax.xml.bind.annotation.XmlElement;

public class Property {
	
	private String name;
	private String type;
	
	private String ref;
	
	private Item items;
	
	private Enums enums;
	
	private String example;
	
	private String description;
	
	private boolean required;
	
	private int minimum;
	private int maximum;
	private boolean exclusiveMaximum;
	private boolean exclusiveMinimum;
	private int maxLength;
	private int minLength;
	private int maxItems;	
	private boolean	uniqueItems;
	
	public Property(String name, String type, String ref, Item items, Enums enums, String example, String description,
			boolean required, int minimum, int maximum, boolean exclusiveMaximum, boolean exclusiveMinimum,
			int maxLength, int minLength, int maxItems, boolean uniqueItems) {
		super();
		this.name = name;
		this.type = type;
		this.ref = ref;
		this.items = items;
		this.enums = enums;
		this.example = example;
		this.description = description;
		this.required = required;
		this.minimum = minimum;
		this.maximum = maximum;
		this.exclusiveMaximum = exclusiveMaximum;
		this.exclusiveMinimum = exclusiveMinimum;
		this.maxLength = maxLength;
		this.minLength = minLength;
		this.maxItems = maxItems;
		this.uniqueItems = uniqueItems;
	}

	@XmlElement(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlElement(name = "items")
	public Item getItems() {
		return items;
	}

	public void setItems(Item items) {
		this.items = items;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public Enums getEnums() {
		return enums;
	}

	public void setEnums(Enums enums) {
		this.enums = enums;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public boolean isExclusiveMaximum() {
		return exclusiveMaximum;
	}

	public void setExclusiveMaximum(boolean exclusiveMaximum) {
		this.exclusiveMaximum = exclusiveMaximum;
	}

	public boolean isExclusiveMinimum() {
		return exclusiveMinimum;
	}

	public void setExclusiveMinimum(boolean exclusiveMinimum) {
		this.exclusiveMinimum = exclusiveMinimum;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMinLength() {
		return minLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMaxItems() {
		return maxItems;
	}

	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}

	public boolean isUniqueItems() {
		return uniqueItems;
	}

	public void setUniqueItems(boolean uniqueItems) {
		this.uniqueItems = uniqueItems;
	}
	
	
	
	
	
}
