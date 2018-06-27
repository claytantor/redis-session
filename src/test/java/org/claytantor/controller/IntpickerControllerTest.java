package org.claytantor.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.claytantor.model.IntpickerModel;
import org.claytantor.service.IntegerPickerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by claytongraham on 5/25/18.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = IntpickerController.class, secure = false)
public class IntpickerControllerTest {

    private Gson objGson = new GsonBuilder().setPrettyPrinting().create();

    @MockBean
    private IntegerPickerService integerPickerService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void evaluatesExpression() {
        Integer sum = 6;
        assertEquals(6, sum.intValue());
    }

    @Test
    public void evaluatesPostActionHappyPath() throws Exception {

        //mock results from service
        //should return 2 and 4
        Integer[] intArray = { 2, 4};

        IntpickerModel mockResponse = new IntpickerModel(Arrays.asList(intArray), 2, 15);


        Mockito.when(
                integerPickerService.pickIntegers(Mockito.anyList(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(mockResponse);


        List<Integer> items = Arrays.asList(intArray);
        IntpickerModel request = new IntpickerModel(items, 2, 15);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
                "/api/intpicker").accept(
                MediaType.APPLICATION_JSON)
                .content(objGson.toJson(request))
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        Type listType = new TypeToken<IntpickerModel>(){}.getType();

        IntpickerModel model = objGson.fromJson(result.getResponse().getContentAsString(), listType);
        assertEquals(2, model.getItems().get(0).longValue());
        assertEquals(4, model.getItems().get(1).longValue());


    }
}
