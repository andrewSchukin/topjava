package ru.javawebinar.topjava.to;

import org.hibernate.validator.constraints.Range;

import javax.persistence.Convert;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class MealToUI extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private LocalDateTime dateTime;

    @NotBlank
    @Size(min = 2, max = 120)
    private String description;

    @Range(min = 10, max = 5000)
    private int calories;

    public MealToUI() {
    }

    public MealToUI(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = LocalDateTime.parse(dateTime.replace(" ", "T"));
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(String calories) {
        this.calories = calories.length() > 0 ? Integer.valueOf(calories) : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealToUI that = (MealToUI) o;
        return calories == that.calories &&
                Objects.equals(id, that.id) &&
                Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateTime, description, calories);
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}