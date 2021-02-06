package information_processing.validation.formats;

import java.util.regex.Pattern;

import information_processing.boundaries.InformationIDsValidation;

public class NameFormatValidation implements InformationIDsValidation {
  private final static String NAME_REGEX = "^[\\p{L} . '-]+$";
  private String name;
  private String errorMsg;
  
  public NameFormatValidation(String mName, String mErrorMsg) {
	  this.name = mName;
	  this.errorMsg = mErrorMsg;
  }
  
  private void checkForNullName() throws IllegalArgumentException {
	  if(name == null) {
		  throw new IllegalArgumentException(errorMsg);
	  }
  }
  
  private void validateNameFormat() throws IllegalArgumentException {
	  if (!Pattern.matches(NAME_REGEX, name)) {
		  throw new IllegalArgumentException(errorMsg);
	  }
  }
  
  @Override
  public void validate() throws IllegalArgumentException{
	  checkForNullName();
	  validateNameFormat();
  }
  
  
  
}
