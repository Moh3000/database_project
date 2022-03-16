package application;

public class Car {
	private String registration_number;
	private String vin;
	private boolean availability;
	private int meter_reading;
	private int number_of_bags;
	private int number_of_occupants;
	private String manufacturer_name;
	private String color;
	private String model;
	private double rental_price_per_day;
	private String size;
	private int year_of_manufacture;
	private String fuel_efficiency;
	private String transmission_system;
	private double security_deposit;

	public Car(String vin, String manufacturer_name, String model, int year_of_manufacture, double rental_price_per_day,
			String fuel_efficiency, double security_deposit, String transmission_system, String size,
			int number_of_occupants, String color) {
		this.color = color;
		this.fuel_efficiency = fuel_efficiency;
		this.manufacturer_name = manufacturer_name;
		this.model = model;
		this.number_of_occupants = number_of_occupants;
		this.rental_price_per_day = rental_price_per_day;
		this.security_deposit = security_deposit;
		this.size = size;
		this.transmission_system = transmission_system;
		this.vin = vin;
		this.year_of_manufacture = year_of_manufacture;
	}

	public String getRegistration_number() {
		return registration_number;
	}

	public void setRegistration_number(String registration_number) {
		this.registration_number = registration_number;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public int getMeter_reading() {
		return meter_reading;
	}

	public void setMeter_reading(int meter_reading) {
		this.meter_reading = meter_reading;
	}

	public int getNumber_of_bags() {
		return number_of_bags;
	}

	public void setNumber_of_bags(int number_of_bags) {
		this.number_of_bags = number_of_bags;
	}

	public int getNumber_of_occupants() {
		return number_of_occupants;
	}

	public void setNumber_of_occupants(int number_of_occupants) {
		this.number_of_occupants = number_of_occupants;
	}

	public String getManufacturer_name() {
		return manufacturer_name;
	}

	public void setManufacturer_name(String manufacturer_name) {
		this.manufacturer_name = manufacturer_name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getRental_price_per_day() {
		return rental_price_per_day;
	}

	public void setRental_price_per_day(double rental_price_per_day) {
		this.rental_price_per_day = rental_price_per_day;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getYear_of_manufacture() {
		return year_of_manufacture;
	}

	public void setYear_of_manufacture(int year_of_manufacture) {
		this.year_of_manufacture = year_of_manufacture;
	}

	public String getFuel_efficiency() {
		return fuel_efficiency;
	}

	public void setFuel_efficiency(String fuel_efficiency) {
		this.fuel_efficiency = fuel_efficiency;
	}

	public String getTransmission_system() {
		return transmission_system;
	}

	public void setTransmission_system(String transmission_system) {
		this.transmission_system = transmission_system;
	}

	public double getSecurity_deposit() {
		return security_deposit;
	}

	public void setSecurity_deposit(double security_deposit) {
		this.security_deposit = security_deposit;
	}

}
