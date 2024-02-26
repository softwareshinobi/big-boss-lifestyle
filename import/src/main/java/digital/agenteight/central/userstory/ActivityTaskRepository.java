package digital.agenteight.central.userstory;

import digital.agenteight.central.userstory.ActivityTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActivityTaskRepository extends JpaRepository<ActivityTask, Integer> {

    Optional<ActivityTask> findByIdAllIgnoreCase(Integer id);

}