package com.gardner.datadogsubmitter;
import org.w3c.dom.Element;


public class DynatraceMeasurement
{
	private int m_iTimestamp = -1;
	private double m_dValue = -1;
	
	public DynatraceMeasurement(Element oElement, String strSelectedAggregation)
	{
		buildMeasurement(oElement, strSelectedAggregation);
	}
	
	public int getTimestamp()
	{
		return m_iTimestamp;
	}
	
	public double getValue()
	{
		return m_dValue;
	}
	
	//=========== PRIVATE METHODS =============
	private void buildMeasurement(Element oElement, String strSelectedAggregation)
	{
		// Get timestamp. First convert to unix.
		m_iTimestamp = timestampToUnix(oElement.getAttribute("timestamp"));
		//System.out.println("Timestamp: " + m_iTimestamp);
				
		// Set value based on selected aggregation.
		// eg. Aggregation is "Minimum" but first convert to "min"
		m_dValue = Double.valueOf(oElement.getAttribute(aggregationToAbbreviation(strSelectedAggregation)));
		//System.out.println("Value: " + m_dValue);
	}
	
	private int timestampToUnix(String strTimestamp)
	{
		long lVal = Long.valueOf(strTimestamp);
		return (int) (lVal / 1000L);
	}
	
	private String aggregationToAbbreviation(String strInput)
	{
		if (strInput.equals("Minimum")) return "min";
		else if (strInput.equals("Average")) return "avg";
		else if (strInput.equals("Maximum")) return "max";
		else if (strInput.equals("Sum")) return "sum";
		else if (strInput.equals("Count")) return "count";
		else return "UNKNOWN";
	}

}
