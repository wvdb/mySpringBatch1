package be.colruyt.e2e.ordermanagement.mySpringBatch1.job;

import be.colruyt.e2e.ordermanagement.mySpringBatch1.model.wim.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class CustomerWriter implements ItemWriter<Customer> {

    String fileserverJobParam;
    String applicatieJobParam;

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        JobParameters jobParameters = stepExecution.getJobExecution().getJobParameters();
        fileserverJobParam = jobParameters.getString( "fileserver" );
        applicatieJobParam = jobParameters.getString( "applicatie" );
    }

    @Override
    public void write(List<? extends Customer> customers)  {

        for (Customer customer : customers) {
            if (customer.getCustomerId() >= 10 && customer.getCustomerId() >= 15) {
                // TODO
            }
        }
    }

//    private String getFileName(TransportPlanningReportData transportPlanningReportData) {
//        return transportPlanningReportData.getTripNumber().concat( "_" )
//                .concat( transportPlanningReportData.getOrderNumber() )
//                .concat( "_" )
//                .concat( transportPlanningReportData.getClientName() )
//                .concat( ".pdf" );
//    }
}
