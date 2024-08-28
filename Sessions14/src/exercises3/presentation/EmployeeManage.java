package exercises3.presentation;

import business.entity.Department;
import business.entity.Employee;
import business.feature.IEmployeeFeature;
import business.feature.impl.DepartmentFeatureImpl;
import business.feature.impl.EmployeeFeatureImpl;

import java.util.*;
import java.util.stream.Stream;

public class EmployeeManage
{
	IEmployeeFeature employeeFeature= new EmployeeFeatureImpl();
	public void menuEmployee(Scanner scanner)
	{
		boolean isCheck=true;
		do {
			System.out.println("""
				====================================== MENU ======================================
					1. Hiển thị danh sách thông tin tất cả nhân viên(mã nhân viên và tên)
					2. Xem chi tiết thông tin nhân viên theo mã nhân viên (toàn bộ thông tin)
					3. Thêm mới nhân viên
					4. Chỉnh sửa thông tin nhân viên
					5. Xóa nhân viên
					6. Thống kê số lượng nhân viên trung bình của mỗi phòng
					7. Tìm ra 5 phòng có số lượng nhân viên đông nhất
					8. Tìm ra người quản lý nhiều nhân viên nhất
					9. Tìm ra 5 nhân viên có tuổi cao nhất công ty
					10.Tìm ra 5 nhân viên hưởng lương cao nhất
					11.Quay lai
				==================================================================================
				  """);
			int choice = Integer.parseInt(scanner.nextLine());
			switch (choice)
			{
				case 1:
				{
					showEmployeeNameandId();
					break;
				}
				case 2:
				{
					showDetailEmployeeForId(scanner);
					break;
				}
				case 3:
				{
					addNewEmployee(scanner);
					break;
				}
				case 4:
				{
					editEmployee(scanner);
					break;
				}
				case 5:
				{
					deleteEmployee(scanner);
					break;
				}
				case 6:
				{
					statisticalAvageNumberEmp();
					break;
				}
				case 7:
				{

					findTop5Departments();
					break;
				}
				case 8:
				{
					SortAgeTop5();
					break;
				}
				case 9:
				{
					SortSalaryTop5();
					break;
				}
				case 10:
				{
					calculateAverageMembers();
					break;
				}
				case 11:
				{
					isCheck = false;
					break;
				}
				default:
					System.err.println("Nhập lại từ 1 -> 11 đê");
			}

		}while (isCheck);
		}

	private void calculateAverageMembers() {
		OptionalDouble averageMember=DepartmentFeatureImpl.departments.stream()
				.mapToInt(Department::getTotalMembers)
				.average();
	}

	private void findTop5Departments() {
		Stream<Department> limit5= DepartmentFeatureImpl.departments.stream()
				.sorted((d1, d2) -> Integer.compare(d2.getTotalMembers(), d1.getTotalMembers()))
				.limit(5);
		limit5.forEach(Department::displayData);

	}

	private void SortAgeTop5() {
		System.out.println("5 nhân viên có tuổi cao nhất công ty");
		Stream<Employee> limitAge5= EmployeeFeatureImpl.employees.stream().sorted(Comparator.comparing(Employee::getBirthday).reversed()).limit(5);
		limitAge5.forEach(Employee::displayData);
	}

	private void SortSalaryTop5() {
		System.out.println("5 nhân viên có lương cao nhất công ty");
		Stream<Employee> limitSalary5= EmployeeFeatureImpl.employees.stream()
				.sorted(Comparator.comparingDouble(Employee::getSalary)
						.reversed()).limit(5);
		limitSalary5.forEach(Employee::displayData);


	}

	private void statisticalAvageNumberEmp() {
		System.out.println("Danh sách các phòng ban: ");
		DepartmentFeatureImpl.departments.forEach(Department::displayData);

	}

	private void deleteEmployee(Scanner scanner) {
		System.out.println("Nhập vào mã nhân viên cần xóa: ");
		String empId= scanner.nextLine();
		boolean isExist= EmployeeFeatureImpl.employees.stream()
				.anyMatch(item-> item.getEmployeeId().equalsIgnoreCase(empId));
		if (!isExist){
			System.err.println("Không tồn tại mã nhân viên: "+empId);
			return;
		}else {
			employeeFeature.delete(empId);
		}

	}

	private void editEmployee(Scanner scanner) {
		System.out.println("Nhập vào mã nhân viên cần sửa: (Exxxx ");
		String empId= scanner.nextLine();
		int indexUpdate= employeeFeature.findIndexById(empId);
		if (indexUpdate==-1){
			System.err.println("Không tồn tại mã nhân viên tìm kiếm");
		}
		Employee oldEmp= EmployeeFeatureImpl.employees.get(indexUpdate);
		employeeFeature.addOrUpdate(oldEmp);

	}

	private void addNewEmployee(Scanner scanner) {
		System.out.println("Bạn muốn thêm bao nhiêu nhân viên ");
		int number= Integer.parseInt(scanner.nextLine());
		for (int i = 0; i < number; i++) {
			Employee employee= new Employee();
			employee.inputData(scanner);
			employeeFeature.addOrUpdate(employee);
		}
	}

	private void showDetailEmployeeForId(Scanner scanner) {
		if (EmployeeFeatureImpl.employees.isEmpty()){
			System.err.println("Danh sách trống");
			return;
		}
		System.out.println("Nhập vào mã nhân viên");
		String empId = scanner.nextLine();
		List<Employee> employees= EmployeeFeatureImpl.employees.stream()
				.filter(item-> item.getEmployeeId().equalsIgnoreCase(empId)).toList();
		if (employees.isEmpty()){
			System.err.println("Không tìm thấy nhân viên nào");
			return;
		}
		employees.forEach(Employee::displayData);

	}

	private void showEmployeeNameandId() {
		if (EmployeeFeatureImpl.employees.isEmpty()){
			System.err.println("Danh sách trống");
			return;
		}
		EmployeeFeatureImpl.employees.forEach(emp-> System.out.printf("ID: %s |Name: %s",emp.getEmployeeId(),emp.getEmployeeName()));

	}
}
