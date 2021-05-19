package learn.inventory.data;

import learn.inventory.models.PlatformFee;

import java.util.List;

public interface PlatformFeeRepository {
    PlatformFee findById(int platformFeeId);

    List<PlatformFee> findAll();

    PlatformFee add(PlatformFee platformFee);

    boolean update(PlatformFee platformFee);

    boolean deleteById(int platformFeeId);
}
