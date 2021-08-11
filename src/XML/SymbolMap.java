package XML;

import java.io.File;
import java.util.Properties;

public class SymbolMap {
	  private Properties symbolmap;
	  
	  public SymbolMap( File file ) {
	    symbolmap = new Properties();
	 
	    try {
	      // Populate the symbol map from the XML file
	      symbolmap.loadFromXML( file.toURI().toURL().openStream() );
	    }
	    catch ( Exception e ) {
	    	System.out.println("loadFromXML failed!");
	    }
	  }
	 
	  /*
	  variable length arguments are packed into an array
	  which can be accessed and passed just like any array
	  */
	  public String lookupSymbol( String symbol, String... variables ) {
	    // Retrieve the value of the associated key
	    String message = symbolmap.getProperty( symbol );
	 
	    if( message == null ) {
	      return "";
	    }
	 
	    // Interpolate parameters if necessary and return the message
	    return String.format( message, variables );
	  }
}
