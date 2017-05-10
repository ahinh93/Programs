/**
 * Andrew Hinh
 * 12/5/13
 * @author ahinh
 *
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

import myUtil.*;
/**
 * This class will simulate car traffic for three hours given an input of mean
 * time (to pass the booth), variance (to pass the booth), traffic flow density,
 * and number of booths available defined by the user.
 */
public class Sim {

	public static void main(String[] args) {
		/*
		 * Declare user defined variables and store
		 */
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Enter mean: ");
		double mtime = keyboard.nextDouble(); //Variable for the mean time
		System.out.print("Enter variance: ");
		double vtime = keyboard.nextDouble(); //Variable for the variance time
		System.out.print("Enter flow: ");
		double flow = keyboard.nextDouble(); //Variable for the traffic flow
		System.out.print("Enter number of booths: ");
		int boothNum = keyboard.nextInt(); //Variable for the number of tollBooths

		Sim temp = new Sim();

		temp.setBoothNum(boothNum);
		
		System.out.println("Simulation" + " -- " + "3 hours" + " (Booth No: "
				+ boothNum + ")  " + "(Without   EZ Pass: " + " m= " + mtime
				+ " , " + "v= " + vtime + " )");
		System.out.println("** Flow: " + flow + " vehicles/second");
		
		NormalDistribution pass = new NormalDistribution(mtime, vtime);

		ExpDistribution nextVehicleArrive = new ExpDistribution(1 / flow);
		temp.run(nextVehicleArrive, pass, mtime, vtime);
	}

	private class Booth {
		// keeps track of each toll booths line through a linked list
		Queue<Car> track = new LinkedList<Car>();
	}

	private class Car {
		// time the car requires to be served at booth
		double serviceTime;
		// time the car arrives at the booth
		double arriveTime;
		/**
		 * Constructor for car will create an object car that has two variables.
		 * 
		 * @param serviceTime
		 *            - determined by Normal distribution with given mean and
		 *            variance
		 * @param arriveTime
		 *            - determined when car reaches booth
		 */
		public Car(double serviceTime, double arriveTime) {
			this.serviceTime = serviceTime;
			this.arriveTime = arriveTime;
		}
		/**
		 * getter method to access service time
		 * 
		 * @return
		 */
		public double getServiceTime() {
			return this.serviceTime;
		}
	}

	// declare array list to manage booths
	ArrayList<Booth> allBooths = new ArrayList<Booth>();
	// the road that cars will wait in when all booths are full capacity
	Queue<Car> road = new LinkedList<Car>();
	// total number of vehicles in this track
	private int totalVehicles = 0;
	// total number of booths requested open
	private int totalBooths = 0;
	// total number of Cars with EZ Pass
	private int totalEZPass = 0;
	// total number of Cars without EZ Pass
	private int totalNoPass = 0;
	// maximum number of cars on the road
	private int maxNumOfVehiclesOnRoad = 0;
	// total time each vehicle takes to travel through toll
	private double totalTimeForVehicles = 0;
	// separate counter for average time it takes for cars without EZ Pass
	private double avgTimeNoPass = 0;
	// time it takes to pass booth from lane or road
	private double avgTimeToPassBooth = 0;

	/**
	 * This method sets the total number of booths that will be available
	 * determined by the user
	 * 
	 * @param numBooths
	 *            - number of booths that will be taking Cars
	 */
	public void setBoothNum(int numBooths) {
		this.totalBooths = numBooths;
	}

	/**
	 * This method places tries to place a car into the shortest non-full
	 * track by creating a new car and giving it a booth time. If all lanes
	 * are at full capacity, the car will be added to the end of the 'road'
	 * queue.
	 * 
	 * @param pass
	 * @param mtime
	 *            - mean time
	 * @param arriveTime
	 *            - time that the car arrives at booth
	 */
	private void addCar(NormalDistribution pass, double mtime, double arriveTime) {
		/*
		 * Amount of time each car speeds at a booth
		 * if car has EZ pass, service time will be 1 second 
		 */
		double serviceTime = pass.sample();
		if (serviceTime<mtime) serviceTime = 1.0;

		Car someCar = new Car(serviceTime, arriveTime);
		//find the track with the least number of cars, if all lanes
		//are full then append to road
		int boothIndex = checkFullBooths();
		if (boothIndex != -1) {
			if (allBooths.size() > 0) {
				//if road has cars, the new car will poll the lead car into track
				if (road.size() > 0) {
					allBooths.get(boothIndex).track.offer(road.poll());
					road.offer(someCar);
				}
				else {
					allBooths.get(boothIndex).track.offer(someCar);
					//update counter for maximum number of cars on road if it applies
				}
			}
		}
		else {
			road.offer(someCar);
			if (road.size() > maxNumOfVehiclesOnRoad) {
				maxNumOfVehiclesOnRoad = road.size();
			}
		}
	}

	/**
	 * This method will be called to find the smallest track that is not at
	 * full capacity. If all booths are full, will return -1. If not full,
	 * will return the booth number with the least number of cars.
	 * 
	 * @return the booth number with the least number of cars that isn't
	 *         full, otherwise -1
	 */

	private int checkFullBooths() {
		int boothIndex = -1;
		int tempSize = 20;
		// returns booth index with smallest amount of cars, -1 if all full
		for (int i = 0; i < allBooths.size(); i++) {
			if (allBooths.get(i).track.size() != 20) {
				if (allBooths.get(i).track.size() < tempSize) {
					tempSize = allBooths.get(i).track.size();
					boothIndex = i;
				}
			}
		}
		return boothIndex;
	}

