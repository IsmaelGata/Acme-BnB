
package domain;

public enum Type {

	URL("URL", "URL"), PICTURE("PICTURE", "IMAGEN"), NUMBER("NUMBER", "NUMERO"), BOOLEAN("BOOLEAN", "BOOLEANO"), TEXT("TEXT", "TEXTO");

	private final String	name;
	private final String	spanishName;


	private Type(String name, String spanishName) {
		this.name = name;
		this.spanishName = spanishName;
	}

	public String getName() {
		return name;
	}

	public String getSpanishName() {
		return spanishName;
	}
}
