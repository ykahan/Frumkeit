package general;

public class ErrorTracker {
	private int ErrorsGlobal;
	private int ErrorsLocal;
	
	public ErrorTracker() {
		ErrorsGlobal = 0;
		ErrorsLocal = 0;
	}
	
	public void incrementErrorsLocal() {
		ErrorsLocal++;
		incrementErrorsGlobal();
		System.out.println("Error Found!");
	}
	
	private void incrementErrorsGlobal() {
		ErrorsGlobal++;
	}
	
	private void resetErrorsLocal() {
		ErrorsLocal = 0;
	}
	
	private int getErrorsLocal() {
		return ErrorsLocal;
	}
	
	public void showErrorsLocal() {
		System.out.println("Local Errors: " + getErrorsLocal());
		resetErrorsLocal();
	}
	
	private int getErrorsGlobal() {
		return ErrorsGlobal;
	}
	
	public void showErrorsGlobal() {
		System.out.println("Global Errors: " + getErrorsGlobal());
	}
	
	public void getAllErrors() {
		getErrorsLocal();
		getErrorsGlobal();
	}

}
