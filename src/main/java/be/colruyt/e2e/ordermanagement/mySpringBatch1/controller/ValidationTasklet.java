package be.colruyt.e2e.ordermanagement.mySpringBatch1.controller;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ValidationTasklet implements Tasklet {

//    private final ReadService readService;
//    private final WriteService writeService;
//    private final Validator validator;

    @Override
    public RepeatStatus execute(StepContribution sc, ChunkContext cc){

//        // read the input file
//        List<Customer> userList = readService.readFile("/path/to/input/file");
//
//        // validate the input
//        List<Customer> validUserList = userList.stream()
//                .map(customer -> {
//                    Set<ConstraintViolation<Customer> violations = validator.validate(customer);
//                    return violations.isEmpty() ? customer : null;
//                })
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//
//        // write the output file
//        writeService.writeFile(validUserList,"/path/to/output/file");

        return RepeatStatus.FINISHED;
    }
}