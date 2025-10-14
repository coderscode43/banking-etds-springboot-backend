package domain.in.rjsa.controller;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import domain.in.rjsa.exception.CustomException;
import domain.in.rjsa.exception.FieldErrorDTO;
import domain.in.rjsa.model.form.AddChallan;
import domain.in.rjsa.model.form.CorrectionRequest;
import domain.in.rjsa.model.form.CorrectionRequestAmountDetails;
import domain.in.rjsa.model.form.ListCount;
import domain.in.rjsa.service.AddChallanService;
import domain.in.rjsa.service.BranchService;
import domain.in.rjsa.service.CorrectionRemarksService;
import domain.in.rjsa.service.CorrectionRequestAmountDetailsService;
import domain.in.rjsa.service.CorrectionRequestService;
import domain.in.rjsa.service.Regular24QDeducteeService;
import domain.in.rjsa.service.Regular26QDeducteeService;
import domain.in.rjsa.service.Regular27EQDeducteeService;
import domain.in.rjsa.service.Regular27QDeducteeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/apicorrectionRequest")
public class CorrectionRequestController
		extends AbstractControllerForm<Long, CorrectionRequest, CorrectionRequestService> {

	@Autowired
	CorrectionRequestService service;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	CorrectionRemarksService rService;
	@Autowired
	CorrectionRequestAmountDetailsService crService;
	@Autowired
	Regular24QDeducteeService r24qService;
	@Autowired
	Regular26QDeducteeService r26qService;
	@Autowired
	Regular27EQDeducteeService r27eqService;
	@Autowired
	Regular27QDeducteeService r27qService;

	@Autowired
	BranchService bService;

	@Autowired
	AddChallanService challanService;

	@Override
	public CorrectionRequestService getService() {
		// TODO Auto-generated method stub
		return service;
	}

	@Override
	public Class<CorrectionRequest> getEntity() {
		// TODO Auto-generated method stub
		return CorrectionRequest.class;
	}

	// ------------------- Get List ---------------------------------
	@GetMapping(value = "/list/{fy}/{branchCode}/count/")
	public ResponseEntity<?> count(@PathVariable String fy, @PathVariable Long branchCode, HttpServletRequest request) {
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
			}
			constrains.put("branchCode", b);
		} else {
		}
		constrains.put("branchCode", branchCode);
		try {
			Long count = getService().findallCount(constrains);
			List<?> list = getList(fy, branchCode, 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/list/{fy}/{branchCode}/get/{pageNo}/{resultPerPage}")
	public ResponseEntity<?> listAll(@PathVariable String fy, @PathVariable Long branchCode, HttpServletRequest request,
			@PathVariable int pageNo, @PathVariable int resultPerPage) {
		try {

			if (!"admin".equals(getBranchCode())) {
				Long b = 1L;
				try {
					b = Long.valueOf(getBranchCode());
				} catch (Exception e) {
				}
				branchCode = b;
			} else {
			}

			List<?> list = getList(fy, branchCode, pageNo, resultPerPage);

			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public List<?> getList(String fy, Long branchCode, int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
			}
			constrains.put("branchCode", b);
		} else {
		}
		// constrains.put("fy", fy);
		constrains.put("branchCode", branchCode);
		return getService().getList(constrains, pageNo, resultPerPage);
	}

	public List<?> getList(int pageNo, int resultPerPage) {
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
			}
			constrains.put("branchCode", b);
		}
		return getService().getList(constrains, pageNo, resultPerPage);
	}

	// ------------------- Get Detail ---------------------------------

	@GetMapping(value = "/detail/{fy}/{branchCode}/{id}")
	public ResponseEntity<?> getDetailController(@PathVariable Long id, @PathVariable String fy,
			@PathVariable Long branchCode) {
		// verify the clientId authorization
		try {
			return new ResponseEntity<>(getDetail(id, fy, branchCode), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getting detail", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public Object getDetail(Long id, String fy, Long branchCode) {
		HashMap<String, Object> constrains = new HashMap<>();
		if (!"admin".equals(getBranchCode())) {
			Long b = 1L;
			try {
				b = Long.valueOf(getBranchCode());
			} catch (Exception e) {
			}
			constrains.put("branchCode", b);
		}
		return getService().getDetail(constrains, id, getPrincipal(), branchCode);

	}

	// ------------------- Get Add ---------------------------------
	@PostMapping(value = "/add/{fy}/{branchCode}")
	@ResponseBody
	public ResponseEntity<?> createEntity(@RequestBody LinkedHashMap<String, Object> entity, @PathVariable String fy,
			@PathVariable String branchCode) {
		try {
			logger.info("Creating new Return instance");

			adminValidation(entity);
			create(entity);
			addLogs("Add");

			// ermsg.setMessage(" Saved Successfully");
			return new ResponseEntity<Object>(HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public void create(LinkedHashMap<String, Object> entity) {
		try {
			service.saveCorrection(entity, getPrincipal());
		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}

	}

	@SuppressWarnings({ "deprecation", "unused" })
	@PostMapping(value = "/addCorrection/singleFile")
	@ResponseBody
	public ResponseEntity<?> addCorrection(@RequestParam("blob") List<MultipartFile> listsd,
			@RequestParam("dec") String entity) {
		logger.info("Creating new Return instance");
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(entity, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			adminValidation(map);
//			JsonParser jsonParser = new JsonParser();
//			JsonObject jsonObject = (JsonObject) jsonParser.parse(entity.toString());
//			JsonObject jsonObject1 = (JsonObject) jsonObject.get("cd");
//			JsonObject jsonObject2 = (JsonObject) jsonObject.get("cad");
			// Parse JSON string into a JsonNode
			JsonNode rootNode = mapper.readTree(entity.toString());

			// Access nested objects
			JsonNode jsonObject1 = rootNode.get("cd");
			JsonNode jsonObject2 = rootNode.get("cad");
			HashMap<String, Object> fn = new HashMap<>();
			int i = 1;
			String fname = "";
			Long ticketNumber = service.autoGenerateTicketNumber();
			for (MultipartFile sd : listsd) {
				if (jsonObject2 != null) {
					if (jsonObject2.has("challanSupportingDoc")) {
						fn.put("cd", sd.getOriginalFilename());
					}
				} else if (jsonObject1 != null) {
					if (rootNode.has("docs")) {
						fname = fname + sd.getOriginalFilename() + "^";
						fn.put("sd", fname);
						i++;
					}
				}
				getService().saveFile(sd, entity, ticketNumber);
			}
			createWithFileName(entity, fn, ticketNumber);
//			getService().saveAmount(map);
			addLogs("Add");
			// ermsg.setMessage(" Saved Successfully");
			return new ResponseEntity<Object>(HttpStatus.CREATED);

		} catch (Exception e) {
			ermsg.setExceptionMsg(e.getMessage());
			ermsg.setEntityName("Correction Request");
			return new ResponseEntity<Object>(ermsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping(value = "/addCorrection/multipleFile")
	public ResponseEntity<?> addCorrection(@RequestParam("blob") List<MultipartFile> listsd,
			@RequestParam("blob2") MultipartFile cd, @RequestParam("dec") String entity) {
		logger.info("Creating new Return instance");
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			ObjectMapper mapper = new ObjectMapper();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			map = mapper.readValue(entity, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			adminValidation(map);
			HashMap<String, Object> fn = new HashMap<>();
			Long ticketNumber = service.autoGenerateTicketNumber();
			getService().saveMultiFile(listsd, cd, entity, ticketNumber);
			String fname = "";
			for (MultipartFile sd : listsd) {
				fname = fname + sd.getOriginalFilename() + "^";
			}
			fn.put("sd", fname);
			fn.put("cd", cd.getOriginalFilename());
			createWithFileName(entity, fn, ticketNumber);
//			getService().saveAmount(map);
			addLogs("Add");
			// ermsg.setMessage(" Saved Successfully");
			return new ResponseEntity<Object>(HttpStatus.CREATED);

		} catch (Exception e) {
			ermsg.setExceptionMsg(e.getMessage());
			ermsg.setEntityName("Correction Request");
			return new ResponseEntity<Object>(ermsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@SuppressWarnings("deprecation")
	public void createWithFileName(String entity, HashMap<String, Object> fileName, Long ticketNumber) {
		try {
			// Parse the JSON into a tree
			JsonNode rootNode = mapper.readTree(entity.toString());

			// Extract the "cd" part
			JsonNode cdNode = rootNode.get("cd");

			// Convert that node into your target type
			CorrectionRequest cr = mapper.treeToValue(cdNode, getEntity());
			cr.setCorrectionRequestDate(new Date());
			cr.setTicketNumber(ticketNumber);
			cr.setMakerBy(getPrincipal());
			cr.setStatus("Pending Checker Approval");
			cr.setRejectStatus(false);
			if (fileName.containsKey("sd")) {
				cr.setFileName(fileName.get("sd").toString());
			}
			String q = cr.getQuarter().substring(0, cr.getQuarter().toString().length() - 2);
			cr.setQuarter(q);
			String tos = cr.getTypeOfCorrection().substring(0, cr.getTypeOfCorrection().toString().length() - 2);
			cr.setTypeOfCorrection(tos);
			cr.setLastUpdatedOn(new Date());
			getService().save(cr);

			if (rootNode.has("correctAmount")) {
				ArrayNode jsonArray = (ArrayNode) rootNode.get("correctAmount");
				if (jsonArray.size() != 0) {
					String name = "";
					String pan = "";
					String quarter = "";
					for (JsonNode node : jsonArray) {
						 CorrectionRequestAmountDetails requestAmountDetails =
						            mapper.treeToValue(node, CorrectionRequestAmountDetails.class);
						requestAmountDetails.setCorrectionRequestId(cr.getId());
						crService.save(requestAmountDetails);
						if (!quarter.contains(requestAmountDetails.getQuarter())) {
							quarter = quarter + requestAmountDetails.getQuarter() + ", ";
						}
						if (cr.getName() == null || cr.getName() == "") {
							name = node.get("name").toString();
						}
						if (cr.getPan() == null || cr.getPan() == "") {
							if (!pan.contains(node.get("pan").toString())) {
								pan += node.get("pan").toString() + ", ";
							}
						}
					}
					if (quarter != null || quarter != "") {
						cr.setQuarter(quarter.substring(0, quarter.toString().length() - 2));
					}
					if (cr.getName() == null || cr.getName() == "") {
						cr.setName(name.replace("\"", ""));
					}
					if (cr.getPan() == null || cr.getPan() == "") {
						cr.setPan(pan.substring(0, pan.toString().length() - 2).replace("\"", ""));
					}
					getService().update(cr);
				}
			}

			if (rootNode.has("cad")) {
				ObjectNode dataObject = (ObjectNode) rootNode.get("cad");
				if (!dataObject.isNull()) {
					AddChallan cad = mapper.treeToValue(dataObject, AddChallan.class);
					cad.setCorrectionRequestId(cr.getId());
					if (dataObject.has("challanSupportingDoc")) {
						if (fileName.containsKey("cd")) {
							cad.setChallanSupportingDocument(fileName.get("cd").toString());
						}
					}
					challanService.save(cad);
				}
			}
			
			try {
				getService().sendMail(cr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("done");

		} catch (Exception e) {
			throw new CustomException(e.getMessage());
		}
	}

	@PostMapping(value = "/regenerateCorrectionRequest")
	@ResponseBody
	public ResponseEntity<?> regenerateCorrectionRequest(@RequestParam("crId") Long correctionRequestId,
			@RequestParam("crRemark") String correctionRequestremark) {
		logger.info("Creating new Return instance");
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			CorrectionRequest cr = getService().getByKey(correctionRequestId);
			Long ticketNumber = service.autoGenerateTicketNumber();
			cr.setTicketNumber(ticketNumber);
			String remark = "Old Ticket Response : "+ cr.getRemark().toString() + "; Latest Ticket Response : " + correctionRequestremark ;
			cr.setRemark(remark);
			cr.setId(null);
			cr.setStatus("Pending Checker Approval");
			cr.setCheckerApprovedBy(null);
			cr.setCheckerApprovedOn(null);
			cr.setRejectStatus(false);
			cr.setRegenarateRequest(false);
			getService().save(cr);
			CorrectionRequest crr = getService().getByKey(correctionRequestId);
			updateOldcr(crr,ticketNumber);
			List<CorrectionRequestAmountDetails> cradList = crService.getByCorrectionId(correctionRequestId);
			if (!cradList.isEmpty()) {
				for (CorrectionRequestAmountDetails cd : cradList) {
					cd.setCorrectionRequestId(cr.getId());
					crService.save(cd);
				}
			}
			AddChallan ac = challanService.getByCorrectionId(correctionRequestId);
			if (ac != null) {
				ac.setCorrectionRequestId(cr.getId());
				ac.setId(null);
				challanService.save(ac);
			}
			
			try {
				getService().sendMail(cr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			addLogs("Add");
			// ermsg.setMessage(" Saved Successfully");
			return new ResponseEntity<Object>(HttpStatus.CREATED);

		} catch (Exception e) {
			ermsg.setExceptionMsg(e.getMessage());
			ermsg.setEntityName("Correction Request");
			return new ResponseEntity<Object>(ermsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private void updateOldcr(CorrectionRequest cr, Long ticketNumber) {
		cr.setRegenarateRequest(true);
		cr.setNewRequestTicketNo(ticketNumber);
		service.update(cr);
		
	}

	// ------------------- Get Search ---------------------------------
	@GetMapping(value = "/search/{fy}/{branchCode}/{pageNo}/{resultPerPage}/{json}/**")
	public ResponseEntity<?> search(HttpServletRequest request, @PathVariable String fy, @PathVariable Long branchCode,
			@PathVariable int pageNo, @PathVariable int resultPerPage, @PathVariable String json) {
		try {
			final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
			final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
					.toString();

			String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

			String searchParam;
			if (null != arguments && !arguments.isEmpty()) {
				String decodedString = URLDecoder.decode(arguments, "UTF-8");
				decodedString = decodedString.replace(", \"", "\"");
				searchParam = json + '/' + decodedString;
			} else {
				searchParam = json;
			}
			ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(searchParam, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			adminValidation(map);
			// map.put("fy", fy);
			map.put("branchCode", branchCode);
			Long count = getService().findallCount(map);
			List<?> list = getSearch(map, 0, 100);
			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);
			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// ------------------- Search Table ---------------------------------
	@GetMapping(value = "/searchTable/get/{pageNo}/{resultPerPage}/{json}/**")
	public ResponseEntity<?> searchTable(@PathVariable String json, HttpServletRequest request,
			@PathVariable int pageNo, @PathVariable int resultPerPage) {
		try {
			final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
			final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE)
					.toString();

			String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);

			String searchParam;

			if (null != arguments && !arguments.isEmpty()) {
				String decodedString = URLDecoder.decode(arguments, "UTF-8");
				decodedString = decodedString.replace(", \"", "\"");
				searchParam = json + '/' + decodedString;
			} else {
				searchParam = json;
			}
			ObjectMapper mapper = new ObjectMapper();

			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();

			// convert JSON string to Map
			map = mapper.readValue(searchParam, new TypeReference<LinkedHashMap<String, Object>>() {
			});
			if (map.containsKey("branchCode")) {
				Long branchCode = Long.valueOf((String) map.get("branchCode"));
				map.put("branchCode", branchCode);
			}
			if (map.containsKey("TAN")) {
				String TAN = (map.get("TAN").toString().split(Pattern.quote("-"), -1))[0];
				map.put("TAN", TAN);
			}
			adminValidation(map);
			Long count = 0L;
			List<?> list = new ArrayList<>();
			if (map.containsKey("typeOfForm")) {
				if (map.get("typeOfForm").toString().trim().equals("24Q-Salary")) {
					map.remove("typeOfForm");
					map.remove("typeOfCorrection");
					map.remove("mobileNumber");
					map.remove("remark");
					if (map.get("empNo") != "" && map.get("empNo") != null) {
						map.put("custVendId", map.get("empNo"));
					}
					if (map.get("tanOfCust") == "" || map.get("tanOfCust") == null) {
						map.remove("tanOfCust");
					}
					map.remove("empNo");
					count = r24qService.findallCount(map);
					list = r24qService.search(map, pageNo, resultPerPage);
				} else if (map.get("typeOfForm").toString().trim().equals("26Q-Other than Salary")) {
					map.remove("typeOfForm");
					map.remove("typeOfCorrection");
					map.remove("mobileNumber");
					map.remove("remark");
					if (map.get("empNo") != "" && map.get("empNo") != null) {
						map.put("custVendId", map.get("empNo"));
					}
					if (map.get("tanOfCust") == "" || map.get("tanOfCust") == null) {
						map.remove("tanOfCust");
					}
					map.remove("empNo");
					count = r26qService.findallCount(map);
					list = r26qService.search(map, pageNo, resultPerPage);
				} else if (map.get("typeOfForm").toString().trim().equals("27Q-Other than Salary/NRI")) {
					map.remove("typeOfForm");
					map.remove("typeOfCorrection");
					map.remove("mobileNumber");
					map.remove("remark");
					if (map.get("empNo") != "" && map.get("empNo") != null) {
						map.put("custVendId", map.get("empNo"));
					}
					if (map.get("tanOfCust") == "" || map.get("tanOfCust") == null) {
						map.remove("tanOfCust");
					}
					map.remove("empNo");
					count = r27qService.findallCount(map);
					list = r27qService.search(map, pageNo, resultPerPage);
				} else if (map.get("typeOfForm").toString().trim().equals("27EQ-TCS")) {
					map.remove("typeOfForm");
					map.remove("typeOfCorrection");
					map.remove("mobileNumber");
					map.remove("remark");
					if (map.get("empNo") != "" && map.get("empNo") != null) {
						map.put("custVendId", map.get("empNo"));
					}
					if (map.get("tanOfCust") == "" || map.get("tanOfCust") == null) {
						map.remove("tanOfCust");
					}
					map.remove("empNo");
					count = r27eqService.findallCount(map);
					list = r27eqService.search(map, pageNo, resultPerPage);
				}
			}

			ListCount send = new ListCount();
			send.setCount(count);
			send.setEntities(list);

			return new ResponseEntity<>(send, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in listALL", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping(value = "/downloadDoc/{id}")
	public ResponseEntity<?> downloadDocument(HttpServletResponse response, @PathVariable Long id) {
		FieldErrorDTO ermsg = new FieldErrorDTO();
		try {
			service.downloadDocument(id, response);
			ermsg.setSuccessMsg("File downloaded successfully");
			return new ResponseEntity<>(ermsg, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			ermsg.setEntityName("Upload Document");
			ermsg.setExceptionMsg(e.getMessage());
			return new ResponseEntity<>(ermsg, HttpStatus.BAD_REQUEST);
		}

	}
}
