package org.claytantor.service;

import org.claytantor.model.IntpickerModel;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by claytongraham on 5/25/18.
 */
@Component
public class IntegerPickerService {

    /**
     * this is a hash lookup for values.
     * @param items
     * @return
     */
    public Map<Integer,Integer> makeIntegerHash(List<Integer> items){

        Map<Integer,Integer> result = new HashMap<>();
        for (int i = 0; i < items.size(); i++) {
            result.put(items.get(i),i);
        }
        return result;
    }

    /**
     * given a list of integers, the count, and the sum, compute the values
     * that can add up to the sum
     *
     * @param items
     * @param sum
     * @return
     */
    public IntpickerModel pickIntegers(List<Integer> items, Integer count, Integer sum) throws ServiceExcpetion {

        if(count!=2)
            throw new ServiceExcpetion("Throwing lame exception, needs to be a count of two, we really should type this better.");

        Map<Integer,Integer> lookup = makeIntegerHash(items);
        for(Integer keyval:lookup.keySet()){
            //try to for the value where sum-key exists
            Integer remainder = sum-keyval.intValue();
            if(lookup.get(remainder) != null){
                Integer[] intArray = { lookup.get(keyval), lookup.get(remainder) };
                return new IntpickerModel(Arrays.asList(intArray), count, sum);
            }
        }

        throw new ServiceExcpetion("Throwing BL exception. Could not find a match.");

    }

}
