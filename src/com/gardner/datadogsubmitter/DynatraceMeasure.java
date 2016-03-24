package com.gardner.datadogsubmitter;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class DynatraceMeasure
{

	private String m_strName = "";
	private String m_strAggregation = "";
	private String m_strUnit = "";
	private List<DynatraceMeasurement> m_oMeasurements;
	
	public DynatraceMeasure(Element oElement)
	{
		m_oMeasurements = new ArrayList<DynatraceMeasurement>();
		
		buildMeasure(oElement);
	}
	
	public String getName()
	{
		return m_strName;
	}
	
	public String getAggregation()
	{
		return m_strAggregation;
	}
	
	public String getUnit()
	{
		return m_strUnit;
	}
	
	public List<DynatraceMeasurement> getValues()
	{
		return m_oMeasurements;
	}
	
	public boolean hasData()
	{
		if (m_oMeasurements.size() > 0) return true;
		return false;
	}
	
	//=========== Private Methods ================
	private void buildMeasure(Element oElement)
	{
		//System.out.println("------ Building Measure ---------");
		// Name
		m_strName = oElement.getAttribute("measure");
		
		// Aggregation
		m_strAggregation = oElement.getAttribute("aggregation");


		/*
		 * Each of these represents a 10 second timeslot.
		 * eg. in 1 min you'd expect 6 but if no data arrives for 10 seconds, you may have less.
		 */
		NodeList oMeasurementList = oElement.getElementsByTagName("measurement");
		for (int iCount = 0; iCount < oMeasurementList.getLength(); iCount++)
		{
			Element oMeasurementElem = (Element) oMeasurementList.item(iCount);
			DynatraceMeasurement oMeasurement = new DynatraceMeasurement(oMeasurementElem, m_strAggregation);
			
			m_oMeasurements.add(oMeasurement);
		}
		
		//System.out.println("Measure: " + m_strName + " with aggregation set to: " + m_strAggregation + " has been built with: " + m_oMeasurements.size() + " measurements");
		//System.out.println("------ END Building Measure ---------");
		
	}
}
