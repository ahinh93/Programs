/**
 * Car class to gets put into Queue
 * @author ahinh
 *
 */
public class Car {
	//time the car requires to be served at booth
	double serviceTime;
	//time the car arrives at the booth
	double arriveTime;
	/**
	 * Constructor for car will create an object car that has two variables.
	 * @param serviceTime - determined by Normal distribution with given mean and variance
	 * @param arriveTime - determined when car reaches booth
	 */
	public Car(double serviceTime, double arriveTime) {
		this.serviceTime = serviceTime;
		this.arriveTime = arriveTime;
	}
	/**
	 * getter method to access service time
	 * @return
	 */
	public double getServiceTime() {
		return this.serviceTime;
	}
}
