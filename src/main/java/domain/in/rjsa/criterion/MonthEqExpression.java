package domain.in.rjsa.criterion;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Custom expression for filtering entities where MONTH(dateField) = given month.
 * 
 * Hibernate 6 + Spring Boot 3.x compatible (uses JPA Criteria API).
 */
public class MonthEqExpression {

    private final String propertyName;
    private final int month;

    public MonthEqExpression(String propertyName, int month) {
        this.propertyName = propertyName;
        this.month = month;
    }

    /**
     * Converts this expression into a Predicate.
     *
     * Equivalent SQL: extract(month from <propertyName>) = :month
     */
    public Predicate toPredicate(CriteriaBuilder cb, Root<?> root) {
        // Use SQL MONTH() function (portable across many DBs)
        Expression<Integer> monthExpression =
                cb.function("month", Integer.class, root.get(propertyName));
        return cb.equal(monthExpression, this.month);
    }

    @Override
    public String toString() {
        return "extract(month from " + propertyName + ") = " + month;
    }
}
