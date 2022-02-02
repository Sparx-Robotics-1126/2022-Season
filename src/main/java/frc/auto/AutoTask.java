package frc.auto;

public class AutoTask 
{	
	public static AutoTask createTask(AutoFeature feature) 
	{
		return new AutoTask(feature, 0, 0);
	}
	
	public static AutoTask createTask(AutoFeature feature, double value1) 
	{
		return new AutoTask(feature, value1, 0);
	}
	
	public static AutoTask createTask(AutoFeature feature, double value1, double value2) 
	{
		return new AutoTask(feature, value1, value2);
	}

	private final AutoFeature feature;
	public final double value1;
	public final double value2;
	
	private AutoTask(AutoFeature feature, double value1, double value2) 
	{
		this.feature = feature;
		this.value1 = value1;
		this.value2 = value2;
	}
	
	public AutoFeature getFeature() 
	{
		return feature;
	}
	
	@Override
	public String toString() {
		return feature.toString() + " | " + value1 + " : " + value2;
	}
}