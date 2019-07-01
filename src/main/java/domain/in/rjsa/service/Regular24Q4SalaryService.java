package domain.in.rjsa.service;

import domain.in.rjsa.model.form.Regular24Q4Salary;

public interface Regular24Q4SalaryService extends ServiceInterface<Long,Regular24Q4Salary> {
 public Regular24Q4Salary getByKey(Long id);
}
