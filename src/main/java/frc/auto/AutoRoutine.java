package frc.auto;

public abstract class AutoRoutine {
	
	private final String AUTO_NAME;
	private final AutoTask[] AUTO_SEQUENCE;
	
	public AutoRoutine(String autoName, AutoTask[] autoSequence) {
		AUTO_NAME = autoName;
		AUTO_SEQUENCE = autoSequence;
	}

	public String getAutoName() {
		return AUTO_NAME;
	}

	public AutoTask[] getAutoSequence() {
		return AUTO_SEQUENCE;
	}
	
}
