package org.claytantor.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.claytantor.model.IntpickerModel;
import org.claytantor.service.IntegerPickerService;
import org.claytantor.service.ServiceExcpetion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by claytongraham on 5/24/18.
 */
@RestController
public class IntpickerController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private IntegerPickerService integerPickerService;


    @ApiOperation(value = "Find integers that equal sum",
            notes = "Retrieving the model for the sum of integers.", response = IntpickerModel.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success", response = IntpickerModel.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = Map.class)
    })
    @RequestMapping(method = RequestMethod.POST, produces = "application/json", value="/api/intpicker")
    public ResponseEntity<?> items(@RequestBody IntpickerModel request) {

        logger.debug("IntpickerController.items.POST called.");

        try {
            return new ResponseEntity<IntpickerModel>(
                    integerPickerService.pickIntegers(
                            request.getItems(), request.getCount(), request.getSum()), HttpStatus.OK);
        } catch (ServiceExcpetion serviceExcpetion) {
            logger.info("there was a BL error attempting to count the integers.");
            Map<String, String> errorModel = new HashMap<>();
            errorModel.put("message",serviceExcpetion.getMessage());
            return new ResponseEntity<Map<String,String>>(errorModel, HttpStatus.NOT_ACCEPTABLE);

        }
    }
}
