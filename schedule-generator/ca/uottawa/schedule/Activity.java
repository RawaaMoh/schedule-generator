package ca.uottawa.schedule;

/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.18.0.3209 modeling language!*/


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

// line 18 "model.ump"
// line 50 "model.ump"
public class Activity implements Serializable
{
    private static final long serialVersionUID = 1L;
  @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
  public @interface umplesourcefile{int[] line();String[] file();int[] javaline();int[] length();}

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Activity Attributes
  private String type;
  private int number;
  private int day;
  private Date startTime;
  private Date endTime;
  private String place;
  private String professor;
  private boolean selected;

  //Activity Associations
  private Section section;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Activity(String aType, int aNumber, int aDay, Date aStartTime, Date aEndTime, String aPlace, String aProfessor, boolean aSelected, Section aSection)
  {
    type = aType;
    number = aNumber;
    day = aDay;
    startTime = aStartTime;
    endTime = aEndTime;
    place = aPlace;
    professor = aProfessor;
    selected = aSelected;
    boolean didAddSection = setSection(aSection);
    if (!didAddSection)
    {
      throw new RuntimeException("Unable to create activity due to section");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumber(int aNumber)
  {
    boolean wasSet = false;
    number = aNumber;
    wasSet = true;
    return wasSet;
  }

  public boolean setDay(int aDay)
  {
    boolean wasSet = false;
    day = aDay;
    wasSet = true;
    return wasSet;
  }

  public boolean setStartTime(Date aStartTime)
  {
    boolean wasSet = false;
    startTime = aStartTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(Date aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setPlace(String aPlace)
  {
    boolean wasSet = false;
    place = aPlace;
    wasSet = true;
    return wasSet;
  }

  public boolean setProfessor(String aProfessor)
  {
    boolean wasSet = false;
    professor = aProfessor;
    wasSet = true;
    return wasSet;
  }

  public boolean setSelected(boolean aSelected)
  {
    boolean wasSet = false;
    selected = aSelected;
    wasSet = true;
    return wasSet;
  }

  public String getType()
  {
    return type;
  }

  public int getNumber()
  {
    return number;
  }

  public int getDay()
  {
    return day;
  }

  public Date getStartTime()
  {
    return startTime;
  }

  public Date getEndTime()
  {
    return endTime;
  }

  public String getPlace()
  {
    return place;
  }

  public String getProfessor()
  {
    return professor;
  }

  public boolean getSelected()
  {
    return selected;
  }

  public boolean isSelected()
  {
    return selected;
  }

  public Section getSection()
  {
    return section;
  }

  public boolean setSection(Section aSection)
  {
    boolean wasSet = false;
    //Must provide section to activity
    if (aSection == null)
    {
      return wasSet;
    }

    if (section != null && section.numberOfActivities() <= Section.minimumNumberOfActivities())
    {
      return wasSet;
    }

    Section existingSection = section;
    section = aSection;
    if (existingSection != null && !existingSection.equals(aSection))
    {
      boolean didRemove = existingSection.removeActivity(this);
      if (!didRemove)
      {
        section = existingSection;
        return wasSet;
      }
    }
    section.addActivity(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Section placeholderSection = section;
    this.section = null;
    placeholderSection.removeActivity(this);
  }


  public String toString()
  {
	  String day = null;
	  switch (getDay()) {
	  case 1: day = "Sunday";
	  break;
	  case 2: day = "Monday";
	  break;
	  case 3: day = "Tuesday";
	  break;
	  case 4: day = "Wednesday";
	  break;
	  case 5: day = "Thursday";
	  break;
	  case 6: day = "Friday";
	  break;
	  case 7: day = "Saturday";
	  break;
	  default:
		  day = "None";
	  }
	  SimpleDateFormat date_format = new SimpleDateFormat("HH:mm");
	  String outputString = "\t" + getType() + " " + getNumber() + ". " + day + " " + date_format.format(getStartTime()) + "-" + date_format.format(getEndTime()) + ". " + getPlace() + ". Prof: " + getProfessor() + ". Selected: " + isSelected() + System.getProperty("line.separator");
	  
	  
    return outputString;
  }
  
  //------------------------
  // MANUALLY-MADE METHODS
  //------------------------
  
  public boolean overlaps(Activity e) {
	return (getDay() == e.getDay())&&(getStartTime().compareTo(e.getEndTime()) < 0) && (e.getStartTime().compareTo(getEndTime()) < 0); 
  }

public String getPgSQLQuery(String SName, int semester) {
	String nl = System.getProperty("line.separator");
	SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
	String day = null;
	  switch (this.day) {
	  case 1: day = "Sunday";
	  break;
	  case 2: day = "Monday";
	  break;
	  case 3: day = "Tuesday";
	  break;
	  case 4: day = "Wednesday";
	  break;
	  case 5: day = "Thursday";
	  break;
	  case 6: day = "Friday";
	  break;
	  case 7: day = "Saturday";
	  break;
	  default:
		  day = "None";
	  }
	  String qPlace = place.replaceAll("'", "''");
	  qPlace = qPlace.replace("\\", "/");
	  String qProf = professor.replaceAll("'", "''");
	  qProf = qProf.replace("\\", "/");
	  String query = "INSERT INTO activities(aType, aNumber, SName, semester, dayOfWeek, startTime, endTime, place, professor) VALUES('"
			  + type + "'," + number + ", '" + SName + "'," + semester + ",'" + day
			  + "','" + df.format(startTime) + "','" + df.format(endTime) + "','" + qPlace +"','" + qProf +"');" + nl;
	  return query;
}
  
  
}