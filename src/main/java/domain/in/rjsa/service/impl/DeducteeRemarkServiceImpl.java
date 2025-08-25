package domain.in.rjsa.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TimeZone;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.dao.DeducteeRemarkDao;
import domain.in.rjsa.model.fy.DeducteeRemark;
import domain.in.rjsa.model.fy.Regular24QDeductee;
import domain.in.rjsa.model.fy.Regular26QDeductee;
import domain.in.rjsa.model.fy.Regular27EQDeductee;
import domain.in.rjsa.model.fy.Regular27QDeductee;
import domain.in.rjsa.service.AbstractServiceForm;
import domain.in.rjsa.service.DeducteeRemarkService;
import domain.in.rjsa.service.Regular24QDeducteeService;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.Regular27EQDeducteeService;
import domain.in.rjsa.service.Regular27QDeducteeService;

@Transactional
@Service("DeducteeRemarkService")
public class DeducteeRemarkServiceImpl extends AbstractServiceForm<Long, DeducteeRemark, DeducteeRemarkDao>
		implements DeducteeRemarkService {

	@Autowired
	DeducteeRemarkDao dao;

	@Autowired
	Regular24QDeducteeService regular24QDeducteeService;

	@Autowired
	Regular26QDeducteeService regular26QDeducteeService;

	@Autowired
	Regular27EQDeducteeService regular27EQDeducteeService;

	@Autowired
	Regular27QDeducteeService regular27QDeducteeService;

	@Override
	public String createUserExcel(LinkedHashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> search(LinkedHashMap<String, Object> map, int pageNo, int resultPerPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DeducteeRemarkDao getPrimaryDao() {
		// TODO Auto-generated method stub
		return dao;
	}

	@Override
	public void save(LinkedHashMap<String, Object> entity) {
		try {
			System.out.println(entity);
			DeducteeRemark remark = new DeducteeRemark();
			remark.setDEDUCTEEID(Long.valueOf(entity.get("id").toString()));
			remark.setADDEDBY(getPrincipal());
			remark.setDATETIME(new Date());

			// Saving Updated JSON till Approval from Another Admin
			String jsonElement = mapper.writeValueAsString(entity);
			remark.setFORMDATA(jsonElement);

			remark.setSTATUS("Pending");
			createRemark(entity, remark);
			remark.setFY(entity.get("fy").toString());
			remark.setDEDUCTEEFORM(entity.get("challanHeading").toString());
			remark.setBRANCHCODE(entity.get("branchCode").toString());

			dao.persist(remark);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void createRemark(LinkedHashMap<String, Object> newDeductee, DeducteeRemark remark) {
		ObjectMapper mapper = new ObjectMapper();
		LinkedHashMap<String, Object> old_deductee = null;

		if (newDeductee.get("challanHeading").toString().equalsIgnoreCase("INTEREST_24Q")) {
			Regular24QDeductee OLDdeductee = regular24QDeducteeService
					.getByKey(Long.valueOf(newDeductee.get("id").toString()));

			OLDdeductee.setResolved(true);
			regular24QDeducteeService.update(OLDdeductee);

			old_deductee = mapper.convertValue(OLDdeductee, new TypeReference<LinkedHashMap<String, Object>>() {
			});

		}

		if (newDeductee.get("challanHeading").toString().equalsIgnoreCase("INTEREST_26Q")) {
			Regular26QDeductee OLDdeductee = regular26QDeducteeService
					.getByKey(Long.valueOf(newDeductee.get("id").toString()));

			OLDdeductee.setResolved(true);
			regular26QDeducteeService.update(OLDdeductee);

			old_deductee = mapper.convertValue(OLDdeductee, new TypeReference<LinkedHashMap<String, Object>>() {
			});

		}

		if (newDeductee.get("challanHeading").toString().equalsIgnoreCase("INTEREST_27EQ")) {
			Regular27EQDeductee OLDdeductee = regular27EQDeducteeService
					.getByKey(Long.valueOf(newDeductee.get("id").toString()));

			OLDdeductee.setResolved(true);
			regular27EQDeducteeService.update(OLDdeductee);

			old_deductee = mapper.convertValue(OLDdeductee, new TypeReference<LinkedHashMap<String, Object>>() {
			});

		}

		if (newDeductee.get("challanHeading").toString().equalsIgnoreCase("INTEREST_27Q")) {
			Regular27QDeductee OLDdeductee = regular27QDeducteeService
					.getByKey(Long.valueOf(newDeductee.get("id").toString()));

			OLDdeductee.setResolved(true);
			regular27QDeducteeService.update(OLDdeductee);

			old_deductee = mapper.convertValue(OLDdeductee, new TypeReference<LinkedHashMap<String, Object>>() {
			});

		}

		StringBuilder remarkBuilder = new StringBuilder();

		for (String key : newDeductee.keySet()) {
			if (key.equalsIgnoreCase("resolved")) {
				continue;

			} else {
				Object newValue = newDeductee.get(key);
				Object oldValue = old_deductee.get(key);

				String newValueStr = (newValue != null) ? newValue.toString() : null;
				String oldValueStr = (oldValue != null) ? oldValue.toString() : null;

				if (oldValueStr != null && oldValueStr.endsWith(".0")) {
					oldValueStr = oldValueStr.substring(0, oldValueStr.length() - 2); // Remove ".0"
				}

				if ((oldValueStr == null && newValueStr != null)
						|| (oldValueStr != null && !oldValueStr.equals(newValueStr))) {
					if (key.equals("dateOfDeduction") || key.equals("dateOfPayment") 
							|| key.equals("ifAnswerto681AisyesthenDateofpaymentofTDStoCentralGovernment")) {
						newValueStr = formatDate(newValue);
					}
					addRemark(remarkBuilder, key, oldValueStr, newValueStr);
				}
			}
		}

		if (remarkBuilder.length() > 0) {
			System.out.println(remarkBuilder.toString());
			remark.setREMARK(remarkBuilder.toString());
		}
	}

	public String formatDate(Object dateObj) {
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		format.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

		Date date = null;
		if (dateObj instanceof String) {
			try {
				SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
				inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
				date = inputFormat.parse(dateObj.toString());

				return format.format(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new RuntimeException();
			}
		} else if (dateObj instanceof Date) {
			date = (Date) dateObj;
			return new SimpleDateFormat(dateFormat).format(date);
		} else {
			throw new IllegalArgumentException("Unsupported date object type");
		}

	}

	public void addRemark(StringBuilder remarkBuilder, String key, String oldValueStr, String newValueStr) {
		if (remarkBuilder.length() > 0) {
			remarkBuilder.append(" | "); // Add separator only if remarkBuilder is not empty
		}
		remarkBuilder.append("Old ").append(key).append(": ").append(oldValueStr).append(" - New ").append(key)
				.append(": ").append(newValueStr);
	}

	public String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;

	}

}
