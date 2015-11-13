package com.almeida.repository;

import com.almeida.entity.user.Authorities;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by theo on 12/11/15.
 */
public interface AuthorityRepository  extends CrudRepository<Authorities, String> {
}
