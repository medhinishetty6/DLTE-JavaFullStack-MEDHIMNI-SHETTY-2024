package spring.jpa.demo;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<AccountInformation, Long> {

}
