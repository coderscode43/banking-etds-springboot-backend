package domain.in.rjsa.criterion;

import org.hibernate.Criteria;
import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.engine.spi.TypedValue;



public class MonthEqExpression implements Criterion{
	private final String propertyName;
    private final Long month;

    public MonthEqExpression(String propertyName, Long month) {
        this.propertyName = propertyName;
        this.month = month;
    }

    @Override
    public String toSqlString(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        String[] columns = criteriaQuery.getColumns(propertyName, criteria);
        if (columns.length != 1) {
            throw new HibernateException("monthEq may only be used with single-column properties");
        }
        return "extract(month from " + columns[0] + ") = ?";
    }

    @Override
    public TypedValue[] getTypedValues(Criteria criteria, CriteriaQuery criteriaQuery) throws HibernateException {
        return new TypedValue[] {new TypedValue(criteriaQuery.getIdentifierType(criteria), month, EntityMode.POJO)};
    }

    @Override
    public String toString() {
        return "extract(month from " + propertyName + ") = " + month;
    }
}
