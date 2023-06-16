package com.carpark.accounts.repositories;

import com.carpark.accounts.models.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountsRepository extends CrudRepository<Accounts, Long> {
    Optional<Accounts> findById(Long id);
}
