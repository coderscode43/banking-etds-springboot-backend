package domain.in.rjsa.confirguration;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class SequenceInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Value("${hibernate.dialect:}")
	private String dialect;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	if (dialect.toLowerCase().contains("oracle")) {
			updateSequences();
		}
    }

    private void updateSequences() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            String[][] sequencesAndTables = {
            		  /* TAXO */
					  {"TAXO.DEDUCTORDETAILSSEQUENCE","TAXO.DEDUCTORDETAILS"},
					  {"TAXO.DEFAULTSUMMARYSEQUENCE","TAXO.DEFAULTSUMMARY"},
					  {"TAXO.REQUESTSTATUSSEQUENCE","TAXO.REQUESTSTATUS"},
					  {"TAXO.STATEMENTSTATUSSEQUENCE","TAXO.STATEMENTSTATUS"},
					  {"TAXO.GH15RETURNSTATUSSEQUENCE","TAXO.GH15RETURNSTATUS"},
            		
					  /* FY */
					  {"AAACN4165C_2324.CHALLANDETAILSSEQUENCE","AAACN4165C_2324.CHALLANDETAILS"}, 
					  {"AAACN4165C_2324.G15SEQUENCE","AAACN4165C_2324.G15"},
					  {"AAACN4165C_2324.H15SEQUENCE","AAACN4165C_2324.H15"}, 
					  {"AAACN4165C_2324.LOGSSEQUENCE","AAACN4165C_2324.LOGS"},
					  {"AAACN4165C_2324.MONTHLYCHALLAN1SEQUENCE","AAACN4165C_2324.MONTHLYCHALLAN1"},
					  {"AAACN4165C_2324.PANUPDATELISTSEQUENCE","AAACN4165C_2324.PANUPDATELIST"},
					  {"AAACN4165C_2324.REGULAR24QDEDUCTEESEQUENCE","AAACN4165C_2324.REGULAR24QDEDUCTEE"},
					  {"AAACN4165C_2324.REGULAR26QDEDUCTEESEQUENCE","AAACN4165C_2324.REGULAR26QDEDUCTEE"},
					  {"AAACN4165C_2324.REGULAR27EQDEDUCTEESEQUENCE","AAACN4165C_2324.REGULAR27EQDEDUCTEE"},
					  {"AAACN4165C_2324.REGULAR27QDEDUCTEESEQUENCE","AAACN4165C_2324.REGULAR27QDEDUCTEE"},
					  {"AAACN4165C_2324.TOTALAMOUNTSEQUENCE","AAACN4165C_2324.TOTALAMOUNT"},
					  {"AAACN4165C_2324.DEDUCTEEREMARKSEQUENCE","AAACN4165C_2324.DEDUCTEEREMARK"},
					  
					  /* FORM */
					  {"AAACN4165C_FORM.ADDCHALLANSEQUENCE","AAACN4165C_FORM.ADDCHALLAN"},
					  {"AAACN4165C_FORM.BRANCHSEQUENCE","AAACN4165C_FORM.BRANCH"},
					  {"AAACN4165C_FORM.CORRECTIONREMARKSSEQUENCE","AAACN4165C_FORM.CORRECTIONREMARKS"},
					  {"AAACN4165C_FORM.CORRECTIONREQUESTSEQUENCE","AAACN4165C_FORM.CORRECTIONREQUEST"},
					  {"AAACN4165C_FORM.CORRECTIONREQUESTAMOUNTDETAILSSEQUENCE","AAACN4165C_FORM.CORRECTIONREQUESTAMOUNTDETAILS"},
					  {"AAACN4165C_FORM.EMPLOYEEMASTERSEQUENCE","AAACN4165C_FORM.EMPLOYEEMASTER"},
					  {"AAACN4165C_FORM.LOGINSEQUENCE","AAACN4165C_FORM.LOGIN"},
					  {"AAACN4165C_FORM.ORGANIZATIONDETAILSSEQUENCE","AAACN4165C_FORM.ORGANIZATIONDETAILS"},
					  {"AAACN4165C_FORM.PENSIONMASTERSEQUENCE","AAACN4165C_FORM.PENSIONMASTER"},
					  {"AAACN4165C_FORM.REGULARRETURNSEQUENCE","AAACN4165C_FORM.REGULARRETURN"},
					  {"AAACN4165C_FORM.REGULARRETURNREMARKSEQUENCE","AAACN4165C_FORM.REGULARRETURNREMARK"},
					  {"AAACN4165C_FORM.REMARKSSEQUENCE","AAACN4165C_FORM.REMARKS"},
					  {"AAACN4165C_FORM.RODETAILSSEQUENCE","AAACN4165C_FORM.RODETAILS"},
					  {"AAACN4165C_FORM.SUPPORTINGDOCUMENTSSEQUENCE","AAACN4165C_FORM.SUPPORTINGDOCUMENTS"},
					  {"AAACN4165C_FORM.TICKETSEQUENCE","AAACN4165C_FORM.TICKET"},
					  {"AAACN4165C_FORM.TICKETREMARKSEQUENCE","AAACN4165C_FORM.TICKETREMARK"},
					  {"AAACN4165C_FORM.VENDORMASTERSEQUENCE","AAACN4165C_FORM.VENDORMASTER"},
					  {"AAACN4165C_FORM.ZONALBRANCHESSEQUENCE","AAACN4165C_FORM.ZONALBRANCHES"},
					  {"AAACN4165C_form.UPLOADCERTIFICATESEQUENCE","AAACN4165C_form.UPLOADCERTIFICATE"}
            };
            for (String[] pair : sequencesAndTables) {
            	BigDecimal sequenceCount = BigDecimal.ZERO;
            	
                String sequenceName = pair[0];
                String tableName = pair[1];
                
                if(sequenceName.contains(".")) {
                	 String[] parts = sequenceName.split("\\.");
                     String owner = parts[0] ;
                     String name =  parts[1];
                     
                	 // Check if the sequence exists for seprate DB User //UAT
                	 sequenceCount = (BigDecimal) session.createNativeQuery(
                			"SELECT COUNT(*) FROM all_sequences WHERE sequence_name = UPPER(:sequenceName) AND sequence_owner = UPPER(:sequenceOwner)")
                			.setParameter("sequenceName", name)
                			.setParameter("sequenceOwner", owner)
                			.uniqueResult();
                }else {
                	// Check if the sequence exists for single DB User //PROD
                	 sequenceCount = (BigDecimal) session.createNativeQuery(
                			"SELECT COUNT(*) FROM user_sequences WHERE sequence_name = UPPER(:sequenceName)")
                			.setParameter("sequenceName", sequenceName)
                			.uniqueResult();
                }
                
                // If the sequence does not exist, create it
                if (sequenceCount.equals(BigDecimal.ZERO)) {
                    String createSequenceSql = "CREATE SEQUENCE " + sequenceName +
                        " INCREMENT BY 1 MINVALUE 1 MAXVALUE 9999999999999999999999999999 " +
                        "NOCYCLE CACHE 20 NOORDER";
                    session.createNativeQuery(createSequenceSql).executeUpdate();
                }

                // get Max Sequence Value
                BigDecimal maxId = (BigDecimal) session.createNativeQuery("SELECT MAX(ID) FROM " + tableName).uniqueResult();
                if (maxId == null) {
                    maxId = BigDecimal.ZERO;
                }

                BigDecimal incrementedId = maxId.add(BigDecimal.ONE);

                BigDecimal sequenceValue = (BigDecimal) session.createNativeQuery("SELECT " + sequenceName + ".NEXTVAL FROM dual").uniqueResult();

                if (incrementedId.compareTo(sequenceValue) != 0 && sequenceValue.compareTo(incrementedId) < 0) {
                    String sql = "ALTER SEQUENCE " + sequenceName + " RESTART START WITH " + incrementedId;
                    session.createNativeQuery(sql).executeUpdate();
                    session.flush();
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
