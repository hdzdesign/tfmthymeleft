package chc.tfm.udt.repositorios;

import chc.tfm.udt.entidades.DonacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "iDonacionDAO")
public interface IDonacionDAO extends JpaRepository<DonacionEntity, Long> {
}
