import java.time.*;

public class Building {
    private String address;
    private LocalDate yearOfConstructiion;
    private int numberOfFloors;
    private String buildingType;
    private int numberOfRooms;
    private boolean isUnfit;
    private float areaOfRooms;

    public Building() {
        this.address = "";
        this.yearOfConstructiion = LocalDate.of(LocalDate.now().getYear(), 1, 1);
        this.numberOfFloors = 1;
        this.buildingType = "Постройка";
        this.numberOfRooms = 1;
        this.isUnfit = false;
        this.areaOfRooms = 500.0f;
    }
    public Building(String address, int year, int numberOfFloors, String buildingType, int numberOfRooms, boolean isUnfit, float areaOfRooms) {
        this.address = address;
        this.yearOfConstructiion = LocalDate.of(year,  1, 1);
        this.numberOfFloors = numberOfFloors;
        this.buildingType = buildingType;
        this.numberOfRooms = numberOfRooms;
        this.isUnfit = isUnfit;
        this.areaOfRooms = areaOfRooms;
    }

    public String getAddress() 
    {
        return this.address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getYearOfConstructiion()
    {
        return this.yearOfConstructiion.getYear();
    }

    public void setYearOfConstruction(int year)
    {
        this.yearOfConstructiion = LocalDate.of(year, 1, 1);
    }

    public int getNumberOfFloors()
    {
        return this.numberOfFloors;
    }

    public void setNumberOfFloors(int numberOfFloors)
    {
        this.areaOfRooms = this.areaOfRooms / this.numberOfFloors * numberOfFloors;
        this.numberOfFloors = numberOfFloors;
    }

    public String getBuildingType()
    {
        return this.buildingType;
    }

    public void setBuildingType(String type)
    {
        this.buildingType = type;
    }

    public int getNumberOfRooms()
    {
        return this.numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms)
    {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean getIsUnfit()
    {
        return this.isUnfit;
    }

    public void setIsUnfit(boolean isUnfit)
    {
        this.isUnfit = isUnfit;
    }

    public float getAreaOfRooms()
    {
        return this.areaOfRooms;
    }

    public void setAreaOfRooms(float areaOfRooms)
    {
        this.areaOfRooms = areaOfRooms;
    }

    public float AverageRoomSize()
    {
        return this.areaOfRooms / this.numberOfRooms;
    }
}
