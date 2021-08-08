import java.io.File;

public class ConfigXMLParser {
	private SymbolMap symbolmap;
	
	public ConfigXMLParser (String fileName) {
		System.out.println("the XML filename is >" + fileName + "<");
		symbolmap = new SymbolMap( new File( fileName ) );
	}
	
	public String getWorkingDirectory() {
		return (symbolmap.lookupSymbol("initDir"));
	}
	
	public String getPostfix() {
		return (symbolmap.lookupSymbol("outputFilePostFix"));
	}
	
	public String getWarning() {
		return (symbolmap.lookupSymbol("warning"));
	}
 
}