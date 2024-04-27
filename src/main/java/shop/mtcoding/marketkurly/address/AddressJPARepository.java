package shop.mtcoding.marketkurly.address;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AddressJPARepository extends JpaRepository<Address, Integer> {

    List<Address> findByUserId(Integer userId);

    @Modifying
    @Query(value = "update ADDRESS_TB set IS_DEFAULT_ADDRESS = false", nativeQuery = true)
    void updateDefaultAddress();

    @Query(value = "select * from ADDRESS_TB WHERE USER_ID = :id AND IS_DEFAULT_ADDRESS = TRUE", nativeQuery = true)
    Optional<Address> 기본배송지찾기(@Param("id") Integer memberId);

}