	public void run(ExpDistribution nextVehicleArrive, NormalDistribution pass, double mtime, double vtime) {
		do {
			//begin simulation, break when 10800 seconds (3 hours) expires
			totalVehicles = 0;
			totalEZPass = 0;
			totalNoPass = 0;
			maxNumOfVehiclesOnRoad = 0;
			totalTimeForVehicles = 0;
			avgTimeNoPass = 0;
			avgTimeToPassBooth = 0;
			road.clear();
			for (int i = 0; i < allBooths.size(); i++) {
				allBooths.get(i).track.clear();
			}

			double totalTime = 0;
			allBooths.add(new Booth());
			totalBooths++;

			while(totalTime < 10800) {//3 hours to 10800 seconds unit time
				double waitNextArrive = nextVehicleArrive.next();
				int lowestServiceTimeLocation = 0; //location for vehicle to arrive
				//determines which booth just finished service
				double lowestServiceTime = 100; // vehicle with lowest service time
				for (int i = 0; i < allBooths.size(); i++) {
					//where there is an open track
					if (allBooths.get(i).track.isEmpty()) {
						lowestServiceTimeLocation = i;
						lowestServiceTime = 0;
						i = allBooths.size();
					}
					else {
						//if there is a lower service time than current, lower time will be set as current
						if (allBooths.get(i).track.peek().serviceTime < lowestServiceTime) {
							lowestServiceTimeLocation = i;
							lowestServiceTime = allBooths.get(i).track.peek().serviceTime;
						}
					}
				}
				//if track is empty
				if (allBooths.get(lowestServiceTimeLocation).track.size() == 0) {
					for (int i = 0; i < allBooths.size(); i++) {
						if (!allBooths.get(i).track.isEmpty()) {
							if (i != lowestServiceTimeLocation) {
								allBooths.get(i).track.peek().serviceTime =
										allBooths.get(i).track.peek().serviceTime - lowestServiceTime;
							}
						}
					}
					//update total time and add car
					totalTime += waitNextArrive;
					//add car to count
					addCar(pass,mtime,totalTime);
				}
				else {
					//wait for next event of car leaving or arriving
					if (waitNextArrive > allBooths.get(lowestServiceTimeLocation).track.peek().serviceTime) {
						for (int i = 0; i < allBooths.size(); i++) {
							if (!allBooths.get(i).track.isEmpty()) {
								if (i != lowestServiceTimeLocation) {
									allBooths.get(i).track.peek().serviceTime = allBooths.get
											(i).track.peek().serviceTime - lowestServiceTime;
								}
							}
						}
						waitNextArrive = waitNextArrive-allBooths.get(lowestServiceTimeLocation).track.peek().serviceTime;
						//update total time
						totalTime += lowestServiceTime;
						double arriveTime = removeCar(lowestServiceTimeLocation);
						//update total time for vehicles
						totalTimeForVehicles += (totalTime - arriveTime);
					}
					//vehicle arrives as next event
					else if (waitNextArrive < allBooths.get(lowestServiceTimeLocation).track.peek().serviceTime) {
						for (int i = 0; i < allBooths.size(); i++) {
							if (!allBooths.get(i).track.isEmpty()) {
								if (i != lowestServiceTimeLocation) {
									allBooths.get(i).track.peek().serviceTime = 
											allBooths.get(i).track.peek().serviceTime - waitNextArrive;
								}
							}
						}
						//increase total time
						totalTime += waitNextArrive;
						//add car to count
						addCar(pass,mtime,totalTime);

					}
					//when wait time is equal to shortest service
					else if (waitNextArrive == allBooths.get(lowestServiceTimeLocation).track.peek().serviceTime) {
						for (int i = 0; i < allBooths.size(); i++) {
							if (!allBooths.get(i).track.isEmpty()) {
								if (i != lowestServiceTimeLocation) {
									allBooths.get(i).track.peek().serviceTime = allBooths.get(i)
											.track.peek().serviceTime - lowestServiceTime;
								}
							}
						}
						totalTime+= allBooths.get(lowestServiceTimeLocation).track.peek().serviceTime;
						double arriveTime = removeCar(lowestServiceTimeLocation);
						totalTimeForVehicles += (totalTime - arriveTime);
						addCar(pass,mtime,totalTime);
					}
				}
				if (totalVehicles!= 0) {
					//calculate average time to pass booth
					avgTimeToPassBooth = (totalTimeForVehicles / totalVehicles);
					//format number to be rounded
					avgTimeToPassBooth = (double) Math.round((avgTimeToPassBooth * 10000) / 10000);
				}
				if (avgTimeToPassBooth > 10800) {// if above 10800 seconds (3 hours), breaks
					break;
				}
			}
			avgTimeNoPass = avgTimeNoPass / totalNoPass;
			avgTimeNoPass = (double) Math.round((avgTimeNoPass * 10000) / 10000);
		}

		while (avgTimeToPassBooth > 10800); 
		System.out.println("** Total Cars:  " +totalVehicles + " \tEZ-Pass:  " +totalEZPass);
		System.out.println("** Pass window without EZ-Pass:  " + avgTimeNoPass + " Secs");
		System.out.println("** Max number of cars waiting on the road: " + maxNumOfVehiclesOnRoad + " cars") ;
		System.out.println("** Average waiting time:  " + avgTimeToPassBooth + " seconds");
	}
	private double removeCar(int boothLocation) {
		if (allBooths.get(boothLocation).track.peek().serviceTime <= 1) {
			totalEZPass++;
		}
		else {
			totalNoPass++;
			avgTimeNoPass += allBooths.get(boothLocation).track.peek().serviceTime;
		}
		totalVehicles++;
		return 
				allBooths.get(boothLocation).track.poll().arriveTime;
	}
}
