package br.com.kyw.project_kyw.repositories;

import br.com.kyw.project_kyw.application.repositories.UserRepository;
import br.com.kyw.project_kyw.core.entities.User;
import br.com.kyw.project_kyw.factory.UserFactory;
import jdk.jfr.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {


        @Autowired
        private UserRepository repository;

        private Long existsId;
        private Long notExistsId;
        private User category;

        @BeforeEach
        public void setUp() {
            existsId = 1L;
            notExistsId = 1000L;
            category = UserFactory.getUser();
        }

//        @Test
//        public void deleteShouldDeleteObjectWhenIdexist() {
//            repository.deleteById(existsId);
//
//            Optional<Category> obj = repository.findById(existsId);
//
//            Assertions.assertTrue(obj.isEmpty());
//        }
//
//        @Test
//        public void deleteShouldThrowsEmptyResultDataAccessExceptionWhenNotExistsId() {
//            Assertions.assertDoesNotThrow(() -> {
//                repository.deleteById(notExistsId);
//            });
//        }
//
//        @Test
//        public void findByIdShouldReturnObjectNotNullWhenExistsId() {
//            Optional<Category> obj = repository.findById(existsId);
//
//            Assertions.assertTrue(obj.isPresent());
//        }
//
//        @Test
//        public void findByIdShouldReturnObjectNullWhenExistsId() {
//            Optional<Category> obj = repository.findById(notExistsId);
//            Assertions.assertTrue(obj.isEmpty());
//        }
//
//        @Test
//        public void saveShouldPersistProductWhenIdIsNullAndAutoincrement() {
//            Category entity = repository.save(category);
//
//            Assertions.assertNotNull(entity);
//            Assertions.assertEquals(4L, entity.getId());
//        }
//
//        @Test
//        public void findAllShouldReturnPage() {
//            Page<Category> page = repository.findAll(PageRequest.of(0, 10));
//
//            Assertions.assertNotNull(page);
//        }
}
