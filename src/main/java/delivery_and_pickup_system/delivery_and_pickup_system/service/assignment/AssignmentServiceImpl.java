package delivery_and_pickup_system.delivery_and_pickup_system.service.assignment;

import delivery_and_pickup_system.delivery_and_pickup_system.exception.BaseException;
import delivery_and_pickup_system.delivery_and_pickup_system.mapper.AssignmentMapper;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Assignment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.Shipment;
import delivery_and_pickup_system.delivery_and_pickup_system.model.base.User;
import delivery_and_pickup_system.delivery_and_pickup_system.model.dto.assignment.AssignmentDto;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.AssignmentRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.ShipmentRepository;
import delivery_and_pickup_system.delivery_and_pickup_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static delivery_and_pickup_system.delivery_and_pickup_system.constans.TokenConstants.EMAIL_KEY;
import static delivery_and_pickup_system.delivery_and_pickup_system.constans.TokenConstants.ID_KEY;


@Service
@RequiredArgsConstructor
@Slf4j
public class AssignmentServiceImpl implements AssignmentService {


    private final AssignmentRepository assignmentRepository;
    private final ShipmentRepository shipmentRepository;
    private final UserRepository userRepository;
    private final AssignmentMapper assignmentMapper;

    @Override
    public AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Shipment shipment = shipmentRepository.findById(assignmentDto.getShipmentId())
                .orElseThrow(BaseException::shipmentNotFound);

        User courier = userRepository.findById(assignmentDto.getCourierId())
                .orElseThrow(BaseException::shipmentNotFound);

        Assignment assignment = Assignment.builder()
                .shipment(shipment)
                .courier(courier)
                .assignedAt(LocalDateTime.now())
                .build();

        assignmentRepository.save(assignment);

        return assignmentDto;
    }

    @Override
    public AssignmentDto findById(int id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> BaseException.notFound(User.class.getSimpleName(), ID_KEY, id));

        return assignmentMapper.toDto(assignment);
    }

    @Override
    public AssignmentDto update(int id, AssignmentDto assignmentDto) {
        Assignment assignment = assignmentRepository.findByCourierId(id);

        assignmentMapper.updateFromDto(assignmentDto, assignment);

        Assignment updatedAssignment = assignmentRepository.save(assignment);

        return assignmentMapper.toDto(updatedAssignment);
    }

    @Override
    public List<AssignmentDto> getAssignmentsByCourierId(Integer courierId) {
        return assignmentMapper.toDtoList(
                assignmentRepository.findAllByCourierId(courierId)
        );
    }

    @Override
    public List<Assignment> getMyAssignments() {

        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> BaseException.notFound(User.class.getSimpleName(), EMAIL_KEY, email));

        return assignmentRepository.findAllByCourierId(user.getId());
    }

}
