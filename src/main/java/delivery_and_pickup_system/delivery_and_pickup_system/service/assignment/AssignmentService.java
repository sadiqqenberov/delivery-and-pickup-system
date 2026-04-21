package delivery_and_pickup_system.delivery_and_pickup_system.service.assignment;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Assignment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.assignment.AssignmentDto;

import java.util.List;

public interface AssignmentService {

    AssignmentDto createAssignment(AssignmentDto assignmentDto);

    AssignmentDto findById(int id);

    AssignmentDto update(int id, AssignmentDto assignmentDto);

    List<AssignmentDto> getAssignmentsByCourierId(Integer courierId);

    List<Assignment> getMyAssignments();
}


