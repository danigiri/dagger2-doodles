package cat.calidos.doodles.dagger2.dependencies;


public class ParseException extends Exception {

	public ParseException(String m) {
		
		super(m);

		System.err.println("*Parse exception '"+m+"' thrown");	

	}

}
