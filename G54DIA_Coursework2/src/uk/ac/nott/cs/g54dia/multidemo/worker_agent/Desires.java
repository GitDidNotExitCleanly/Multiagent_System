package uk.ac.nott.cs.g54dia.multidemo.worker_agent;

import uk.ac.nott.cs.g54dia.multidemo.data_structure.Goal;
import uk.ac.nott.cs.g54dia.multidemo.data_structure.myPoint;
import uk.ac.nott.cs.g54dia.multidemo.data_structure.myTask;
import uk.ac.nott.cs.g54dia.multilibrary.Tanker;

public class Desires {
	
	private Goal currentDesire;
	
	private myTask task;
	
	private int pointer;
	private myPoint[] route;
	
	public Desires() {
		this.currentDesire = Goal.NONE;
		
		this.task = null;
		
		this.pointer = -1;
		this.route = null;
	}
	
	// get current goal
	public Goal getCurrentDesire() {
		return this.currentDesire;
	}

	// set current desire
	public void changeDesire(Goal goal) {
		this.currentDesire = goal;
	}
	
	// get task
	public myTask getTask() {
		return this.task;
	}
	
	// get pointer
	public int getPointer() {
		return this.pointer;
	}

	// get route
	public myPoint[] getTarget() {
		return this.route;
	}
	
	// set task, pointer, route
	public void setTarget(myTask task, myPoint[] route) {
		this.task = task;
		
		this.pointer = 0;
		this.route = route;
	}
	
	// update pointer
	public void updatePointer(myPoint currentPosition, int currentFuel) {
		if (this.route != null) {
			if (this.pointer < this.route.length) {
				if (this.route[this.pointer].equals(currentPosition)) {
					if (!currentPosition.equals(new myPoint(0,0)) || currentFuel == Tanker.MAX_FUEL) {
						this.pointer ++;
					}
				}
			}
		}
	}
	
	// check if target is finished
	public boolean isTargetFinished() {
		if (this.route == null) {
			return true;
		}
		else {
			if (this.task == null) {
				return (this.pointer == this.route.length);
			}
			else {
				return (this.pointer == this.route.length && this.task.getTask().isComplete());
			}
		}
	}
	
	// terminate current exploration
	public void terminateExploration() {
		if (this.route != null) {
			if (this.task == null) {
				this.pointer = this.route.length;
			}
		}
	}
}
