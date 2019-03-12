package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Meal u WHERE u.id = :id and u.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Optional<Meal> findById(Integer integer);

    @Query("SELECT u FROM Meal u WHERE u.user.id = :userId ORDER BY u.dateTime DESC")
    List<Meal> findAll(@Param("userId") int userID);

    @Override
    @Transactional
    Meal save(Meal meal);

    @Query("SELECT u FROM Meal u WHERE u.dateTime BETWEEN :startDateTime and :endDateTime and u.user.id = :userId ORDER BY u.dateTime DESC")
    List<Meal> findAllBetween(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTIme, @Param("userId") int userId);

}
