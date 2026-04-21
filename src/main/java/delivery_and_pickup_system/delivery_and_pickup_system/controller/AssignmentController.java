package delivery_and_pickup_system.delivery_and_pickup_system.controller;

import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Assignment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.assignment.AssignmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.service.assignment.AssignmentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AssignmentController {

    private final AssignmentService assignmentService;

    @PostMapping
    public ResponseEntity<AssignmentDto> assignShipment(@RequestBody AssignmentDto assignmentDto) {
        return ResponseEntity.ok(assignmentService.createAssignment(assignmentDto));
    }

    @PutMapping("/update/{id}")
    public AssignmentDto updateShipment(@PathVariable("id") int id, @RequestBody AssignmentDto assignmentDto) {
        return assignmentService.update(id,assignmentDto);
    }

    @GetMapping("{id}")
    public AssignmentDto findById(@PathVariable("id") int id) {
        return assignmentService.findById(id);
    }

    @GetMapping("/courier/{courierId}")
    public List<AssignmentDto> getAssignmentsByCourier(@PathVariable Integer courierId) {
        return assignmentService.getAssignmentsByCourierId(courierId);
    }

    @GetMapping("/my")
    public List<Assignment> getMyAssignments() {
        return assignmentService.getMyAssignments();
    }
}