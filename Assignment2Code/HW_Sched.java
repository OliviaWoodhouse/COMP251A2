package A2;
import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * The way you implement this method will define which order the assignments appear in when you sort.
	 * Return 1 if a1 should appear after a2
	 * Return -1 if a1 should appear before a2
	 * Return 0 if a1 and a2 are equivalent 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		if (a1.weight>a2.weight) {
			return -1;
		} else if (a1.weight<a2.weight) {
			return 1;
		} else {
			return 0;
		}
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to when assignment #i will be completed. output[i] is 0 if assignment #i is never completed.
	 * The homework you complete first will be given an output of 1, the second, 2, etc.
	 */
	public int[] SelectAssignments() {
		//Use the following command to sort your Assignments: 
		//Collections.sort(Assignments, new Assignment());
		//This will re-order your assignments. The resulting order will depend on how the compare function is implemented
		Collections.sort(Assignments, new Assignment());
		
		//Initializes the homeworkPlan, which you must fill out and output
		int[] homeworkPlan = new int[Assignments.size()];
		//YOUR CODE GOES HERE
		//loops through sorted assignments and if the time slot they're due is empty add them if not find the latest empty time slot (if none available set it to 0)
		for (int i=0;i<this.Assignments.size();i++) {
			int deadlineParser = this.Assignments.get(i).deadline;
			boolean isTaken = this.isTaken(homeworkPlan, deadlineParser);
			if (isTaken==false) {
				homeworkPlan[this.Assignments.get(i).number] = deadlineParser;
			} else {
				while (isTaken==true && deadlineParser!=0) {
					deadlineParser--;
					isTaken = this.isTaken(homeworkPlan, deadlineParser);
				}
				if (isTaken==false) {
					homeworkPlan[this.Assignments.get(i).number] = deadlineParser;
				} else {
					homeworkPlan[this.Assignments.get(i).number] = 0;
				}
			}
		}		
		return homeworkPlan;
	}
	
	/*Helper method to find out if a time slot is taken or not*/
	public boolean isTaken(int[] hwPlan, int x) {
		boolean taken = false;
		for (int i=0;i<hwPlan.length;i++) {
			if (hwPlan[i]==x) {
				taken = true;
			}
		}
		return taken;
	}
}
	



