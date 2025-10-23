package domain.in.rjsa.service.impl;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import domain.in.rjsa.annotation.Validate;
import domain.in.rjsa.dao.DeducteeRemarkDao;
import domain.in.rjsa.exception.CustomException;
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
import in.rjsa.tdsExcelConvertor.util.MainClassToTestSingleLineValidation;
import jakarta.transaction.Transactional;

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
			validateData(entity);
			System.out.println(entity);
			DeducteeRemark remark = new DeducteeRemark();
			remark.setDEDUCTEEID(Long.valueOf(entity.get("id").toString()));
			remark.setADDEDBY(getPrincipal());
			remark.setDATETIME(new Date());

			remark.setSTATUS("Pending");
			createRemark(entity, remark);
			remark.setFY(entity.get("fy").toString());
			remark.setDEDUCTEEFORM(entity.get("challanHeading").toString());
			remark.setBRANCHCODE(entity.get("branchCode").toString());
			
			// Saving Updated JSON till Approval from Another Admin
			String jsonElement = mapper.writeValueAsString(entity);
			remark.setFORMDATA(jsonElement);

			dao.persist(remark);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void validateData(LinkedHashMap<String, Object> entity) {
		String validationJson = buildValidationJson(entity);
		MainClassToTestSingleLineValidation validateSingleLine = new MainClassToTestSingleLineValidation();
		JSONObject postValidationMsg = validateSingleLine.validateSingleLine(validationJson);

		if (postValidationMsg.containsKey("serverError")) {
			throw new CustomException("Error validating details: "+postValidationMsg.get("serverError"));
		} else {
			throw new CustomException("Error validating details");
		}
	}
	
	private String buildValidationJson(LinkedHashMap<String, Object> map) {
		Map<String, Object> lineMap = new HashMap<String, Object>();

		try {
			String challanHeding = map.get("challanHeading").toString();
			if (challanHeding.contains("24Q")) {
				lineMap.put("form", "24Q");
				lineMap.put("line", buildLine(map, Regular24QDeductee.class));
			} else if (challanHeding.contains("26Q")) {
				lineMap.put("form", "26Q");
				lineMap.put("line", buildLine(map, Regular26QDeductee.class));
			} else if (challanHeding.contains("27EQ")) {
				lineMap.put("form", "27EQ");
				lineMap.put("line", buildLine(map, Regular27EQDeductee.class));
			} else if (challanHeding.contains("27Q")) {
				lineMap.put("form", "27Q");
				lineMap.put("line", buildLine(map, Regular27QDeductee.class));
			} else {
				throw new CustomException("Invalid Challan Heading");
			}

			lineMap.put("fy", map.get("fy"));
			lineMap.put("pan", map.get("pan"));
			lineMap.put("name", map.get("name"));
			lineMap.put("tan", map.get("TAN"));
			lineMap.put("q", map.get("quarter"));
			lineMap.put("month", map.get("month"));
			lineMap.put("challanHeading", challanHeding);

			return mapper.writeValueAsString(lineMap);
		} catch (Exception e) {
			throw new CustomException("Error validating details");
		}
	}

	public static <T> String buildLine(LinkedHashMap<String, Object> map, Class<T> deductee) {
		StringBuilder line = new StringBuilder();

	    try {
	        Field[] fields = deductee.getDeclaredFields();

	        List<Field> orderedFields = Arrays.stream(fields)
	            .filter(f -> f.isAnnotationPresent(Validate.class) && f.getAnnotation(Validate.class).enabled())
	            .sorted(Comparator.comparingInt(f -> f.getAnnotation(Validate.class).order()))
	            .toList();

	        for (Field field : orderedFields) {
	            String key = field.getName();
	            Object value = map.get(key);
	            line.append(value != null ? value.toString() : "").append("^");
	        }

	        if (line.length() > 0) {
	            line.setLength(line.length() - 1);
	        }
	        return line.toString();

	    } catch (Exception e) {
	        throw new CustomException("Error validating details");
	    }
	}

	private void createRemark(LinkedHashMap<String, Object> newDeductee, DeducteeRemark remark) throws Exception {
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

		if (newDeductee.get("challanHeading").toString().equalsIgnoreCase("INTEREST_27EQ") || newDeductee.get("challanHeading").toString().equalsIgnoreCase("TCS_27EQ")) {
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
						
						newDeductee.put(key, getDate(newValue));
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
	
	private Date parseDate(Object dateObj) {
	    if (dateObj instanceof String) {
	        try {
	            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	            inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
	            return inputFormat.parse(dateObj.toString());
	        } catch (ParseException e) {
	            e.printStackTrace();
	            throw new RuntimeException(e);
	        }
	    } else if (dateObj instanceof Date) {
	        return (Date) dateObj;
	    } else {
	        throw new IllegalArgumentException("Unsupported date object type");
	    }
	}

	public Date getDate(Object dateObj) {
	    return parseDate(dateObj);
	}

	public String formatDate(Object dateObj) {
	    Date date = parseDate(dateObj);
	    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	    outputFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
	    return outputFormat.format(date);
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
