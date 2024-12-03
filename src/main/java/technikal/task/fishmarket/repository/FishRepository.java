package technikal.task.fishmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import technikal.task.fishmarket.entity.Fish;

import java.util.List;

public interface FishRepository extends JpaRepository<Fish, Long> {

    @Query("from Fish f left join fetch f.imageFileNames order by f.id desc")
    List<Fish> findAllWithFileNames();
}
