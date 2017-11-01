package com.github.tddiaz;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by tristandiaz on 10/31/17.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public abstract class RepositoryTest {

    @Autowired
    protected TestEntityManager testEntityManager;

}
