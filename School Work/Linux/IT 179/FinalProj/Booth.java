import java.util.ArrayList;

/**
 * Booth class used in Sim.java
 * @author ahinh
 *
 */
public class Booth {
	Queue<Car> track = new LinkedList<Car>();
	
	//declare array list to manage booths
	ArrayList<Booth> allBooths = new ArrayList<Booth>();
	//the road that cars will wait in when all booths are full capacity
	Queue<car> road = new LinkedList<car>();
	//total number of vehicles in this track
	private int totalVehicles = 0;
	//total number of booths requested open
	private int totalBooths = 0;
	//total number of Cars with EZ Pass
	private int totalEZPass = 0;
	//total number of Cars without EZ Pass
	private int totalNoPass = 0;
	//maximum number of cars on the road
	private int maxNumOfVehiclesOnRoad = 0;
	//total time each vehicle takes to travel through toll
	private double totalTimeForVehicles = 0;
	//separate counter for average time it takes for cars without EZ Pass
	private double avgTimeNoPass = 0;
	//time it takes to pass booth from lane or road
	private double avgTimeToPassBooth = 0;
	
	/**
	 * This method sets the total number of booths that will be available
	 * determined by the user
	 * @param numBooths - number of booths that will be taking Cars
	 */
	public void setTotalBooths(int numBooths) {
		this.totalBooths = numBooths;
	}
	/**
	 * This method places tries to place a car into the shortest non-full track
	 * by creating a new car and giving it a booth time. If all lanes are at full
	 * capacity, the car will be added to the end of the 'road' queue.
	 * @param pass 
	 * @param mtime - mean time
	 * @param arriveTime - time that the car arrives at booth
	 */
	private void addCar(NormalDistrubition pass, double mtime, double arriveTime) {
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
					allBooths.get(boothIndex).track.offer(temp);
					//update counter for maximum number of cars on road if it applies
					if (road.size() > maxNumOfVehiclesOnRoad = road.size());
				}
			}
		}
	}
	
	/**
	 * This method will be called to find the smallest track that is not
	 * at full capacity. If all booths are full, will return -1. If not full,
	 * will return the booth number with the least number of cars.
	 * 
	 * @return the booth number with the lesat number of cars that isn't full, otherwise -1
	 */
	
	private int checkFullBooths() {
		int boothIndex = -1;
		int tempSize = 20;
		// Checks to make sure which tracks have the least number of cars
		// by comparing index, and tempsize.
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
	
	public void run(ExpDistribution nextVehicleArrive, NormalDistributio pass, double mtime, double vtime) {
		do {
			//begin simulation, break when 10800 seconds (3 hours) expires
			//clear all values
			totalVehicles = 0;
			totalEZPass = 0;
			totalNoPass = 0;
			maxNumOfVehiclesOnRoad = 0;
			totalTimeForVehicles = 0;
			avgTimeNoPass = 0;
			avrTimeToPassBooth = 0;
			road.clear();
			for (int i = 0; i < allBooths.size(); i++) {
				allBooths.get(i).track.clear();
			}
			
			double totalTime = 0;
			allBooths.add(newBooth());
			totalBooths++;
			
			while(totalTime < 10800) {
				double waitNextArrive = nextVehicleArrive.next();
				int lowestServiceTime = 100; //vehicle with lowest service time
				//determines which booth just finished service
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
					addCar(ass,mtime,totalTime);
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
						totalTime += lowestServiceTime;
						double arriveTime = removeVehicle(lowestServiceTimeLocation);
						totalTimeForVehicles += (totalTime - arriveTime);
					}
					//vehicle arrives as next event
					else if (waitNextArrive < allBooths.get(lowestServiceTimeLocation).track.peek().serviceTime) {
						for (int i = 0; i < allBooths.size(); i++) {
							if (!allBooths.get(i).track.isEmpty()) {
								allBooths.get(i).track.peek().serviceTime = 
										allBooths.get(i).track.peek().serviceTime - waitNextArrive;
							}
						}
						//add car to count
						addCar(pass,mtime,totalTime);
						//increase total time
						totalTime += waitNextArrive;
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
						double arriveTime = removeVehicle (lowestServiceTimeLocation);
						totalTimeForVehicles += (totalTime - arriveTime);
						addVehicle(pass,mtime,totalTime);
					}
				}
				if (numOfVehicles!= 0) {
					//calculate average time to pass booth
					avgTimeToPassBooth = (totalTimeForVehicles / numOfVehicles);
				}
				avgTimeNoPass = (avgTimeNoPass / totalNoPass);
			}
			System.out.println("Simulation -- 3 hours (Booth Num:" + totalBooths + ") (Without " +
					"EZ-Pass: m=" + mtime + ", v=" + vtime + ")");
			System.out.println("** Flow:  " + flow + " cars/Sec");
			System.out.println("** Total Cars:  " +totalVehicles + " EZ-Pass:  " +totalEZPass);
			System.out.println("** Pass window without EZ-Pass:  " + avgTimeNoPass + " Secs");
			System.out.println("** Max number of cars waiting on the road: " + maxNumOfVehiclesOnRoad) ;
			System.out.println("** Average waiting time:  " + avgTimeToPassBooth);
		}
		private double removeVehicle(int boothLocation) {
			if (allBooths.get(boothLocation).track.peek().staticServiceTime <= 1) {
				totalEZPass++;
			}
			else {
				totalNoPass++;
				avgTimeWithoutPass += allBooths.get(boothLocation).track.peek().serviceTime;
			}
			totalVehicles++;
			return 
					allBooths.get(boothLocation).track.poll().arriveTime;
		}
		
		public static void main(String[] args) {
			
		}
	}
}
