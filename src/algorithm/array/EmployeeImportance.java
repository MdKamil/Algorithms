package algorithm.array;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class EmployeeImportance {
	private static class Employee {
		public int id;
		public int importance;
		public List<Integer> subordinates;
	}

	public int getImportance(List<Employee> employees, int id) {
		int totImpVal = 0;
		HashMap<Integer, Employee> employeeMap = new HashMap<>();
		for(Employee employee: employees) {
			employeeMap.put(employee.id, employee);
		}
		Deque<Employee> queue = new ArrayDeque<>();
		queue.addLast(employeeMap.get(id));
		while(!queue.isEmpty()) {
			Employee emp = queue.pollFirst();
			totImpVal += emp.importance;
			for(Integer subId: emp.subordinates) {
				employeeMap.put(subId, employeeMap.get(subId));
			}
		}
		return totImpVal;
	}
}
