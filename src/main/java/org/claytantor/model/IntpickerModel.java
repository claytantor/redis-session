package org.claytantor.model;

import com.google.gson.annotations.Expose;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * Created by claytongraham on 5/24/18.
 */
@ApiModel(value = "IntpickerModel", description = "A set of integers to test as a sum.")
public class IntpickerModel {

    @Expose
    private List<Integer> items;

    @Expose
    private Integer sum;

    @Expose
    private  Integer count;

    public IntpickerModel(){};

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public IntpickerModel(List<Integer> items, Integer count, Integer sum) {
        this.items = items;
        this.count = count;
        this.sum = sum;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
