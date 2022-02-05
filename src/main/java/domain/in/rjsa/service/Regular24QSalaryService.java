package domain.in.rjsa.service;

import domain.in.rjsa.model.fy.Regular24QSalary;

public interface Regular24QSalaryService extends ServiceInterfaceFY<Long,Regular24QSalary> {
 public Regular24QSalary getByKey(Long id);
}
